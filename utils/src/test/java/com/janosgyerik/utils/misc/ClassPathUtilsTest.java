package com.janosgyerik.utils.misc;

import java.net.URL;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ClassPathUtilsTest {

  // fails when building with ./gradlew build ...
  @Ignore
  @Test
  public void testGetJarUrl() {
    URL url = ClassPathUtils.getJarUrl(ClassPathUtils.class);
    assertTrue(url.toString().matches("^file:/.*/classes/"));
  }

  // fails when building with ./gradlew build ...
  @Ignore
  @Test
  public void testGetClassUrl() {
    URL url = ClassPathUtils.getClassUrl(ClassPathUtils.class);
    assertTrue(url.toString().matches("^file:/.*/classes/.*.class$"));
  }

}
