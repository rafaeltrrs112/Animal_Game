package compSciProject.gameTools;
import compSciProject.LinkedList;

import java.util.ArrayList;
import static java.lang.Math.abs;
import java.util.Iterator;
import java.util.NoSuchElementException;



class growArray<K, E>{
    int size;
    public LinkedList<K, E>[] array;
    int addcount;
    public ArrayList<K> keyList = new ArrayList<>();

    public growArray(int length){
        this.size = length;
        array = new LinkedList[length];
    }
    public void add(K key, E element){
        keyList.add(key);
        if(addcount == size){
            addcount++;
            this.size *=2;
            LinkedList<K, E> newArray[] = new LinkedList[size*2];
            for(int i=0;i<size/2;i++){
                newArray[keyList.get(i).hashCode()%size] = array[keyList.get(i).hashCode()%(size/2)];
            }
            int hashKey = abs(key.hashCode()%size);
            System.out.println();
            array = newArray;
            array[hashKey].add(key,element);
            return;
        }
        int hashKey = abs(key.hashCode()%size);
        System.out.println(hashKey);
        if(array[hashKey] == null) {
            array[hashKey] = new LinkedList<>();
            array[hashKey % size].add(key, element);
        }
        else{
            array[hashKey].add(key,element);
        }
    }
    public boolean remove(K key){
        for(LinkedList k: array){
            if(k!=null){
                if(k.contains(key)){
                    k.remove(key);
                    keyList.remove(key);
                    return true;
                }
            }
        }
        return false;
    }
    public E get(K key){
        int hashKey = abs(key.hashCode()%size);
        return array[hashKey].get(key);
    }
    public void print(){
        for (LinkedList k : array) {
            if(k !=null)
                k.print();
        }
    }
}
public class hashMap<K, E> implements Iterable<E> {
    growArray<K, E> elementsArray;

    public hashMap() {
        elementsArray = new growArray<>(10);
    }

    public void insert(K key, E element){
        elementsArray.add(key, element);
    }

    public E get(K key){
        return elementsArray.get(key);
    }
    public void print(){
        elementsArray.print();
    }

    public boolean remove(K key){
        return elementsArray.remove(key);
    }
    public boolean contains(K key){
        return (elementsArray.array[abs(key.hashCode())%elementsArray.array.length].contains(key));
    }
    //Todo add a contains check for elements method, one for get element, and another for return list of keys.
    //Iterator method for HashMap class to make it iterable and linkable.
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    //Iterator implementation implements the Iterator interface
    private class LinkedListIterator implements Iterator<E> {
        private int countOut = elementsArray.keyList.size()-1;
        private ArrayList<K> keys = elementsArray.keyList;
        private K currentKey;

        public LinkedListIterator() {
            this.currentKey = keys.get(countOut);
        }

        public boolean hasNext() {
            if(keys.size()==0){
                return false;
            }
            else if(countOut==-1){
                return false;
            }
            return true;
        }
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            K res = keys.get(countOut);
            currentKey = keys.get(countOut);
            --countOut;
            if(get(res)==null){
                //Lame way to lose my recursion virginity :( .
                return next();
            }
            return get(res);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    public int length(){
        return elementsArray.keyList.size();
    }
    public static void main(String[] args) {
    }
}
