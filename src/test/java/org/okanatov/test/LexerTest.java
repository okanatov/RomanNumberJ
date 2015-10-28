package org.okanatov.test;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class LexerTest {

    @Test
    public void test4() throws IOException {
        Lexer lexer = new Lexer(new StringReader("IV"), "IV");
        StringBuilder buffer = new StringBuilder("");

        int ch;
        Reader reader = lexer.scan();
        while (reader.ready()) {
            ch = reader.read();
            buffer.append((char) ch);
        }

        assertEquals("[IV]", buffer.toString());

        buffer = new StringBuilder("");
        reader = lexer.scan();
        while (reader.ready()) {
            ch = reader.read();
            buffer.append((char) ch);
        }

        assertEquals(Character.toString((char) -1), buffer.toString());

        buffer = new StringBuilder("");
        reader = lexer.scan();
        while (reader.ready()) {
            ch = reader.read();
            buffer.append((char) ch);
        }

        assertEquals(Character.toString((char) -1), buffer.toString());
    }

    @Test
    public void testChains() throws Exception {

        Lexer lexer = new Lexer(new Lexer(new StringReader("XIV"), "IV"), "X");
        StringBuilder buffer = new StringBuilder("");

        int ch;
        Reader reader = lexer.scan();
        while (reader.ready()) {
            ch = reader.read();
            buffer.append((char) ch);
        }

        assertEquals("[X]", buffer.toString());

        buffer = new StringBuilder("");
        reader = lexer.scan();
        while (reader.ready()) {
            ch = reader.read();
            buffer.append((char) ch);
        }

        assertEquals("[IV]", buffer.toString());

        buffer = new StringBuilder("");
        reader = lexer.scan();
        while (reader.ready()) {
            ch = reader.read();
            buffer.append((char) ch);
        }

        assertEquals(Character.toString((char) -1), buffer.toString());

        buffer = new StringBuilder("");
        reader = lexer.scan();
        while (reader.ready()) {
            ch = reader.read();
            buffer.append((char) ch);
        }

        assertEquals(Character.toString((char) -1), buffer.toString());
    }
}