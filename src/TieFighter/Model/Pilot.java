package TieFighter.Model;

public class Pilot {
  enum sort{
    ALPHA_ASC, LENGTH_ASC
  }

  private String name;
  private double length;
  private boolean validPath;
  private int sort;
  private LinkedList vertices = new LinkedList();

  public Pilot(String name) {
    this.name = name;
  }

  public Pilot(String name, LinkedList vertices) {
    this.name = name;
    this.vertices = vertices;
  }

}
