package org.okanatov.test;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class ParserTest {
    @Test
    public void testNull() throws IOException {
        Parser parser = new Parser(new StringReader(""));
        assertEquals(0, parser.evaluate());
    }

    @Test
    public void testOne() throws IOException {
        Parser parser = new Parser(new StringReader("I"));
        assertEquals(1, parser.evaluate());
    }

    @Test
    public void testTwo() throws IOException {
        Parser parser = new Parser(new StringReader("II"));
        assertEquals(2, parser.evaluate());
    }

    @Test
    public void testThree() throws IOException {
        Parser parser = new Parser(new StringReader("III"));
        assertEquals(3, parser.evaluate());
    }

    @Test(expected = Error.class)
    public void testFourWrong() throws IOException {
        Parser parser = new Parser(new StringReader("IIII"));
        parser.evaluate();
    }

    @Test
    public void testFour() throws IOException {
        Parser parser = new Parser(new StringReader("IV"));
        assertEquals(4, parser.evaluate());
    }

    @Test(expected = Error.class)
    public void testFourWrong2() throws IOException {
        Parser parser = new Parser(new StringReader("IIV"));
        parser.evaluate();
    }

    @Test(expected = Error.class)
    public void testFourWrong3() throws IOException {
        Parser parser = new Parser(new StringReader("IIL"));
        parser.evaluate();
    }

    @Test
    public void testNine() throws IOException {
        Parser parser = new Parser(new StringReader("IX"));
        assertEquals(9, parser.evaluate());
    }

    @Test
    public void testFive() throws IOException {
        Parser parser = new Parser(new StringReader("V"));
        assertEquals(5, parser.evaluate());
    }

    @Test
    public void testSix() throws IOException {
        Parser parser = new Parser(new StringReader("VI"));
        assertEquals(6, parser.evaluate());
    }

    @Test
    public void testSeven() throws IOException {
        Parser parser = new Parser(new StringReader("VII"));
        assertEquals(7, parser.evaluate());
    }

    @Test
    public void testEight() throws IOException {
        Parser parser = new Parser(new StringReader("VIII"));
        assertEquals(8, parser.evaluate());
    }

    @Test(expected = Error.class)
    public void testNineWrong() throws IOException {
        Parser parser = new Parser(new StringReader("VIIII"));
        parser.evaluate();
    }

    @Test
    public void test11() throws IOException {
        Parser parser = new Parser(new StringReader("XI"));
        assertEquals(11, parser.evaluate());
    }

    @Test
    public void test101() throws IOException {
        Parser parser = new Parser(new StringReader("CI"));
        assertEquals(101, parser.evaluate());
    }

    @Test
    public void test111() throws IOException {
        Parser parser = new Parser(new StringReader("CXI"));
        assertEquals(111, parser.evaluate());
    }

    @Test
    public void test211() throws IOException {
        Parser parser = new Parser(new StringReader("CCXI"));
        assertEquals(211, parser.evaluate());
    }

    @Test
    public void test331() throws IOException {
        Parser parser = new Parser(new StringReader("CCCXXXI"));
        assertEquals(331, parser.evaluate());
    }

    @Test(expected = Error.class)
    public void test431Wrong() throws IOException {
        Parser parser = new Parser(new StringReader("CCCCXXXI"));
        parser.evaluate();
    }

    @Test
    public void test1883() throws IOException {
        Parser parser = new Parser(new StringReader("MDCCCLXXXIII"));
        assertEquals(1883, parser.evaluate());
    }

    @Test
    public void test55() throws IOException {
        Parser parser = new Parser(new StringReader("LV"));
        assertEquals(55, parser.evaluate());
    }
}