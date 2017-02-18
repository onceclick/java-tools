package com.janosgyerik.utils.algorithm.graphs.impl;

import com.janosgyerik.utils.algorithm.graphs.api.Graph;

import java.util.*;
import java.util.regex.Pattern;

public class GraphImpl implements Graph {
    Map<Integer, List<Integer>> map = new HashMap<>();
    private int edgeCount = 0;

    @Override
    public void addEdge(int v, int w) {
        List<Integer> adj = map.computeIfAbsent(v, k -> new ArrayList<>());
        adj.add(w);
        if (!map.containsKey(w)) {
            map.put(w, new ArrayList<>());
        }
        edgeCount++;
    }

    @Override
    public int vertexCount() {
        return map.size();
    }

    @Override
    public int edgeCount() {
        return edgeCount;
    }

    @Override
    public Collection<Integer> adj(int v) {
        return Collections.unmodifiableCollection(map.get(v));
    }

    @Override
    public String asString() {
        if (map.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int from = entry.getKey();
            for (int to : entry.getValue()) {
                sb.append(from).append('-').append(to).append(',');
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static Graph fromString(String input) {
        Graph g = new GraphImpl();
        try (Scanner scanner = new Scanner(input)) {
            scanner.useDelimiter(Pattern.compile("[-,\\s]"));
            while (scanner.hasNext()) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                g.addEdge(from, to);
            }
        }
        return g;
    }
}
