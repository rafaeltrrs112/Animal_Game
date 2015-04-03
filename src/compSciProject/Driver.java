package compSciProject;

import compSciProject.gameTools.InputVerifier;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Scanner;

public class Driver {
    public String userChoice = "";
    private PC player;
    static Scanner wait = new Scanner(System.in);
    /*
     * Empty constructor for Driver class. Driver class
     * will be placed in a Main class once all methods are sorted out
     * and all features are finalized!
     */
    public Driver() {
    }
    /*
     * Choose door method allows the player
     * to choose which door they would like to go and then
     * place the user there. If room is full it will be caught in the
     * program and user will be notified of this.
     */
    public void playerMove(String inputPosition){
        for(Door door:player.getRoom().userGetDoors()){
            if(door.getPosition().equals(inputPosition)){
                if(door.getLeadTo().isFull()){
                    System.out.println(door.getLeadTo().getName() + " is full..Cannot enter");
                    return;
                }
                System.out.println("Going to " + inputPosition);
                door.getLeadTo().setPlayer(player);
                player.setRoom(door.getLeadTo());
                return;
            }
        }
        System.out.println("Invalid room position selected!");
    }
    /*
     * Kick out method for allowing the player to force
     * an animal out of the room. The animal will change
     * the room they are being kicked too if they don't like it!
     */
    public void creatureForceMove(String name, String doorChoice) {
        LinkedList<Creature> occupants = player.getRoom().getOccupants();
        Door doors[] = player.getRoom().userGetDoors();

        int doorChoiceIndex = (player.getRoom().getDoorIndex(doorChoice));
        if(player.getRoom().getOccupants().contains(name)){
            System.out.println("Invalid command!");
            return;
        }
        else if(doors[doorChoiceIndex].getLeadTo().isFull()){
            System.out.println("Room Full Cannot Kick!");
            return;
        }
        occupants.get(name).leaveRoom(doors[doorChoiceIndex].getLeadTo());
    }
    /*
     * Here they player can change the state of the room they are in.
     * Animals do not respond to the room change yet!
     */
    public void playerChangeRoomState(String state) {
        if(player.getRoom().getState().equals(state)) {
            System.out.println(player.getRoom().iGameStateChange(state));
            return;
        }
        player.getRoom().iGameStateChange(state);
        notifyCreatures();
    }

