package org.okanatov.test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class Lexer {
    private Reader input;

    public Lexer(StringReader input) {
        this.input = input;
    }

    public int scan() throws IOException {
        return input.read();
    }

    public void mark(int readLimit) throws IOException {
        if (!input.markSupported())
            throw new IOException("mark/reset is not supported");

        input.mark(readLimit);
    }

    public void reset() throws IOException {
        if (!input.markSupported())
            throw new IOException("mark/reset is not supported");

        input.reset();
    }
}
