package compSciProject.gameTools;
import compSciProject.LinkedList;

import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by rtorres12 on 4/6/15.
 *
 */
class growArray<K, E>{
    int size;
    LinkedList<K, E>[] array;
    int addcount;
    ArrayList<K> keyList = new ArrayList<>();

    public growArray(int length){
        this.size = length;
        array = new LinkedList[length];
    }
    public void add(K key, E element){
        keyList.add(key);
        addcount++;
        if(addcount == size){
            this.size *=2;
            LinkedList<K, E> newArray[] = new LinkedList[size*2];
            for(int i=0;i<size/2;i++){
                newArray[keyList.get(i).hashCode()%size] = array[keyList.get(i).hashCode()%(size/2)];
            }
            array = newArray;
            array[element.hashCode()%size].add(key,element);
            return;
        }
        System.out.println(element.hashCode());
        array[abs(element.hashCode())%size].add(key,element);
    }
    public E get(String key){
        int hashKey = abs(key.hashCode());
        return array[hashKey].get(key);
    }
    public void print(){
        for (LinkedList k : array) {
            if(k !=null)
            k.print();
        }
    }
}
public class hashMap<K, E> {
    growArray<K, E> elementsArray;

    public hashMap() {
        elementsArray = new growArray<>(10);
    }

    void insert(K key, E element){
        elementsArray.add(key, element);
    }

    E get(String key){
        //System.out.println(elementsArray.get(key.hashCode()%elementsArray.size));
        return elementsArray.get(key);
    }

    public static void main(String[] args) {
        hashMap<String, String> mapTest = new hashMap<>();
        String test = "Hello";
        mapTest.insert("New York", "Albany");
        mapTest.insert("Alaska", "Cold");
        mapTest.insert("Kentucky", "Chicken");
        mapTest.insert("Brazil", "Jiu Jitsu");
        mapTest.insert("Japan", "Anime");
        mapTest.insert("Korea", "K-Pop");
        mapTest.insert("North Korea", "Not-MUCH");
        mapTest.insert("New York City", "Jay Z");
        mapTest.insert("Brooklyn", "Thugs");
        mapTest.insert("Europe", "Game of Thrones");
        mapTest.insert("Airplanes", "In the Sky");
        mapTest.insert("South Africe", "Apartheid");
        mapTest.insert("North America", "Murrica");
        mapTest.insert("Alabama", "Stating");
        System.out.println(mapTest.get("New York"));
        System.out.println(mapTest.get("Alaska"));
        System.out.println(mapTest.get("Kentucky"));
        System.out.println(mapTest.get("Brazil"));
        System.out.println(mapTest.get("Japan"));
        System.out.println(mapTest.get("Korea"));
        System.out.println(mapTest.get("North Korea"));
        System.out.println(mapTest.get("Alabama"));
        //for(int i=0;i<25;i++){
        //    testingArray.add(i,i);
        //}
        //testingArray.print();
    }
}
