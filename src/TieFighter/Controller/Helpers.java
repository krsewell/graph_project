package TieFighter.Controller;

import TieFighter.Model.Pilot;
import TieFighter.Model.WeightedEdge;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Helpers {
  private static final Matcher INT_MATCHER =
          Pattern.compile("^-?\\d+$").matcher("");
  private static final Matcher DOUBLE_MATCHER =
          Pattern.compile("^[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?$").matcher("");

  private Helpers() {
  }


  public static List<Integer> getVertices(Stream<String> stream) {
    return stream.map(Helpers::extractVert)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
  }

  public static List<List<WeightedEdge>> getEdges(Stream<String> stream) {
    return stream.map(Helpers::extractEdges)
            .collect(Collectors.toList());
  }

  public static String extractVert(String s) {
    String sa = s.trim();
    sa = sa.substring(0, sa.indexOf(" "));
    if (INT_MATCHER.reset(sa).matches())
      return sa;
    else throw new IllegalArgumentException("Vertex not proper format.");
  }

  public static List<WeightedEdge> extractEdges(String s) {
    String[] sa = s.trim().split(" ");

    int u = 0;
    if (INT_MATCHER.reset(sa[0]).matches()) {
      u = Integer.parseInt(sa[0]);
    }

    List<WeightedEdge> list = new ArrayList<>();

    for (int i = 1; i < sa.length; i++) {
      if (DOUBLE_MATCHER.reset(sa[i].substring(0, sa[i].indexOf(","))).matches()) {
        if (DOUBLE_MATCHER.reset(sa[i].substring(sa[i].indexOf(",") + 1)).matches()) {
          list.add(new WeightedEdge(
                  u,
                  Integer.parseInt(sa[i].substring(0, sa[i].indexOf(","))),
                  Double.parseDouble(sa[i].substring(sa[i].indexOf(",") + 1))
          ));
        }
      }
    }

    return list;
  }


  public static List<Pilot> getPilots(Stream<String> stream) {
    return new ArrayList<>();
  }
}
