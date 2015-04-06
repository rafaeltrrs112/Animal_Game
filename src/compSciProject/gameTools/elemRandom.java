package compSciProject.gameTools;

import compSciProject.Door;

import java.util.Random;
//Fisher Yates algorithm for selecting a random element

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