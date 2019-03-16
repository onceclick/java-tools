package com.janosgyerik.utils.files.filter;

import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public final class FileFilters {

  private FileFilters() {
    throw new AssertionError("utility class, forbidden constructor");
  }

  public static FileFilter stringMatching(final String pattern) {
    return file -> file.getName().contains(pattern);
  }

  public static FileFilter regexMatching(String regex) {
    final Pattern pattern = Pattern.compile(regex);
    return file -> pattern.matcher(file.getName()).find();
  }

  public static FileFilter dateFormatMatching(String format, Date date) {
    String pattern = new SimpleDateFormat(format).format(date);
    return stringMatching(pattern);
  }

  public static FileFilter multiCriteriaMatching(final FileFilter... fileFilters) {
    return file -> {
      for (FileFilter fileFilter : fileFilters) {
        if (!fileFilter.accept(file)) {
          return false;
        }
      }
      return true;
    };
  }
}
