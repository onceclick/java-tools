package com.janosgyerik.utils.algorithms.graphs.impl;

import com.janosgyerik.utils.algorithms.graphs.api.Graph;

public class Graphs {

    private Graphs() {
        // utility class, forbidden constructor
    }

    public static Graph newDirectedGraph() {
        return new DirectedGraph();
    }

    public static Graph newUndirectedGraph() {
        return new UndirectedGraph();
    }
}
