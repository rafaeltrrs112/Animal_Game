package compSciProject.gameTools;

import compSciProject.Door;

import java.util.Random;

/*
 * Created by Rafael on 2/7/2015.
 * Fisher yates algorithm used here: will
 * place all little utility classes in their
 * own package later to keep program more organized.
 */
public class elemRandom {
    static Random ranGen = new Random();

    public static Door[] randRooms(Door[] roomList) {
        for (int i = roomList.length - 1; i > 0; i--) {
            int catchInd = ranGen.nextInt(i + 1);
            Door temp = roomList[catchInd];
            roomList[catchInd] = roomList[i];
            roomList[i] = temp;
        }
        return roomList;
    }
}