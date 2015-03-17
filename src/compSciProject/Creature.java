package compSciProject;

public abstract class Creature {
    private String name;
    private String description;
    private Room currRoom;
    static final String DEAD = "DEAD";

    public Creature(String name, String description, Room currRoom) {
        this.name = name;
        this.description = description;
        this.currRoom = currRoom;
    }
    public String getName() {
        return name;
    }

    public Room getRoom() {
        return currRoom;
    }

    public void setRoom(Room currRoom) {
        this.currRoom = currRoom;
    }
    //TODO: If all rooms full then creature leaves room to nowhere
    //Returns -1 if creature was not able to find a room to enter.
    public String leaveRoom(){
        int checkAll = getRoom().getDoors().length;
        for (Door x : getRoom().getDoors()) {
            checkAll++;
            Room r = x.getLeadTo();
            if (!r.isFull()) {
                getRoom().removeCreature(this);
                if (modifyRoom(r)!=-1) {
                    r.addCreature(this);
                    return "CREATURE EXITING";
                }
                else {
                    r.setState(Room.HALF_DIRTY);
                    r.addCreature(this);
                    return "CREATURE ENTERING/STATE CHANGE: HALF DIRTY";
                }
            }
        }
        return DEAD;
    }

    public String leaveRoom(Room r){
        if (modifyRoom(r)!=-1) {
            getRoom().removeCreature(this);
            r.addCreature(this);
            return getName() + " content with room ";
        }
        else if(modifyRoom(r) == 0) {
            r.setState(Room.HALF_DIRTY);
            getRoom().removeCreature(this);
            r.addCreature(this);
            return getName() + " had to change the room state ";

        }
        return getName() + " was unable to leave the room and crawled out through" +
                " the ceiling";
    }
    public String react(String forceTask){
        getRoom().iGameStateChange(forceTask);
        return react();
    }

    int modifyRoom(Room peek){
        return peek.getState().equals(Room.HALF_DIRTY) ? 0 : -1;
    }
    abstract String react();


    public String toString() {
        return name + ": " + description;
    }
}