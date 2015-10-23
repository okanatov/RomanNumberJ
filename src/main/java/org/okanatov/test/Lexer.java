package org.okanatov.test;

import java.io.*;

public class Lexer {
    private PushbackReader pushbackReader;
    private BufferedReader bufferedReader;

    public Lexer(Reader input) {
        this.bufferedReader = new BufferedReader(input);
        this.pushbackReader = new PushbackReader(this.bufferedReader);
    }

    public int scan() throws IOException {
        return pushbackReader.read();
    }

    public void mark(int readLimit) throws IOException {
        bufferedReader.mark(readLimit);
    }

    public void reset() throws IOException {
        bufferedReader.reset();
    }

    public void unread(int c) throws IOException {
        pushbackReader.unread(c);
    }
}
