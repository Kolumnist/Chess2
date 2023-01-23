package de.hhn.it.devtools.javafx.duckhunt;

import de.hhn.it.devtools.apis.duckhunt.DuckOrientation;
import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Provides the different duck images.
 */
public enum DuckHuntImageManager {
  NORTHDUCK("/images/duckhunt/NorthDuck.gif"),
  NORTHWESTDUCK("/images/duckhunt/NorthWestDuck.gif"),
  WESTDUCK("/images/duckhunt/WestDuck.gif"),
  NORTHEASTDUCK("/images/duckhunt/NorthEastDuck.gif"),
  EASTDUCK("/images/duckhunt/EastDuck.gif"),
  SCAREDDUCK("/images/duckhunt/ScaredDuck.png"),
  FALLINGDUCK("/images/duckhunt/FallingDuck.gif");

  private Image image;
  private URL path;

  DuckHuntImageManager(String stringPath) {
    path = getClass().getResource(stringPath);
    image = new Image(path.toExternalForm());
  }

  /**
   * Supplies the image view.
   *
   * @return image view of duck
   */
  public ImageView getImageView() {
    ImageView imageView = new ImageView(image);
    imageView.setPreserveRatio(true);
    imageView.setFitHeight(100);
    return imageView;
  }

  public URL getPath() {
    return path;
  }

  /**
   * Translates DuckOrientation to related duck image enum.
   *
   * @param orientation of the duck
   * @return image enum
   */
  public static DuckHuntImageManager getDuckImageFromOrientation(DuckOrientation orientation) {
    switch (orientation) {
      case SOUTH, NORTHEAST -> {
        return NORTHEASTDUCK;
      }
      case EAST, SOUTHEAST -> {
        return EASTDUCK;
      }
      case SOUTHWEST, WEST -> {
        return WESTDUCK;
      }
      case NORTHWEST -> {
        return NORTHWESTDUCK;
      }
      default -> {
        return NORTHDUCK;
      }
    }
  }
}
