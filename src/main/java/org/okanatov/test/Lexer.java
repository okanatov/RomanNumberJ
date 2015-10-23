package org.okanatov.test;

import java.io.*;

public class Lexer {
    public static final int SIZE = 10;
    private PushbackReader pushbackReader;

    public Lexer(Reader input) {
        this.pushbackReader = new PushbackReader(input, SIZE);
    }

    public int scan() throws IOException {
        return pushbackReader.read();
    }

    public void unread(String string) throws IOException {
        pushbackReader.unread(string.toCharArray());
    }
}
