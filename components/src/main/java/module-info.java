import de.hhn.it.devtools.components.battleship.provider.CmpBattleshipService;

module devtools.components {
  exports de.hhn.it.devtools.components.example.coffeemakerservice.provider;
  exports de.hhn.it.devtools.components.duckhunt;
  exports de.hhn.it.devtools.components.chess2;
  exports de.hhn.it.devtools.components.battleship.provider;
  requires org.slf4j;
  requires devtools.apis;
  //requires devtools.javafx;
  provides  de.hhn.it.devtools.apis.examples.coffeemakerservice.AdminCoffeeMakerService
          with de.hhn.it.devtools.components.example.coffeemakerservice.provider.WnckCoffeeMakerService;
  provides  de.hhn.it.devtools.apis.battleship.BattleshipService
          with CmpBattleshipService;
  provides de.hhn.it.devtools.apis.duckhunt.DuckHuntService
          with de.hhn.it.devtools.components.duckhunt.DuckHunt;
  provides de.hhn.it.devtools.apis.game2048.Game2048Service
          with de.hhn.it.devtools.components.game2048.provider.ProviderGame2048;

}
