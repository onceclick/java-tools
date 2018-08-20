package com.janosgyerik.utils.algorithms.graphs.impl;

import com.janosgyerik.utils.algorithms.graphs.api.Dijkstra;
import com.janosgyerik.utils.algorithms.graphs.api.Graph;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DijkstraImplTest {

    private final Dijkstra underTest = new DijkstraImpl();

    @Before
    public void setUp() {
        Graph g = Graphs.newUndirectedGraph();
        g.addEdge(0, 0, 0);
        g.addEdge(1, 2, 7);
        g.addEdge(1, 3, 9);
        g.addEdge(1, 6, 14);
        g.addEdge(2, 3, 10);
        g.addEdge(2, 4, 15);
        g.addEdge(3, 4, 11);
        g.addEdge(3, 6, 2);
        g.addEdge(4, 5, 6);
        g.addEdge(5, 6, 9);
        underTest.compute(g, 1);
    }

    @Test
    public void minDistance() {
        assertThat(underTest.minDistance(0)).isEqualTo(Dijkstra.INFINITY);
        assertThat(underTest.minDistance(1)).isEqualTo(0);
        assertThat(underTest.minDistance(2)).isEqualTo(7);
        assertThat(underTest.minDistance(3)).isEqualTo(9);
        assertThat(underTest.minDistance(4)).isEqualTo(20);
        assertThat(underTest.minDistance(5)).isEqualTo(20);
        assertThat(underTest.minDistance(6)).isEqualTo(11);
        assertThat(underTest.minDistance(7)).isEqualTo(Dijkstra.INFINITY);
    }

    @Test
    public void shortestPath() {
        assertThat(underTest.shortestPath(0)).containsExactly(0);
        assertThat(underTest.shortestPath(1)).containsExactly(1);
        assertThat(underTest.shortestPath(2)).containsExactly(1, 2);
        assertThat(underTest.shortestPath(3)).containsExactly(1, 3);
        assertThat(underTest.shortestPath(4)).containsExactly(1, 3, 4);
        assertThat(underTest.shortestPath(5)).containsExactly(1, 3, 6, 5);
        assertThat(underTest.shortestPath(6)).containsExactly(1, 3, 6);
        assertThat(underTest.shortestPath(7)).containsExactly(7);
    }
}
