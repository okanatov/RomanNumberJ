package org.okanatov.test;

import java.io.IOException;
import java.io.Reader;

public class Parser {
    private Lexer lexer;
    private int lookahead;

    public Parser(Reader reader) throws IOException {
        this.lexer = new Lexer(reader);
        this.lookahead = lexer.scan();
    }

    public int evaluate() throws IOException {
        lexer.unread(lookahead);
        lexer.mark(10);
        lookahead = lexer.scan();

        if (lookahead == -1)
            return 0;

        if (test4())
            return 4;

        lexer.reset();
        lookahead = lexer.scan();

        if (test9())
            return 9;

        lexer.reset();
        lookahead = lexer.scan();

        if (test5())
            return 5;

        throw new RuntimeException("syntax error");
    }

    private boolean test5() throws IOException {
        return match('V') && lookahead == -1;
    }

    private boolean test9() throws IOException {
        return match('I') && match('X') && lookahead == -1;
    }

    private boolean test4() throws IOException {
        return match('I') && match('V') && lookahead == -1;
    }

    private boolean match(int ch) throws IOException {
        if (lookahead == ch) {
            lookahead = lexer.scan();
            return true;
        }
        return false;
    }
}
