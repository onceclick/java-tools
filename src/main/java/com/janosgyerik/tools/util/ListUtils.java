package com.janosgyerik.tools.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListUtils {

    private ListUtils() {
        // utility class, forbidden constructor
    }

    public static <T> List<List<T>> partition(List<T> orig, int size) {
        if (orig == null) {
            throw new NullPointerException("The list to partition must not be null");
        }
        if (size < 1) {
            throw new IllegalArgumentException("The target partition size must be 1 or greater");
        }
        int origSize = orig.size();
        List<List<T>> result = new ArrayList<>(origSize / size + 1);
        for (int i = 0; i < origSize; i += size) {
            result.add(orig.subList(i, Math.min(i + size, origSize)));
        }
        return result;
    }

    public static <T> List<T> toList(Iterator<T> iterator) {
        List<T> list = new LinkedList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }
}
