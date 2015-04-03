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
class testEquals{
    String name;

    testEquals(String name){
        this.name = name;
    }

    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        else{
            return (this.name.equals(obj));
        }
    }
}
class Node<T>{
    T data;
    Node<T> next;
    Node<T> prev;

    public Node(T objectData){
        data = objectData;
        next = null;
    }

    public Node(T objectData, Node next){
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
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
public class LinkedList<T> implements Iterable<T> {
    //Tail is the last node in the list
    //Head is the first node in the list
    private int length;
    Node<T> head;
    Node<T> tail;
    private int count = 0;
    Node nextNode;


    public LinkedList() {
        length = 0;
        head = null;
    }

    //If head is equal to tail then list is a singleton
    public void add(T objectData) {
        Node<T> addToList = new Node<>(objectData);
        if (head == null) {
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
    public T get(String getObject){
        Node<T> node= head;
        while(node != null){
            if(node.next!=null){
                if(node.getData().equals(getObject)){
                    return node.getData();
                }
            }
            node = node.next;
        }
        return null;
    }

    public String printList() {
        String printedList = Node.printList(head);
        String[] printedListFormatted = printedList.split(",");
        String printOut = "";
        for (String states : printedListFormatted) {
            printOut += printOut;
        }
        return printedList;
    }

    public boolean remove(String removeObject) {
        if (!contains(removeObject)) {
            return false;
        } else {
            length--;
            Node<T> current = head;
            while (current != null) {
                T currentValue = current.getData();
                if (currentValue.equals(removeObject)) {
                    if (current.prev != null) {
                        current.prev.next = current.next;
                        if (current.next != null) {
                            current.next.prev = current.prev;
                        }else{
                            tail=tail.prev;
                        }
                    } else {
                        head = current.next;
                        if (current.next != null) {
                            current.next.prev = null;
                        }
                    }
                    return true;
                } else {
                    current = current.next;
                }

            }
        }
        return false;
    }

    public boolean contains(String check){
        Node<T> node = head;
        while (node != null) {
            T currentValue = node.getData();
            if (currentValue.equals(check)) return true;
            node = node.getNext();
        }
        return false;
    }

    public int length() {
        return length;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> nextNode;

        public LinkedListIterator() {
            nextNode = head;
        }

        public boolean hasNext() {
            return nextNode != null;
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T res = nextNode.data;
            nextNode = nextNode.next;
            return res;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    public static void main(String[] args) {
        System.out.println("hello");
        String[] testStrings = {"a","c","d","e","f"};
        LinkedList<String> stringList = new LinkedList<>();
        for (String testString : testStrings) {
            stringList.add(testString);
        }
        System.out.println(stringList.length);
        System.out.println(stringList.contains("a"));
        System.out.println(stringList.contains("b"));
        System.out.println(stringList.contains("R"));
        stringList.forEach(System.out::println);
        System.out.println("Remove d and f");
        stringList.remove("f");
        stringList.remove("d");
        stringList.forEach(System.out::println);
        stringList.add("q");
        System.out.println("add Q to the end");
        stringList.forEach(System.out::println);
        System.out.println(stringList.head.toString() + stringList.tail.toString());
        System.out.println(Node.printList(stringList.head));
        stringList.add("LKJ");
        stringList.add("LDKFJ");
        stringList.forEach(System.out::println);
        stringList.remove("c");
        stringList.forEach(System.out::println);
        stringList.add("i");
        stringList.forEach(System.out::println);
    }

}