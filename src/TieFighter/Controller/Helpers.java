// Copyright (c) 2018. Kristopher J Sewell, All Rights Reserved.
// File: Helpers.java  Module: graph_tiefighter
// Net_ID: kjs170430

package TieFighter.Controller;

import TieFighter.Model.Pilot;
import TieFighter.Model.WeightedEdge;
import TieFighter.View.InputFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public final class Helpers {
  private static final Matcher INT_MATCHER =
          Pattern.compile("^-?\\d+$").matcher("");
  private static final Matcher DOUBLE_MATCHER =
          Pattern.compile("^[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?$").matcher("");
  private static final Matcher NAME_MATCHER =
          Pattern.compile("^(?=.*[a-zA-Z])([a-zA-Z0-9_']+)$").matcher("");

  private Helpers() {
  }


  static List<Integer> getVertices(Stream<String> stream) {
    return stream.map(Helpers::extractVert)
            .map(Integer::parseInt)
            .collect(toList());
  }

  public static String extractVert(String s) {
    String sa = s.trim();
    sa = sa.substring(0, sa.indexOf(" "));
    if (INT_MATCHER.reset(sa).matches())
      return sa;
    else throw new IllegalArgumentException("Vertex not proper format.");
  }


  static List<WeightedEdge> getEdges(Stream<String> stream) {
    List<List<WeightedEdge>> list = stream.map(Helpers::extractEdges)
            .collect(toList());

    List<WeightedEdge> rtnList = new ArrayList<>();
    for (List<WeightedEdge> weightedEdges : list) {
      rtnList.addAll(weightedEdges);
    }
    return rtnList;
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


  static List<Pilot> getPilots(InputFile pilotFile) throws IOException {

    Stream<String> stream = pilotFile.getStream();

    List<Pilot> pilots = stream.map(Helpers::extractName)
            .map(Pilot::new)
            .collect(toList());

    // because I need to use the stream more then once it must be reinitialized here.
    stream = pilotFile.getStream();
    List<LinkedList<Integer>> pilotNodes = stream.map(Helpers::extractNodes)
            .collect(toList());

    for (Pilot p : pilots) {
      p.setVertices(pilotNodes.remove(0)); //pop first LinkedList and add to pilot
    }

    return pilots;
  }

  public static String extractName(String line) {
    //look for alpha characters
    String[] splits = line.split(" ");
    StringBuilder name = new StringBuilder();
    for (String split : splits) {
      if (NAME_MATCHER.reset(split).matches()) {
        name.append(" ").append(split);
      }
    }

    return name.toString().trim();
  }

  public static LinkedList<Integer> extractNodes(String line) {
    String[] splits = line.split(" ");
    LinkedList<Integer> nodes = new LinkedList<>();
    for (String split : splits) {
      if (!NAME_MATCHER.reset(split).matches() && !split.equals("")) {
        nodes.add(Integer.parseInt(split.trim()));
      }
    }
    return nodes;
  }


  public static void convertToKeyValues(List<Integer> list, List<WeightedEdge> edges) {
    edges.forEach(e -> {
      e.u = list.indexOf(e.u);
      e.v = list.indexOf(e.v);
    });
  }

  static void convertToKeyValues(List<Integer> list, LinkedList<Integer> nodes) {
    for (int i = 0; i < nodes.size(); i++) {
      nodes.set(i, list.indexOf(nodes.get(i)));
    }

  }
}
