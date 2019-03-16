package com.janosgyerik.utils.algorithms.graphs.impl;

import com.janosgyerik.utils.algorithms.graphs.api.Graph;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

abstract class AbstractGraph implements Graph {
  final Map<Integer, Collection<Integer>> map = new HashMap<>();

  static Graph fromString(Graph g, String input) {
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

  @Override
  public int vertexCount() {
    return map.size();
  }

  @Override
  public int edgeCount() {
    return map.values().stream().mapToInt(Collection::size).sum();
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
    for (Map.Entry<Integer, Collection<Integer>> entry : map.entrySet()) {
      int from = entry.getKey();
      for (int to : entry.getValue()) {
        sb.append(from).append('-').append(to).append(',');
      }
    }
    return sb.substring(0, sb.length() - 1);
  }
}
