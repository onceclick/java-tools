package com.janosgyerik.utils.files.finder;

import java.io.File;
import java.util.List;
import java.util.function.Function;

/**
 * Find files in a directory, typically filtered by one or more predicates.
 */
public interface FileFinder extends Function<File, List<File>> {
}
