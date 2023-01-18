module devtools.components {
  exports de.hhn.it.devtools.components.example.coffeemakerservice.provider;
  exports de.hhn.it.devtools.components.connectfour.provider;
  requires org.slf4j;
  requires devtools.apis;
  provides de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerService
      with de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;
  provides de.hhn.it.devtools.apis.examples.coffeemakerservice.AdminCoffeeMakerService
      with de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;
  provides de.hhn.it.devtools.apis.connectfour.ConnectFour
      with de.hhn.it.devtools.components.connectfour.provider.ConnectFour;
}
