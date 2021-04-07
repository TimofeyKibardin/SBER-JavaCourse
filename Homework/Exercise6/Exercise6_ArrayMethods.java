package Homework.Exercise6;

import java.util.*;

public class Exercise6_ArrayMethods {
    public static void main(String[] args) {
        ArrayList<Integer> arrlst = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrlst.add(i, (int)(Math.random() * 10));
        }

        //1 size
        int size = arrlst.size();
        System.out.println(size);

        //2 get
        System.out.println(arrlst.get(2));

        //3 set
        arrlst.set(0, 100);

        //4 remove
        arrlst.remove(9);

        //5 sort
        Collections.sort(arrlst);
        System.out.println(arrlst.toString());

        //6 toArray
        //arrlst.toArray();
        //System.out.println(arrlst);

        //7 equals
        ArrayList<Integer> objD = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            objD.add(i, (int) (Math.random() * 10));
        }
        if (arrlst.equals(objD)) System.out.println("Равны");

        //8 Iterator
        Iterator<Integer> it = arrlst.iterator();
        System.out.println("Лист через итератор");
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        //9 addAll
        arrlst.addAll(objD);
        System.out.println(arrlst.toString());

        //10 indexOf
        System.out.println(arrlst.indexOf(100));

        //11 lastIndexOf
        System.out.println(arrlst.lastIndexOf(100));

        //12
        List<Integer> list1 = arrlst.subList(8, 10);
        System.out.println(list1.toString());
    }
}
