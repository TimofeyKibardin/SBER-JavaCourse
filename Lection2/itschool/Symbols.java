//Для чара присваивается 1 символ! Запись идёт через ' '
//Для всего остального - String, " "
//char - 16 бит, 2 в 16 степени, включает знаки всех языков мира
//В Java Unicode поддерживается "из коробки"
// плюсы перед переменной - сначала инкремент, потом вывод переменной
//double / int = double
// double / 0 = INFINITY


package Lection2.itschool;

public class Symbols {
    public static void main(String[] args) {

        int x = 1;
        double a = 33.23 / 0;

        System.out.println(a);

    }
}
