package compSciProject;

/**
 * Created by Rafael on 2/9/2015.
 * Not much has been added to this class yet. Still have not
 * decided whether the PC class should exist simply for the
 * user to manipulate through the driver or whether the class will
 * user more of the driver methods itself...?
 *
 * Remove all print statements after Driver proves that all methods work
 *
 */
public class PC extends Creature {
    private int respect = 40;

    public PC(String name, String description, Room whereAt) {
        super(name, description, whereAt);
    }

    public int leaveRoom() {
        getRoom().removeCreature(this);
        return 0;
    }

    public String react(){
        if(respect<40){
            return "Why doesn't anyone like me...?...\n";
        }
        else{
            return " I rule you all! ";
        }
    }

    public int getRespect() {
        return respect;
    }

    public void addRespect() {
        this.respect++;
    }

    public void decRespect() {
        this.respect--;
    }
}
