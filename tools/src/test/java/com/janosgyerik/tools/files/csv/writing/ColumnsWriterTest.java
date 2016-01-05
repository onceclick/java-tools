package com.janosgyerik.tools.files.csv.writing;

import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class ColumnsWriterTest {
    @Test
    public void test_comma_separated() throws IOException {
        StringWriter stringWriter = new StringWriter();
        CsvWriter writer = CsvWriters.builder().writer(stringWriter).columnsWriter();
        writer.writeLine("Name", "Age");
        assertEquals("Name,Age\n", stringWriter.toString());
    }

    @Test
    public void test_pipe_separated() throws IOException {
        StringWriter stringWriter = new StringWriter();
        CsvWriter writer = CsvWriters.builder().writer(stringWriter).separator("|").columnsWriter();
        writer.writeLine("Name", "Age");
        assertEquals("Name|Age\n", stringWriter.toString());
    }

    @Test
    public void test_multiline() throws IOException {
        StringWriter stringWriter = new StringWriter();
        CsvWriter writer = CsvWriters.builder().writer(stringWriter).columnsWriter();
        writer.writeLine("Name", "Age");
        writer.writeLine("Jack", 21);
        assertEquals("Name,Age\nJack,21\n", stringWriter.toString());
    }
}