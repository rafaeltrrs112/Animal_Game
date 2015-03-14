package compSciProject;

public abstract class Creature {
    private String name;
    private String description;
    private Room currRoom;

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
    public int leaveRoom(){
        for (Door x : getRoom().getDoors()) {
            Room r = x.getLeadTo();
            if (!r.isFull()) {
                getRoom().removeCreature(this);
                //Remove these print statements after main Driver program
                //proves they are all complete
                if (modifyRoom(r)!=-1) {
                    r.addCreature(this);
                    break;
                }
                else {
                    r.setState(Room.HALF_DIRTY);
                    r.addCreature(this);
                    break;
                }
            }
        }
        return -1;
    }

    public int leaveRoom(Room r){
        if (modifyRoom(r)!=-1) {
            getRoom().removeCreature(this);
            r.addCreature(this);
            return 0;
        }
        else {
            r.setState(Room.HALF_DIRTY);
            getRoom().removeCreature(this);
            r.addCreature(this);
            return -1;

        }
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