    public void notifyCreatures() {
        for (Creature x : player.getRoom().getOccupants()) {
            System.out.println(x.react());
        }
    }
    /*
     * File chooser used here allows the user to select a file from their desktop
     * Will save code to use in later projects for other types of input files.
     */
    public static File fileChooserGUI() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter XMLFILTER = new FileNameExtensionFilter(
                "xml files (*.xml)", "xml");
        chooser.setFileFilter(XMLFILTER);
        chooser.setDialogTitle("Open XML file");
        // set selected filter
        chooser.showOpenDialog(null);
        chooser.setFileFilter(XMLFILTER);
        if (chooser.getSelectedFile() == null){
            System.out.println("No File Save Chosen!\n" +
                               "Exiting Now");
            System.exit(0);
        }
        return chooser.getSelectedFile();
    }
    /*
     * Run game method in driver. Currently made to take in simple integers for user
     * input to allow for easy debugging. String input verification that be implemented
     * later.
     */
    public void runGame() {
        RoomParserHandler.run(fileChooserGUI());
        player = RoomParserHandler.currentPlayer;
        System.out.println(introBanner() + "\n" + mainMenuMessage());
        while (!userChoice.equals("exit") && playerGameStatus()==0) {
            System.out.println(displayStatus());
            userChoice = compSciProject.gameTools.InputVerifier.getStringInput();

            String []choiceInit = userChoice.split(":");
            if(choiceInit.length == 1) {
                if(InputVerifier.isPosit(choiceInit[0])){
                    playerMove(choiceInit[0]);
                }
                switch (choiceInit[0]) {
                    case "look": {
                        System.out.println(player.getRoom());
                        System.out.print("Enter any key to continue");
                        wait.nextLine();
                        break;
                    }
                    case "clean": {
                        playerChangeRoomState(Room.CLEAN);
                        System.out.print("Enter any key to continue");
                        wait.nextLine();
                        break;
                    }
                    case "dirty": {
                        playerChangeRoomState(Room.DIRTY);
                        System.out.print("Enter any key to continue");
                        wait.nextLine();
                        break;
                    }
                    case "help": {
                        System.out.println(helpMessage());
                        System.out.print("Enter any key to continue");
                        wait.nextLine();
                        break;
                    }
                }
            }
            if(choiceInit.length == 2){
                //player.getRoom().sort();
                if(player.getRoom().getOccupants().contains(choiceInit[0])){
                    switch(choiceInit[1]){
                        case "clean":{
                            System.out.println(player.getRoom().forceInhabitant(choiceInit[0], Room.CLEAN));
                            System.out.println("Animal " + choiceInit[0] + "  " + choiceInit[1] + " s");
                            notifyCreatures();
                            System.out.print("Enter any key to continue");
                            wait.nextLine();
                            break;
                        }
                        case "dirty":{
                            System.out.println(player.getRoom().forceInhabitant(choiceInit[0],Room.DIRTY));
                            System.out.println("Animal " + choiceInit[0] + "  " + choiceInit[1] + " s");
                            notifyCreatures();
                            System.out.print("Enter any key to continue");
                            wait.nextLine();
                            break;
                        }
                        default : {
                            if (player.getRoom().isEmpty()) {
                                System.out.println("\nRoom is Empty No One to Kick Out\n");

                                break;
                            }
                            creatureForceMove(choiceInit[0], choiceInit[1]);
                            System.out.print("Enter any key to continue");
                            wait.nextLine();
                            break;
                        }
                    }
                }
            }
        }
        if(playerGameStatus()!=0){
            if(playerGameStatus()==-1) {
                System.out.println("\t\t\t\t\tGame Over You Lose\n" + gameOverBanner());
            }
            if(playerGameStatus()==1) {
                System.out.println("\n\t\t\t\tCongratulations You Have Won!\n" + gameWonBanner());
            }
        }
    }

    public int playerGameStatus(){
        if(player.getRespect()<=0)
            return -1;
        if(player.getRespect()>=80)
            return 1;
        return 0;
    }

    public String displayStatus() {
        return  "\n----------------<|Current Status|>------------------\n" +
                "| Name: "  + player.getName() +
                "\n| Respect: " + player.getRespect() +
                "\n| Room: " + player.getRoom().getName() +
                ", " + player.getRoom().getState() + "\n" +
                "----------------------------------------------------";
    }

    public String mainMenuMessage() {
        return
                        "\nMain Menu Options\n" +
                        "Enter 'north,south,east, or west' to Move around\n" +
                        "Enter 'look' to Display data of Room: "
                                + player.getRoom().getName() + "\n" +
                        "Enter 'clean' to sweep the room " +  player.getRoom().getName() + "\n" +
                        "Enter 'dirty' to throw mud in the room\n" +
                        "Enter 'exit' to quit the game\n" +
                        "Enter animal name: desired action to make an animal do something\n" +
                        "Enter 'help' to display Game Info and Commands";
    }
    public String helpMessage(){
        return " HELP INFO   *****\n" +
                " Game: CS241 Animal Game\n " +
                "By:Rafael O. Torres\n\n" +
                "Game Description ****\n" +
                "\tAnimal game is a text based adventure game. Sort of like a cooler version of pokemon\n" +
                "\tYour name is " + player.getName() + " and you have been chosen to live in the great Animal House\n" +
                "\tThe inhabitants of this house are creatures of two types: the clean ANIMALS and the musky stank NPCS (Humans)\n" +
                "\tJust like in the wild humans like mud, dirt, old candy wrappers, slimy goo, and all sorts of nasty things\n" +
                "\tAs the Great Leader of the People....I mean as the player, you " + player.getName() + " have the great powers of\n" +
                "\thousekeeping and un-keeping\n" +
                "\tBeing the indecisive and sadistic individual that I am sure you are, you may want to prod, irritate, and damn right annoy\n" +
                "\tthe denizens of this world...**BUT!!!!!!!**\n" +
                "\tBeware, piss of the animals too much and you will lose...CHOOSE SIDES and make the right allies and you GREAT LEA...I mean...\n" +
                "\t...." + player.getName() + " will win the eighty points of respect and win the grand prize of a STRING METHOD DISPLAYING YOUR GLORY\n" +
                "\tBUT!!!!!!.....If you fail to pick sides, and lose all your creatures or!!!! Lose all their respect and you will REACH THE RET...I mean lose the game\n" +

                "\nHOW TO PLAY****\n" +

                "Name: " + player.getName() + " \n" +
                "Respect: " + player.getRespect() + " \n " +
                "\nObjective****\n" +
                "\t<[:)>Gain 80 respect points to win the game" +
                "\tTo gain respect the inhabitants in your current room happy and they will award with\n" +
                "\tone respect for your kindness.\n" +
                "\t<!> Animals will give respect for CLEANING a room they are in. Or making them clean it.\n" +
                "\tNPCs will give respect for DIRTYING a room they are in. Or making them clean it.\n" +
                "\tAnimal and NPCs will deduct respect if a rooms state is changed toward their undesired state...\n" +
                "\tUNDESIRED STATES are DIRTY and CLEAN, for NPCs and ANIMALS respectively.\n" +
                "\tMove through the rooms and interact with the rooms inhabitants.\n" +
                "\t<!> Rooms can contain only up to ten players...\n" +

                "\t\nGAME OVER****************\n" +
                "\t<!> Animals and Creatures who cannot leave a room they do not like will leave the room and the player will\n" +
                "\t    leave through the roof and DI...I mean...walk away...IF ALL ANIMALS ARE GONE GAME OVER\n" +
                "\t<!> RESPECT ZERO and game will be OVER!!!!!!\n" +
                mainMenuMessage();
    }
    public String introBanner(){
        return
        "                               ,-.             __\n" +
                "                             ,'   `---.___.---'  `.\n" +
                "                           ,'   ,-                 `-._\n" +
                "                         ,'    /                       \\\n" +
                "                      ,\\/     /                        \\\\\n" +
                "                  )`._)>)     |                         \\\\\n" +
                "                  `>,'    _   \\                  /       |\\\n" +
                "                    )      \\   |   |            |        |\\\\\n" +
                "           .   ,   /        \\  |    `.          |        | ))\n" +
                "           \\`. \\`-'          )-|      `.        |        /((\n" +
                "            \\ `-`   a`     _/ ;\\ _     )`-.___.--\\      /  `'\n" +
                "             `._         ,'    \\`j`.__/        \\  `.    \\\n" +
                "               / ,    ,'       _)\\   /`        _) ( \\   /\n" +
                "               \\__   /        /  _) (         /nn__\\_) (\n" +
                "                 `--'         /  /___\\             /nn__\\\n" +
                "                             |___|____|" + "\n";
    }
    public String gameOverBanner(){
        return
                "                                                                _\n" +
                        "                                                              _( (~\\\n" +
                        "       _ _                        /                          ( \\> > \\\n" +
                        "   -/~/ / ~\\                     :;                \\       _  > /(~\\/\n" +
                        "  || | | /\\ ;\\                   |l      _____     |;     ( \\/    > >\n" +
                        "  _\\\\)\\)\\)/ ;;;                  `8o __-~     ~\\   d|      \\      //\n" +
                        " ///(())(__/~;;\\                  \"88p;.  -. _\\_;.oP        (_._/ /\n" +
                        "(((__   __ \\\\   \\                  `>,% (\\  (\\./)8\"         ;:'  i\n" +
                        ")))--`.'-- (( ;,8 \\               ,;%%%:  ./V^^^V'          ;.   ;.\n" +
                        "((\\   |   /)) .,88  `: ..,,;;;;,-::::::'_::\\   ||\\         ;[8:   ;\n" +
                        " )|  ~-~  |(|(888; ..``'::::8888oooooo.  :\\`^^^/,,~--._    |88::  |\n" +
                        " |\\ -===- /|  \\8;; ``:.      oo.8888888888:`((( o.ooo8888Oo;:;:'  |\n" +
                        " |_~-___-~_|   `-\\.   `        `o`88888888b` )) 888b88888P\"\"'     ;\n" +
                        " ; ~~~~;~~         \"`--_`.       b`888888888;(.,\"888b888\"  ..::;-'\n" +
                        "   ;      ;              ~\"-....  b`8888888:::::.`8888. .:;;;''\n" +
                        "      ;    ;                 `:::. `:::OOO:::::::.`OO' ;;;''\n" +
                        " :       ;                     `.      \"``::::::''    .'\n" +
                        "    ;                           `.   \\_              /\n" +
                        "  ;       ;                       +:   ~~--  `:'  -';\n" +
                        "                                   `:         : .::/\n" +
                        "      ;                            ;;+_  :::. :..;;;\n" +
                        "                                   ;;;;;;,;;;;;;;;,;";
    }
    public String gameWonBanner(){
        return ("                                 .''.\n" +
                "       .''.             *''*    :_\\/_:     .\n" +
                "      :_\\/_:   .    .:.*_\\/_*   : /\\ :  .'.:.'.\n" +
                "  .''.: /\\ : _\\(/_  ':'* /\\ *  : '..'.  -=:o:=-\n" +
                " :_\\/_:'.:::. /)\\*''*  .|.* '.\\'/.'_\\(/_'.':'.'\n" +
                " : /\\ : :::::  '*_\\/_* | |  -= o =- /)\\    '  *\n" +
                "  '..'  ':::'   * /\\ * |'|  .'/.\\'.  '._____\n" +
                "      *        __*..* |  |     :      |.   |' .---\"|\n" +
                "       _*   .-'   '-. |  |     .--'|  ||   | _|    |\n" +
                "    .-'|  _.|  |    ||   '-__  |   |  |    ||      |\n" +
                "    |' | |.    |    ||       | |   |  |    ||      |\n" +
                " ___|  '-'     '    \"\"       '-'   '-.'    '`      |____\n" +
                "jgs~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    public static void main(String[] args) {
        Driver runGame = new Driver();
        runGame.runGame();

    }
}