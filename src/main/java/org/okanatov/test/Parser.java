package org.okanatov.test;

import java.io.IOException;
import java.io.Reader;

public class Parser {
    private final Lexer lexer;
    private int lookahead = 0;
    private StringBuilder buffer = new StringBuilder("");

    private final Numbers thousands = new Thousands();
    private final Numbers hundreds = new Hundreds();
    private final Numbers tens = new Tens();
    private final Numbers ones = new Ones();

    public Parser(Reader reader) throws IOException {
        this.lexer = new Lexer(reader);
        read();
    }

    public int evaluate() throws IOException {
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

        reset();
        if (test2(numbers)) {
            return 2;
        }

        reset();
        if (test3(numbers)) {
            return 3;
        }

        reset();
        if (test4(numbers)) {
            return 4;
        }

        reset();
        if (test5(numbers)) {
            return 5;
        }

        reset();
        if (test6(numbers)) {
            return 6;
        }

        reset();
        if (test7(numbers)) {
            return 7;
        }

        reset();
        if (test8(numbers)) {
            return 8;
        }

        reset();
        if (test9(numbers)) {
            return 9;
        }

        throw new Error("parser error");
    }

    private boolean isEnd(Numbers numbers) throws IOException {
        boolean isEnd = numbers.isEnd(lookahead);
        if (isEnd)
            buffer.delete(0, buffer.length() - 1);
        return isEnd;
    }

    private boolean test1(Numbers numbers) throws IOException {
        return matchAndRead(numbers.getOne()) && isEnd(numbers);
    }

    private boolean test2(Numbers numbers) throws IOException {
        return matchAndRead(numbers.getOne()) && matchAndRead(numbers.getOne()) && isEnd(numbers);
    }

    private boolean test3(Numbers numbers) throws IOException {
        return matchAndRead(numbers.getOne()) && matchAndRead(numbers.getOne()) && matchAndRead(numbers.getOne()) && isEnd(numbers);
    }

    private boolean test4(Numbers numbers) throws IOException {
        return matchAndRead(numbers.getOne()) && matchAndRead(numbers.getFive()) && isEnd(numbers);
    }

    private boolean test5(Numbers numbers) throws IOException {
        return matchAndRead(numbers.getFive()) && isEnd(numbers);
    }

    private boolean test6(Numbers numbers) throws IOException {
        return matchAndRead(numbers.getFive()) && matchAndRead(numbers.getOne()) && isEnd(numbers);
    }

    private boolean test7(Numbers numbers) throws IOException {
        return matchAndRead(numbers.getFive()) && matchAndRead(numbers.getOne()) && matchAndRead(numbers.getOne()) && isEnd(numbers);
    }

    private boolean test8(Numbers numbers) throws IOException {
        return matchAndRead(numbers.getFive()) && matchAndRead(numbers.getOne()) && matchAndRead(numbers.getOne()) && matchAndRead(numbers.getOne()) && isEnd(numbers);
    }

    private boolean test9(Numbers numbers) throws IOException {
        return matchAndRead(numbers.getOne()) && matchAndRead(numbers.getTen()) && isEnd(numbers);
    }

    private boolean matchAndRead(char i) throws IOException {
        if (i == (char) lookahead) {
            read();
            return true;
        } else {
            return false;
        }
    }

    private void read() throws IOException {
        lookahead = lexer.scan();
        buffer.append((char) lookahead);
    }

    private void reset() throws IOException {
        lexer.unread(buffer.toString());
        buffer.delete(0, buffer.length());
        read();
    }
}
