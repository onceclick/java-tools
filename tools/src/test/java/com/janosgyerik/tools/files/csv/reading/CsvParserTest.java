package com.janosgyerik.tools.files.csv.reading;

import com.janosgyerik.tools.files.csv.reading.CsvParser;
import com.janosgyerik.tools.files.csv.reading.RowMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CsvParserTest {

    private static RowMapper<String> firstColMapper = new RowMapper<String>() {
        @Override
        public String mapRow(String[] cols) {
            return cols[0];
        }

        @Override
        public boolean isValidRow(String[] cols) {
            return true;
        }
    };

    @Test
    public void test_1line() throws IOException {
        assertEquals(1, CsvParser.objectsFromText("hello", firstColMapper).size());
    }

    @Test
    public void test_3lines() throws IOException {
        assertEquals(3, CsvParser.objectsFromText("hello\n\nx", firstColMapper).size());
    }

    @Test
    public void test_3lines_ignore_blank() throws IOException {
        RowMapper<String> nonBlankFirstColMapper = new RowMapper<String>() {
            @Override
            public String mapRow(String[] cols) {
                return cols[0];
            }

            @Override
            public boolean isValidRow(String[] cols) {
                return cols.length > 0 && cols[0].length() > 0;
            }
        };

        assertEquals(3, CsvParser.objectsFromText("hello\n\nx", firstColMapper).size());
        assertEquals(2, CsvParser.objectsFromText("hello\n\nx", nonBlankFirstColMapper).size());
    }

    @Test
    public void test_nth_col_mapper() throws IOException {
        class NthColMapper implements RowMapper<String> {
            private final int index;

            NthColMapper(int index) {
                this.index = index;
            }

            @Override
            public String mapRow(String[] cols) {
                return cols[index - 1];
            }

            @Override
            public boolean isValidRow(String[] cols) {
                return cols.length >= index;
            }
        }

        NthColMapper mapper = new NthColMapper(2);
        assertEquals(0, CsvParser.objectsFromText("hello\n\nx", mapper).size());
        assertEquals(1, CsvParser.objectsFromText("hello\na,b\nx", mapper).size());
        assertEquals(1, CsvParser.objectsFromText("hello\na,b,c\nx", mapper).size());
        assertEquals(Arrays.asList("b"), CsvParser.objectsFromText("hello\na,b,c\nx", mapper));
    }
}
