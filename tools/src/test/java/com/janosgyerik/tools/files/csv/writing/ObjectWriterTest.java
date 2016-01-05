package com.janosgyerik.tools.files.csv.writing;

import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class ObjectWriterTest {
    static class Person {
        private final String name;
        private final int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    private final Columnizer<Person> columnizer = new Columnizer<Person>() {
        @Override
        public Object[] getHeaders() {
            return new Object[]{"Name", "Age"};
        }

        @Override
        public Object[] getValues(Person item) {
            return new Object[]{item.name, item.age};
        }
    };

    @Test
    public void test_write_header() throws IOException {
        StringWriter stringWriter = new StringWriter();
        ObjectWriter<Person> writer = CsvWriters.builder().writer(stringWriter).objectWriter(columnizer);
        writer.writeHeader();
        assertEquals("Name,Age\n", stringWriter.toString());
    }

    @Test
    public void test_write_object() throws IOException {
        StringWriter stringWriter = new StringWriter();
        ObjectWriter<Person> writer = CsvWriters.builder().writer(stringWriter).objectWriter(columnizer);
        writer.writeObject(new Person("Jack", 21));
        assertEquals("Jack,21\n", stringWriter.toString());
    }

    @Test
    public void test_write_header_and_two_objects() throws IOException {
        StringWriter stringWriter = new StringWriter();
        ObjectWriter<Person> writer = CsvWriters.builder().writer(stringWriter).objectWriter(columnizer);
        writer.writeHeader();
        writer.writeObject(new Person("Jack", 22));
        writer.writeObject(new Person("Mike", 23));
        assertEquals("Name,Age\nJack,22\nMike,23\n", stringWriter.toString());
    }
}