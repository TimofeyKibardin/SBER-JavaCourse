//Сложение, вычитание....
package Homework;

public class Exercise1 {
    public static void main(String[] args) {

        double a = Integer.parseInt(args[0]);
        double b = Integer.parseInt(args[2]);
        double c = switch (args[1]) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> 0;
        };

        System.out.printf("%.4f", c);
    }
}
