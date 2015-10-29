package org.okanatov.test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Lexer {
    private final ArrayList<String> tokens = new ArrayList<>();
    private final String matchingString;
    private InputStream source;
    private Lexer lexer;

    public Lexer(String source, String matchingString) {
        this(matchingString);
        this.source = new ByteArrayInputStream(source.getBytes());
    }

    public Lexer(Lexer lexer, String matchingString) {
        this(matchingString);
        this.lexer = lexer;
    }

    private Lexer(String matchingString) {
        this.matchingString = matchingString;
    }

    public String readToken() {
        InputStream inputStream = getInputStream();
        if (inputStream == null) return null;

        StringBuilder buffer = new StringBuilder("");
        while (tokens.isEmpty()) {
            try {
                readFromStreamAndCheckForMatchingStringInBuffer(inputStream, buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        removeEmptyTokensFromArray();
        if (!tokens.isEmpty())
            return tokens.remove(0);
        return null;
    }

    private InputStream getInputStream() {
        InputStream inputStream;
        if (source != null) {
            inputStream = new DataInputStream(source);
        } else {
            String source = lexer.readToken();
            inputStream = source != null ? new ByteArrayInputStream(source.getBytes()) : null;
        }
        return inputStream;
    }

    private void readFromStreamAndCheckForMatchingStringInBuffer(InputStream inputStream, StringBuilder buffer) throws IOException {
        int ch;
        if ((ch = inputStream.read()) != -1) {
            buffer.append((char) ch);
            if (buffer.toString().contains(matchingString)) {
                int idx = buffer.indexOf(matchingString);
                tokens.add(buffer.substring(0, idx));
                tokens.add("[" + matchingString + "]");
            }
        }
        if (ch == -1)
            tokens.add(buffer.toString());
    }

    private void removeEmptyTokensFromArray() {
        Iterator<String> iterator = tokens.iterator();
        while (iterator.hasNext()) {
            String object = iterator.next();
            if (object.equals("") || object.equals(" "))
                iterator.remove();
        }
    }
}
