package de.hhn.it.devtools.javafx.ttrpgsheets;

import de.hhn.it.devtools.apis.ttrpgsheets.CharacterDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple class to save and load {@link CharacterDescriptor} from file system.
 */
public class CharacterSheetFileIo {
  private static final Logger logger = LoggerFactory.getLogger(CharacterSheetFileIo.class);

  private final FileChooser fileChooser;
  private Window parent;

  /**
   * Constructor for {@link CharacterSheetFileIo}.
   * The file extension filter is set here.
   */
  public CharacterSheetFileIo() {
    logger.info("Constructor : no params");
    fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("TTRPG Character Sheet files (*.tcs)", "*.tcs"));
  }

  public void setParent(Window parent) {
    this.parent = parent;
  }

  /**
   * Saves the {@link CharacterDescriptor} with the given name as the standard file name.
   *
   * @param name the standard file name
   * @param characterDescriptor the {@link CharacterDescriptor} to save
   */
  public void saveCharacterFile(String name, CharacterDescriptor characterDescriptor) {
    logger.info("saveCharacterFile : name = {}, characterDescriptor = {}",
            name, characterDescriptor);
    fileChooser.setTitle("Save your character");
    fileChooser.setInitialFileName(name.isBlank() ? "My Character" : name);
    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

    File saveFile = fileChooser.showSaveDialog(parent);
    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(saveFile))) {
      outputStream.writeObject(characterDescriptor);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  /**
   * Loads a {@link CharacterDescriptor} from a file the user selected.
   *
   * @return the loaded {@link CharacterDescriptor}
   */
  public CharacterDescriptor loadCharacterFile() {
    logger.info("loadCharacterFile : no params");
    fileChooser.setTitle("Load your character");
    fileChooser.setInitialFileName("");
    fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

    CharacterDescriptor loadedCharacter = null;
    File loadFile = fileChooser.showOpenDialog(parent);
    try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(loadFile))) {
      loadedCharacter = (CharacterDescriptor) inputStream.readObject();
    } catch (IOException exception) {
      exception.printStackTrace();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    return loadedCharacter;
  }
}
