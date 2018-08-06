package com.janosgyerik.utils.algorithms.graphs.impl;

import com.janosgyerik.utils.algorithms.graphs.api.Graph;
import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;

public class GraphUtilsTest {
    private final Graph g = Graphs.newUndirectedGraph();
    {
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 6);
        g.addEdge(0, 5);
        g.addEdge(3, 5);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(7, 8);
        g.addEdge(9, 10);
        g.addEdge(9, 11);
        g.addEdge(9, 12);
        g.addEdge(11, 12);
    }

    @Test
    public void test_degree() {
        assertThat(GraphUtils.degree(g, 0)).isEqualTo(4);
        assertThat(GraphUtils.degree(g, 3)).isEqualTo(2);
    }

    @Test
    public void test_maxDegree() {
        assertThat(GraphUtils.maxDegree(g)).isEqualTo(4);
    }

    @Test
    public void test_averageDegree() {
        assertThat(GraphUtils.averageDegree(g)).isEqualTo(2);
    }

    @Test
    public void test_selfLoopCount() {
        assertThat(GraphUtils.selfLoopCount(g)).isEqualTo(0);
    }

    @Test
    public void test_selfLoopCount_1() {
        Graph g2 = Graphs.newUndirectedGraph();
        g2.addEdge(0, 0);
        assertThat(GraphUtils.selfLoopCount(g2)).isEqualTo(2);
    }

    @Test
    public void test_isBipartite_true() {
        Graph g2 = Graphs.newUndirectedGraph();
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(0, 5);
        g2.addEdge(0, 6);
        g2.addEdge(1, 3);
        g2.addEdge(2, 3);
        g2.addEdge(2, 4);
        g2.addEdge(4, 6);
        g2.addEdge(4, 5);
        assertThat(GraphUtils.isBipartite(g2)).isTrue();
    }

    @Test
    public void test_isBipartite_false() {
        Graph g2 = Graphs.newUndirectedGraph();
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(0, 5);
        g2.addEdge(0, 6);
        g2.addEdge(1, 3);
        g2.addEdge(2, 3);
        g2.addEdge(2, 4);
        g2.addEdge(4, 6);
        g2.addEdge(4, 5);
        g2.addEdge(4, 7);
        g2.addEdge(5, 7);
        assertThat(GraphUtils.isBipartite(g2)).isFalse();
    }

    @Test
    public void test_hasCycle_true() {
        Graph g2 = Graphs.newUndirectedGraph();
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(0, 5);
        g2.addEdge(0, 6);
        g2.addEdge(1, 3);
        g2.addEdge(2, 3);
        g2.addEdge(2, 4);
        g2.addEdge(4, 6);
        g2.addEdge(4, 5);
        assertThat(GraphUtils.hasCycle(g2)).isTrue();
    }

    @Test
    public void test_hasCycle_false() {
        Graph g2 = Graphs.newUndirectedGraph();
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(0, 6);
        g2.addEdge(1, 3);
        g2.addEdge(2, 7);
        g2.addEdge(2, 8);
        g2.addEdge(4, 6);
        g2.addEdge(4, 5);
        assertThat(GraphUtils.hasCycle(g2)).isFalse();
    }
}
