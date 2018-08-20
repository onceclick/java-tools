package com.janosgyerik.utils.algorithms.graphs.impl;

import com.janosgyerik.utils.algorithms.graphs.api.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class UndirectedGraph extends AbstractGraph {
    private final Map<Integer, Map<Integer, Integer>> weights = new HashMap<>();

    @Override
    public void addEdge(int v, int w) {
        map.computeIfAbsent(v, k -> new ArrayList<>()).add(w);
        map.computeIfAbsent(w, k -> new ArrayList<>()).add(v);
    }

    @Override
    public void addEdge(int p, int q, int w) {
        this.addEdge(p, q);
        weights.computeIfAbsent(p, HashMap::new).put(q, w);
        weights.computeIfAbsent(q, HashMap::new).put(p, w);
    }

    @Override
    public int edgeCount() {
        return super.edgeCount() / 2;
    }

    @Override
    public int weight(int from, int to) {
        if (!weights.containsKey(from)) {
            throw new IllegalArgumentException(String.format("No known weights from vertex %s", from));
        }
        Map<Integer, Integer> neigh = weights.get(from);
        if (!neigh.containsKey(to)) {
            throw new IllegalArgumentException(String.format("No known weights to vertex %s from %s", to, from));
        }
        return neigh.get(to);
    }

    static Graph fromString(String input) {
        return fromString(new UndirectedGraph(), input);
    }
}
