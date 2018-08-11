package com.janosgyerik.utils.range;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

// Validated with https://leetcode.com/problems/range-module/description/
public class RangeModule {

    private final TreeSet<Range> ranges = new TreeSet<>();

    public void add(int left0, int right0) {
        int left = left0;
        int right = right0;

        Iterator<Range> it = ranges.tailSet(new Range(0, left - 1)).iterator();

        while (it.hasNext()) {
            Range range = it.next();
            if (right < range.left) {
                break;
            }
            left = Math.min(left, range.left);
            right = Math.max(right, range.right);
            it.remove();
        }

        ranges.add(new Range(left, right));
    }

    public void remove(int left, int right) {
        Iterator<Range> it = ranges.tailSet(new Range(0, left)).iterator();
        List<Range> parts = new ArrayList<>();

        while (it.hasNext()) {
            Range range = it.next();
            if (right < range.left) {
                break;
            }
            if (range.left < left) {
                parts.add(new Range(range.left, left));
            }
            if (right < range.right) {
                parts.add(new Range(right, range.right));
            }
            it.remove();
        }

        ranges.addAll(parts);
    }

    public boolean contains(int left, int right) {
        Range range = ranges.higher(new Range(0, left));
        return range != null && range.left <= left && right <= range.right;
    }

    private static class Range implements Comparable<Range> {
        final int left;
        final int right;

        Range(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Range other) {
            if (right == other.right) return left - other.left;
            return right - other.right;
        }
    }
}
