package org.leetcode.dkm.ms4;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class TestDemoTest {
    TestDemo testDemo = null;

    static StringBuffer sb = null;

    @BeforeAll
    static void beforeAll() {
        sb = new StringBuffer("start");
        System.out.println("come in BeforeAll sb length: " + sb.length());
    }
    @AfterAll
    static void afterAll() {
        System.out.println("come in AfterAll sb add end: " + sb.append(" end"));
        System.out.println("come in AfterAll sb length: " + sb.length());
    }


    @BeforeEach
    void setUp() {
        System.out.println("come in BeforeEach");
        testDemo = new TestDemo();
    }

    @AfterEach
    void afterEach() {
        System.out.println("come in AfterEach");
    }

    @Test
    void add() {

        int result = testDemo.add(1, 2);
        assertEquals(3, result);
    }

    @Test
    void div() {
        int result = testDemo.div(1, 2);
        assertEquals(0, result);
    }

    @Test
    void mul() {
    }

    @Test
    void sub() {
    }
}