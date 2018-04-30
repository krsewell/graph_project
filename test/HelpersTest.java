// Copyright (c) 2018. Kristopher J Sewell, All Rights Reserved.
// File: HelpersTest.java  Module: graph_tiefighter
// Net_ID: kjs170430

import TieFighter.Controller.Helpers;
import TieFighter.Model.WeightedEdge;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HelpersTest {

  @Test
  void getVertices() {
  }

  @Test
  void getEdges() {
  }

  @Test
  void extractVert() {
    String s = Helpers.extractVert(" 11 10,7 2,9 9,8 ");
    assertTrue(s.compareTo("11") == 0);
  }

  @Test
  void extractEdges() {
    List<WeightedEdge> weightedEdges = Helpers.extractEdges(" 11 10,7 2,9 9,8 ");
    assertEquals(11, weightedEdges.get(0).u);
    assertEquals(10, weightedEdges.get(0).v);
    assertEquals(7, weightedEdges.get(0).weight);
    //real names need to be mapped to indices so the graph can work correctly.
    //done: Helpers.convertToKeyValues
  }

  @Test
  void extractNameSimple() {
    String test = Helpers.extractName("John 1 2 3 4 5");
    assertEquals("John", test);
  }

  @Test
  void extractNameDouble() {
    String test = Helpers.extractName("John Smith 1.4 2 3 4 5.0");
    assertEquals("John Smith", test);

  }

  @Test
  void extractNameExtraSpace() {
    String test = Helpers.extractName(" John  Smith 1 2  3 4 5");
    assertEquals("John Smith", test);
  }

  @Test
  void extractNameSpecialCharacter() {
    String test = Helpers.extractName("John J3bbid_hia Smith's Hor2e 4 5 3 1 2");
  }

  @Test
  void extractNodesSimple() {
    LinkedList<Integer> test = Helpers.extractNodes("John Smith 1 2 3 4 5 6");
    for (int i = 0; i < 6; i++)
      assertEquals(Optional.of(i + 1), Optional.of(test.get(i)));

  }

  @Test
  void extractNodesExtraSpace() {
    LinkedList<Integer> test = Helpers.extractNodes(" John  Smith 1 2  3 4 5");
    for (int i = 0; i < 5; i++)
      assertEquals(Optional.of(i + 1), Optional.of(test.get(i)));
  }

  @Test
  void convertToKeyValuesTest() {
    List<Integer> list = new ArrayList<>();
    List<WeightedEdge> edges = new ArrayList<>();
    Collections.addAll(list, 2, 11, 5, 7, 8, 9, 10, 3);
    edges = new ArrayList<>();
    Collections.addAll(edges,
            new WeightedEdge(11, 2, 9),
            new WeightedEdge(11, 9, 8),
            new WeightedEdge(11, 10, 7),
            new WeightedEdge(5, 11, 7),
            new WeightedEdge(7, 11, 4),
            new WeightedEdge(7, 8, 3),    //
            new WeightedEdge(8, 9, 2),
            new WeightedEdge(3, 8, 4),
            new WeightedEdge(3, 10, 7),
            new WeightedEdge(10, 8, 3),
            new WeightedEdge(9, 10, 4)
    );
    Helpers.convertToKeyValues(list, edges);
    assertEquals(1, edges.get(0).u);
    assertEquals(0, edges.get(0).v);

    assertEquals(3, edges.get(5).u);
    assertEquals(4, edges.get(5).v);
  }

}