package de.hhn.it.devtools.javafx.controllers.memory;

import java.util.HashMap;
import java.util.Map;

/**
 * AttributeStore of memory
 */
public class MemoryAttributeStore {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(MemoryAttributeStore.class);

  private static MemoryAttributeStore myself = new MemoryAttributeStore();

  Map<String, Object> attributeMap;

  private MemoryAttributeStore() {
    attributeMap = new HashMap<>();
  }

  public static MemoryAttributeStore getReference() {
    return myself;
  }

  private void assertNameIsValid(final String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("parameter name is invalid: ->" + name + "<-");
    }
  }

  public void setAttribute(final String name, final Object object) {
    assertNameIsValid(name);
    if (object == null) {
      throw new IllegalArgumentException("Object is null reference");
    }
    attributeMap.put(name, object);
  }

  public Object getAttribute(final String name) {
    assertNameIsValid(name);
    if (!attributeMap.containsKey(name)) {
      throw new IllegalArgumentException("No object known for name " + name);
    }
    return attributeMap.get(name);
  }

  public void removeAttribute(final String name) {
    assertNameIsValid(name);

    if (!attributeMap.containsKey(name)) {
      throw new IllegalArgumentException("Object not known for name " + name);
    }
    attributeMap.remove(name);
  }
}
