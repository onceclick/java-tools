package com.janosgyerik.tools.files;

import java.io.File;

class Filename {

    private static final char EXT_SEP = '.';

    private final File file;

    public Filename(File file) {
        this.file = file;
    }

    public String basename() {
        String name = file.getName();
        int dot = name.lastIndexOf(EXT_SEP);
        return dot > -1 ? name.substring(0, dot) : name;
    }

    public String ext() {
        String name = file.getName();
        int dot = name.lastIndexOf(EXT_SEP);
        return dot > -1 ? name.substring(dot + 1) : null;
    }

    public String filename() {
        return file.getName();
    }

    public String dirname() {
        return file.getParentFile().getAbsolutePath();
    }
}