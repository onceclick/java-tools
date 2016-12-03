package com.janosgyerik.utils.misc;

import java.io.*;

public class IOUtils {

    private static final int EOF = -1;

    private IOUtils() {
        // utility class, forbidden constructor
    }

    public static boolean contentEquals(File file1, File file2) throws IOException {
        return contentEquals(new FileInputStream(file1), new FileInputStream(file2));
    }

    // http://grepcode.com/file/repo1.maven.org/maven2/commons-io/commons-io/2.4/org/apache/commons/io/IOUtils.java
    public static boolean contentEquals(InputStream input1, InputStream input2) throws IOException {
        BufferedInputStream buf1 = toBufferedInputStream(input1);
        BufferedInputStream buf2 = toBufferedInputStream(input2);

        int ch = buf1.read();
        while (EOF != ch) {
            int ch2 = buf2.read();
            if (ch != ch2) {
                return false;
            }
            ch = buf1.read();
        }

        return buf2.read() == EOF;
    }

    private static BufferedInputStream toBufferedInputStream(InputStream inputStream) {
        if (inputStream instanceof BufferedInputStream) {
            return (BufferedInputStream) inputStream;
        }
        return new BufferedInputStream(inputStream);
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
