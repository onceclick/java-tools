package com.janosgyerik.tools.files.finder;

import com.janosgyerik.tools.files.filter.FileFilters;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FileFinders {

	private FileFinders() {
		// utility class, forbidden constructor
	}

	private static class FileFinderImpl implements FileFinder {
		private final FileFilter fileFilter;

		public FileFinderImpl(FileFilter fileFilter) {
			this.fileFilter = fileFilter;
		}

		@Override
		public List<File> findFiles(File basedir) {
			File[] files = basedir.listFiles(fileFilter);
			return files == null ? Collections.<File>emptyList() : Arrays.asList(files);
		}
	}

	public static FileFinder stringMatching(String pattern) {
		return new FileFinderImpl(FileFilters.stringMatching(pattern));
	}

	public static FileFinder regexMatching(String regex) {
		return new FileFinderImpl(FileFilters.regexMatching(regex));
	}

	public static FileFinder dateFormatMatching(String format, Date date) {
		return new FileFinderImpl(FileFilters.dateFormatMatching(format, date));
	}

	public static FileFinder multiCriteriaMatching(FileFilter ... fileFilters) {
		return new FileFinderImpl(FileFilters.multiCriteriaMatching(fileFilters));
	}
}
