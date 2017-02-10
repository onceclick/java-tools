package com.janosgyerik.utils.files.csv.writing;

import java.io.*;
import java.util.Collection;

public class CsvWriters {

    private static final String DEFAULT_SEPARATOR = ",";
    private static final String DEFAULT_NEWLINE = System.getProperty("line.separator");

    private CsvWriters() {
        // utility class, forbidden constructor
    }

    public static class Builder {

        private final Writer writer;
        private String separator = DEFAULT_SEPARATOR;
        private String newline = DEFAULT_NEWLINE;

        private Builder(Writer writer) {
            this.writer = writer;
        }

        private Builder(String path) throws IOException {
            this.writer = new FileWriter(path);
        }

        public Builder separator(String separator) {
            this.separator = separator;
            return this;
        }

        public Builder newline(String newline) {
            this.newline = newline;
            return this;
        }

        public CsvWriter columnsWriter() {
            return CsvWriters.columnsWriter(writer, separator, newline);
        }

        public <T> ObjectWriter<T> objectWriter(Columnizer<T> columnizer) {
            return CsvWriters.objectWriter(writer, separator, newline, columnizer);
        }

        public <T> void write(Collection<T> collection, Columnizer<T> columnizer) throws IOException {
            CsvWriters.write(writer, separator, newline, collection, columnizer);
        }
    }

    public static Builder builder(Writer writer) {
        return new Builder(writer);
    }

    public static Builder builder(String path) throws IOException {
        return new Builder(path);
    }

    private static class AbstractCsvWriter implements CsvWriter {

        private final Writer writer;
        private final String separator;
        private final String newline;

        private AbstractCsvWriter(Writer writer, String separator, String newline) {
            this.writer = writer;
            this.separator = separator;
            this.newline = newline;
        }

        @Override
        public void writeLine(Object... columns) throws IOException {
            writer.append(toCsv(columns));
            writer.append(newline);
            writer.flush();
        }

        @Override
        public void close() throws IOException {
            writer.close();
        }

        private String toCsv(Object[] columns) {
            StringBuilder builder = new StringBuilder();
            builder.append(columns[0]);
            for (int i = 1; i < columns.length; ++i) {
                builder.append(separator).append(columns[i]);
            }
            return builder.toString();
        }
    }

    private static class ColumnsWriter extends AbstractCsvWriter implements CsvWriter {
        private ColumnsWriter(Writer writer, String separator, String newline) {
            super(writer, separator, newline);
        }
    }

    private static class ObjectWriterImpl<T> extends AbstractCsvWriter implements ObjectWriter<T> {

        private final Columnizer<T> columnizer;

        private ObjectWriterImpl(Writer writer, String separator, String newline, Columnizer<T> columnizer) {
            super(writer, separator, newline);
            this.columnizer = columnizer;
        }

        @Override
        public void writeHeader() throws IOException {
            writeLine(columnizer.getHeaders());
        }

        @Override
        public void writeObject(T object) throws IOException {
            writeLine(columnizer.getValues(object));
        }
    }

    public static CsvWriter columnsWriter(Writer writer, String separator, String newline) {
        return new ColumnsWriter(writer, separator, newline);
    }

    public static <T> ObjectWriter<T> objectWriter(Writer writer, String separator, String newline,
                                                   Columnizer<T> columnizer) {
        return new ObjectWriterImpl<>(writer, separator, newline, columnizer);
    }

    public static <T> void write(Writer writer, String separator, String newline, Collection<T> collection,
                                 Columnizer<T> columnizer) throws IOException {
        ObjectWriter<T> objectWriter = objectWriter(writer, separator, newline, columnizer);
        objectWriter.writeLine(columnizer.getHeaders());
        for (T item : collection) {
            objectWriter.writeObject(item);
        }
        objectWriter.close();
    }
}
