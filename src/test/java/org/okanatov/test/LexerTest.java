package org.okanatov.test;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LexerTest {

    @Test
    public void test4() throws IOException {
        Lexer lexer = new Lexer("IV X", "IV");
        StringBuilder buffer = new StringBuilder("");
        int ch;

        BufferedInputStream inputStream = new BufferedInputStream(lexer.read());
        while (inputStream.available() > 0) {
            ch = inputStream.read();
            buffer.append((char) ch);
        }
        assertEquals("[IV]", buffer.toString());

        inputStream = new BufferedInputStream(lexer.read());
        buffer = new StringBuilder("");
        while (inputStream.available() > 0) {
            ch = inputStream.read();
            buffer.append((char) ch);
        }
        assertEquals(" X", buffer.toString());

        inputStream = new BufferedInputStream(lexer.read());
        buffer = new StringBuilder("");
        while (inputStream.available() > 0) {
            ch = inputStream.read();
            buffer.append((char) ch);
        }
        assertEquals("", buffer.toString());
    }

    @Test
    public void testComplex() throws IOException {
        Lexer lexer = new Lexer(new Lexer("IV X", "IV"), "X");
        StringBuilder buffer = new StringBuilder("");
        int ch;

        BufferedInputStream inputStream = new BufferedInputStream(lexer.read());
        while (inputStream.available() > 0) {
            ch = inputStream.read();
            buffer.append((char) ch);
        }
        assertEquals("[IV]", buffer.toString());

        inputStream = new BufferedInputStream(lexer.read());
        buffer = new StringBuilder("");
        while (inputStream.available() > 0) {
            ch = inputStream.read();
            buffer.append((char) ch);
        }
        assertEquals("[X]", buffer.toString());

        inputStream = new BufferedInputStream(lexer.read());
        buffer = new StringBuilder("");
        while (inputStream.available() > 0) {
            ch = inputStream.read();
            buffer.append((char) ch);
        }
        assertEquals("", buffer.toString());
    }
}