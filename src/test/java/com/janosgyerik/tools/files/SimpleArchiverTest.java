package com.janosgyerik.tools.files;

import com.janosgyerik.tools.files.SimpleArchiver;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleArchiverTest {

    SimpleArchiver archiver;

    @Before
    public void setUp() {
        File targetDir = new File(System.getProperty("java.io.tmpdir"));
        archiver = new SimpleArchiver(targetDir);
    }

    private File createSampleFile() throws IOException {
        return File.createTempFile("sample", null);
    }

    @Test
    public void testCopyWithDate() throws IOException {
        File source = createSampleFile();
        assertTrue(source.isFile());

        File target = archiver.getTargetWithDate(source);
        assertFalse(target.isFile());

        archiver.copyWithDate(source);
        assertTrue(target.isFile());
        assertTrue(source.isFile());
    }

    @Test
    public void testMoveWithDate() throws IOException {
        File source = createSampleFile();
        assertTrue(source.isFile());

        File target = archiver.getTargetWithDate(source);
        assertFalse(target.isFile());

        archiver.moveWithDate(source);
        assertTrue(target.isFile());
        assertFalse(source.isFile());
    }
}
