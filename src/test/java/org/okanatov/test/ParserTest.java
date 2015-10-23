package org.okanatov.test;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class ParserTest {
    @Ignore
    public void testNull() throws IOException {
        Parser parser = new Parser(new StringReader(""));
        assertEquals(0, parser.evaluate());
    }

    @Ignore
    public void test4() throws IOException {
        Parser parser = new Parser(new StringReader("IV"));
        assertEquals(4, parser.evaluate());
    }

    @Test
    public void test9() throws IOException {
        Parser parser = new Parser(new StringReader("IX"));
        assertEquals(9, parser.evaluate());
    }

    @Ignore
    public void test5() throws IOException {
        Parser parser = new Parser(new StringReader("V"));
        assertEquals(5, parser.evaluate());
    }
}
