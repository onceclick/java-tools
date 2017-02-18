package com.janosgyerik.utils.algorithm.graphs.impl;

import com.janosgyerik.utils.algorithm.graphs.api.Graph;

import java.util.ArrayDeque;
import java.util.Collection;

public class Paths {

    private final int start;
    private final boolean[] visited;
    private final int[] edgeTo;

    /**
     * Find paths in s from source s.
     */
    public Paths(Graph g, int s) {
        start = s;
        visited = new boolean[g.vertexCount()];
        edgeTo = new int[g.vertexCount()];
        dfs(g, s);
    }

    private void dfs(Graph g, int v) {
        visited[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                dfs(g, w);
                edgeTo[w] = v;
            }
        }
    }

    /**
     * Is there a path from s to v?
     */
    public boolean hasPathTo(int v) {
        return visited[v];
    }

    /**
     * Get path from s to v, if exists.
     */
    public Collection<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            throw new IllegalStateException(String.format("there is no path from %d to %d", start, v));
        }
        ArrayDeque<Integer> path = new ArrayDeque<>();
        for (int x = v; x != start; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(start);
        return path;
    }

}
