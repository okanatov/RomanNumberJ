package org.okanatov.test;

public class Hundreds implements Numbers {
    public char getOne() {
        return 'C';
    }

    public char getFive() {
        return 'D';
    }

    public char getTen() {
        return 'M';
    }

    public boolean isEnd(int i) {
        return i != getOne() && i != getFive() && i != getTen();
    }
}
