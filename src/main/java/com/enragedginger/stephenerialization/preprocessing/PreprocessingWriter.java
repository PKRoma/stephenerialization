package com.enragedginger.stephenerialization.preprocessing;

import java.io.IOException;
import java.io.Writer;

/**
 * Wraps a Writer instance in order to add convenience methods.
 * @author Stephen Hopper
 */
public class PreprocessingWriter {

    private Writer writer;
    private int indentation;
    private static final String TAB = "    ";

    public PreprocessingWriter(Writer writer) {
        this.writer = writer;
    }

    public int getIndentation() {
        return indentation;
    }

    public void setIndentation(int indentation) {
        this.indentation = Math.max(0, indentation);
    }

    public void indent() {
        indentation++;
    }

    public void deindent() {
        indentation--;
    }

    public void resetIndentation() {
        indentation = 0;
    }

    public void writeLine(String line) throws IOException {
        final StringBuffer buffy = new StringBuffer();
        for (int i = 0; i < indentation; i++) {
            buffy.append(TAB);
        }
        buffy.append(line);
        buffy.append("\n");
        writer.write(buffy.toString());
    }

    public void close() throws IOException {
        writer.close();
    }

}
