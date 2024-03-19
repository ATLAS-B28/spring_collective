package demo.shape;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class NestTest {

    @BeforeEach()
    void beforeEach() {
        System.out.println("NestTest.beforeEach()");
    }

    @Nested
    public class FirstNestedClass {
        @BeforeEach()
        void beforeEach() {
            System.out.println("NestTest.FirstNestedClass.beforeEach()");
        };

        @Test
        public void test() {
            System.out.println("NestedTest.FirstNestedClass.test()");
        }
    }

    @Nested
    public class SecondNestedClass {
        @BeforeEach()
        void beforeEach() {
            System.out.println("NestTest.SecondNestedClass.beforeEach()");
        }

        @Test
        public void test() {
            System.out.println("NestedTest.SecondNestedClass.test()");
        }
    }
}
