package com.janosgyerik.utils.algorithms.graphs.impl;

import com.janosgyerik.utils.algorithms.graphs.api.Graph;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectedGraphTest {
  @Test
  public void empty_graph_should_have_no_vertex_and_edge() {
    Graph g = newGraph();
    assertThat(g.vertexCount()).isEqualTo(0);
    assertThat(g.edgeCount()).isEqualTo(0);
  }

  @Test(expected = Exception.class)
  public void nonexistent_vertex_should_raise_error() {
    newGraph().adj(0);
  }

  @Test
  public void test_addEdge_one() {
    Graph g = newGraph();
    g.addEdge(0, 1);
    assertThat(g.vertexCount()).isEqualTo(2);
    assertThat(g.edgeCount()).isEqualTo(1);
    assertThat(g.adj(0)).containsExactly(1);
    assertThat(g.adj(1)).isEmpty();
  }

  @Test
  public void test_addEdge_same_twice() {
    Graph g = newGraph();
    g.addEdge(0, 1);
    g.addEdge(0, 1);
    assertThat(g.vertexCount()).isEqualTo(2);
    assertThat(g.edgeCount()).isEqualTo(2);
    assertThat(g.adj(0)).containsExactly(1, 1);
    assertThat(g.adj(1)).isEmpty();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void test_adj_is_unmodifiable() {
    Graph g = newGraph();
    g.addEdge(0, 1);
    g.adj(0).add(3);
  }

  @Test
  public void fromString_should_read_from_simple_pairs_csv() {
    Graph g = DirectedGraph.fromString("0-1,1-2,2-3");
    assertThat(g.edgeCount()).isEqualTo(3);
    assertThat(g.vertexCount()).isEqualTo(4);
    assertThat(g.adj(0)).containsExactly(1);
    assertThat(g.adj(1)).containsExactly(2);
    assertThat(g.adj(2)).containsExactly(3);
  }

  @Test
  public void fromString_should_allow_space_delimited_format() {
    Graph g = DirectedGraph.fromString("0 1 1 2 2 3");
    assertThat(g.edgeCount()).isEqualTo(3);
    assertThat(g.vertexCount()).isEqualTo(4);
    assertThat(g.adj(0)).containsExactly(1);
    assertThat(g.adj(1)).containsExactly(2);
    assertThat(g.adj(2)).containsExactly(3);
  }

  @Test
  public void should_allow_self_loop() {
    Graph g = newGraph();
    g.addEdge(0, 0);
    assertThat(g.edgeCount()).isEqualTo(1);
    assertThat(g.vertexCount()).isEqualTo(1);
    assertThat(g.adj(0)).containsExactly(0);
  }

  @Test
  public void should_allow_cycle_of_2() {
    Graph g = newGraph();
    g.addEdge(0, 1);
    g.addEdge(1, 0);
    assertThat(g.edgeCount()).isEqualTo(2);
    assertThat(g.vertexCount()).isEqualTo(2);
    assertThat(g.adj(0)).containsExactly(1);
    assertThat(g.adj(1)).containsExactly(0);
  }

  @Test
  public void test_asString() {
    Graph g = newGraph();
    assertThat(g.asString()).isEmpty();

    g.addEdge(0, 1);
    assertThat(g.asString()).isEqualTo("0-1");

    g.addEdge(0, 1);
    assertThat(g.asString()).isEqualTo("0-1,0-1");
  }

  private Graph newGraph() {
    return new DirectedGraph();
  }
}
