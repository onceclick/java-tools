package com.janosgyerik.utils.files.csv.writing;

import java.io.IOException;
import java.io.StringWriter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ColumnsWriterTest {
  @Test
  public void test_comma_separated() throws IOException {
    StringWriter stringWriter = new StringWriter();
    CsvWriter writer = CsvWriters.builder(stringWriter).columnsWriter();
    writer.writeLine("Name", "Age");
    assertEquals("Name,Age\n", stringWriter.toString());
  }

  @Test
  public void test_pipe_separated() throws IOException {
    StringWriter stringWriter = new StringWriter();
    CsvWriter writer = CsvWriters.builder(stringWriter).separator("|").columnsWriter();
    writer.writeLine("Name", "Age");
    assertEquals("Name|Age\n", stringWriter.toString());
  }

  @Test
  public void test_multiline() throws IOException {
    StringWriter stringWriter = new StringWriter();
    CsvWriter writer = CsvWriters.builder(stringWriter).columnsWriter();
    writer.writeLine("Name", "Age");
    writer.writeLine("Jack", 21);
    assertEquals("Name,Age\nJack,21\n", stringWriter.toString());
  }
}
