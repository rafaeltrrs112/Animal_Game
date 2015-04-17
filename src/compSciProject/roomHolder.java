//TODO Redo door handling. Door's are not being assigned properly now.
package compSciProject;
import java.util.HashMap;
import java.util.*;
import compSciProject.gameTools.hashMap;

public class roomHolder {
    //roomMap is for getting the position.
    public HashMap<String, HashMap<String, String>> roomMap = new HashMap<>();
    //This is the final list of rooms that have been added.
    //Maybe this doesn't have to be removed...hmm???...
    public hashMap<String, Room> roomList = new hashMap<>();
    public HashMap<String, Room> roomSourceMap = new HashMap<>();
    public String[] roomPositions = {Door.NORTH, Door.SOUTH, Door.EAST, Door.WEST};


    public roomHolder(){
    }

    public  void addRoomPosits(String roomName, String neighborPosition, String neighborName){
        System.out.println(roomName + " door : " + neighborPosition + " leads to room: " + neighborName);

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
            System.out.println(pair.getKey());
            roomList.insert(pair.getKey().toString(), mp.get(pair.getKey()));
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
    public void addNeighbors() {
        Iterator it = roomMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " " + pair.getValue());
            for(Room r: roomList){
                if(pair.getKey().equals(r.getName())){
                    for(String x: roomPositions){
                        if(roomMap.get(r.getName()).containsKey(x)){
                            r.insertNeighbor(roomSourceMap.get((roomMap.get(r.getName()).get(x))), x);
                        }
                    }
                }
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
}
