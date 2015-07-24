package org.okanatov.test;

public class Parser {
    private char lookahead;
    private int result = 0;
    private int count = 0;

    private final Lexer lexer;
    private final Numbers Thousands = new Thousands();
    private final Numbers Hundreds = new Hundreds();
    private final Numbers Tens = new Tens();
    private final Numbers Ones = new Ones();

    public Parser(final String str) {
        this.lexer = new Lexer(str);
        lookahead = lexer.scan();
    }

    public int evaluate() {
        final int thousands = evaluate(Thousands);
        final int hundreds = evaluate(Hundreds);
        final int tens = evaluate(Tens);
        final int ones = evaluate(Ones);
        return 1000 * thousands + 100 * hundreds + 10 * tens + ones;
    }

    private int evaluate(final Numbers numbers) {
        final int save = lexer.mark() - 1;
        result = 0;
        count = 0;

        if (SmallDigit(numbers))
            return result;

        lexer.reset(save);
        lookahead = lexer.scan();
        if (S4(numbers))
            return 4;

        lexer.reset(save);
        lookahead = lexer.scan();
        if (S9(numbers))
            return 9;

        lexer.reset(save);
        lookahead = lexer.scan();
        if (S5(numbers))
            return 5 + result;

        throw new Error("syntax error");
    }

    private boolean SmallDigit(final Numbers numbers) {
        return S1(numbers) || S2(numbers);
    }

    private boolean S1(final Numbers numbers) {
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

    private boolean S4(final Numbers numbers) {
        return match(numbers.getOne()) && match(numbers.getFive()) && S2(numbers);
    }

    private boolean S9(final Numbers numbers) {
        return match(numbers.getOne()) && match(numbers.getTen()) && S2(numbers);
    }

    private boolean S5(final Numbers numbers) {
        count = 0;
        return match(numbers.getFive()) && SmallDigit(numbers);
    }

    private boolean match(final char i) {
        if ((lookahead == i)) {
            lookahead = lexer.scan();
            return true;
        }
        return false;
    }
}
