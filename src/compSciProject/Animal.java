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
    public String react() {
        String reaction = getName();
        if (!getRoom().getState().equals(Room.CLEAN)) {
            reaction += " growls at " + getRoom().getPlayer().getName();
            getRoom().getPlayer().decRespect();
            if (getRoom().getState().equals(Room.DIRTY)) {
                if(leaveRoom().equals(Creature.DEAD)) {
                    getRoom().removeCreature(this);
                    this.setRoom(new Room("Roof"," Animal Heaven", Room.CLEAN));
                    return reaction + " and exits through the roof";
                }
                reaction += " and leaves the room...";
                return reaction;
            }
        }
        else{
            reaction += " licks the face of " + getRoom().getPlayer().getName();
            getRoom().getPlayer().addRespect();
        }

        return reaction;
    }
}