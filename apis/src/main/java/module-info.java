/**
 * Module descriptor for the apis module.
 */
module devtools.apis {
  requires org.slf4j;
  exports de.hhn.it.devtools.apis.examples.coffeemakerservice;
  exports de.hhn.it.devtools.apis.exceptions;
  // -----------------------------------------------------------------------------------------------
  exports de.hhn.it.devtools.apis.connectfour.exceptions;                           // Connect Four
  exports de.hhn.it.devtools.apis.connectfour.enums;
  exports de.hhn.it.devtools.apis.connectfour.interfaces;
  exports de.hhn.it.devtools.apis.connectfour.helper;
  // -----------------------------------------------------------------------------------------------
}
