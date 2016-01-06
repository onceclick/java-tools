package com.janosgyerik.tools.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.janosgyerik.tools.util.StringUtils.*;
import static org.junit.Assert.assertEquals;

public class StringUtilsTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test_empty_text() {
        assertEquals("", replace("", new String[]{"foo"}, new String[]{"bar"}));
    }

    @Test
    public void test_empty_searchstrings_and_replacements() {
        assertEquals("", replace("", new String[0], new String[0]));
    }

    @Test
    public void test_replace_one_searchstring_once() {
        String before = "foo";
        String after = "bar";
        assertEquals(after, replace(before, new String[]{before}, new String[]{after}));
    }

    @Test
    public void test_replace_one_searchstring_twice() {
        String before = "foo";
        String after = "bar";
        assertEquals(after + after, replace(before + before, new String[]{before}, new String[]{after}));
    }

    @Test
    public void test_replace_two_searchstrings_simultaneously() {
        String before1 = "foo";
        String after1 = "bar";
        String after2 = "baz";
        assertEquals(after1 + after2, replace(before1 + after1,
                new String[]{before1, after1},
                new String[]{after1, after2}));
    }

    @Test
    public void test_no_matches() {
        String text = "foofoo";
        assertEquals(text, replace(text, new String[]{"notmatching"}, new String[]{"bar"}));
    }

    @Test
    public void test_source_overlap() {
        assertEquals("foovel", replace("applevel", new String[]{"apple", "level"}, new String[]{"foo", "bar"}));
    }

    @Test
    public void test_result_overlap() {
        assertEquals("spacemarvel", replace("applevel",
                new String[]{"apple", "marvel"},
                new String[]{"spacemar", "bar"}));
    }

    @Test
    public void test_replace_multiple_simultaneous_searchstrings() {
        assertEquals("Once upon a foo, there was a bar and a baz, and another bar and a cat.",
                replace("Once upon a baz, there was a foo and a bar, and another foo and a cat.",
                        new String[]{"foo", "bar", "baz"},
                        new String[]{"bar", "baz", "foo"})
        );
    }

    @Test
    public void test_circular_replacement() {
        assertEquals("barfoo", replace("foobar", new String[]{"foo", "bar"}, new String[]{"bar", "foo"}));
    }

    @Test
    public void test_null_text_should_throw() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ERR_NULL_PARAM);

        replace(null, new String[0], new String[0]);
    }

    @Test
    public void test_null_searchstrings_should_throw() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ERR_NULL_PARAM);

        replace("", null, new String[0]);
    }

    @Test
    public void test_null_replacements_should_throw() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ERR_NULL_PARAM);

        replace("", new String[0], null);
    }

    @Test
    public void test_more_replacements_than_searchstrings_should_throw() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ERR_SEARCHSTRINGS_REPLACEMENTS_LENGTH_MISMATCH);

        replace("", new String[0], new String[]{"bar"});
    }

    @Test
    public void test_more_searchstrings_than_replacements_should_throw() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ERR_SEARCHSTRINGS_REPLACEMENTS_LENGTH_MISMATCH);

        replace("", new String[]{"foo"}, new String[0]);
    }

    @Test
    public void test_null_element_in_searchstrings_should_throw() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ERR_NULL_OR_EMPTY_SEARCHSTRING);

        replace("", new String[]{"foo", null}, new String[]{"bar", "baz"});
    }

    @Test
    public void test_empty_element_in_searchstrings_should_throw() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ERR_NULL_OR_EMPTY_SEARCHSTRING);

        replace("", new String[]{"foo", ""}, new String[]{"bar", "baz"});
    }

    @Test
    public void test_null_element_in_replacements_should_throw() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ERR_NULL_REPLACEMENT);

        replace("", new String[]{"foo"}, new String[]{null});
    }

    @Test
    public void test_non_distinct_searchstrings_should_throw() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(ERR_DUPLICATE_SEARCHSTRINGS);

        String before = "foo";
        replace("", new String[]{before, before}, new String[]{"bar", "baz"});
    }
}
