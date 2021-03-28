//На ввод подается n чисел не разделенных пробелом (строка вида 123235094659843).
//Вычислить k-тый символ строки. Целочисленный параметр k передается пользователем

package Homework;
import java.util.*;

public class Exercise2 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String str = args[0]; //Передача параметра из командной строки
        StringBuilder sb = new StringBuilder(str);
        int k = in.nextInt(); //k-ый символ строки

        if (k >= 0 && k < str.length()) {
            System.out.println(sb.substring(k, k + 1)); //Вернёт k-ый символ строки
        } else {
            System.out.println("Введено отрицательное число или число, выходящее за пределы строки.");
        }

        //разбиваем строку на массив int'ов
        char[] chArray = str.toCharArray();
        int[] numArray = new int[chArray.length];
        for (int i = 0; i < str.length(); i++) {
            numArray[i] = chArray[i] - '0';
        }

        //min и max значения
        int min = numArray[0];
        int max = numArray[0];
        for (int i = 1; i < numArray.length; i++) {
            if (numArray[i] < min) {
                min = numArray[i];
            }
        }
        for (int i = 1; i < numArray.length; i++) {
            if (numArray[i] > max) {
                max = numArray[i];
            }
        }

        System.out.println("Минимальное: " + min + "\nМаксимальное: " + max);
    }

}
