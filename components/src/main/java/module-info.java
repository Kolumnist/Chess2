import de.hhn.it.devtools.components.battleship.cmpBattleshipService;

module devtools.components {
  exports de.hhn.it.devtools.components.example.coffeemakerservice.provider;
  exports de.hhn.it.devtools.components.battleship;
  exports de.hhn.it.devtools.components.duckhunt;
  exports de.hhn.it.devtools.components.chess2;
  requires org.slf4j;
  requires devtools.apis;
  provides  de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerService
          with de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;
  provides  de.hhn.it.devtools.apis.examples.coffeemakerservice.AdminCoffeeMakerService
          with de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;
  provides  de.hhn.it.devtools.apis.battleship.BattleshipService
          with de.hhn.it.devtools.components.battleship.cmpBattleshipService;
  provides de.hhn.it.devtools.apis.duckhunt.DuckHuntService
          with de.hhn.it.devtools.components.duckhunt.DuckHunt;
        }
