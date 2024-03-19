package demo.shape;
/*
import org.example.MathClass;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class MyMathTest {
    private static final MathClass mathClass = new MathClass();

   /* @BeforeClass
    public static void setUp() {
        mathClass = new MathClass();
        System.out.println("Before Class");
    }

    @AfterClass
    public static void tearDown() {
        mathClass = null;
        System.out.println("After Class");
    }

    @Test
    public void testAdd() {
        int result = mathClass.add(5, 10);
        assertEquals(15, result);
        System.out.println("Test Add");
    }

    @Test
    public void testSubtract() {
        int result = mathClass.subtract(10, 5);
        assertEquals(5, result);
        System.out.println("Test Subtract");
    }
    @Nested
    public class MultiplicationTests {
        @Test
        public void testPositiveNumbers() {
            int result = mathClass.multiply(5, 10);
            assertEquals(50, result);
            System.out.println("Test Positive Numbers");
        }

        @Test
        public void testNegativeNumbers() {
            int result = mathClass.multiply(-5, -10);
            assertEquals(50, result);
            System.out.println("Test Negative Numbers");
        }
    }


}*/
import org.example.MathClass;
import org.junit.Test;
import org.junit.jupiter.api.Nested;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.Enclosed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(Enclosed.class)
public class MathTest {

    public final MathClass Math = new MathClass();

    @Test
    public void testAddition() {
        int result = MathClass.add(2, 3);
        assertEquals(5, result);
    }

    @Test
    public void testSubtraction() {
        int result = Math.subtract(5, 2);
        assertEquals(3, result);
    }

    @Nested
    public class MultiplicationTests {

        @Test
        public void testPositiveNumbers() {
            int result = Math.multiply(4, 6);
            assertEquals(24, result);
        }

        @Test
        public void testNegativeNumbers() {
            int result = Math.multiply(-2, 3);
            assertEquals(-6, result);
        }
    }
}
