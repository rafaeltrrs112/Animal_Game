package compSciProject;

import java.util.*;

/**Cargo is the value of the current node
 * next is a linked node to this one.
 *
 * NOTE: Making linked nodes allows you to assemble multiple objects into
 * a single entity, or a **Collection**.
 *
 * 1st NODE: The first Node of the List serves as a reference to the entire list.
 *
 */
class Node{
    Object data;
    Node next;
    Node prev;

    public Node(Object objectData){
        data = objectData;
        next = null;
    }

    public Node(Objects objectData, Node next){
        this.data = objectData;
        this.next = next;
    }
    public static String printList(Node list){
        String printedList ="";
        Node node= list;
        while(node != null){
            printedList += node;
            if(node.next!=null) printedList += ",";
            node = node.next;
        }
        return printedList;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
        next.prev = this;
    }

    public String toString(){
        return data.toString();
    }

}
public class LinkedList implements Iterable<Creature>{
    //Tail is the last node in the list
    //Head is the first node in the list
    private int length;
    Node head;
    Node tail;
    private int count = 0;
    Node nextNode;


    public LinkedList(){
        length = 0;
        head = null;
    }

    //If head is equal to tail then list is a singleton
    public void add(Creature objectData){
        Node addToList = new Node(objectData);
        if (head == null){
            head = addToList;
            length++;
            //Be careful this may cause SOF when trying to print
            tail = head;
            return;
        }
        tail.setNext(addToList);
        tail = addToList;
        length++;
    }
    /**
     * Try making it generic so you don't have to create a method for
     * every different type of possible object.
     * */
    public void add(Object object){
        Node addToList = new Node(object);
        if (head == null){
            head = addToList;
            length++;
            //Be careful this may cause SOF when trying to print
            tail = head;
            return;
        }
        tail.setNext(addToList);
        tail = addToList;
        length++;
    }
    public Creature getCreature(String name){
        Node node = head;
        while(node!=null){
            Creature currentCreature = (Creature)node.getData();
            if (currentCreature.getName().equals(name)) return currentCreature;
            node = node.getNext();
        }
        return null;
    }
    public Object getString(String findString){
        Node node = head;
        while(node!=null){
            String currentString = (String)node.getData();
            if(currentString.equals(findString)) return currentString;
            node = node.getNext();
        }
        return null;
    }

    public String printList(){
        String printedList = Node.printList(head);
        String[] printedListFormatted = printedList.split(",");
        String printOut = "";
        for (String states:printedListFormatted){
            printOut += printOut;
        }
        return printedList;
    }

    public boolean removeCreature(String name){
        if(!containsCreature(name)){
            return false;
        }
        else{
            length--;
            Node current = head;
            while(current!=null){
                Creature currentCreature = (Creature)current.getData();
                if (currentCreature.getName().equals(name)){
                    if(current.prev!=null){
                        current.prev.next = current.next;
                        if(current.next!=null){
                            current.next.prev = current.prev;
                        }
                    } else {
                        head = current.next;
                        if(current.next!=null){
                            current.next.prev = null;
                        }
                    }

                    //current.prev = current.next;

                    return true;
                } else {
                    current = current.next;
                }

            }
        }
        return false;
    }

    public boolean containsCreature(String name){
        Node node = head;
        while(node!=null){
            Creature currentCreature = (Creature)node.getData();
            if (currentCreature.getName().equals(name)) return true;
            node = node.getNext();
        }
        return false;
    }
    //Length
    public int length(){
        return length;
    }

    public Iterator<Creature> iterator()
    {
        return new LinkedListIterator();
    }
    private class LinkedListIterator  implements Iterator<Creature>
    {
        private Node nextNode;

        public LinkedListIterator()
        {
            nextNode = head;
        }

        public boolean hasNext()
        {
            return nextNode != null;
        }

        public Creature next()
        {
            if (!hasNext()) throw new NoSuchElementException();
            Creature res = (Creature)nextNode.data;
            nextNode = nextNode.next;
            return res;
        }

        public void remove() { throw new UnsupportedOperationException(); }
    }

    public static void main(String[] args) {
        System.out.println("hello");
        String[] testStrings = {"a","c","d","e","f"};
        LinkedList list = new LinkedList();
        //RoomParserHandler.run(Driver.fileChooserGUI());
        PC player = RoomParserHandler.currentPlayer;
        Room testRoom = new Room("null","null","null");
        Creature test = new Animal("nulla","nulla",testRoom);
        list.add(test);
        list.add(test);
        System.out.println(list.length);
        String hello;
        list.forEach(System.out::println);
        LinkedList stringList = new LinkedList();
        System.out.println("String list length: " + stringList.length());
        for(String s:testStrings){
            stringList.add(s);
        }
        stringList.forEach(System.out::println);
    }

}