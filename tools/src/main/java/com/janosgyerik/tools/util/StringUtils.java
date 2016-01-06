package com.janosgyerik.tools.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public final class StringUtils {

    private StringUtils() {
        throw new AssertionError("utility class, forbidden constructor");
    }

    public static final String ERR_NULL_PARAM = "none of the parameters should be null";
    public static final String ERR_SEARCHSTRINGS_REPLACEMENTS_LENGTH_MISMATCH =
            "there must be the same number of search strings and replacements";
    public static final String ERR_NULL_OR_EMPTY_SEARCHSTRING = "there must be no null element or empty search string";
    public static final String ERR_NULL_REPLACEMENT = "there must be no null element in replacements";
    public static final String ERR_DUPLICATE_SEARCHSTRINGS = "search strings must be distinct";

    /**
     * Replace multiple search strings simultaneously
     *
     * @param text the source text
     * @param searchStrings search strings to replace
     * @param replacements texts to replace the corresponding search strings
     * @return new text with search strings replaced
     */
    public static String replace(String text, String[] searchStrings, String[] replacements) {
        validateParams(text, searchStrings, replacements);

        StringBuffer buffer = new StringBuffer();
        Pattern pattern = Pattern.compile(Stream.of(searchStrings).collect(joining("|")));
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String match = matcher.group();
            for (int i = 0; i < searchStrings.length; ++i) {
                if (match.equals(searchStrings[i])) {
                    matcher.appendReplacement(buffer, replacements[i]);
                    break;
                }
            }
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    private static void validateParams(String text, String[] searchStrings, String[] replacements) {
        if (text == null || searchStrings == null || replacements == null) {
            throw new IllegalArgumentException(ERR_NULL_PARAM);
        }
        if (searchStrings.length != replacements.length) {
            throw new IllegalArgumentException(ERR_SEARCHSTRINGS_REPLACEMENTS_LENGTH_MISMATCH);
        }
        if (searchStrings.length == 0) {
            return;
        }
        if (anyNullOrEmpty(searchStrings)) {
            throw new IllegalArgumentException(ERR_NULL_OR_EMPTY_SEARCHSTRING);
        }
        if (anyNull(replacements)) {
            throw new IllegalArgumentException(ERR_NULL_REPLACEMENT);
        }
        if (containsDuplicates(searchStrings)) {
            throw new IllegalArgumentException(ERR_DUPLICATE_SEARCHSTRINGS);
        }
    }

    private static boolean anyNullOrEmpty(String[] strings) {
        return Stream.of(strings).anyMatch(x -> x == null || x.isEmpty());
    }

    private static boolean anyNull(String[] strings) {
        return Stream.of(strings).anyMatch(x -> x == null);
    }

    private static boolean containsDuplicates(String[] strings) {
        return Stream.of(strings).distinct().count() != strings.length;
    }
}
