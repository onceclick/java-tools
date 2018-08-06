package com.janosgyerik.utils.algorithms.unionfind;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UnionFindImplTest {
    @Test
    public void test_example() {
        UnionFind uf = new UnionFindImpl();

        uf.union(0, 5);
        uf.union(5, 6);

        uf.union(1, 2);
        uf.union(2, 7);

        uf.union(3, 4);
        uf.union(3, 8);
        uf.union(4, 9);

        assertThat(uf.connected(0, 6)).isTrue();
        assertThat(uf.connected(0, 1)).isFalse();
        assertThat(uf.connected(8, 9)).isTrue();
    }

    @Test
    public void test_length() {
        UnionFindTester uf = new UnionFindTester();

        uf.union(0, 1);
        assertThat(uf.length(0)).isEqualTo(2);
        assertThat(uf.length(1)).isEqualTo(1);

        uf.union(2, 3);
        assertThat(uf.length(2)).isEqualTo(2);
        assertThat(uf.length(3)).isEqualTo(1);

        uf.union(4, 5);
        assertThat(uf.length(4)).isEqualTo(2);
        assertThat(uf.length(5)).isEqualTo(1);

        uf.union(6, 7);
        assertThat(uf.length(6)).isEqualTo(2);
        assertThat(uf.length(7)).isEqualTo(1);

        uf.union(0, 3);
        uf.union(4, 7);
        uf.union(0, 7);

        for (int i = 1; i <= 7; i++) {
            assertThat(uf.connected(0, i)).isTrue();
        }

        for (int i = 0; i <= 7; i++) {
            assertThat(uf.length(i)).isLessThanOrEqualTo(2);
        }
    }

    static class UnionFindTester extends UnionFindImpl {
        int length(int p) {
            int len = 1;
            for (int x = p; x != getIds().get(x); x = getIds().get(x)) {
                len++;
            }
            return len;
        }
    }
}
