package com.janosgyerik.utils.algorithms.graphs.impl;

import com.janosgyerik.utils.algorithms.graphs.api.Graph;

public class ConnectedComponents {

  private final boolean[] visited;
  private final int[] ids;
  private int count = 0;

  /**
   * Find connected components in g.
   */
  public ConnectedComponents(Graph g) {
    visited = new boolean[g.vertexCount()];
    ids = new int[g.vertexCount()];
    for (int v = 0; v < g.vertexCount(); v++) {
      if (!visited[v]) {
        dfs(g, v);
        count++;
      }
    }
  }

  private void dfs(Graph g, int v) {
    visited[v] = true;
    ids[v] = count;
    for (int w : g.adj(v)) {
      if (!visited[w]) {
        dfs(g, w);
      }
    }
  }

  /**
   * Are v and w connected?
   */
  public boolean connected(int v, int w) {
    return ids[v] == ids[w];
  }

  /**
   * Get the number of connected components.
   */
  public int count() {
    return count;
  }

  /**
   * Get the component identifier for v.
   */
  public int id(int v) {
    return ids[v];
  }
}
