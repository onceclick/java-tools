package com.janosgyerik.demo.csv.writing;

import com.janosgyerik.demo.company.Person;
import com.janosgyerik.utils.files.csv.writing.Columnizer;
import com.janosgyerik.utils.files.csv.writing.CsvWriter;
import com.janosgyerik.utils.files.csv.writing.CsvWriters;
import com.janosgyerik.utils.files.csv.writing.ObjectWriter;
import com.janosgyerik.utils.misc.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

public class CsvWriterDemo {

    List<Person> persons = Arrays.asList(new Person("Jack", 25), new Person("Mike", 29));

    Columnizer<Person> columnizer = new Columnizer<Person>() {
        @Override
        public Object[] getHeaders() {
            return new Object[]{"name", "age"};
        }

        @Override
        public Object[] getValues(Person item) {
            return new Object[]{item.name, item.age};
        }
    };

    public static void main(String[] args) throws IOException {
        CsvWriterDemo demo = new CsvWriterDemo();
        demo.writeColumnsDemo();
        demo.writeObjectsDemo();
        demo.writeToFileDemo();
        demo.writeToBufferDemo();
        demo.writeCollectionDemo();
    }

    private void writeColumnsDemo() throws IOException {
        printHeader("writeColumnsDemo");
        CsvWriter writer = CsvWriters.builder().columnsWriter();
        writer.writeLine("name", "age");
        Person person = persons.get(0);
        writer.writeLine(person.name, person.age);
    }

    private void writeObjectsDemo() throws IOException {
        printHeader("writeObjectsDemo");
        ObjectWriter<Person> writer = CsvWriters.builder().objectWriter(columnizer);
        writer.writeHeader();
        writer.writeObject(persons.get(0));
    }

    private void writeToBufferDemo() throws IOException {
        printHeader("writeToBufferDemo");

        StringWriter writer = new StringWriter();
        CsvWriters.builder().writer(writer).write(persons, columnizer);

        System.out.println(writer.toString());
    }

    private void writeToFileDemo() throws IOException {
        printHeader("writeToFileDemo");

        String path = FileUtils.createTempFile().getPath();
        CsvWriters.builder().path(path).write(persons, columnizer);

        System.out.println(FileUtils.read(new File(path)));
    }

    private void writeCollectionDemo() throws IOException {
        printHeader("writeCollectionDemo");
        CsvWriters.builder().write(persons, columnizer);
    }

    private void printHeader(String label) {
        System.out.println("---- " + label + " ----");
    }
}
