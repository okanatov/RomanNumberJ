package org.okanatov.test;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LexerTest {

    @Test
    public void test4() throws IOException {
        Lexer lexer = new Lexer("IV X", "IV");

        String line = lexer.readToken();
        assertEquals("[IV]", line);

        line = lexer.readToken();
        assertEquals(" X", line);

        line = lexer.readToken();
        assertEquals(null, line);
    }

    @Test
    public void testComplex() throws IOException {
        Lexer lexer =
                new Lexer(
                        new Lexer(
                                new Lexer("II IV X", "IV"),
                                "II"),
                        "X");

        String line = lexer.readToken();
        assertEquals("[II]", line);

        line = lexer.readToken();
        assertEquals("[IV]", line);

        line = lexer.readToken();
        assertEquals("[X]", line);

        line = lexer.readToken();
        assertEquals(null, line);
    }
}