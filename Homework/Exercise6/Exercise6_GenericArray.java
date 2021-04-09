package Homework.Exercise6;

import java.util.*;

public class Exercise6_GenericArray {
    public static void main(String[] args) {

        MyArrayList<Integer> ob = new MyArrayList<>();
        for (int i = 0; i < 12; i++) {
            ob.add(i);
        }
        System.out.print("Массив: ");
        for (int i = 0; i < ob.size(); i++) {
            System.out.print(ob.get(i) + " ");
        }
        System.out.println();

        //1 size()
        System.out.println("getSize: " + ob.size());

        //2 get()
        System.out.println("get: " + ob.get(0));

        //3 set()
        ob.set(0, 1000);
        System.out.println("set 0: " + ob.get(0));

        //4 delete()
        ob.delete(12);
        System.out.print("Массив: ");
        for (int i = 0; i < ob.size(); i++) {
            System.out.print(ob.get(i) + " ");
        }
        System.out.println();

        //5 indexOf()
        System.out.println("indexOf 3: " + ob.indexOf(3));

        //6 lastIndexOf()
        System.out.println("lastIndexOf 1000: " + ob.lastIndexOf(7));

        //7 toArray()
        ob.toArray(); //Хотя здесь и так нет коллекции

        //8 clear()
        //ob.clear();

        //9 add
        ob.add(5000);
        ob.add(4, 1000);

        //10 addAll()
        ArrayList<Integer> ob2 = new ArrayList<>();
        for (int i = 0; i < ob.size(); i++) {
            ob2.add((int)(Math.random() * -100));
        }
        ob.addAll(1, ob2);
        System.out.print("Массив ob + ob2: ");
        for (int i = 0; i < ob.size(); i++) {
            System.out.print(ob.get(i) + " ");
        }
        System.out.println();

        //11 equals()
        System.out.println("equals: " + ob.equals(ob2));

        //12 sublist()
        MyArrayList<Integer> ob3 = ob.subList(10, 15);
        System.out.print("sublist: ");
        for (int i = 0; i < ob3.size(); i++) {
            System.out.print(ob3.get(i) + " ");
        }
        System.out.println();

        //13 iterator()
        Iterator<Integer> it = ob3.iterator();
        System.out.print("iterator: ");
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }

        //14 sort()
        /* ob.sort();
        System.out.print("Массив ob после sort(): ");
        for (int i = 0; i < ob.size(); i++) {
            System.out.print(ob.get(i) + " ");
        }
        System.out.println(); */
    }
}
