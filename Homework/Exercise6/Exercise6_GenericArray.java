package Homework.Exercise6;

import java.util.*;

class Gen <T extends Number> {
    ArrayList<T> obj;

    //Передаём конструктору ссылку на объект числовой коллекции
    Gen(ArrayList<T> o) {
        obj = o;
    }

    int getSize(ArrayList<T> obj) {
        return obj.size();
    }

    Object get(int index) {
        return obj.get(index);
    }

    void set(int index, T elem) {
        obj.set(index, elem);
    }

    void delete(int index) {
        obj.remove(index);
    }

    /**
     * Не понимаю, как реализовать этот метод
     */
    /* void sort() {
        Collections.sort(obj);
    } */

    void toArray() {
        obj.toArray();
    }

    boolean equals(List o) {
        return obj.equals(o);
    }

    void iterator() {
        Iterator<T> it = obj.iterator();
        System.out.println("Массив через итератор");
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }

    void add(int index, T element) {
        obj.add(index, element);
        System.out.println("Вы добавили элемент " + element + " по индексу " + index);
    }

    void addAll(int index, Collection<T> c) {
        obj.addAll(index, c);
        System.out.println("Коллекция теперь выглядит так: " + obj.toString());
    }

    int indexOf(Object o) {
        return obj.indexOf(o);
    }

    int lastIndexOf(Object o) {
        return obj.lastIndexOf(o);
    }

    Collection<T> subList(int fromIndex, int toIndex) {
        Collection<T> objSub = obj.subList(fromIndex, toIndex);
        return objSub;
    }
}

public class Exercise6_GenericArray {
    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i, (int) (Math.random() * 100));
        }
        Gen<Integer> ob = new Gen<>(list);

        //1 getSize
        System.out.println("getSize: " + ob.getSize(list));

        //2 get
        System.out.println("get: " + ob.get(0));

        //3 set
        ob.set(0, 11);
        System.out.println("set: " + ob.get(0));

        //4 delete
        ob.delete(0);

        /* 5 sort
        ob.sort();
        System.out.println("sorted: " + ob.toString()); */

        //6 toArray
        ob.toArray();

        //7 equals
        ArrayList<Integer> list2 = (ArrayList)list.clone();
        System.out.println(ob.equals(list2));

        //8 iterator
        ob.iterator();

        //9 add
        ob.add(0, 1000);

        //10 addAll
        ob.addAll(0, list2);

        //11 indexOf
        System.out.println(ob.indexOf(1000));

        //12 lastIndexOf
        System.out.println(ob.lastIndexOf(1000));

        //13 sublist
        System.out.println(ob.subList(0, 4).toString());
    }
}
