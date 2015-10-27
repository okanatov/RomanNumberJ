package org.okanatov.test;

import java.io.IOException;
import java.io.Reader;

public class Parser {
    private final Reader reader;
    private int lookahead = -1;

    private final Numbers thousands = new Thousands();
    private final Numbers hundreds = new Hundreds();
    private final Numbers tens = new Tens();
    private final Numbers ones = new Ones();

    public Parser(Reader reader) throws IOException {
        this.reader = reader;
        this.lookahead = reader.read();
    }

    public int evaluate() throws IOException {
        int thousand = romanNumber(thousands);
        if (!isEnd(thousands))
            throw new Error("parser error");

        int hundred = romanNumber(hundreds);
        if (!isEnd(hundreds))
            throw new Error("parser error");

        int ten = romanNumber(tens);
        if (!isEnd(tens))
            throw new Error("parser error");

        int one = romanNumber(ones);
        if (!isEnd(ones))
            throw new Error("parser error");

        return 1000 * thousand + 100 * hundred + 10 * ten + one;
    }

    private int romanNumber(Numbers numbers) throws IOException {
        if (match(numbers.getOne()))
            return 1 + romanNumber1(numbers);
        else if (match(numbers.getFive()))
            return 5 + romanNumber2(numbers) + romanNumber2(numbers) + romanNumber2(numbers);
        else
            return 0;
    }

    private int romanNumber1(Numbers numbers) throws IOException {
        if (match(numbers.getOne()))
            return 1 + romanNumber2(numbers);
        else if (match(numbers.getFive()))
            return 3;
        else if (match(numbers.getTen()))
            return 8;
        else
            return 0;
    }

    private int romanNumber2(Numbers numbers) throws IOException {
        if (match(numbers.getOne()))
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

    private boolean isEnd(Numbers numbers) {
        return numbers.isEnd(lookahead);
    }
}
