package com.janosgyerik.tools.files.finder;

import java.util.ArrayList;
import java.util.List;

public class StringMatchingFileFinderTest extends FileFinderTest {

    @Override
    protected FileFinder getFileFinder() {
        return FileFinders.stringMatching(".csv");
    }

    @Override
    protected List<String> getMatchingNames(int num) {
        List<String> names = new ArrayList<>(num);
        for (int i = 0; i < num; ++i) {
            names.add("hello" + i + ".csv");
        }
        return names;
    }

    @Override
    protected List<String> getNonMatchingNames(int num) {
        List<String> names = new ArrayList<>(num);
        for (int i = 0; i < num; ++i) {
            names.add("hello" + i + ".txt");
        }
        return names;
    }
}
