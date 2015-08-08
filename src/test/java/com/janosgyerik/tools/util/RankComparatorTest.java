package com.janosgyerik.tools.util;

import com.janosgyerik.tools.util.RankComparator;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RankComparatorTest {

    // Yes. An enum would make sense for this example.
    // But with an enum, the rank-comparator is pointless,
    // as the enum values can be compared directly using their natural order.
    // The purpose of this example is to illustrate usage
    // with objects that don't have a natural ordering by themselves.
    private static class Rating {
        static Rating AAA_PLUS = new Rating("AAA+");
        static Rating AAA = new Rating("AAA");
        static Rating AAA_MINUS = new Rating("AAA-");
        static Rating AA_PLUS = new Rating("AA+");
        static Rating AA = new Rating("AA");
        static Rating AA_MINUS = new Rating("AA-");
        static Rating BB = new Rating("BB");
        static Rating NR = new Rating("Non-rated");

        private final String label;

        Rating(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private static class RatingComparator implements Comparator<Rating> {
        private Comparator<Rating> comparator =
                RankComparator.fromHighToLow(
                        Arrays.asList(Rating.AAA_PLUS, Rating.AAA, Rating.AAA_MINUS, Rating.AA_PLUS, Rating.AA,
                                Rating.BB, Rating.NR));

        @Override
        public int compare(Rating o1, Rating o2) {
            return comparator.compare(o1, o2);
        }
    }

    private final RatingComparator comparator = new RatingComparator();

    @Test
    public void test_AA_lessThan_AAA() {
        assertTrue(comparator.compare(Rating.AA, Rating.AAA) < 0);
    }

    @Test
    public void test_AAA_lessThan_AAA_PLUS() {
        assertTrue(comparator.compare(Rating.AAA, Rating.AAA_PLUS) < 0);
    }

    @Test
    public void test_AAA_greaterThan_AAA_MINUS() {
        assertTrue(comparator.compare(Rating.AAA, Rating.AAA_MINUS) > 0);
    }

    @Test
    public void test_AAA_equals_AAA() {
        assertEquals(0, comparator.compare(Rating.AAA, Rating.AAA));
    }

    @Test
    public void test_NR_lessThan_A_MINUS() {
        assertTrue(comparator.compare(Rating.NR, Rating.AAA) < 0);
    }

    @Test
    public void test_sort_AA_AAA_NR_BB() {
        List<Rating> ratings = Arrays.asList(Rating.AA, Rating.AAA, Rating.NR, Rating.BB);
        Collections.sort(ratings, comparator);
        assertEquals(Arrays.asList(Rating.NR, Rating.BB, Rating.AA, Rating.AAA), ratings);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_inconsistent_ranks() {
        RankComparator.fromHighToLow(Arrays.asList(Rating.AA, Rating.AA_MINUS, Rating.AA));
    }

    @Test
    public void test_sortIssuersByRating() {
        class Issuer {
            final Rating rating;

            Issuer(Rating rating) {
                this.rating = rating;
            }

            @Override
            public String toString() {
                return "I-" + rating;
            }
        }

        Issuer issuerBB = new Issuer(Rating.BB);
        Issuer issuerAA = new Issuer(Rating.AA);
        Issuer issuerNR = new Issuer(Rating.NR);
        Issuer issuerNull = new Issuer(null);

        List<Issuer> issuers = Arrays.asList(issuerBB, issuerAA, issuerNR, issuerNull);
        Collections.sort(issuers, new Comparator<Issuer>() {
            @Override
            public int compare(Issuer o1, Issuer o2) {
                return comparator.compare(o1.rating, o2.rating);
            }
        });

        assertEquals(Arrays.asList(issuerNull, issuerNR, issuerBB, issuerAA), issuers);
    }
}
