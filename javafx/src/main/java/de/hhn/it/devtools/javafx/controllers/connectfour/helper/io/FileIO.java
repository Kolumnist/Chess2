package de.hhn.it.devtools.javafx.controllers.connectfour.helper.io;

import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.UUID;

public class FileIO {

  HashMap<UUID, Profile> profiles;

  public void loadProfileData() {
    File in = new File(getFilePath());
    try {
      FileInputStream fis = new FileInputStream(in);
      ObjectInputStream ois = new ObjectInputStream(fis);
      profiles = (HashMap<UUID, Profile>) ois.readObject();
      Instance.getInstance().setProfiles(profiles);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  public void saveProfileData() {
    profiles = Instance.getInstance().getProfiles();
    File out = new File(getFilePath());
    try {
      new FileOutputStream(getFilePath()).close();
      FileOutputStream fos = new FileOutputStream(out);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(profiles);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  private String getFilePath() {
    String path = "javafx/src/main/resources/fxml/connectfour/files/Profiles.txt";
    File file = new File(path);
    return file.getAbsolutePath();
  }
}
