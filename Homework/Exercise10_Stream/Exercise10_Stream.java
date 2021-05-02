package Homework.Exercise10_Stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercise10_Stream {
    public static void main(String[] args) {
        /*//1 Создайте стрим из случайных положительных и отрицательных чисел
        ArrayList<Integer> list = new ArrayList<>(List.of(-1, -2, 2, 4, 6, 10, -43, 1, 78, 0, -24, 100, -100, 67, 23));

        //2 Выберите только положительные, нечетные числа
        //3 Умножьте каждое число на 2
        //4 Выведите в консоль stream
        Stream<Integer> stream = list.stream();
        stream.filter(x -> x % 2 == 1)
                .map(x -> x * 2)
                .forEach(System.out::println);
        System.out.println("------------------");

        //5 Сохранить результат 15ти чисел в Set Integer
        Stream<Integer> stream2 = list.stream(); //Стрим нельзя использовать дважды
        Set<Integer> setI = stream2.collect(Collectors.toSet());
        System.out.println(setI);
        System.out.println("------------------");

        //6 Найти сумму этих 15ти чисел
        Stream<Integer> streamSum = setI.stream();
        //streamSum.reduce(Integer::sum)
        int sum = streamSum.mapToInt(Integer::valueOf).sum();
        System.out.println(sum);*/

        //Другой вариант
        Set<Integer> set = Stream.generate(() -> new Random().nextInt(100)-50)
                .filter(x -> x % 2 == 1)
                .map(x -> x * 2)
                .limit(15)
                .collect(Collectors.toSet());
        set.stream().reduce(Integer::sum).ifPresent(System.out::println);

    }
}
