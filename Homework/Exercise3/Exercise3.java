package Homework.Exercise3;
import java.util.Scanner;

public class Exercise3 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int num = (int)(Math.random() * 10);
        int guess;

        System.out.println("Загадано число от 0 до 10. Попробуйте его отгадать:");


        do {
            guess = in.nextInt();

            if (guess != num) {
                if (guess > num) {
                    System.out.println("Загаданное число меньше введённого! Попробуйте угадать ещё раз:");
                } else {
                    System.out.println("Загаданное число больше введённого! Попробуйте угадать ещё раз:");
                }
            }
        } while (guess != num);
        System.out.println("Вы угадали!");
    }
}
