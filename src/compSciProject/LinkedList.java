package compSciProject;
import java.util.*;

public class LinkedList<T> implements Iterable<T> {

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

        public T getData() {
            return data;
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

    private int length;
    private Node<T> head;
    private Node<T> tail;


    public LinkedList() {
        length = 0;
        head = null;
    }
    @SafeVarargs
    public LinkedList(T ... E){
        length = E.length;
        head = null;
        for(T element : E){
            add(element);
        }
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
    public T get(String check){
        Node<T> node = head;
        while (node != null) {
            T currentValue = node.getData();
            if (currentValue.equals(check)) return currentValue;
            node = node.getNext();
        }
        return null;
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
        RoomParserHandler.run(Driver.fileChooserGUI());
        PC testPc = RoomParserHandler.currentPlayer;
        testPc.getRoom().getOccupants().forEach(System.out::println);
        LinkedList<Creature> testList = testPc.getRoom().getOccupants();
        System.out.println(testList.contains("Mary"));
        System.out.println(testList.get("Mary"));
    }
}