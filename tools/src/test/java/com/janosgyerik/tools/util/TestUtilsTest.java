package com.janosgyerik.tools.util;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestUtilsTest {

    @Test(expected = IOException.class)
    public void testSetupCleanDir_Throws_IfPathIsFile() throws IOException {
        File tempfile = FileUtils.createTempFile();
        assertTrue(tempfile.isFile());
        TestUtils.setupCleanDir(tempfile);
    }

    @Test(expected = IOException.class)
    public void testSetupCleanDir_Throws_IfPermissionDenied() throws IOException {
        TestUtils.setupCleanDir(new File("/nonexistent"));
    }

    @Test
    public void testSetupCleanDir_OK_IfPathIsDir() throws IOException {
        File tempdir = FileUtils.createTempDir();
        assertTrue(tempdir.isDirectory());
        TestUtils.setupCleanDir(tempdir);
        assertTrue(tempdir.isDirectory());
    }

    @Test
    public void testSetupCleanDir_WipesNonEmptyDir() throws IOException {
        File tempdir = FileUtils.createTempDir();
        assertTrue(tempdir.isDirectory());
        assertEquals(0, tempdir.list().length);
        assertTrue(new File(tempdir, "dummy").createNewFile());
        assertEquals(1, tempdir.list().length);
        TestUtils.setupCleanDir(tempdir);
        assertEquals(0, tempdir.list().length);
    }

    @Test
    public void testSetupCleanDir_CreatesDir() throws IOException {
        File tempdir = FileUtils.createTempDir();
        assertTrue(tempdir.delete());
        assertFalse(tempdir.exists());
        TestUtils.setupCleanDir(tempdir);
        assertTrue(tempdir.isDirectory());
    }

    @Test
    public void testCreateTestFiles() throws IOException {
        File basedir = FileUtils.createTempDir();
        List<String> filenames = Arrays.asList("file1.txt", "file2.csv", "file3.xml");
        TestUtils.createTempFiles(basedir, filenames);
        assertEquals(filenames, Arrays.asList(basedir.list()));

        for (String filename : basedir.list()) {
            assertTrue(new File(basedir, filename).isFile());
        }
    }

    @Test
    public void testCreateTestDirs() throws IOException {
        File basedir = FileUtils.createTempDir();
        String[] dirnames = {"dir1", "dir2", "dir3"};
        TestUtils.createTestDirs(basedir, dirnames);
        assertArrayEquals(dirnames, basedir.list());

        for (String filename : basedir.list()) {
            assertTrue(new File(basedir, filename).isDirectory());
        }
    }
}
