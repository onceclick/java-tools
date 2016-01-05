package com.janosgyerik.tools.files.finder;


import com.janosgyerik.tools.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class DateMatchingFileFinderTest extends FileFinderTest {

	@Override
	FileFinder getFileFinder() {
		return FileFinders.dateFormatMatching("'fund_'yyyyMMdd", DateUtils.create(2015, 2, 17));
	}

	@Override
	protected List<String> getMatchingNames(int num) {
		List<String> names = new ArrayList<String>(num);
		for (int i = 0; i < num; ++i) {
			names.add("fund_20150217-" + i + ".csv");
		}
		return names;
	}

	@Override
	protected List<String> getNonMatchingNames(int num) {
		List<String> names = new ArrayList<String>(num);
		for (int i = 0; i < num; ++i) {
			names.add("hello" + i + ".csv");
		}
		return names;
	}
}
