//Ленивость логических операторов
//Локальные переменные живут в тех циклах, где они объявлены
//Switch, В каждом кейсе нужно использовать default
//While - сначала проверяется условие; Do - сначала одна итерация цикла, потом проверка


package Lection2.itschool;

import java.util.Random;

public class Construkcii {
    public static void main(String[] args) {

        Random random = new Random();
        int num = random.nextInt(100);

        System.out.println(num);
    }
}
