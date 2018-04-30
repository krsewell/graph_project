// Copyright (c) 2018. Kristopher J Sewell, All Rights Reserved.
// File: Main.java  Module: graph_tiefighter
// Net_ID: kjs170430

package TieFighter;

import TieFighter.Controller.Control;

import java.io.IOException;

public class Main {

  public static void main(String[] args) {
    String galaxy = "galaxy.txt";
    String pilot_roster = "pilot_routes.txt";
    String report = "patrols.txt";

    try {
      Control control = new Control(galaxy, pilot_roster, report);
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.exit(0);
  }
}
