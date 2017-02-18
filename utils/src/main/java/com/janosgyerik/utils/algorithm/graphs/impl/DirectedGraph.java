package com.janosgyerik.utils.algorithm.graphs.impl;

import com.janosgyerik.utils.algorithm.graphs.api.Graph;

import java.util.*;
import java.util.regex.Pattern;

public class DirectedGraph implements Graph {
    private final Map<Integer, List<Integer>> map = new HashMap<>();

    @Override
    public void addEdge(int v, int w) {
        map.computeIfAbsent(v, k -> new ArrayList<>()).add(w);
        map.putIfAbsent(w, new ArrayList<>());
    }

    @Override
    public int vertexCount() {
        return map.size();
    }

    @Override
    public int edgeCount() {
        return map.values().stream().mapToInt(List::size).sum();
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
        Graph g = new DirectedGraph();
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
