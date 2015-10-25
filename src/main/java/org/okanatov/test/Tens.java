package org.okanatov.test;

public class Tens implements Numbers {
    public char getOne() {
        return 'X';
    }

    public char getFive() {
        return 'L';
    }

    public char getTen() {
        return 'C';
    }

    public boolean isEnd(int i) {
        return i != getOne() && i != getFive() && i != getTen();
    }
}
