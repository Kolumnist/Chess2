package de.hhn.it.devtools.javafx.controllers.connectfour.helper.io;

import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

public class FileIO {

  public FileIO() {

  }

  public void loadProfileData() {
    List<Profile> profiles = new LinkedList<>();
    File in = new File(getClass().getResource("/fxml/connectfour/files/Profiles.txt").getPath());
    if (in.exists() && !in.isDirectory()) {
      try (FileInputStream fis = new FileInputStream(in);
           ObjectInputStream ois = new ObjectInputStream(fis)) {
        profiles = (LinkedList<Profile>) ois.readObject();
      } catch (Exception ex) {
        System.err.println(ex);
      }
      int count = 0;
      for (; count < profiles.size(); count++) {
        try {
          Instance.getConnectFour().createProfile(profiles.get(count).getName());
        } catch (IllegalArgumentException ex) {

        }
      }
    }
  }


  public void saveProfileData() {
    List<Profile> profiles;
    profiles = Instance.getConnectFour().getProfiles();
    File out = new File(getClass().getResource("/fxml/connectfour/files/Profiles.txt").getPath());
    try (FileOutputStream fos = new FileOutputStream(out);
         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
      oos.writeObject(profiles);
    } catch (Exception ex) {
      System.err.println(ex);
    }
  }

}
