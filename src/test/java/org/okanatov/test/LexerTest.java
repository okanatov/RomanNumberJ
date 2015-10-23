package org.okanatov.test;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class LexerTest {

    @Test
    public void testComplex() throws IOException {
        Lexer lexer = new Lexer(new StringReader("abcd"));
        assertEquals('a', lexer.scan());
        lexer.unread('a');
        lexer.mark(5);
        assertEquals('a', lexer.scan());
        assertEquals('b', lexer.scan());
        lexer.reset();
        assertEquals('a', lexer.scan());
        assertEquals('b', lexer.scan());
        assertEquals('c', lexer.scan());
        assertEquals('d', lexer.scan());
        assertEquals(-1, lexer.scan());
    }

    @Ignore
    public void testMarkAndTwiceReset() throws IOException {
        Lexer lexer = new Lexer(new StringReader("abcd"));
        assertEquals('a', lexer.scan());
        assertEquals('b', lexer.scan());
        lexer.unread("b");
        assertEquals('b', lexer.scan());
        assertEquals('c', lexer.scan());
        assertEquals('d', lexer.scan());
        assertEquals(-1, lexer.scan());
    }

    @Test
    public void testComplex() throws IOException {
        Lexer lexer = new Lexer(new StringReader("abcd"));
        assertEquals('a', lexer.scan());
        lexer.unread("a");
        assertEquals('a', lexer.scan());
        assertEquals('b', lexer.scan());
        lexer.unread("ab");
        assertEquals('a', lexer.scan());
        assertEquals('b', lexer.scan());
        lexer.mark(4);
        assertEquals('c', lexer.scan());
        lexer.reset();
        assertEquals('c', lexer.scan());
        assertEquals('d', lexer.scan());
        assertEquals(-1, lexer.scan());
    }
}