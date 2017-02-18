package com.janosgyerik.utils.algorithm.graphs.impl;

import com.janosgyerik.utils.algorithm.graphs.api.Graph;
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
        Graph g2 = new UndirectedGraph();
        g2.addEdge(0, 0);
        assertThat(GraphUtils.selfLoopCount(g2)).isEqualTo(1);
    }
}
