package com.janosgyerik.utils.files.finder;

import com.janosgyerik.utils.files.filter.FileFilters;
import com.janosgyerik.utils.misc.DateUtils;
import java.util.ArrayList;
import java.util.List;

public class MultiCriteriaFileFinderTest extends FileFinderTest {
  @Override
  FileFinder getFileFinder() {
    return FileFinders.multiCriteriaMatching(FileFilters.regexMatching("spread-.*-\\d+\\.csv"), FileFilters.dateFormatMatching("dd-MM-yy", DateUtils.create(2015, 2, 18)));
  }

  @Override
  protected List<String> getMatchingNames(int num) {
    List<String> names = new ArrayList<>(num);
    for (int i = 0; i < num; ++i) {
      names.add("spread-18-02-15-" + i + ".csv");
    }
    return names;
  }

  @Override
  protected List<String> getNonMatchingNames(int num) {
    List<String> names = new ArrayList<>(num);
    for (int i = 0; i < num; ++i) {
      names.add("spread-19-02-15-" + i + ".csv");
      names.add("spread-18-02-15-" + i + ".txt");
    }
    return names;
  }
}
