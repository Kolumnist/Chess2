package de.hhn.it.devtools.javafx.controllers.connectfour.helper.io;

import de.hhn.it.devtools.apis.connectfour.IllegalNameException;
import de.hhn.it.devtools.apis.connectfour.Profile;
import de.hhn.it.devtools.apis.connectfour.helper.Profile;
import de.hhn.it.devtools.javafx.controllers.connectfour.helper.Instance;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class FileIO {

  HashMap<UUID, Profile> profiles;

  public FileIO(){

  }

  public void loadProfileData() {
    File in = new File(getFilePath());
    try {
      FileInputStream fis = new FileInputStream(in);
      ObjectInputStream ois = new ObjectInputStream(fis);
      profiles = (HashMap<UUID, Profile>) ois.readObject();
      Instance.getInstance().setProfiles(profiles.values().stream().toList());
    } catch (Exception ex) {
      System.err.println(ex);
    }
  }


  public void saveProfileData() {
    profiles = Instance.getInstance().getProfilesForSave();
    File out = new File(getFilePath());
    try {
      new FileOutputStream(getFilePath()).close();
      FileOutputStream fos = new FileOutputStream(out);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(profiles);
    } catch (Exception ex) {
      System.err.println(ex);
    }
  }

  private String getFilePath(){
    String path = "javafx/src/main/resources/fxml/connectfour/files/Profiles.txt";
    File file = new File(path);
    String absolutePath = file.getAbsolutePath();
    return absolutePath;
  }

}
