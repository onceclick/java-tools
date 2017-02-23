package com.janosgyerik.utils.algorithm.graphs.impl;

import com.janosgyerik.utils.algorithm.graphs.api.Graph;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.stream.IntStream;

public class GraphUtils {
    private GraphUtils() {
        // utility class, forbidden constructor
    }

    public static int degree(Graph g, int v) {
        return g.adj(v).size();
    }

    public static int maxDegree(Graph g) {
        int max = 0;
        for (int v = 0; v < g.vertexCount(); v++) {
            max = Math.max(max, g.adj(v).size());
        }
        return max;
    }

    public static int averageDegree(Graph g) {
        return 2 * g.edgeCount() / g.vertexCount();
    }

    public static int selfLoopCount(Graph g) {
        return IntStream.range(0, g.vertexCount()).map(v -> (int) g.adj(v).stream().filter(w -> v == w).count()).sum();
    }

    /**
     * Check if a connected graph is bipartite.
     */
    public static boolean isBipartite(Graph g) {
        int[] colors = new int[g.vertexCount()];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.add(0);
        int c = 1;
        colors[0] = c;
        while (!stack.isEmpty()) {
            int v = stack.pop();
            c = 3 - colors[v];
            for (int w : g.adj(v)) {
                if (colors[w] == 0) {
                    colors[w] = c;
                    stack.push(w);
                } else if (colors[w] != c) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check if a connected graph has cycles.
     */
    public static boolean hasCycle(Graph g) {
        boolean[] visited = new boolean[g.vertexCount()];
        int[] edgeTo = new int[g.vertexCount()];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.add(0);
        visited[0] = true;
        while (!stack.isEmpty()) {
            int v = stack.pop();
            for (int w : g.adj(v)) {
                if (edgeTo[v] == w) {
                    // the vertex from which we got here
                    continue;
                }
                if (!visited[w]) {
                    visited[w] = true;
                    edgeTo[w] = v;
                    stack.push(w);
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Is there a (general) cycle that uses each edge exactly once?
     */
    public static boolean hasEulerianTour(Graph g) {
        return IntStream.range(0, g.vertexCount()).allMatch(v -> GraphUtils.degree(g, v) % 2 == 0);
    }

    /**
     * Find a (general) cycle that uses every edge exactly once.
     */
    public static Collection<Integer> findEulerianTour(Graph g) {
        // TODO
        return null;
    }

    /**
     * Find a cycle that visits every vertex exactly once.
     * Note: this is a classic NP-complete problem, intractable.
     */
    public static Collection<Integer> findHamiltonianCycle(Graph g) {
        // TODO
        return null;
    }

    /**
     * Are two graphs identical except for vertex names?
     * Note: this is a longstanding open problem.
     */
    public static boolean isomorphic(Graph g1, Graph g2) {
        // TODO
        return false;
    }

    /**
     * Lay out a graph in the plane without crossing edges.
     * Note: linear-time DFS-based planarity algorithm was discovered
     * by Tarjan in 1970s (too complicated for most practitioners)
     */
    public static void printPlanar(Graph g) {
        // TODO
    }

    // TODO javadoc
    Paths paths(Graph g, int start) {
        return new Paths(g, start);
    }

    // TODO javadoc
    ConnectedComponents connectedComponents(Graph g) {
        return new ConnectedComponents(g);
    }

}
