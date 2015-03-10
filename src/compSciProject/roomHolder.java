package compSciProject;


import java.util.HashMap;
import java.util.*;

/**
 *
 * Created by Rtorres on 3/9/2015.
 *
 */
public class roomHolder {
    public HashMap<String, HashMap<String, String>> roomMap = new HashMap<>();
    public ArrayList<Room> roomList = new ArrayList<>();
    public HashMap<String, Room> roomSourceMap = new HashMap<>();
    public roomHolder(){

    }
    public static void main(String[] args) {

    }
    public void printFinishedRooms(){
        roomList.forEach(System.out::println);
    }
    public  void addRoomPosits(String roomName, String neighborPosition, String neighborName){
        if(!roomMap.containsKey(roomName)){
            roomMap.put(roomName, new HashMap<>());
            roomMap.get(roomName).put(neighborPosition, neighborName);
        }
        else{
            roomMap.get(roomName).put(neighborPosition, neighborName);
        }
    }
    public void setRoomList(HashMap<String, Room> mp){
        roomSourceMap.putAll(mp);
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            roomList.add(mp.get(pair.getKey()));
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
    public void addNeighbors() {
        Iterator it = roomMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
}
