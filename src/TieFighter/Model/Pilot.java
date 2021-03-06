// Copyright (c) 2018. Kristopher J Sewell, All Rights Reserved.
// File: Pilot.java  Module: graph_tiefighter
// Net_ID: kjs170430

package TieFighter.Model;

import java.util.LinkedList;

public class Pilot implements Comparable<Pilot> {

  private String name;
  private double length;
  private boolean validPath;
  private sort_preference sort = sort_preference.ALPHA_ASC;
  private LinkedList<Integer> vertices = new LinkedList<>();

  /*
    Getters and Setters
   */

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getLength() {
    return length;
  }

  public void setLength(double length) {
    //set to public for test cases to be independent of other logic that computes length.
    //TODO: change to private before production.
    if (length < 0) throw new IllegalArgumentException("length may not be negative");
    this.length = length;
  }

  public boolean isValidPath() {
    return validPath;
  }

  private void setValidPath(boolean validPath) {
    this.validPath = validPath;
  }

  public sort_preference getSort() {
    return sort;
  }

  public void setSort(sort_preference sort) {
    this.sort = sort;
  }

  public LinkedList<Integer> getVertices() {
    return vertices;
  }

  public Pilot(String name, LinkedList<Integer> vertices) {
    this.name = name;
    this.vertices = vertices;
  }

  /*
    Constructors
  */

  public Pilot(String name) {
    this.name = name;
  }

  public void setVertices(LinkedList<Integer> vertices) {
    this.vertices = vertices;
  }

  /*
    Comparable, toString, algorithm functions
  */

  @Override
  public int compareTo(Pilot p) {
    if (sort == sort_preference.ALPHA_ASC)
      return this.name.compareToIgnoreCase(p.getName());
    else if (sort == sort_preference.LENGTH_ASC) {
      Double d = this.length - p.getLength();
      return d.intValue();
    }
    return 0;
  }

  @Override
  public String toString() {
    return String.format("%-30s", name) +
            String.format(length != Double.POSITIVE_INFINITY ? "%-15.0f" : "%-15s",
                    length != Double.POSITIVE_INFINITY ? length : "") +
            String.format("%-10s", validPath ? "valid" : "invalid") +
            System.lineSeparator();
  }

  public double getDirectPathLength(int start, int end, WeightedGraph<Integer> graph) {
    //WeightedGraph<Integer>.ShortestPathTree shortestPath = graph.getShortestPath(start);
    double d; //double d = shortestPath.getCost(end);
    try {
      d = graph.getWeight(start, end);  //throws if edge doesn't exist.
    } catch (Exception ex) {
      d = Double.POSITIVE_INFINITY;     //if it doesn't exist cost is infinite.
    }
    setLength(d);
    if (this.length != Double.POSITIVE_INFINITY) setValidPath(true);
    return d;
  }

  public void testPrescribedPath(WeightedGraph<Integer> graph) {
    if (vertices != null) {
      double sum = 0;
      while (vertices.size() != 1) {
        Integer var = vertices.pop();
        Integer var2 = vertices.get(0);
        if (var >= 0 && var2 >= 0)    //added to check for non-existent nodes which look like -1 at this point
          sum += getDirectPathLength(var, var2, graph);
        else sum = Double.POSITIVE_INFINITY;
        if (sum == Double.POSITIVE_INFINITY) break;
      }
      vertices = null;
      setLength(sum);
      validPath = sum != Double.POSITIVE_INFINITY;
    } else
      System.out.println("No Path to Test");
  }


  public enum sort_preference {ALPHA_ASC, LENGTH_ASC}
}
