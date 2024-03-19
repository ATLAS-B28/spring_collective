package org.example;

public class Triangle {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public double area() {
        return 0.5 * base * height;
    }

    public double perimeter() {
        return base * 3;
    }

    public static void main(String[] args) {
        Triangle triangle = new Triangle(5, 10);
        System.out.println("Area: " + triangle.area());  // Output: 25.0
        System.out.println("Perimeter: " + triangle.perimeter());  // Output: 15.0
    }

    public boolean isValid() {
        return base > 0 && height > 0;
    }
}
