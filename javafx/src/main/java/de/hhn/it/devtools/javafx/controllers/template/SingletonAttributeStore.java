package de.hhn.it.devtools.javafx.controllers.template;

import java.util.HashMap;
import java.util.Map;

public class SingletonAttributeStore  {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(SingletonAttributeStore.class);


  private static SingletonAttributeStore myself = new SingletonAttributeStore();

  Map<String, Object> attributeMap;

  private SingletonAttributeStore() {
    attributeMap = new HashMap<>();
  }

  public static SingletonAttributeStore getReference() {
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
