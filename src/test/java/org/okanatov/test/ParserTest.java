package org.okanatov.test;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {
    @Test
    public void testNull() {
        Parser parser = new Parser("");
        assertEquals(0, parser.evaluate());
    }

    @Test
    public void testOne() {
        Parser parser = new Parser("I");
        assertEquals(1, parser.evaluate());
    }

    @Test
    public void testTwo() {
        Parser parser = new Parser("II");
        assertEquals(2, parser.evaluate());
    }

    @Test
    public void testThree() {
        Parser parser = new Parser("III");
        assertEquals(3, parser.evaluate());
    }

    @Test(expected = Error.class)
    public void testFourWrong() {
        Parser parser = new Parser("IIII");
        assertEquals(0, parser.evaluate());
    }

    @Test
    public void testFour() {
        Parser parser = new Parser("IV");
        assertEquals(4, parser.evaluate());
    }

    @Test(expected = Error.class)
    public void testFourWrong2() {
        Parser parser = new Parser("IIV");
        assertEquals(0, parser.evaluate());
    }

    @Test(expected = Error.class)
    public void testFourWrong3() {
        Parser parser = new Parser("IIL");
        assertEquals(2, parser.evaluate());
    }

    @Test
    public void testNine() {
        Parser parser = new Parser("IX");
        assertEquals(9, parser.evaluate());
    }

    @Test
    public void testFive() {
        Parser parser = new Parser("V");
        assertEquals(5, parser.evaluate());
    }

    @Test
    public void testSix() {
        Parser parser = new Parser("VI");
        assertEquals(6, parser.evaluate());
    }
    @Test
    public void testSeven() {
        Parser parser = new Parser("VII");
        assertEquals(7, parser.evaluate());
    }
    @Test
    public void testEight() {
        Parser parser = new Parser("VIII");
        assertEquals(8, parser.evaluate());
    }

    @Test(expected = Error.class)
    public void testNineWrong() {
        Parser parser = new Parser("VIIII");
        assertEquals(0, parser.evaluate());
    }

    @Test
    public void test11() {
        Parser parser = new Parser("XI");
        assertEquals(11, parser.evaluate());
    }

    @Test
    public void test101() {
        Parser parser = new Parser("CI");
        assertEquals(101, parser.evaluate());
    }

    @Test
    public void test111() {
        Parser parser = new Parser("CXI");
        assertEquals(111, parser.evaluate());
    }

    @Test
    public void test211() {
        Parser parser = new Parser("CCXI");
        assertEquals(211, parser.evaluate());
    }

    @Test
    public void test331() {
        Parser parser = new Parser("CCCXXXI");
        assertEquals(331, parser.evaluate());
    }

    @Test(expected = Error.class)
    public void test431Wrong() {
        Parser parser = new Parser("CCCCXXXI");
        assertEquals(331, parser.evaluate());
    }

    @Test
    public void test1883() {
        Parser parser = new Parser("MDCCCLXXXIII");
        assertEquals(1883, parser.evaluate());
    }

    @Test
    public void test55() {
        Parser parser = new Parser("LV");
        assertEquals(55, parser.evaluate());
    }
}