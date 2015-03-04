package compSciProject;

/**
 * Test class used to test classes.
 *
 */
public class checkIsInstnce {
    public static void main(String[] args) {
        Room r = new Room("Quarto"," not very clean",Room.CLEAN);
        Animal a = new Animal("Fluffy"," nice poodle dog",r);
        System.out.println(a + a.getClass().getSimpleName());
        Creature c = a;
        //Nice, use this to simplify class and loops in code
        if(c instanceof Animal){
            System.out.println("Yes I am an animal");
        }
    }

}
