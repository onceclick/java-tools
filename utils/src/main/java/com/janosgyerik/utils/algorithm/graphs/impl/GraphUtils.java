package com.janosgyerik.utils.algorithm.graphs.impl;

import com.janosgyerik.utils.algorithm.graphs.api.Graph;

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
}
