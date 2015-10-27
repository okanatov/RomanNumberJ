package org.okanatov.test;

import java.io.IOException;
import java.io.Reader;

public class Parser {
    private final Reader reader;
    private int lookahead = -1;

    public Parser(Reader reader) throws IOException {
        this.reader = reader;
        this.lookahead = reader.read();
    }

    public int evaluate() throws IOException {
        int result = romanNumber();
        if (isEnd())
            return result;
        else
            throw new Error("parser error");
    }

    private int romanNumber() throws IOException {
        if (match('I'))
            return 1 + romanNumber1();
        else if (match('V'))
            return 5 + romanNumber2() + romanNumber2() + romanNumber2();
        else
            return 0;
    }

    private int romanNumber1() throws IOException {
        if (match('I'))
            return 1 + romanNumber2();
        else if (match('V'))
            return 3;
        else if (match('X'))
            return 8;
        else
            return 0;
    }

    private int romanNumber2() throws IOException {
        if (match('I'))
            return 1;
        else
            return 0;
    }

    private boolean match(char x) throws IOException {
        if (x == (char) lookahead) {
            lookahead = reader.read();
            return true;
        }
        return false;
    }

    public boolean isEnd() {
        return lookahead == -1;
    }
}
