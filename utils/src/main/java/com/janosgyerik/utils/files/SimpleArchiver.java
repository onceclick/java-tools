package com.janosgyerik.utils.files;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleArchiver {

    private final File targetDir;

    public SimpleArchiver(File targetDir) {
        this.targetDir = targetDir;
    }

    public File getTargetWithDate(File source) {
        String datepart = new SimpleDateFormat("yyyyMMdd").format(new Date());
        return new File(targetDir, datepart + "_" + source.getName());
    }

    private void prepareTargetDir() throws IOException {
        if (!targetDir.isDirectory() && !targetDir.mkdirs()) {
            throw new IOException("Could not create target directory: " + targetDir);
        }
    }

    public void copyWithDate(File source) throws IOException {
        prepareTargetDir();
        File target = getTargetWithDate(source);
        copy(source, target);
    }

    public void moveWithDate(File source) throws IOException {
        prepareTargetDir();
        File target = getTargetWithDate(source);
        copy(source, target);
        if (!source.delete()) {
            throw new IOException("Could not delete source: " + source);
        }
    }

    private void copy(File source, File target) throws IOException {
        byte[] buf = new byte[1024];
        try (InputStream input = new FileInputStream(source);
             OutputStream output = new FileOutputStream(target);) {
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        }
    }
}
