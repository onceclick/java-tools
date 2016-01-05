package com.janosgyerik.tools.files.finder;

import java.io.File;
import java.util.List;

public interface FileFinder {
    /**
     * Find files in a directory, typically filtered by one or more predicates.
     *
     * @param basedir Base directory
     * @return list of files found
     */
    List<File> findFiles(File basedir);
}
