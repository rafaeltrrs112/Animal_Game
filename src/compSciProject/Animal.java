package compSciProject;

public class Animal extends Creature {
    public Animal(String name, String description, Room whereAt) {
        super(name, description, whereAt);
    }
    public int modifyRoom(Room peek){
        String check = peek.getState();
        if (check.equals(Room.DIRTY)) {
            return -1;
        }
        else if(check.equals(Room.CLEAN)){
            return 1;
        }
        return 0;
    }
}