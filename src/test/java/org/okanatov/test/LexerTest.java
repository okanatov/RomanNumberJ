package org.okanatov.test;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class LexerTest {

    @Test
    public void test4() throws IOException {
        Lexer lexer = Lexer.createFromReader(new StringReader("IV"), "IV");
        StringBuilder buffer = new StringBuilder("");

        int ch;
        Reader reader;
        while (((reader = lexer.scan()) != null) && (reader.ready())) {
            ch = reader.read();
            buffer.append((char) ch);
        }

        assertEquals("[IV]", buffer.toString());
    }
}