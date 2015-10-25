package org.okanatov.test;

import java.io.IOException;
import java.io.Reader;

public class Parser {
    private final Lexer lexer;
    private int lookahead = 0;

    private final Numbers thousands = new Thousands();
    private final Numbers hundreds = new Hundreds();
    private final Numbers tens = new Tens();
    private final Numbers ones = new Ones();

    public Parser(Reader reader) {
        this.lexer = new Lexer(reader);
    }

    public int evaluate() throws IOException {
        lexer.mark(10);
        lookahead = lexer.scan();
        int thousand = evaluate(thousands);
        int hundred = evaluate(hundreds);
        int ten = evaluate(tens);
        int one = evaluate(ones);
        return 1000 * thousand + 100 * hundred + 10 * ten + one;
    }

    public int evaluate(Numbers numbers) throws IOException {
        if (isEnd(numbers)) {
            return 0;
        }

        if (test1(numbers)) {
            return 1;
        }

        lexer.reset();
        lookahead = lexer.scan();
        if (test2(numbers)) {
            return 2;
        }

        lexer.reset();
        lookahead = lexer.scan();
        if (test3(numbers)) {
            return 3;
        }

        lexer.reset();
        lookahead = lexer.scan();
        if (test4(numbers)) {
            return 4;
        }

        lexer.reset();
        lookahead = lexer.scan();
        if (test5(numbers)) {
            return 5;
        }

        lexer.reset();
        lookahead = lexer.scan();
        if (test6(numbers)) {
            return 6;
        }

        lexer.reset();
        lookahead = lexer.scan();
        if (test7(numbers)) {
            return 7;
        }

        lexer.reset();
        lookahead = lexer.scan();
        if (test8(numbers)) {
            return 8;
        }

        lexer.reset();
        lookahead = lexer.scan();
        if (test9(numbers)) {
            return 9;
        }

        throw new Error("parser error");
    }

    private boolean isEnd(Numbers numbers) {
        return numbers.isEnd(lookahead);
    }

    private boolean test1(Numbers numbers) throws IOException {
        return match(numbers.getOne()) && isEnd(numbers);
    }

    private boolean test2(Numbers numbers) throws IOException {
        return match(numbers.getOne()) && match(numbers.getOne()) && isEnd(numbers);
    }

    private boolean test3(Numbers numbers) throws IOException {
        return match(numbers.getOne()) && match(numbers.getOne()) && match(numbers.getOne()) && isEnd(numbers);
    }

    private boolean test4(Numbers numbers) throws IOException {
        return match(numbers.getOne()) && match(numbers.getFive()) && isEnd(numbers);
    }

    private boolean test5(Numbers numbers) throws IOException {
        return match(numbers.getFive()) && isEnd(numbers);
    }

    private boolean test6(Numbers numbers) throws IOException {
        return match(numbers.getFive()) && match(numbers.getOne()) && isEnd(numbers);
    }

    private boolean test7(Numbers numbers) throws IOException {
        return match(numbers.getFive()) && match(numbers.getOne()) && match(numbers.getOne()) && isEnd(numbers);
    }

    private boolean test8(Numbers numbers) throws IOException {
        return match(numbers.getFive()) && match(numbers.getOne()) && match(numbers.getOne()) && match(numbers.getOne()) && isEnd(numbers);
    }

    private boolean test9(Numbers numbers) throws IOException {
        return match(numbers.getOne()) && match(numbers.getTen()) && isEnd(numbers);
    }

    private boolean match(char i) throws IOException {
        if (i == (char) lookahead) {
            lookahead = lexer.scan();
            return true;
        } else {
            return false;
        }
    }
}
