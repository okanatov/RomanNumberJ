package org.okanatov.test;

import java.io.*;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Lexer {
    private Reader source;
    private String replacingString;
    private PipedWriter pipedWriter = new PipedWriter();
    private PipedReader pipedReader;
    private PrintWriter printWriter;
    private ArrayList<String> tokens = new ArrayList<>();

    public Lexer(Reader source, String string) throws IOException {
        this.source = source;
        this.replacingString = string;
        pipedReader = new PipedReader(pipedWriter);
        printWriter = new PrintWriter(pipedWriter);
    }

    public static Lexer createFromReader(Reader reader, String string) throws IOException {
        return new Lexer(reader, string);
    }

    public Reader scan() throws IOException {
        if (tokens.size() <= 0) {
            StringBuilder buffer = new StringBuilder("");

            while (tokens.size() == 0) {
                int ch;
                if ((ch = source.read()) != -1) {
                    buffer.append((char) ch);
                    if (buffer.toString().contains(replacingString)) {
                        int idx = buffer.indexOf(replacingString);
                        tokens.add(buffer.substring(0, idx));
                        tokens.add("[" + replacingString + "]");
                        tokens.removeIf(Predicate.isEqual(""));
                    }
                }
                if (ch == -1) {
                    tokens.add(buffer.toString());
                    pipedWriter.close();
                    printWriter.close();
                }
            }
        }
        if (!tokens.isEmpty()) {
            String result = tokens.remove(0);
            printWriter.print(result);
        }

        return pipedReader;
    }
}
