package compSciProject.gameTools;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Created by rtorres12 on 4/6/15.
 *
 */
class growArray<T, E>{
    int size;
    T[] array;
    int addcount;
    ArrayList<E> keyList = new ArrayList<>();

    public growArray(int length){
        this.size = length;
        array = (T[]) new Object[this.size];
    }
    public void add(T element, E keyHold){
        keyList.add(keyHold);
        addcount++;
        if(addcount == size){
            this.size *=2;
            T[] newArray = (T[]) new Object[this.size];
            for(int i=0;i<size/2;i++){
                newArray[keyList.get(i).hashCode()%size] = array[keyList.get(i).hashCode()%(size/2)];
            }
            array = newArray;
            array[keyHold.hashCode()%size] = element;
            return;
        }
        System.out.println(keyHold.hashCode());
        array[abs(keyHold.hashCode())%size]= element;
    }
    public T get(int index){
        return array[index];
    }
    public void print(){
        for (T t : array) {
            if(t!=null)
            System.out.println(t);
        }
    }
}
public class hashMap<K, E> {
    growArray<E,K> elementsArray;

    public hashMap() {
        elementsArray = new growArray<>(10);
    }

    void insert(K key, E element){
        elementsArray.add(element, key);
    }

    E get(String key){
        //System.out.println(elementsArray.get(key.hashCode()%elementsArray.size));
        return elementsArray.get(key.hashCode()%elementsArray.size);
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
