package org.okanatov.test;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

public class LexerTest {
    private String token;

    @Test
    public void test4() {
        Lexer lexer = new Lexer(new ByteArrayInputStream("IVX".getBytes()), "IV");

        token = lexer.readToken();
        assertEquals("[IV]", token);

        token = lexer.readToken();
        assertEquals("X", token);

        token = lexer.readToken();
        assertEquals(null, token);
    }

    @Test
    public void testComplex() {
        Lexer lexer =
                new Lexer(
                        new Lexer(
                                new Lexer(
                                        new ByteArrayInputStream("IIIVX".getBytes()), "IV"),
                                "II"),
                        "X");

        token = lexer.readToken();
        assertEquals("[II]", token);

        token = lexer.readToken();
        assertEquals("[IV]", token);

        token = lexer.readToken();
        assertEquals("[X]", token);

        token = lexer.readToken();
        assertEquals(null, token);
    }
}