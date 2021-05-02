package Homework.Exercise7_Exceptions;

import java.io.*;
import java.util.Scanner;

public class Exercise7_Exceptions {
    public static void main(String[] args) {
        System.out.println("Протестируем разные exception:");

        int a = 0;
        int[] b = new int[10];
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int num;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        //1. Arithmetic Exception
        try {
            a = 20 / 0;

        } catch (ArithmeticException exc) {
            System.out.println(exc);
        }

        //2. ArrayIndexOutOfBounds Exception
        try {
            b[11] = 100;
        } catch (ArrayIndexOutOfBoundsException exc) {
            System.out.println(exc);
        }

        //3. NumberFormat Exception
        try {
            num = Integer.parseInt(s);
        } catch (NumberFormatException exc) {
            System.out.println(exc);
        }

        //4. FileNotFoundException
        InputStream in = null;
        try {
            FileInputStream fin = new FileInputStream("fdsfdsfs");
        } catch (FileNotFoundException exc) {
            System.out.println("Файл не найден");
        }

        //5. IOException
        String str = null;
        try {
            str = reader.readLine();
        } catch (IOException exc) {
            System.out.println(exc);
        }
    }
}
