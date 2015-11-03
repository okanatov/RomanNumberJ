package org.okanatov.test;

public class Ones implements Numbers {
    public char getOne() {
        return 'I';
    }

    public char getFive() {
        return 'V';
    }

    public char getTen() {
        return 'X';
    }

    public boolean isEnd(int i) {
        /* Always false because "ones" must be the latest characters in string representing
         * roman number and all previous characters from "ones" must already be parsed (isEnd() is
         * the latest called method when parsing the string). So, in case of more characters,
         * this method must return false.
         */
        return false;
    }
}
