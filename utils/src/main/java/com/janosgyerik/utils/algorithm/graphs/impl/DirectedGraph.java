package com.janosgyerik.utils.algorithm.graphs.impl;

import com.janosgyerik.utils.algorithm.graphs.api.Graph;

import java.util.*;

public class DirectedGraph extends AbstractGraph {
    @Override
    public void addEdge(int v, int w) {
        map.computeIfAbsent(v, k -> new ArrayList<>()).add(w);
        map.putIfAbsent(w, new ArrayList<>());
    }

    static Graph fromString(String input) {
        return fromString(new DirectedGraph(), input);
    }
}
