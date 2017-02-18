package com.janosgyerik.utils.algorithm.graphs.impl;

import com.janosgyerik.utils.algorithm.graphs.api.Graph;

import java.util.ArrayDeque;
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
}
