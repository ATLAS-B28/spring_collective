package org.example;

public class Calculator {
    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int result = calculator.add(5, 10);
        System.out.println("Result of addition: " + result);  // Output: 15
    }
}
