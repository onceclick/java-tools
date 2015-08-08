package com.janosgyerik.tools.files.filter;

import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class FileFilters {

	private FileFilters() {
		// utility class, forbidden constructor
	}

	public static FileFilter stringMatching(final String pattern) {
		return new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.getName().contains(pattern);
			}
		};
	}

	public static FileFilter regexMatching(String regex) {
		final Pattern pattern = Pattern.compile(regex);
		return new FileFilter() {
			@Override
			public boolean accept(File file) {
				return pattern.matcher(file.getName()).find();
			}
		};
	}

	public static FileFilter dateFormatMatching(String format, Date date) {
		String pattern = new SimpleDateFormat(format).format(date);
		return stringMatching(pattern);
	}

	public static FileFilter multiCriteriaMatching(final FileFilter ... fileFilters) {
		return new FileFilter() {
			@Override
			public boolean accept(File file) {
				for (FileFilter fileFilter : fileFilters) {
					if (!fileFilter.accept(file)) {
						return false;
					}
				}
				return true;
			}
		};
	}
}
