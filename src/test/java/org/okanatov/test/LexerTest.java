package org.okanatov.test;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class LexerTest {

    @Test
    public void testMarkAndReset() throws IOException {
        Lexer lexer = new Lexer(new StringReader("abcd"));
        assertEquals('a', lexer.scan());
        assertEquals('b', lexer.scan());
        assertEquals('c', lexer.scan());
        lexer.unread("bc");
        assertEquals('b', lexer.scan());
        assertEquals('c', lexer.scan());
        assertEquals('d', lexer.scan());
        assertEquals(-1, lexer.scan());
    }
}