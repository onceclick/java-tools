package com.janosgyerik.utils.algorithms.graphs.impl;

import com.janosgyerik.utils.algorithms.graphs.api.Graph;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class Paths {

  private final int start;
  private final boolean[] visited;
  private final int[] edgeTo;

  /**
   * Find paths in g from source s.
   */
  public Paths(Graph g, int s) {
    start = s;
    visited = new boolean[g.vertexCount()];
    edgeTo = new int[g.vertexCount()];
    bfs(g, s);
  }

  private void bfs(Graph g, int v) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(v);
    visited[v] = true;

    while (!queue.isEmpty()) {
      int x = queue.poll();
      for (int w : g.adj(x)) {
        if (!visited[w]) {
          queue.add(w);
          visited[w] = true;
          edgeTo[w] = x;
        }
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
