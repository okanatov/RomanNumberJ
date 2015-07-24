package org.okanatov.test;

import org.junit.Test;

import static org.junit.Assert.*;

public class LexerTest {
    @Test
    public void testMarkAndTwiceReset() {
        Lexer lexer = new Lexer("abcd");
        assertEquals('a', lexer.scan());
        int pos = lexer.mark();
        assertEquals('b', lexer.scan());
        assertEquals('c', lexer.scan());
        lexer.reset(pos);
        assertEquals('b', lexer.scan());
        assertEquals('c', lexer.scan());
        lexer.reset(pos);
        assertEquals('b', lexer.scan());
        assertEquals('c', lexer.scan());
        assertEquals('d', lexer.scan());
        assertEquals('$', lexer.scan());

    }
}