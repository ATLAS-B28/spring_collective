package org.example;

public class MathClass {
    public static int add(int num1, int num2) {
        return num1 + num2;
    }

    public int subtract(int num1, int num2) {
        return num1 - num2;
    }

    public int multiply(int num1, int num2) {
        return num1 * num2;
    }
    public static void main(String[] args) {
        MathClass math = new MathClass();
        int sum = math.add(5, 10);
        int difference = math.subtract(10, 5);
        System.out.println("Sum: " + sum);  // Output: 15
        System.out.println("Difference: " + difference);  // Output: 5
    }
}
