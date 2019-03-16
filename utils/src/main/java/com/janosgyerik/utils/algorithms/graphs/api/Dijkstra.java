package com.janosgyerik.utils.algorithms.graphs.api;

import java.util.List;

public interface Dijkstra {

  int INFINITY = Integer.MAX_VALUE;

  /**
   * Compute efficient data structures to answer such questions as:
   *
   * - What is the minimum distance to reach some target (from predefined source)
   *
   * - What is a shortest path to reach some target (from predefined source)
   *
   */
  void compute(Graph g, int source);

  /**
   * @return the minimum distance to reach the target vertex from the source vertex,
   * or INFINITY if the target cannot be reached.
   */
  int minDistance(int target);

  /**
   * @return a shortest sequence of vertex ids that reach the target vertex from the source vertex,
   * or a singleton list if the target cannot be reached from the source.
   */
  List<Integer> shortestPath(int target);
}
