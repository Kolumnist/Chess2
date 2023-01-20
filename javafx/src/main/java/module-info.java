module devtools.javafx {
  requires org.slf4j;
  requires devtools.apis;
  requires devtools.components;
  requires javafx.controls;
  requires javafx.fxml;
  uses de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerService;
  uses de.hhn.it.devtools.apis.examples.coffeemakerservice.AdminCoffeeMakerService;
  uses de.hhn.it.devtools.apis.wordle.WordleService;
  opens de.hhn.it.devtools.javafx.controllers.wordleAdditional to javafx.fxml;
  opens de.hhn.it.devtools.javafx.controllers to javafx.fxml;
  opens de.hhn.it.devtools.javafx.controllers.coffeemaker to javafx.fxml;
  opens de.hhn.it.devtools.javafx.controllers.template to javafx.fxml;
  exports de.hhn.it.devtools.javafx.controllers.wordleAdditional;
  exports de.hhn.it.devtools.javafx;
  exports de.hhn.it.devtools.javafx.controllers;
  exports de.hhn.it.devtools.javafx.controllers.coffeemaker;
        }
