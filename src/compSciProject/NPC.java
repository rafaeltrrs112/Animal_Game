package compSciProject;

public class NPC extends Creature {
    public NPC(String name, String description, Room whereAt) {
        super(name, description, whereAt);
    }

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
    public String react(){
        String reaction = getName();
        if (!getRoom().getState().equals(Room.DIRTY)) {
            reaction += " grumbles at " + getRoom().getPlayer().getName();
            getRoom().getPlayer().decRespect();
            if (getRoom().getState().equals(Room.CLEAN)) {
                reaction += " and leaves the room...";
                getRoom().getPlayer().decRespect();
                leaveRoom();
                return reaction;
            }
        }
        else {
            reaction += " smiles at " + getRoom().getPlayer().getName();
            getRoom().getPlayer().addRespect();
        }
        return reaction;

    }
}