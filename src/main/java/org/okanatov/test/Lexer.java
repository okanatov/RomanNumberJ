package org.okanatov.test;

import java.io.*;
import java.util.ArrayList;

public class Lexer {
    private Reader source = null;
    private Lexer lexer = null;
    private String replacingString;
    private PipedReader pipedReader;
    private PrintWriter printWriter;
    private ArrayList<String> tokens = new ArrayList<>();

    public Lexer(Reader source, String string) throws IOException {
        this.source = source;
        this.replacingString = string;

        PipedWriter pipedWriter = new PipedWriter();
        pipedReader = new PipedReader(pipedWriter);
        printWriter = new PrintWriter(pipedWriter);
    }

    public Lexer(Lexer lexer, String string) throws IOException {
        this.lexer = lexer;
        this.replacingString = string;

        PipedWriter pipedWriter = new PipedWriter();
        pipedReader = new PipedReader(pipedWriter);
        printWriter = new PrintWriter(pipedWriter);
    }

    public Reader scan() throws IOException {
        if (source != null)
            return scanFromReader();
        else
            return scanFromLexer();
    }

    private Reader scanFromReader() throws IOException {
        if (tokens.isEmpty()) {
            StringBuilder buffer = new StringBuilder("");

            while (tokens.isEmpty()) {
                int ch = 0;
                if (source.ready()) {
                    ch = source.read();
                    if (ch != ' ')
                        buffer.append((char) ch);
                    if (buffer.toString().contains(replacingString)) {
                        int idx = buffer.indexOf(replacingString);
                        tokens.add(buffer.substring(0, idx));
                        tokens.add("[" + replacingString + "]");
                        for (String s : tokens)
                            if (s.equals(""))
                                tokens.remove(s);
                        // TODO: buffer needs cleaning
                        buffer = new StringBuilder("");
                    }
                }
                if (ch == -1) {
                    if (buffer.length() != 0)
                        tokens.add(buffer.toString());
                }
            }
        }
        if (!tokens.isEmpty()) {
            String result = tokens.remove(0);
            printWriter.print(result);
        }

        return pipedReader;
    }

    private Reader scanFromLexer() throws IOException {
        if (tokens.isEmpty()) {
            Reader reader = lexer.scan();
            StringBuilder buffer = new StringBuilder("");

            while (tokens.isEmpty()) {
                int ch;
                if (reader.ready()) {
                    ch = reader.read();
                    if (ch != ' ')
                        buffer.append((char) ch);
                    if (buffer.toString().contains(replacingString)) {
                        int idx = buffer.indexOf(replacingString);
                        tokens.add(buffer.substring(0, idx));
                        tokens.add("[" + replacingString + "]");
                        for (String s : tokens)
                            if (s.equals(""))
                                tokens.remove(s);
                        // TODO: buffer needs cleaning
                        buffer = new StringBuilder("");
                    }
                }
                if (!reader.ready()) {
                    if (buffer.length() != 0)
                        tokens.add(buffer.toString());
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
