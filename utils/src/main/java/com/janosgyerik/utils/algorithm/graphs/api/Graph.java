package com.janosgyerik.utils.algorithm.graphs.api;

import java.util.Collection;

/**
 * A simple graph with numeric vertex ids, starting from 0.
 *
 * Implementations may be directed or undirected.
 */
public interface Graph {
    /**
     * Add an edge from one vertex to another.
     */
    void addEdge(int v, int w);

    int vertexCount();

    int edgeCount();

    /**
     * Get the collection of vertices from given vertex.
     */
    Collection<Integer> adj(int v);

    /**
     * Get a string representation that can be used to reconstruct an identical Graph.
     */
    String asString();
}
