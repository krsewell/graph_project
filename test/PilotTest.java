import TieFighter.Model.Pilot;
import TieFighter.Model.WeightedEdge;
import TieFighter.Model.WeightedGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PilotSortTests {
  private List<Pilot> list;
  private Pilot p1;
  private Pilot p2;
  private Pilot p3;
  private Pilot p4;

  @BeforeEach
  void setUp() {
    p1 = new Pilot("Johnny Walker");
    p2 = new Pilot("jose Cuevo");
    p3 = new Pilot("Jim Bean");
    p4 = new Pilot("Ron Zacapa");
    p1.setLength(1.0);
    p2.setLength(4.0);
    p3.setLength(32.0);
    p4.setLength(4.0);

    list = new ArrayList<>();
    list.add(p1);
    list.add(p4);
    list.add(p3);
    list.add(p2);
  }

  @Test
  void testAlphaSort() {

    Collections.sort(list);

    assertEquals(list.get(0).getName(), "Jim Bean");
    assertEquals(list.get(1).getName(), "Johnny Walker");
    assertEquals(list.get(2).getName(), "jose Cuevo");
    assertEquals(list.get(3).getName(), "Ron Zacapa");
  }

  @Test
  void testLengthSort() {

    list.forEach(e -> e.setSort(Pilot.sort_preference.LENGTH_ASC));
    Collections.sort(list);

    assertEquals(list.get(0).getLength(), 1.00);
    assertEquals(list.get(1).getLength(), 4.00);
    assertEquals(list.get(2).getLength(), 4.00);
    assertEquals(list.get(3).getLength(), 32.00);
  }

  @Test
  void testDoubleSort() {
    Collections.sort(list);
    list.forEach(e -> e.setSort(Pilot.sort_preference.LENGTH_ASC));
    Collections.sort(list);

    assertEquals(list.get(0), p1);
    assertEquals(list.get(1), p2);
    assertEquals(list.get(2), p4);
    assertEquals(list.get(3), p3);

  }

}

class PilotTest {

  private Pilot pilot;
  private Pilot pilot1;

  private List<Integer> vertices;
  private List<WeightedEdge> edges;
  private WeightedGraph<Integer> graph;

  @BeforeEach
  @Resource
  void setUp() {
    vertices = new ArrayList();
    Collections.addAll(vertices, 2, 11, 5, 7, 8, 9, 10, 3);

    edges = new ArrayList<>();
    Collections.addAll(edges, new WeightedEdge(vertices.indexOf(11), vertices.indexOf(2), 9),
            new WeightedEdge(vertices.indexOf(11), vertices.indexOf(9), 8),
            new WeightedEdge(vertices.indexOf(11), vertices.indexOf(10), 7),
            new WeightedEdge(vertices.indexOf(5), vertices.indexOf(11), 7),
            new WeightedEdge(vertices.indexOf(7), vertices.indexOf(11), 4),
            new WeightedEdge(vertices.indexOf(7), vertices.indexOf(8), 3),
            new WeightedEdge(vertices.indexOf(8), vertices.indexOf(9), 2),
            new WeightedEdge(vertices.indexOf(3), vertices.indexOf(8), 4),
            new WeightedEdge(vertices.indexOf(3), vertices.indexOf(10), 7),
            new WeightedEdge(vertices.indexOf(10), vertices.indexOf(8), 3),
            new WeightedEdge(vertices.indexOf(9), vertices.indexOf(10), 4)
    );

    graph = new WeightedGraph<>(vertices, edges);

    pilot = new Pilot("Freddy David");
    pilot1 = new Pilot("Mark Taine");
  }

  @Test
  void testToString() {

//    System.out.print(pilot1.toString());
//    System.out.print(pilot.toString());
    assertEquals("Freddy David                  0.0            invalid   " + System.lineSeparator(),
            pilot.toString());
    assertEquals("Mark Taine                    0.0            invalid   " + System.lineSeparator(),
            pilot1.toString());
  }

  @Test
  void testGetPathSimple() {
    pilot.getShortestPathLength(vertices.indexOf(11), vertices.indexOf(2), graph);
    assertEquals(9, pilot.getLength());

  }

  @Test
  void testGetPathLonger() {
    pilot.getShortestPathLength(vertices.indexOf(3), vertices.indexOf(9), graph);
    assertEquals(6, pilot.getLength());
  }

  @Test
  void testGetPathInvalid() {
    pilot.getShortestPathLength(vertices.indexOf(10), vertices.indexOf(2), graph);
    assertEquals(Double.POSITIVE_INFINITY, pilot.getLength());
  }

  @Test
  void testGetPathLoop() {
    pilot.getShortestPathLength(vertices.indexOf(7), vertices.indexOf(10), graph);
    assertEquals(9, pilot.getLength());
  }


  @Test
  void testPrescribedPathSimple() {
    LinkedList<Integer> list = new LinkedList<>();
    Collections.addAll(list, vertices.indexOf(11), vertices.indexOf(2));
    pilot.setVertices(list);
    pilot.testPrescribedPath(graph);

    assertTrue(pilot.isValidPath());
    assertEquals(9, pilot.getLength());
  }

  @Test
  void testPrescibedPathLong() {
    LinkedList<Integer> list = new LinkedList<>();
    Collections.addAll(list, vertices.indexOf(11),
            vertices.indexOf(10),
            vertices.indexOf(8),
            vertices.indexOf(9),
            vertices.indexOf(10));
    pilot.setVertices(list);
    pilot.testPrescribedPath(graph);

    assertTrue(pilot.isValidPath());
    assertEquals(16, pilot.getLength());
  }

  @Test
  void testPrescribedPathInvalid() {
    LinkedList<Integer> list = new LinkedList<>();
    Collections.addAll(list, vertices.indexOf(2), vertices.indexOf(9));
    pilot.setVertices(list);
    pilot.testPrescribedPath(graph);

    assertFalse(pilot.isValidPath());
    assertEquals(Double.POSITIVE_INFINITY, pilot.getLength());
  }
}