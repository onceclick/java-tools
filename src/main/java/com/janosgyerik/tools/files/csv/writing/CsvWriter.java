package com.janosgyerik.tools.files.csv.writing;

import java.io.IOException;

public interface CsvWriter extends AutoCloseable {

    /**
     * Write columns separated by some delimiter
     *
     * @param columns columns to form a record
     */
    void writeLine(Object... columns) throws IOException;

    /**
     * Close the output resource
     */
    void close() throws IOException;
}
