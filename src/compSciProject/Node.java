package compSciProject;

public class Node<K, E>{
    private K key;
    private E element;
    private Node<K, E> next;
    private Node<K, E> prev;

    public Node(K key, E element){
        this.key = key;
        this.element = element;
        this.next = null;
    }

    public Node(K objectData, Node next){
        this.key = objectData;
        this.next = next;
    }

    public K getKey() {
        return key;
    }
    public E getElement(){
        return element;
    }

    public Node getNext() {
        return next;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public Node<K, E> getPrev() {
        return prev;
    }

    public void setPrev(Node<K, E> prev) {
        this.prev = prev;
    }

    private void setNext(Node next) {
        this.next = next;
        next.prev = this;
    }

    public String toString(){
        return "Node" + key.toString() + " "  + element.toString();
    }
}