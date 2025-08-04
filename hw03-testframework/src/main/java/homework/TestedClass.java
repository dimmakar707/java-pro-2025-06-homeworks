package homework;

import homework.annotations.After;
import homework.annotations.Before;
import homework.annotations.Test;

public class TestedClass {

    @Before
    public void setUp1() {
        System.out.println("method setUp1");
    }

    @Before
    public void setUp2() {
        System.out.println("method setUp2");
    }

    @Test
    public void goodMethod1() {
        System.out.println("This is good method 1");
    }

    @Test
    public void goodMethod2() {
        System.out.println("This is good method 2");
    }

    @Test
    public void badMethod1() {
        System.out.println("This is bad method 1");
        throw new RuntimeException();
    }

    @Test
    public void badMethod2() {
        System.out.println("This is bad method 2");
        throw new RuntimeException();
    }

    @Test
    public void badMethod3() {
        System.out.println("This is bad method 3");
        throw new RuntimeException();
    }

    @After
    public void tearDown1() {
        System.out.println("method tearDown1");
    }

    @After
    public void tearDown2() {
        System.out.println("method tearDown2");
    }
}
