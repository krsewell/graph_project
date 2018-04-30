// Copyright (c) 2018. Kristopher J Sewell, All Rights Reserved.
// File: Control.java  Module: graph_tiefighter
// Net_ID: kjs170430

package TieFighter.Controller;


import TieFighter.Model.Pilot;
import TieFighter.Model.WeightedEdge;
import TieFighter.Model.WeightedGraph;
import TieFighter.View.InputFile;
import TieFighter.View.OutputFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Control {

  public Control(String galaxy, String pilot_roster, String report) throws IOException {
    InputFile inputFile = new InputFile(Paths.get(galaxy));
    OutputFile outputFile = new OutputFile(Paths.get(report));

    List<Integer> vertices = Helpers.getVertices(inputFile.getStream());
    List<WeightedEdge> edges = Helpers.getEdges(inputFile.getStream());
    Helpers.convertToKeyValues(vertices, edges);
    WeightedGraph<Integer> galaxy_map = new WeightedGraph<>(vertices, edges);

    inputFile = new InputFile(Paths.get(pilot_roster));
    List<Pilot> pilots = Helpers.getPilots(inputFile);
    for (Pilot pilot : pilots)
      Helpers.convertToKeyValues(vertices, pilot.getVertices());

    pilots.forEach(e -> e.testPrescribedPath(galaxy_map));
    Collections.sort(pilots);
    pilots.forEach(e -> e.setSort(Pilot.sort_preference.LENGTH_ASC));
    Collections.sort(pilots);
    pilots.forEach(e -> outputFile.write(e.toString()));
  }



}
