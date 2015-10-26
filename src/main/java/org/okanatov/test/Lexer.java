package org.okanatov.test;

import java.io.*;

public class Lexer {
    private PushbackReader pushbackReader;

    public Lexer(Reader input) {
        this.pushbackReader = new PushbackReader(input, 10);
    }

    public int scan() throws IOException {
        return pushbackReader.read();
    }

    public void unread(String string) throws IOException {
        pushbackReader.unread(string.toCharArray());
    }
}
