package org.okanatov.test;

public class Thousands implements Numbers {
    public char getOne() {
        return 'M';
    }

    public char getFive() {
        return 0;
    }

    public char getTen() {
        return 0;
    }

    public boolean isEnd(int i) {
        return i != getOne() && i != getFive() && i != getTen();
    }
}
