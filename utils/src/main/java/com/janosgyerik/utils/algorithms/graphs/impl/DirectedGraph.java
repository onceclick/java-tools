package com.janosgyerik.utils.algorithms.graphs.impl;

import com.janosgyerik.utils.algorithms.graphs.api.Graph;
import java.util.ArrayList;

class DirectedGraph extends AbstractGraph {
  static Graph fromString(String input) {
    return fromString(new DirectedGraph(), input);
  }

  @Override
  public void addEdge(int v, int w) {
    map.computeIfAbsent(v, k -> new ArrayList<>()).add(w);
    map.putIfAbsent(w, new ArrayList<>());
  }
}
