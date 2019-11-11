package org.danny.demo;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.printf("Input number: ");
        int i = 0;
        try {
            Scanner s = new Scanner(System.in);
            i = s.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
        decomposition(i, 2);
    }

    public static int decomposition(int x, int y) {
        if (x < 2) {
            System.out.print("");
            return 0;
        } else if (x == y) {
            System.out.print(x);
            System.out.print(',');
            return 0;
        } else {
            if (0 == x % y) {
                System.out.print(y);
                System.out.print(',');
                return decomposition(x/y, 2);
            } else {
                return decomposition(x, y+1);
            }
        }
    }



}
