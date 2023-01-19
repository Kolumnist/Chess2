import de.hhn.it.devtools.apis.connectfour.interfaces.IConnectFour;
import de.hhn.it.devtools.apis.connectfour.interfaces.IConnectFourListener;
import de.hhn.it.devtools.components.connectfour.provider.ConnectFour;
import de.hhn.it.devtools.components.connectfour.provider.helper.ConnectFourListener;

module devtools.components {
  exports de.hhn.it.devtools.components.example.coffeemakerservice.provider;
  // -----------------------------------------------------------------------------------------------
  exports de.hhn.it.devtools.components.connectfour.provider;                       // Connect Four
  exports de.hhn.it.devtools.components.connectfour.provider.helper;
  // -----------------------------------------------------------------------------------------------
  requires org.slf4j;
  requires devtools.apis;
  provides de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerService
      with de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;
  provides de.hhn.it.devtools.apis.examples.coffeemakerservice.AdminCoffeeMakerService
      with de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;
  // -----------------------------------------------------------------------------------------------
  provides IConnectFour                                                             // Connect Four
      with ConnectFour;
  provides IConnectFourListener
      with ConnectFourListener;
  // -----------------------------------------------------------------------------------------------
}
