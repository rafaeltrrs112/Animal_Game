package compSciProject;

/**
 * Room class used to contain up to ten animals.
 */
public class Door {
    //Static variable holding the position of the door
    public static final String NORTH = "north";
    public static final String SOUTH = "south";
    public static final String EAST = "east";
    public static final String WEST = "west";

    private String position;
    private Room leadTo;

    //Constructor only ever used in parser
    public Door(Room leadTo, String position) {
        this.leadTo = leadTo;
        this.position = position;
    }

    //Method shows where it leads to the calling object.
    public Room getLeadTo() {
        return leadTo;
    }
    public String getPosition(){
        return position;
    }

    public String toString() {
        if (position == null)
            return null;
        return position + " door leads to Room: " + this.leadTo.getName() +
                ", " + this.getLeadTo().getState();
    }
}