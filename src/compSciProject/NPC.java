package compSciProject;

public class NPC extends Creature {
    public NPC(String name, String description, Room whereAt) {
        super(name, description, whereAt);
    }
    //Room modifier method for most all modifications.
    //Use this or polymorphism? Not sure yet. Did change to using this
    //is instance of here though...waiting on feedback before making change.
    public int modifyRoom(Room peek){
        String check = peek.getState();
        if (check.equals(Room.CLEAN)) {
            return -1;
        }
        else if(check.equals(Room.DIRTY)){
            return 1;
        }
        return 0;
    }
}