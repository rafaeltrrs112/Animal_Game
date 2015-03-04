package compSciProject;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 */
public class InputVerifier {
        static String[] commands = {"help","look","clean","dirty","north",
                             "east","south","west","exit","move"};

        public static boolean isValid(String input){
            for(String x:commands){
                if(input.equals(x)){
                    return true;
                }
            }
            return false;
        }

        public static String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        String entry;
        System.out.println("Enter a command: ");
        do {
            entry = scanner.nextLine();
            if(!isValid(entry))
                System.out.println("Command must be valid!");
        } while (!isValid(entry));
        return entry;
    }

    public static int getIntRange(int max) {
        Scanner sc = new Scanner(System.in);
        int entry;

        do {
            try {
                entry = sc.nextInt();
                if (entry <= 0 || entry > max) {
                    throw new OutOfRangeException(1, max);
                }
                break;
            } catch (InputMismatchException exc) {
                System.out.println("Number must be an integer!");
                sc.nextLine();
            } catch (OutOfRangeException exx) {
                System.out.println(exx);
                sc.nextLine();
            }
        } while (true);
        return entry;
    }
    public static void main(String[] args){
        InputVerifier.getStringInput();
    }
}
