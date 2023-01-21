package de.hhn.it.devtools.javafx.duckhunt;

import java.util.HashMap;
import java.util.Map;

/**
 * AttributeStore of duckhunt.
 */
public class DuckHuntAttributeStore {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(DuckHuntAttributeStore.class);

  private static DuckHuntAttributeStore myself = new DuckHuntAttributeStore();

  Map<String, Object> attributeMap;

  private DuckHuntAttributeStore() {
    attributeMap = new HashMap<>();
  }

  public static DuckHuntAttributeStore getReference() {
    return myself;
  }

  private void assertNameIsValid(final String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("parameter name is invalid: ->" + name + "<-");
    }
  }

  /**
   * Sets an attribute.
   *
   * @param name name of the attribute
   * @param object object of the attribute
   */
  public void setAttribute(final String name, final Object object) {
    assertNameIsValid(name);
    if (object == null) {
      throw new IllegalArgumentException("Object is null reference");
    }
    attributeMap.put(name, object);
  }

  /**
   * Return an attribute.
   *
   * @param name name of the attribute
   * @return object the attribute
   */
  public Object getAttribute(final String name) {
    assertNameIsValid(name);
    if (!attributeMap.containsKey(name)) {
      throw new IllegalArgumentException("No object known for name " + name);
    }
    return attributeMap.get(name);
  }

  /**
   * Removes an attribute.
   *
   * @param name name of the attribute
   */
  public void removeAttribute(final String name) {
    assertNameIsValid(name);

    if (!attributeMap.containsKey(name)) {
      throw new IllegalArgumentException("Object not known for name " + name);
    }
    attributeMap.remove(name);
  }
}
