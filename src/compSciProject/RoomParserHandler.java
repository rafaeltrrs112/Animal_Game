package compSciProject; /**
 * Created by Rtorres on 3/8/2015.
 *
 */
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.*;
import java.io.File;

class RoomParserHandler extends DefaultHandler {
    static Room currentRoom;
    static HashMap<String, String> roomFieldMap = new HashMap<>();
    static HashMap<String, String> creatureFieldMap = new HashMap<>();
    static HashMap<String, Room> roomMap = new HashMap<>();
    static String[] roomPositions = {Door.NORTH, Door.SOUTH, Door.EAST, Door.WEST};
    static roomHolder currentRoomPosit = new roomHolder();
    static PC currentPlayer;


    public static void run(File inputFile){
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            RoomParserHandler handler = new RoomParserHandler();
            parser.parse(inputFile, handler);
            currentRoomPosit.setRoomList(roomMap);
            currentRoomPosit.addNeighbors();
            currentRoomPosit.getRooms();
        }
        catch (org.xml.sax.SAXException|javax.xml.parsers.ParserConfigurationException exc){
            System.out.println("Error Parsing File");
        }
        catch(IOException exc){
            System.out.println("Error Obtaining File");
        }
    }

    public  void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{

        int attributeLength = attributes.getLength();
        //Insert a switch statement here instead of a bunch of if's.
        String create = qName;
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
        roomFieldMap.clear();
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
            PC currentPlayer = (new PC(creatureFieldMap.get("name"), creatureFieldMap.get("description"), currentRoom));
            currentRoom.setPlayer(currentPlayer);
            RoomParserHandler.currentPlayer = currentPlayer;
            System.out.println(currentRoom);
        }
    }
}
