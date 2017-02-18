package com.janosgyerik.utils.algorithm.graphs.impl;

import com.janosgyerik.utils.algorithm.graphs.api.Graph;

import java.util.ArrayList;

public class UndirectedGraph extends AbstractGraph {
    @Override
    public void addEdge(int v, int w) {
        map.computeIfAbsent(v, k -> new ArrayList<>()).add(w);
        map.computeIfAbsent(w, k -> new ArrayList<>()).add(v);
    }

    @Override
    public int edgeCount() {
        return super.edgeCount() / 2;
    }

    static Graph fromString(String input) {
        return fromString(new UndirectedGraph(), input);
    }
}
