package com.janosgyerik.utils.algorithms.graphs.api;

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

    /**
     * Add a weighted edge from one vertex to another.
     */
    default void addEdge(int p, int q, int w) {
        throw new UnsupportedOperationException();
    }

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

    /**
     * Get the weight of an edge between two vertices.
     */
    default int weight(int from, int to) {
        throw new UnsupportedOperationException();
    }
}
