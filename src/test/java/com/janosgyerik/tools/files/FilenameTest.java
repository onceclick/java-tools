package com.janosgyerik.tools.files;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FilenameTest {

    @Test
    public void testAbsolutePathWithExt() throws IOException {
        String prefix = "hello";
        String ext = "txt";
        String filename = prefix + "." + ext;
        File basedir = new File(".");
        File sample = new File(basedir, filename);
        Filename fn = new Filename(sample);
        Assert.assertEquals(ext, fn.ext());
        Assert.assertEquals(prefix, fn.basename());
        Assert.assertEquals(filename, fn.filename());
        Assert.assertEquals(basedir.getAbsolutePath(), fn.dirname());
    }

    @Test
    public void testAbsolutePathWithoutExt() throws IOException {
        String prefix = "hello";
        String filename = prefix;
        File basedir = new File(".");
        File sample = new File(basedir, filename);
        Filename fn = new Filename(sample);
        Assert.assertEquals(null, fn.ext());
        Assert.assertEquals(prefix, fn.basename());
        Assert.assertEquals(filename, fn.filename());
        Assert.assertEquals(basedir.getAbsolutePath(), fn.dirname());
    }
}
