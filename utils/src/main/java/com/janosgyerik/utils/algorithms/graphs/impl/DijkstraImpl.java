package com.janosgyerik.utils.algorithms.graphs.impl;

import com.janosgyerik.utils.algorithms.graphs.api.Dijkstra;
import com.janosgyerik.utils.algorithms.graphs.api.Graph;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraImpl implements Dijkstra {

  private final Map<Integer, Integer> dist = new HashMap<>();
  private final Map<Integer, Integer> prev = new HashMap<>();

  @Override
  public void compute(Graph g, int source) {
    init();

    for (int i = 0; i < g.vertexCount(); i++) {
      dist.put(i, INFINITY);
    }
    dist.put(source, 0);

    PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(dist::get));
    queue.add(source);

    while (!queue.isEmpty()) {
      int current = queue.poll();

      for (Integer neigh : g.adj(current)) {
        int d = dist.get(current) + g.weight(current, neigh);
        if (d < dist.get(neigh)) {
          dist.put(neigh, d);
          prev.put(neigh, current);
          queue.remove(neigh);
          queue.add(neigh);
        }
      }
    }
  }

  private void init() {
    dist.clear();
    prev.clear();
  }

  @Override
  public int minDistance(int target) {
    return dist.getOrDefault(target, INFINITY);
  }

  @Override
  public List<Integer> shortestPath(int target) {
    List<Integer> path = new LinkedList<>();
    int q = target;
    while (prev.containsKey(q)) {
      path.add(0, q);
      q = prev.get(q);
    }
    path.add(0, q);
    return path;
  }
}
