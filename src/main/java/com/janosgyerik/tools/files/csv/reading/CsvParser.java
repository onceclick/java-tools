package com.janosgyerik.tools.files.csv.reading;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CsvParser {

    private static final String DEFAULT_SEPARATOR = ",";

    private static abstract class AbstractCsvParser {

        private final Pattern pattern;

        private AbstractCsvParser(String separator) {
            this.pattern = Pattern.compile("\\s*" + separator + "\\s*");
        }

        abstract String nextLine() throws IOException;

        public <T> List<T> parseLines(RowMapper<T> mapper) throws IOException {
            List<T> lines = new ArrayList<>();
            {
                String line;
                while ((line = nextLine()) != null) {
                    String[] cols = pattern.split(line.trim());
                    if (mapper.isValidRow(cols)) {
                        lines.add(mapper.mapRow(cols));
                    }
                }
            }
            return lines;
        }
    }

    private static class ScannerCsvParser extends AbstractCsvParser {

        private final Scanner scanner;

        private ScannerCsvParser(Scanner scanner, String separator) {
            super(separator);
            this.scanner = scanner;
        }

        @Override
        String nextLine() {
            try {
                return scanner.nextLine();
            } catch (NoSuchElementException e) {
                return null;
            }
        }
    }

    private static class FileCsvParser extends AbstractCsvParser {

        private final BufferedReader reader;

        private FileCsvParser(File file, String separator) throws FileNotFoundException {
            super(separator);
            reader = new BufferedReader(new FileReader(file));
        }

        @Override
        String nextLine() throws IOException {
            String line = reader.readLine();
            if (line == null) {
                reader.close();
            }
            return line;
        }
    }

    private CsvParser() {
        // utility class, forbidden constructor
    }

    public static <T> List<T> objectsFromFile(String path, RowMapper<T> mapper) throws IOException {
        return objectsFromFile(path, DEFAULT_SEPARATOR, mapper);
    }

    public static <T> List<T> objectsFromFile(String path, String separator, RowMapper<T> mapper) throws IOException {
        return objectsFromFile(new File(path), separator, mapper);
    }

    public static <T> List<T> objectsFromFile(File file, RowMapper<T> mapper) throws IOException {
        return objectsFromFile(file, DEFAULT_SEPARATOR, mapper);
    }

    public static <T> List<T> objectsFromFile(File file, String separator, RowMapper<T> mapper) throws IOException {
        return new FileCsvParser(file, separator).parseLines(mapper);
    }

    public static <T> List<T> objectsFromScanner(Scanner scanner, RowMapper<T> mapper) throws IOException {
        return objectsFromScanner(scanner, DEFAULT_SEPARATOR, mapper);
    }

    public static <T> List<T> objectsFromScanner(Scanner scanner, String separator, RowMapper<T> mapper) throws IOException {
        return new ScannerCsvParser(scanner, separator).parseLines(mapper);
    }

    public static <T> List<T> objectsFromText(String text, RowMapper<T> mapper) throws IOException {
        return objectsFromText(text, DEFAULT_SEPARATOR, mapper);
    }

    public static <T> List<T> objectsFromText(String text, String separator, RowMapper<T> mapper) throws IOException {
        return objectsFromScanner(new Scanner(text), separator, mapper);
    }

}
