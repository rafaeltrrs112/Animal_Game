package compSciProject; /**
 * Created by Rtorres on 3/8/2015.
 *
 */
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.*;

class RoomParserHandler extends DefaultHandler {
    Room currentRoom;
    private HashMap<String, String> roomFieldMap = new HashMap<>();
    private HashMap<String, String> creatureFieldMap = new HashMap<>();
    public HashMap<String, Room> roomMap = new HashMap<>();
    public String[] roomPositions = {Door.NORTH, Door.SOUTH, Door.EAST, Door.WEST};


    public roomHolder currentRoomPosit = new roomHolder();



    public static void main(String[] args) {
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            RoomParserHandler handler = new RoomParserHandler();
            parser.parse("input.xml", handler);
            System.out.println(handler.roomMap.get("Violet"));
            handler.currentRoomPosit.setRoomList(handler.roomMap);
            handler.currentRoomPosit.addNeighbors();
            handler.currentRoomPosit.printFinishedRooms();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{

        int attributeLength = attributes.getLength();
        if("room".equals(qName)){
            for(int i=0;i<attributeLength;i++){

                String attrName = attributes.getQName(i);
                String attrVal = attributes.getValue(i);
                System.out.println(attrName + " = " + attrVal + "; ");
                roomFieldMap.put(attrName, attrVal);
            }
            Room testRoom = new Room(roomFieldMap.get("name"), roomFieldMap.get("description"), roomFieldMap.get("state"));
            currentRoom = testRoom;
            roomMap.put(testRoom.getName(), testRoom);

            for(String x: roomPositions){
                if(roomFieldMap.containsKey(x)){
                    currentRoomPosit.addRoomPosits(currentRoom.getName(), x, roomFieldMap.get(x));
                }
            }
        }
        if("animal".equals(qName)){
            for(int i = 0;i<attributeLength;i++){
                String attrName = attributes.getQName(i);
                String attrVal = attributes.getValue(i);
                creatureFieldMap.put(attrName, attrVal);
            }
            roomMap.get(currentRoom.getName()).addCreature(new Animal(creatureFieldMap.get("name"), creatureFieldMap.get("description"), currentRoom));
            System.out.println(currentRoom);
        }
        if("NPC".equals(qName)){
            for(int i = 0;i<attributeLength;i++){
                String attrName = attributes.getQName(i);
                String attrVal = attributes.getValue(i);
                creatureFieldMap.put(attrName, attrVal);
            }
            roomMap.get(currentRoom.getName()).addCreature(new NPC(creatureFieldMap.get("name"), creatureFieldMap.get("description"), currentRoom));
            System.out.println(currentRoom);
        }
        if("PC".equals(qName)){
            for(int i = 0;i<attributeLength;i++){
                String attrName = attributes.getQName(i);
                String attrVal = attributes.getValue(i);
                creatureFieldMap.put(attrName, attrVal);
            }
            roomMap.get(currentRoom.getName()).addCreature(new PC(creatureFieldMap.get("name"), creatureFieldMap.get("description"), currentRoom));
            System.out.println(currentRoom);
        }
    }
}
