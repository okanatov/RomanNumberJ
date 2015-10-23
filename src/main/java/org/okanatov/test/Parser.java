package org.okanatov.test;

import java.io.IOException;
import java.io.StringReader;

public class Parser {
    private char lookahead;
    private int result = 0;
    private int count = 0;

    private final Lexer lexer;
    private final Numbers Thousands = new Thousands();
    private final Numbers Hundreds = new Hundreds();
    private final Numbers Tens = new Tens();
    private final Numbers Ones = new Ones();

    public Parser(final String str) throws IOException {
        this.lexer = new Lexer(new StringReader(str));
        lookahead = (char) lexer.scan();
    }

    public int evaluate() throws IOException {
        final int thousands = evaluate(Thousands);
        final int hundreds = evaluate(Hundreds);
        final int tens = evaluate(Tens);
        final int ones = evaluate(Ones);
        return 1000 * thousands + 100 * hundreds + 10 * tens + ones;
    }

    private int evaluate(final Numbers numbers) throws IOException {
        //lexer.mark(5);
        result = 0;
        count = 0;

        if (SmallDigit(numbers))
            return result;

        //lexer.reset();
        lookahead = (char) lexer.scan();
        if (S4(numbers))
            return 4;

        //lexer.reset();
        lookahead = (char) lexer.scan();
        if (S9(numbers))
            return 9;

        //lexer.reset();
        lookahead = (char) lexer.scan();
        if (S5(numbers))
            return 5 + result;

        throw new Error("syntax error");
    }

    private boolean SmallDigit(final Numbers numbers) throws IOException {
        return S1(numbers) || S2(numbers);
    }

    private boolean S1(final Numbers numbers) throws IOException {
        if (++count > 3) return false;
        if (match(numbers.getOne()) && SmallDigit(numbers)) {
            result++;
            return true;
        }
        return false;
    }

    private boolean S2(final Numbers numbers) {
        if (numbers instanceof Ones) return lookahead == '$';
        return lookahead != numbers.getOne() &&
               lookahead != numbers.getFive() &&
               lookahead != numbers.getTen();
    }

    private boolean S4(final Numbers numbers) throws IOException {
        return match(numbers.getOne()) && match(numbers.getFive()) && S2(numbers);
    }

    private boolean S9(final Numbers numbers) throws IOException {
        return match(numbers.getOne()) && match(numbers.getTen()) && S2(numbers);
    }

    private boolean S5(final Numbers numbers) throws IOException {
        count = 0;
        return match(numbers.getFive()) && SmallDigit(numbers);
    }

    private boolean match(final char i) throws IOException {
        if ((lookahead == i)) {
            lookahead = (char) lexer.scan();
            return true;
        }
        return false;
    }
}
