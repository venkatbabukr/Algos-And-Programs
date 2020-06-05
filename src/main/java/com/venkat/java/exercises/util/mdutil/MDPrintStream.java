package com.venkat.java.exercises.util.mdutil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MDPrintStream extends PrintStream {

    public static final char BACKSLASH = '\\';

    private Map<Integer, String> translationMap;

    private Set<Integer> escapeChars;

    private void init() {
        translationMap = new HashMap<>();
        translationMap.put((int) '<', "&lt;");
        translationMap.put((int) '>', "&gt;");

        escapeChars = Stream.of(BACKSLASH, '~', '/', '`', '+', '.')
                          .mapToInt(c -> (int) c)
                          .boxed()
                          .collect(Collectors.toSet());
    }

    public MDPrintStream(File mdFile) throws FileNotFoundException {
        super(mdFile);
        this.init();
    }

    @Override
    public void write(int b) {
        // If we have to translate, do that
        String translationStr = translationMap.get(b);
        if (translationStr != null) {
            super.write(translationStr.getBytes(), 0, translationStr.length());
            return;
        }

        // If we have to escape, do that
        if (escapeChars.contains(b)) {
            super.write(BACKSLASH);
            super.write(b);
            return;
        }
    }

}
