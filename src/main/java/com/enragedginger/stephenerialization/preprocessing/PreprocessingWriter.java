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

    /**
     * Creates a new PreprocessingWriter instance.
     * @param writer The writer to wrap.
     */
    public PreprocessingWriter(Writer writer) {
        this.writer = writer;
    }

    public int getIndentation() {
        return indentation;
    }

    public void setIndentation(int indentation) {
        this.indentation = Math.max(0, indentation);
    }

    /**
     * Indents the writer one more level.
     */
    public void indent() {
        indentation++;
    }

    /**
     * Deindents the writer one level
     */
    public void deindent() {
        setIndentation(indentation - 1);
    }

    /**
     * Resets indentation to zero.
     */
    public void resetIndentation() {
        indentation = 0;
    }

    /**
     * Writes a line out to the writer, complete with indentation.
     * @param line The line to write.
     * @throws IOException If an error occurs.
     */
    public void writeLine(String line) throws IOException {
        final StringBuffer buffy = new StringBuffer();
        for (int i = 0; i < indentation; i++) {
            buffy.append(TAB);
        }
        buffy.append(line);
        buffy.append("\n");
        writer.write(buffy.toString());
    }

    /**
     * Closes the writer.
     * @throws IOException If an error occurs.
     */
    public void close() throws IOException {
        writer.close();
    }

}
