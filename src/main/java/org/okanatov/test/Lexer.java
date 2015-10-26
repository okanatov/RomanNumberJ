package org.okanatov.test;

import java.io.*;

public class Lexer {
    private BufferedReader bufferedReader;

    public Lexer(Reader input) {
        this.bufferedReader = new BufferedReader(input);
    }

    public int scan() throws IOException {
        return bufferedReader.read();
    }

    public void mark(int readLimit) throws IOException {
        bufferedReader.mark(readLimit);
    }

    public void reset() throws IOException {
        bufferedReader.reset();
    }
}
