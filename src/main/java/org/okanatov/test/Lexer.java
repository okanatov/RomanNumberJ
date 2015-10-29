package org.okanatov.test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Lexer {
    private ArrayList<String> tokens = new ArrayList<>();

    private InputStream source = null;
    private Lexer lexer = null;
    private String replacingString;

    public Lexer(String source, String string) throws IOException {
        this.source = new ByteArrayInputStream(source.getBytes());
        this.replacingString = string;
    }

    public Lexer(Lexer lexer, String string) throws IOException {
        this.lexer = lexer;
        this.replacingString = string;
    }

    public InputStream read() throws IOException {
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);
        PrintStream printStream = new PrintStream(pipedOutputStream);

        StringBuilder buffer = new StringBuilder("");
        DataInputStream inputStream = new DataInputStream(source != null ? source : lexer.read());

        while (tokens.isEmpty()) {
            int ch;
            if ((ch = inputStream.read()) != -1) {
                buffer.append((char) ch);
                if (buffer.toString().contains(replacingString)) {
                    int idx = buffer.indexOf(replacingString);
                    tokens.add(buffer.substring(0, idx));
                    tokens.add("[" + replacingString + "]");
                }
            }
            if (ch == -1)
                tokens.add(buffer.toString());
        }

        removeSpacesFromTokensArray();
        if (!tokens.isEmpty())
            printStream.print(tokens.remove(0));
        printStream.close();

        return pipedInputStream;
    }

    private void removeSpacesFromTokensArray() {
        Iterator<String> iterator = tokens.iterator();
        while (iterator.hasNext()) {
            String object = iterator.next();
            if (object.equals("") || object.equals(" "))
                iterator.remove();
        }
    }
}
