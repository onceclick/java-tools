package com.janosgyerik.utils.algorithms.tree.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

interface Graph {
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

public class Solution {
  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    List<String> words = makeWords(beginWord, endWord, wordList);
    if (words.isEmpty())
      return Collections.emptyList();
    List<List<Integer>> bags = makeBags(words);
    Graph g = makeGraph(bags);
    IntStream.range(0, words.size()).boxed().filter(i -> g.adj(i).isEmpty()).forEach(i -> g.addEdge(i, i));
    if (g.vertexCount() == 0)
      return Collections.emptyList();
    Paths paths = new Paths(g, 0);
    if (!paths.hasPathTo(words.size() - 1))
      return Collections.emptyList();
    return findLadders(g, words).stream().distinct().collect(Collectors.toList());
  }

  public List<List<String>> findLadders(Graph g, List<String> words) {
    boolean[] visited = new boolean[g.vertexCount()];

    Queue<List<Integer>> queue = new LinkedList<>();
    queue.add(Collections.singletonList(0));

    List<List<String>> paths = new ArrayList<>();
    while (paths.isEmpty()) {
      List<List<Integer>> level = new ArrayList<>(queue);
      queue.clear();

      level.forEach(p -> visited[p.get(p.size() - 1)] = true);

      for (List<Integer> path : level) {
        int v = path.get(path.size() - 1);
        if (v == words.size() - 1) {
          paths.add(toLadder(path, words));
          continue;
        }
        for (int w : g.adj(v)) {
          if (!visited[w]) {
            List<Integer> copy = new ArrayList<>(path);
            copy.add(w);
            queue.add(copy);
          }
        }
      }
    }

    return paths;
  }

  List<String> toLadder(List<Integer> path, List<String> words) {
    return path.stream().map(words::get).collect(Collectors.toList());
  }

  public List<String> makeWords(String beginWord, String endWord, List<String> wordList) {
    Set<String> set = wordList.stream().collect(Collectors.toSet());
    if (!set.remove(endWord)) {
      return Collections.emptyList();
    }
    set.remove(beginWord);

    List<String> words = new ArrayList<>();
    words.add(beginWord);
    words.addAll(set);
    words.add(endWord);
    return words;
  }

  Graph makeGraph(List<List<Integer>> bags) {
    Graph g = new UndirectedGraph();
    for (List<Integer> list : bags) {
      for (int i = 0; i < list.size() - 1; i++) {
        for (int j = i + 1; j < list.size(); j++) {
          g.addEdge(list.get(i), list.get(j));
        }
      }
    }
    return g;
  }

  List<List<Integer>> makeBags(List<String> words) {
    Map<String, List<Integer>> bags = new HashMap<>();
    IntStream.range(0, words.size()).forEach(i -> add(bags, i, words.get(i)));
    return bags.values().stream().filter(x -> x.size() > 1).collect(Collectors.toList());
  }

  void add(Map<String, List<Integer>> bags, int id, String word) {
    char[] chars = word.toCharArray();
    for (int i = 0; i < word.length(); i++) {
      char[] copy = chars.clone();
      copy[i] = '.';
      String key = new String(copy);
      bags.computeIfAbsent(key, k -> new ArrayList<>()).add(id);
    }
  }

  String chip(String word, int index) {
    return new StringBuilder(word).deleteCharAt(index).toString();
  }
}

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
    if (!map.containsKey(v))
      return Collections.emptyList();
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

class UndirectedGraph extends AbstractGraph {
  static Graph fromString(String input) {
    return fromString(new UndirectedGraph(), input);
  }

  @Override
  public void addEdge(int v, int w) {
    map.computeIfAbsent(v, k -> new ArrayList<>()).add(w);
    map.computeIfAbsent(w, k -> new ArrayList<>()).add(v);
  }

  @Override
  public int edgeCount() {
    return super.edgeCount() / 2;
  }
}

class Paths {

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
