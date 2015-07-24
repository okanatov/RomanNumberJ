package org.okanatov.test;

public class Lexer {
    private char[] buffer;
    private int curPos = 0;

    public Lexer(String str) {
        this.buffer = str.toCharArray();
    }

    public char scan() {
        if (curPos < buffer.length)
            return buffer[curPos++];
        else
            return '$';
    }

    public int mark() {
        return curPos;
    }

    public void reset(int pos) {
        curPos = pos;
    }
}
