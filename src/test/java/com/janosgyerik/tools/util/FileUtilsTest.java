package com.janosgyerik.tools.util;

import com.janosgyerik.tools.util.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class FileUtilsTest {

    @Test
    public void testCreateTempFile() throws IOException {
        File tempFile = FileUtils.createTempFile();
        assertTrue(tempFile.isFile());
        FileUtils.deleteFile(tempFile);
    }

    @Test
    public void testDeleteFile() throws IOException {
        File tempFile = FileUtils.createTempFile();
        assertTrue(tempFile.isFile());
        FileUtils.deleteFile(tempFile);
        assertFalse(tempFile.exists());
    }

    @Test
    public void testDeleteRecursively_DeletesFile() throws IOException {
        File file = FileUtils.createTempFile();
        assertTrue(file.exists());
        FileUtils.deleteRecursively(file);
        assertFalse(file.exists());
    }

    @Test
    public void test_writing_and_reading() throws IOException {
        File file = FileUtils.createTempFile();
        String origContent = "hello world";
        FileUtils.write(file, origContent);
        String readContent = FileUtils.read(file);
        assertEquals(origContent, readContent);
    }

}
