package com.janosgyerik.utils.misc;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public final class StringUtils {

    static final String ERR_NULL_PARAM = "none of the parameters should be null";
    static final String ERR_SEARCHSTRINGS_REPLACEMENTS_LENGTH_MISMATCH =
        "there must be the same number of search strings and replacements";
    static final String ERR_NULL_OR_EMPTY_SEARCHSTRING = "there must be no null element or empty search string";
    static final String ERR_NULL_REPLACEMENT = "there must be no null element in replacements";
    static final String ERR_DUPLICATE_SEARCHSTRINGS = "search strings must be distinct";

    private StringUtils() {
        throw new AssertionError("utility class, forbidden constructor");
    }

    /**
     * Replace multiple search strings simultaneously
     *
     * @param text          the source text
     * @param searchStrings search strings to replace
     * @param replacements  texts to replace the corresponding search strings
     * @return new text with search strings replaced
     */
    public static String replace(String text, String[] searchStrings, String[] replacements) {
        validateParams(text, searchStrings, replacements);

        if (searchStrings.length == 0) {
            return text;
        }

        Map<String, String> searchStringsToReplacements = zipToMap(searchStrings, replacements);
        // note: using StringBuffer because of match.appendReplacement
        StringBuffer buffer = new StringBuffer();
        Matcher matcher = buildPattern(searchStrings).matcher(text);
        while (matcher.find()) {
            String pattern = matcher.group();
            String replacement = searchStringsToReplacements.get(pattern);
            matcher.appendReplacement(buffer, replacement);
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    private static Map<String, String> zipToMap(String[] searchStrings, String[] replacements) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < searchStrings.length; ++i) {
            map.put(searchStrings[i], replacements[i]);
        }
        return map;
    }

    private static Pattern buildPattern(String[] searchStrings) {
        return Pattern.compile(Stream.of(searchStrings).map(Pattern::quote).collect(joining("|")));
    }

    private static void validateParams(String text, String[] searchStrings, String[] replacements) {
        if (anyNull(new Object[]{text, searchStrings, replacements})) {
            throw new IllegalArgumentException(ERR_NULL_PARAM);
        }
        if (searchStrings.length != replacements.length) {
            throw new IllegalArgumentException(ERR_SEARCHSTRINGS_REPLACEMENTS_LENGTH_MISMATCH);
        }
        if (anyNullOrEmpty(searchStrings)) {
            throw new IllegalArgumentException(ERR_NULL_OR_EMPTY_SEARCHSTRING);
        }
        if (anyNull(replacements)) {
            throw new IllegalArgumentException(ERR_NULL_REPLACEMENT);
        }
        if (!allDistinct(searchStrings)) {
            throw new IllegalArgumentException(ERR_DUPLICATE_SEARCHSTRINGS);
        }
    }

    private static boolean anyNullOrEmpty(String[] strings) {
        return Stream.of(strings).anyMatch(x -> x == null || x.isEmpty());
    }

    private static boolean anyNull(Object[] args) {
        return Stream.of(args).anyMatch(Objects::isNull);
    }

    private static boolean allDistinct(String[] strings) {
        return Stream.of(strings).distinct().count() == strings.length;
    }
}
