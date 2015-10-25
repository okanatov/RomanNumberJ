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
        return i == -1;
    }
}
