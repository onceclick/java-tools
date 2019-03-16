package com.janosgyerik.utils.algorithms.graphs.impl;

import com.janosgyerik.utils.algorithms.graphs.api.Graph;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConnectedComponentsTest {
  private final ConnectedComponents cc;
  {
    Graph g = Graphs.newUndirectedGraph();
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(0, 5);
    g.addEdge(0, 6);
    g.addEdge(3, 4);
    g.addEdge(3, 5);
    g.addEdge(4, 5);
    g.addEdge(4, 6);
    g.addEdge(7, 8);
    g.addEdge(9, 10);
    g.addEdge(9, 11);
    g.addEdge(9, 12);
    g.addEdge(11, 12);

    cc = new ConnectedComponents(g);
  }

  @Test
  public void test_count() {
    assertThat(cc.count()).isEqualTo(3);
  }

  @Test
  public void test_id() {
    assertThat(cc.id(0)).isEqualTo(0);
    assertThat(cc.id(6)).isEqualTo(0);
    assertThat(cc.id(7)).isEqualTo(1);
    assertThat(cc.id(11)).isEqualTo(2);
  }

  @Test
  public void test_connected() {
    assertThat(cc.connected(0, 4)).isTrue();
    assertThat(cc.connected(0, 7)).isFalse();
    assertThat(cc.connected(8, 7)).isTrue();
    assertThat(cc.connected(3, 7)).isFalse();
    assertThat(cc.connected(11, 9)).isTrue();
    assertThat(cc.connected(11, 7)).isFalse();
  }
}
