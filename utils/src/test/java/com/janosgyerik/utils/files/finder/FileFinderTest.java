package com.janosgyerik.utils.files.finder;

import com.janosgyerik.utils.misc.FileUtils;
import com.janosgyerik.utils.misc.TestUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public abstract class FileFinderTest {

  private File tempDir;

  abstract FileFinder getFileFinder();

  protected abstract List<String> getMatchingNames(int num);

  protected abstract List<String> getNonMatchingNames(int num);

  @Before
  public void setUp() throws IOException {
    tempDir = FileUtils.createTempDir();
  }

  @After
  public void tearDown() throws IOException {
    FileUtils.deleteRecursively(tempDir);
    tempDir = null;
  }

  private File createTempFile(String filename) {
    return new File(tempDir, filename);
  }

  private void createTempFiles(List<String> filenames) throws IOException {
    TestUtils.createTempFiles(tempDir, filenames);
  }

  @Test
  public void testNonexistentDir() {
    assertEquals(0, getFileFinder().apply(new File("nonexistent")).size());
  }

  @Test
  public void testInvalidPath() {
    assertEquals(0, getFileFinder().apply(new File("!@#$%^&*()")).size());
  }

  @Test
  public void testPathIsFile() throws IOException {
    assertEquals(0, getFileFinder().apply(createTempFile("hello")).size());
  }

  @Test
  public void testPathIsEmptyDir() {
    assertEquals(0, getFileFinder().apply(tempDir).size());
  }

  @Test
  public void testMatchOneOfMany() throws IOException {
    createTempFiles(getMatchingNames(1));
    createTempFiles(getNonMatchingNames(3));
    File[] files = tempDir.listFiles();
    assertNotNull(files);
    assertTrue(files.length > 1);
    assertEquals(1, getFileFinder().apply(tempDir).size());
  }

  @Test
  public void testMatchManyOfMany() throws IOException {
    createTempFiles(getMatchingNames(2));
    createTempFiles(getNonMatchingNames(3));
    File[] files = tempDir.listFiles();
    assertNotNull(files);
    assertTrue(files.length > 2);
    assertEquals(2, getFileFinder().apply(tempDir).size());
  }

  @Test
  public void testMatchNoneOfMany() throws IOException {
    createTempFiles(getNonMatchingNames(3));
    File[] files = tempDir.listFiles();
    assertNotNull(files);
    assertTrue(files.length > 0);
    assertEquals(0, getFileFinder().apply(tempDir).size());
  }
}
