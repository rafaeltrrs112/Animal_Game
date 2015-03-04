package compSciProject;

/*
 * Created by Rafael on 2/5/2015.
 * Class: Animal
 */
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

    public String getDescription() {
        return description;
    }

    public Room getRoom() {
        return currRoom;
    }

    public void setRoom(Room currRoom) {
        this.currRoom = currRoom;
    }
    //Voluntary leave room
    public void leaveRoom(){
        for (Door x : getRoom().getDoors()) {
            Room r = x.getLeadTo();
            if (!r.isFull()) {
                getRoom().removeCreature(this);
                //Remove these print statements after main Driver program
                //proves they are all complete
                System.out.println(getName() + " now in: " + getRoom().getName());
                if (modifyRoom(r)!=-1) {
                    r.addCreature(this);
                    System.out.println(this.getName() + " entered " + getRoom().getName());
                    return;
                }
                else {
                    System.out.println(getName() + " half dirtied " + getRoom().getName());
                    r.setState(Room.HALF_DIRTY);
                    r.addCreature(this);
                    return;
                }
            }
        }
    }
    //Used to kick an animal out to a specific room
    //Force leave room.
    public void leaveRoom(Room r){
        System.out.println(getName() + " now in: " + getRoom().getName());
        if (modifyRoom(r)!=-1) {
            getRoom().removeCreature(this);
            r.addCreature(this);
            System.out.println(this.getName() + " entered " + getRoom().getName());
        }
        else {
            System.out.println(getName() + " half dirtied " + r.getName());
            r.setState(Room.HALF_DIRTY);
            getRoom().removeCreature(this);
            r.addCreature(this);

        }
    }
    //Room modifier method for most all modifications.
    //Use isinstaceof or polymorphism? Not sure yet. Did change to using this
    //is instance of here though...waiting on feedback before making change.
    abstract int modifyRoom(Room peek);


    public String toString() {
        return name + ": " + description;
    }
}