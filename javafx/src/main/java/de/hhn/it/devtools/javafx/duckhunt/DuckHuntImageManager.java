package de.hhn.it.devtools.javafx.duckhunt;

import javafx.scene.image.ImageView;

import java.net.URL;

public enum DuckHuntImageManager {
  NORTHDUCK("/images/duckhunt/NorthDuck.gif");

  public ImageView image;
  public URL path;

  DuckHuntImageManager(String stringPath) {
    path = getClass().getResource(stringPath);
    image = new ImageView(path.toExternalForm());
    image.setPreserveRatio(true);
    image.setFitHeight(100);
  }
}
