import TieFighter.Controller.Helpers;
import TieFighter.Model.WeightedEdge;
import org.junit.jupiter.api.Test;

import java.util.List;

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
    //TODO: implement a function that revalues each edge.*/
  }
}