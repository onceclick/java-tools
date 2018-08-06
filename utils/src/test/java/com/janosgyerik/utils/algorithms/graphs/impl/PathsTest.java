package com.janosgyerik.utils.algorithms.graphs.impl;

import com.janosgyerik.utils.algorithms.graphs.api.Graph;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class PathsTest {
    private final Paths paths;
    {
        Graph g = Graphs.newUndirectedGraph();
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 5);
        g.addEdge(0, 6);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(7, 8);
        g.addEdge(9, 10);
        g.addEdge(9, 11);
        g.addEdge(9, 12);
        g.addEdge(11, 12);

        paths = new Paths(g, 0);
    }

    @Test
    public void test_hashPathTo() {
        IntStream.range(0, 7).forEach(v -> assertThat(paths.hasPathTo(v)).isTrue());
        IntStream.range(7, 13).forEach(v -> assertThat(paths.hasPathTo(v)).isFalse());
    }

    @Test
    public void test_pathTo() {
        assertThat(paths.pathTo(3)).containsExactly(0, 5, 3);
        assertThat(paths.pathTo(2)).containsExactly(0, 2);
    }
}
