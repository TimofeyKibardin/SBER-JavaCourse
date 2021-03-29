package Homework;
import java.util.Scanner;

public class Exercise4 {
    static int calculatePositionX(int length, String word) {
        return (length - word.length()) / 2;
    }

    static int calculatePositionY(int width) {
        return width / 2;
    }

    static void builder(int length, int width, String word) {
        int x = calculatePositionX(length, word);
        int y = calculatePositionY(width);
        char[][] matrix = new char[width][length]; //Т.к. отсчёт с нуля, везде нужно будет ставить -1
        char[] wordChars = word.toCharArray(); //разбили строку на символы

        //Заполняем массив пробелами
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                matrix[i][j] = ' ';
            }
        }

        //Записываем символы в массив
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                if (i == (y - 1) && j == x) {
                    System.arraycopy(wordChars, 0, matrix[i], j, wordChars.length);
                    break;
                }
            }
        }

        //Проставляем *
        for (int i = 0; i < width; i++) {
            matrix[i][0] = '*';
            matrix[i][length - 1] = '*';
            if (i == 0  || i == (width - 1)) {
                for (int j = 1; j < length; j++) {
                    matrix[i][j] = '*';
                }
            }
        }

        //Отображаем полученное
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Введите ширину: ");
        int width = in.nextInt(); //Ввод ширины
        System.out.print("Введите длину: ");
        int length = in.nextInt(); //Ввод длины
        System.out.print("Введите слово: ");
        String word = in.next(); //Ввод слова, если больше длины - вывод ошибки

        if (word.length() > (length - 2)) {
            System.out.println("Ошибка");
            System.exit(0);  //Завершение работы программы
        }

        System.out.println();
        builder(length, width, word);
    }
}
