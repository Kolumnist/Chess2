package de.hhn.it.devtools.javafx.duckhunt;

import de.hhn.it.devtools.apis.duckhunt.DuckOrientation;
import javafx.geometry.NodeOrientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

public enum DuckHuntImageManager {
  NORTHDUCK("/images/duckhunt/NorthDuck.gif", false),
  NORTHWESTDUCK("/images/duckhunt/NorthWestDuck.gif", false),
  WESTDUCK("/images/duckhunt/WestDuck.gif", false),
  NORTHEASTDUCK("/images/duckhunt/NorthWestDuck.gif", true),
  EASTDUCK("/images/duckhunt/WestDuck.gif", true),
  SCAREDDUCK("/images/duckhunt/ScaredDuck.png", false),
  FALLINGDUCK("/images/duckhunt/FallingDuck.gif", false);

  private Image image;
  private URL path;

  DuckHuntImageManager(String stringPath, boolean isMirrored) {
    path = getClass().getResource(stringPath);
    image = new Image(path.toExternalForm());
  }

  public ImageView getImageView() {
    ImageView imageView = new ImageView(image);
    imageView.setPreserveRatio(true);
    imageView.setFitHeight(100);
    return imageView;
  }

  public URL getPath() {
    return path;
  }

  public static DuckHuntImageManager getDuckImageFromOrientation(DuckOrientation orientation) {
    switch (orientation) {
      case NORTH, SOUTH -> {
        return NORTHDUCK;
      }
      case NORTHWEST, SOUTHWEST -> {
        return NORTHWESTDUCK;
      }
      case WEST -> {
        return WESTDUCK;
      }
      case NORTHEAST, SOUTHEAST -> {
        return NORTHEASTDUCK;
      }
      case EAST -> {
        return EASTDUCK;
      }
      default -> {
        return NORTHDUCK;
      }
    }
  }
}
