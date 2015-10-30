package org.okanatov.test;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

public class LexerTest {
    private Token token;

    @Test
    public void test4() {
        Lexer lexer = new Lexer(new ByteArrayInputStream("IVX".getBytes()), "IV");

        token = lexer.readToken();
        assertEquals("[IV]", token.toString());

        token = lexer.readToken();
        assertEquals("X", token.toString());

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
                                "I{2}"),
                        "X");

        token = lexer.readToken();
        assertEquals("[II]", token.toString());

        token = lexer.readToken();
        assertEquals("[IV]", token.toString());

        token = lexer.readToken();
        assertEquals("[X]", token.toString());

        token = lexer.readToken();
        assertEquals(null, token);
    }

    @Test
    public void testIteration() {
        Lexer lexer =
                new Lexer(
                        new Lexer(
                                new Lexer(
                                        new ByteArrayInputStream("IIIVX".getBytes()), "IV"),
                                "II"),
                        "X");

        for (Token aLexer : lexer) {
            token = aLexer;
            System.out.println(token.toString());
        }
    }

    @Test
    public void testLookAheadAndBehind() throws Exception {
        Lexer lexer = new Lexer(new ByteArrayInputStream("IVX".getBytes()), "(?<=I)V(?=X)");

        token = lexer.readToken();
        assertEquals("I", token.toString());

        token = lexer.readToken();
        assertEquals("[V]", token.toString());

        token = lexer.readToken();
        assertEquals("X", token.toString());

        token = lexer.readToken();
        assertEquals(null, token);
    }
}