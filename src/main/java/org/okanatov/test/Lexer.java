package org.okanatov.test;

import java.io.*;

public class Lexer {
    private Reader source;
    private String replacingString;

    public Lexer(Reader source, String string) {
        this.source = source;
        this.replacingString = string;
    }

    public static Lexer createFromReader(Reader reader, String string) {
        return new Lexer(reader, string);
    }

    public Reader scan() throws IOException {
        PipedWriter pipedWriter = new PipedWriter();
        PipedReader pipedReader = new PipedReader(pipedWriter);
        PrintWriter printWriter = new PrintWriter(pipedWriter);

        int ch;
        while ((ch = source.read()) != -1) {
            printWriter.print((char) ch);
        }

        printWriter.close();
        pipedWriter.close();

        return pipedReader;
    }
}
