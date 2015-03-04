//Driver test class.
package compSciProject;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Driver {
    public String userChoice = "";
    private PC player;

    /*
     * Empty constructor for Driver class. Driver class
     * will be placed in a Main class once all methods are sorted out
     * and all features are finalized!
     */
    public Driver() {
    }
    //Verifier method to get input for use in menu and verify it.

    /*
     * Choose door method allows the player
     * to choose which door they would like to go and then
     * place the user there. If room is full it will be caught in the
     * program and user will be notified of this.
     */
    public void chooseRoom() {
        Door doors[] = player.getRoom().userGetDoors();
        System.out.println("Where would you like to go! \n Enter door position");
        for (int i = 0; i < doors.length; i++) {
            System.out.println("Door: " + (i + 1) + " :" + player.getRoom().userGetDoors()[i]);
        }

        int choice = InputVerifier.getIntRange(doors.length);
        player.setRoom(doors[choice - 1].getLeadTo());
        //Player cannot enter an empty room. Will throw exception!!!
        doors[choice - 1].getLeadTo().setPlayer(player);
        player.setRoom(doors[choice - 1].getLeadTo());
    }

    /*
     * Kick out method for allowing the player to force
     * an animal out of the room. The animal will change
     * the room they are being kicked too if they don't like it!
     */
    public void kickOut() {
        Creature[] occupants = player.getRoom().getOccupants();
        Door doors[] = player.getRoom().userGetDoors();
        int creatureChoice;
        int doorChoice;
        if (player.getRoom().isEmpty()) {
            System.out.println("Room is empty!");
            return;
        }

        System.out.println("Who would you like to interact with?\n Enter *Number* corresponding to animals");
        for (int i = 0; i < occupants.length; i++) {
            System.out.println((i + 1) + " :" + occupants[i]);
        }
        creatureChoice = (InputVerifier.getIntRange(occupants.length)-1);
        System.out.println("To Where?.. \n ");
        for (int i = 0; i < doors.length; i++) {
            System.out.println("Door: " + (i + 1) + " :" + player.getRoom().userGetDoors()[i]);
        }
        doorChoice = (InputVerifier.getIntRange(doors.length)-1);
        occupants[creatureChoice].leaveRoom(doors[doorChoice].getLeadTo());
    }

    /*
     * Here they player can change the state of the room they are in.
     * Animals do not respond to the room change yet!
     */
    public void playerChangeRoomState(String state) {
        player.forceModify(state);
        notifyCreatures(state);
    }

    public void notifyCreatures(String stateChange) {
        for (Creature x : player.getRoom().getOccupants()) {
            if (x instanceof NPC) {
                if (!stateChange.equals(Room.DIRTY)) {
                    System.out.println(x.getName() + " grumbles at " + player.getName());
                    if (x.getRoom().getState().equals(Room.CLEAN))
                        x.leaveRoom();
                }
                else{
                    System.out.println(x.getName() + " smiles at " + player.getName());
                }
            } else if (x instanceof Animal) {
                if (!stateChange.equals(Room.CLEAN)) {
                    System.out.println(x.getName() + " growls at " + player.getName());
                    if (x.getRoom().getState().equals(Room.DIRTY))
                        x.leaveRoom();
                }
                else{
                    System.out.println(x.getName() + " licks " + player.getName() + "'s face");
                }
            }
        }
    }
    /*
     * File chooser used here allows the user to select a file from their desktop
     * Will save code to use in later projects for other types of input files.
     */
    public File getInputFile() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter XMLFILTER = new FileNameExtensionFilter(
                "xml files (*.xml)", "xml");
        chooser.setFileFilter(XMLFILTER);
        chooser.setDialogTitle("Open XML file");
        // set selected filter
        chooser.showOpenDialog(null);
        chooser.setFileFilter(XMLFILTER);
        return chooser.getSelectedFile();
    }

    /*
     * Run game method in driver. Currently made to take in simple integers for user
     * input to allow for easy debugging. String input verification that be implemented
     * later.
     */
    public void runGame() {
        inputParser parser = new inputParser();
        Room[] rooms;
        rooms = parser.generateRooms(getInputFile());
        player = parser.getPC();
        for (Room x : rooms) {
            System.out.println(x);
        }

        /*
         * Current version of switch statement runs on an integer case, may be
         * modified later to take in a string. Though an integer is less confusing for debugging
         * right now.
         */
        while (!userChoice.equals("exit")) {
            System.out.println(mainMessage());
            System.out.println(displayStatus());
            userChoice = InputVerifier.getStringInput();
            switch (userChoice) {
                case "kick": {
                    if (player.getRoom().isEmpty()) {
                        System.out.println("\nRoom is Empty No One to Kick Out\n");
                        break;
                    }
                    kickOut();
                    break;
                }
                //Is this ok?...
                case "move": {
                    chooseRoom();
                    break;
                }
                case "look": {
                    System.out.println(player.getRoom());
                    break;
                }
                case "clean": {
                    playerChangeRoomState(Room.CLEAN);
                    break;
                }
                case "dirty":{
                    playerChangeRoomState(Room.DIRTY);
                }
            }
        }
    }

    /*
     * May put all menus message in one method, get menu
     * class later...for now they are out on their own and can
     * be returned depending on the message need...will implement later.
     */
    public String displayStatus() {
        return "Name: "  + player.getName() +
                "\nRoom: " + player.getRoom().getName() +
                ", " + player.getRoom().getState();
    }

    /*
     * Create a string array of messages used in the game to make code cleaner
     */
    public String mainMessage() {
        return
                "****Welcome to Animal Game*****" +
                        "\nEnter 'kick' to Kick out an animal!\n" +
                        "Enter 'move' to Move around\n" +
                        "Enter 'look' to Display data of Room: "
                                + player.getRoom().getName() + "\n" +
                        "Enter 'clean' to sweep the room\n" +  player.getRoom().getName() +
                        "Enter 'dirty' to dirty to throw mud in the room\n" +
                        "Enter 'exit' to quit the game";
    }

    public static void main(String[] args) {
        Driver runGame = new Driver();
        runGame.runGame();
    }
}