package com.janosgyerik.tools.util;

import com.janosgyerik.tools.util.ClassPathUtils;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertTrue;

public class ClassPathUtilsTest {

    @Test
    public void testGetJarUrl() {
        URL url = ClassPathUtils.getJarUrl(ClassPathUtils.class);
        assertTrue(url.toString().matches("^file:/.*/classes/"));
    }

    @Test
    public void testGetClassUrl() {
        URL url = ClassPathUtils.getClassUrl(ClassPathUtils.class);
        assertTrue(url.toString().matches("^file:/.*/classes/.*.class$"));
    }

}