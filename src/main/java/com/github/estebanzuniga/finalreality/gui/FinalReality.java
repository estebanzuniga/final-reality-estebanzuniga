package com.github.estebanzuniga.finalreality.gui;

import com.github.estebanzuniga.finalreality.controller.GameController;
import com.github.estebanzuniga.finalreality.controller.phases.InvalidMovementException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.control.Button;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Main entry point for the application.
 * <p>
 * <Complete here with the details of the implemented application>
 *
 * @author Ignacio Slater Muñoz.
 * @author Esteban Zúñiga Salamanca.
 */
public class FinalReality extends Application {

  private final GameController controller = new GameController();
  private Label partySizeLabel;
  private Label phaseLabel;
  private Label mainLabel;
  private Label enemiesLabel;
  private Label playerCharactersLabel;
  private Label inventoryLabel;
  private Label aliveEnemiesLabel;
  private Label alivePlayerCharacterLabel;
  private Label turnLabel;
  private Label characterNameLabel;
  private Label characterLifeLabel;
  private Label characterDefenseLabel;
  private Label equippedWeaponLabel;
  private Label characterAttackLabel;
  private Label inventorySizeLabel;
  private Pane enemyPane = new Pane();
  private Pane playerPane = new Pane();

  private Label aLabel;
  private Scene actualScene = createSetPartyScene();
  private final Random rng = new Random();

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(@NotNull Stage primaryStage) {

    //SET PARTY
    primaryStage.setTitle("Final reality");
    //primaryStage.setResizable(false);
    startAnimatorPrimaryScene(primaryStage);
    primaryStage.show();
  }

  private void startAnimatorPrimaryScene(Stage primaryStage) {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        primaryStage.setScene(actualScene);
      }
    };
    timer.start();
  }


  //SET PARTY SCENE

  private Scene createSetPartyScene() {
    Group root = new Group();
    Scene scene = new Scene(root, 400, 400);

    Label label = new Label("Choose three different characters");
    label.setAlignment(Pos.TOP_CENTER);
    label.setMinSize(336,40);
    root.getChildren().add(label);

    List<String> buttons = Arrays.asList("Engineer", "Knight", "Thief", "White Mage", "Black Mage");

    FlowPane pane = new FlowPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(30, 20, 30, 20));
    pane.setHgap(5);
    pane.setVgap(5);
    pane.setMinWidth(400);
    pane.setPrefWidth(400);
    pane.setMaxWidth(400);
    root.getChildren().add(pane);

    for (String button : buttons) {
      Button b = new Button(button);
      b.setMinSize(60, 60);
      pane.getChildren().add(b);
      switch(button) {
        case "Engineer":
          b.setOnAction((e) -> {controller.createEngineer("Engineer", 500, 50); b.setDisable(true);});
          break;
        case "Knight":
          b.setOnAction((e) -> {controller.createKnight("Knight", 500, 50); b.setDisable(true);});
          break;
        case "Thief":
          b.setOnAction((e) -> {controller.createThief("Thief", 500, 50); b.setDisable(true);});
          break;
        case "White Mage":
          b.setOnAction((e) -> {controller.createKnight("White Mage", 500, 50); b.setDisable(true);});
          break;
        case "Black Mage":
          b.setOnAction((e) -> {controller.createKnight("Black Mage", 500, 50); b.setDisable(true);});
          break;
      }
    }

    partySizeLabel = new Label("Party size: " + controller.getParty().size());
    partySizeLabel.setAlignment(Pos.TOP_RIGHT);
    partySizeLabel.setMinSize(336,40);
    root.getChildren().add(partySizeLabel);

    phaseLabel = new Label("Current phase: " + controller.getCurrentPhase());
    phaseLabel.setLayoutX(20);
    phaseLabel.setLayoutY(100);
    phaseLabel.setMinSize(336,40);
    root.getChildren().add(phaseLabel);

    controller.completeInventory();

    startAnimatorSetPartyScene();

    return scene;
  }

  private void startAnimatorSetPartyScene() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        int partySize = controller.getParty().size();
        partySizeLabel.setText("Party Size: " + partySize);
        phaseLabel.setText("Current phase: " + controller.getCurrentPhase());
        if (partySize == 3) {
          controller.tryToSetEnemies();
          controller.setActualCharacter(controller.getTurns().peek());
          actualScene = createMainScene();
          stop();
        }
      }
    };
    timer.start();
  }

  private Scene createMainScene() {
    Group root = new Group();
    Scene scene = new Scene(root, 400, 400);

    mainLabel = new Label("MAY THE BEST WIN...");
    mainLabel.setLayoutX(150);
    mainLabel.setLayoutY(10);
    mainLabel.setMinSize(100, 10);
    root.getChildren().add(mainLabel);

    enemiesLabel = new Label("Enemies");
    enemiesLabel.setLayoutX(160);
    enemiesLabel.setLayoutY(40);
    enemiesLabel.setMinSize(100, 10);
    root.getChildren().add(enemiesLabel);

    playerCharactersLabel = new Label("");
    playerCharactersLabel.setLayoutX(160);
    playerCharactersLabel.setLayoutY(250);
    playerCharactersLabel.setMinSize(100, 10);
    root.getChildren().add(playerCharactersLabel);

    inventoryLabel = new Label("Inventory");
    inventoryLabel.setLayoutX(160);
    inventoryLabel.setLayoutY(250);
    inventoryLabel.setMinSize(100, 10);
    root.getChildren().add(inventoryLabel);

    aliveEnemiesLabel = new Label("Alive enemies: " + controller.getEnemies().size());
    aliveEnemiesLabel.setLayoutX(160);
    aliveEnemiesLabel.setLayoutY(165);
    aliveEnemiesLabel.setMinSize(100, 10);
    root.getChildren().add(aliveEnemiesLabel);

    alivePlayerCharacterLabel = new Label("Alive player characters: " + controller.getParty().size());
    alivePlayerCharacterLabel.setLayoutX(160);
    alivePlayerCharacterLabel.setLayoutY(195);
    alivePlayerCharacterLabel.setMinSize(100, 10);
    root.getChildren().add(alivePlayerCharacterLabel);

    turnLabel = new Label("Character in turn:");
    turnLabel.setLayoutX(10);
    turnLabel.setLayoutY(150);
    turnLabel.setMinSize(100, 10);
    root.getChildren().add(turnLabel);

    characterNameLabel = new Label("Name: " + controller.getNameCharacter(controller.getActualCharacter()));
    characterNameLabel.setLayoutX(10);
    characterNameLabel.setLayoutY(165);
    characterNameLabel.setMinSize(100, 10);
    root.getChildren().add(characterNameLabel);

    characterLifeLabel = new Label("Life: " + controller.getLifeCharacter(controller.getActualCharacter()));
    characterLifeLabel.setLayoutX(10);
    characterLifeLabel.setLayoutY(180);
    characterLifeLabel.setMinSize(100, 10);
    root.getChildren().add(characterLifeLabel);

    characterDefenseLabel = new Label("Defense: " + controller.getDefenseCharacter(controller.getActualCharacter()));
    characterDefenseLabel.setLayoutX(10);
    characterDefenseLabel.setLayoutY(195);
    characterDefenseLabel.setMinSize(100, 10);
    root.getChildren().add(characterDefenseLabel);

    characterAttackLabel = new Label("");
    characterAttackLabel.setLayoutX(10);
    characterAttackLabel.setLayoutY(210);
    characterAttackLabel.setMinSize(100, 10);
    root.getChildren().add(characterAttackLabel);

    equippedWeaponLabel = new Label("");
    equippedWeaponLabel.setLayoutX(10);
    equippedWeaponLabel.setLayoutY(225);
    equippedWeaponLabel.setMinSize(100, 10);
    root.getChildren().add(equippedWeaponLabel);

    phaseLabel = new Label("Current phase: " + controller.getCurrentPhase());
    phaseLabel.setLayoutX(10);
    phaseLabel.setLayoutY(380);
    phaseLabel.setMinSize(110,10);
    root.getChildren().add(phaseLabel);

    inventorySizeLabel = new Label("Inventory size: " + controller.getInventory().size());
    inventorySizeLabel.setLayoutX(10);
    inventorySizeLabel.setLayoutY(365);
    inventorySizeLabel.setMinSize(110,10);
    root.getChildren().add(inventorySizeLabel);

    enemyPane = new Pane();
    enemyPane.setMinWidth(400);
    enemyPane.setPrefWidth(400);
    enemyPane.setMaxWidth(400);
    root.getChildren().add(enemyPane);

    createEnemyAttackPane();

    /*playerPane = new Pane();
    playerPane.setMinWidth(400);
    playerPane.setPrefWidth(400);
    playerPane.setMaxWidth(400);*/
    root.getChildren().add(playerPane);

    startAnimatorMainScene();

    return scene;
  }

  private void startAnimatorMainScene() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        int alivePlayerCharacters = controller.getParty().size();
        alivePlayerCharacterLabel.setText("Alive player characters: " + alivePlayerCharacters);
        int aliveEnemies = controller.getEnemies().size();
        aliveEnemiesLabel.setText("Alive enemies: " + aliveEnemies);

        characterNameLabel.setText("Name: " + controller.getNameCharacter(controller.getActualCharacter()));
        characterLifeLabel.setText("Life: " + controller.getLifeCharacter(controller.getActualCharacter()));
        characterDefenseLabel.setText("Defense: " + controller.getDefenseCharacter(controller.getActualCharacter()));
        phaseLabel.setText("Current phase: " + controller.getCurrentPhase());

        if (alivePlayerCharacters == 0) {
          actualScene = createDefeatScene();
        }
        else if (aliveEnemies == 0) {
          actualScene = createVictoryScene();
        }
        //LO MATAN ALTIRO, POR ESO NO ESTA EN LA LISTA
        if (controller.isPlayerCharacter(controller.getActualCharacter())) {
          characterAttackLabel.setText("Attack: " + controller.getDamageWeapon(controller.getActualCharacter()));
          equippedWeaponLabel.setText("Equipped weapon: " + controller.getEquippedWeaponCharacter(controller.getActualCharacter()));
        }
        else if (!controller.isPlayerCharacter(controller.getActualCharacter())) {
          characterAttackLabel.setText("Attack: " + controller.getAttackEnemy(controller.getActualCharacter()));
        }
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if (controller.isPlayerCharacter(controller.getActualCharacter())) {
          equippedWeaponLabel.setText("Equipped weapon: " + controller.getEquippedWeaponCharacter(controller.getActualCharacter()));
          while (controller.getActualWeapon() == null) {
            //playerPane = createEquipPane();
            createEquipPane();
          }
          playerPane.getChildren().removeAll();
          controller.tryToEquip(controller.getActualCharacter(), controller.getActualWeapon());
          while (controller.getActualEnemyToAttack() == null) {
            playerCharactersLabel.setText("Player Characters");
            createPlayerCharacterPane();
          }
          playerPane.getChildren().removeAll();
          controller.tryToAttack(controller.getActualCharacter(), controller.getActualEnemyToAttack());
          controller.setActualCharacter(null);
          controller.setActualWeapon(null);
          controller.setActualEnemyToAttack(null);
        }
        else {
          int i = rng.nextInt(controller.getParty().size());
          controller.tryToAttack(controller.getActualCharacter(), controller.getPlayer(i));
        }
        try {
          controller.tryToExtractCharacter();
        } catch (InvalidMovementException e) {
          e.printStackTrace();
        }
        controller.setActualCharacter(controller.getTurns().peek());
      }
    };
    timer.start();
  }

  private void createEquipPane() {

    //Pane pane = new Pane();

    List<String> buttons = Arrays.asList("Axe", "Bow", "Knife", "Staff", "Sword");

    int x = 30;
    for (String button : buttons) {
      Button b = new Button(button);
      b.setLayoutX(x);
      b.setLayoutY(280);
      x += 70;
      b.setMinSize(60, 60);
      playerPane.getChildren().add(b);
      switch(button) {
        case "Axe":
          b.setOnAction((e) -> {
            controller.setActualWeapon(controller.getWeapon(0));
            b.setDisable(true);
          });
          break;
        case "Bow":
          b.setOnAction((e) -> {
            controller.setActualWeapon(controller.getWeapon(1));
            b.setDisable(true);
          });
          break;
        case "Knife":
          b.setOnAction((e) -> {
            controller.setActualWeapon(controller.getWeapon(2));
            b.setDisable(true);
          });
          break;
        case "Staff":
          b.setOnAction((e) -> {
            controller.setActualWeapon(controller.getWeapon(3));
            b.setDisable(true);
          });
          break;
        case "Sword":
          b.setOnAction((e) -> {
            controller.setActualWeapon(controller.getWeapon(4));
            b.setDisable(true);
          });
          break;
      }
    }
    //return pane;
  }

  private void createPlayerCharacterPane() {

    List<String> buttons = Arrays.asList("Axe", "Bow", "Knife", "Staff", "Sword");

    int x = 30;
    for (String button : buttons) {
      Button b = new Button(button);
      b.setLayoutX(x);
      b.setLayoutY(280);
      x += 70;
      b.setMinSize(60, 60);
      playerPane.getChildren().add(b);
      switch(button) {
        case "Axe":
          b.setOnAction((e) -> {
            controller.equipWeapon(controller.getActualCharacter(), controller.getWeapon(0));
            b.setDisable(true);
          });
          break;
        case "Bow":
          b.setOnAction((e) -> {
            controller.equipWeapon(controller.getActualCharacter(), controller.getWeapon(1));
            b.setDisable(true);
          });
          break;
        case "Knife":
          b.setOnAction((e) -> {
            controller.equipWeapon(controller.getActualCharacter(), controller.getWeapon(2));
            b.setDisable(true);
          });
          break;
        case "Staff":
          b.setOnAction((e) -> {
            controller.equipWeapon(controller.getActualCharacter(), controller.getWeapon(3));
            b.setDisable(true);
          });
          break;
        case "Sword":
          b.setOnAction((e) -> {
            controller.equipWeapon(controller.getActualCharacter(), controller.getWeapon(4));
            b.setDisable(true);
          });
          break;
      }
    }
  }

  private void createEnemyAttackPane() {

    List<String> buttons = Arrays.asList("Enemy 1", "Enemy 2", "Enemy 3");

    int x = 100;
    for (String button : buttons) {
      Button b = new Button(button);
      b.setLayoutX(x);
      b.setLayoutY(70);
      x += 70;
      b.setMinSize(60, 60);
      enemyPane.getChildren().add(b);
      switch(button) {
        case "Axe":
          b.setOnAction((e) -> {controller.attack(controller.getActualCharacter(), controller.getEnemy(0));
            b.setDisable(true);});
          break;
        case "Bow":
          b.setOnAction((e) -> {controller.attack(controller.getActualCharacter(), controller.getEnemy(1));
            b.setDisable(true);});
          break;
        case "Knife":
          b.setOnAction((e) -> {controller.attack(controller.getActualCharacter(), controller.getEnemy(2));
            b.setDisable(true);});
          break;
      }
    }
  }

  private Scene createVictoryScene() {
    Group root = new Group();
    Scene scene = new Scene(root, 400, 400);

    mainLabel = new Label("CONGRATULATIONS, YOU WON!");
    mainLabel.setLayoutX(150);
    mainLabel.setLayoutY(150);
    mainLabel.setMinSize(150, 30);
    root.getChildren().add(mainLabel);

    return scene;
  }

  private Scene createDefeatScene() {
    Group root = new Group();
    Scene scene = new Scene(root, 400, 400);

    mainLabel = new Label("YOU LOST:(");
    mainLabel.setLayoutX(150);
    mainLabel.setLayoutY(150);
    mainLabel.setMinSize(150, 30);
    root.getChildren().add(mainLabel);

    return scene;
  }

}









  /*private Scene createMainScene() {
    Group root = new Group();
    Scene scene = new Scene(root, 400, 400);

    mainLabel = new Label("Next turn is for...");
    mainLabel.setAlignment(Pos.CENTER);
    mainLabel.setMinSize(336,40);
    root.getChildren().add(mainLabel);

    phaseLabel = new Label("Current phase: " + controller.getCurrentPhase());
    phaseLabel.setLayoutX(20);
    phaseLabel.setLayoutY(100);
    phaseLabel.setMinSize(336,40);
    root.getChildren().add(phaseLabel);


    aLabel = new Label("Current phase: " + controller.getCurrentPhase());
    aLabel.setLayoutX(20);
    aLabel.setLayoutY(150);
    aLabel.setMinSize(336,40);
    root.getChildren().add(aLabel);

    startAnimatorMainScene();

    return scene;
  }

  private void startAnimatorMainScene() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        phaseLabel.setText("Current phase: " + controller.getCurrentPhase());
        aLabel.setText("Size: " + controller.getParty().size());
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        controller.setActualCharacter(controller.getTurns().peek());
        mainLabel.setText(controller.getNameCharacter(controller.getActualCharacter()));
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if (controller.isPlayer(controller.getActualCharacter())) {
          actualScene = createEquipWeaponScene();
        }
        else {
          actualScene = createEnemyAttackScene();
        }
        stop();
      }
    };
    timer.start();
  }


  //ENEMY ATTACK SCENE

  private Scene createEnemyAttackScene() {
    Group root = new Group();
    Scene scene = new Scene(root, 400, 400);

    Label label = new Label(controller.getNameCharacter(controller.getActualCharacter()) + "is attacking");
    label.setAlignment(Pos.CENTER);
    label.setMinSize(336,40);
    root.getChildren().add(label);

    phaseLabel = new Label("Current phase: " + controller.getCurrentPhase());
    phaseLabel.setLayoutX(20);
    phaseLabel.setLayoutY(100);
    phaseLabel.setMinSize(336,40);
    root.getChildren().add(phaseLabel);

    startAnimatorEnemyAttackScene();

    return scene;
  }

  private void startAnimatorEnemyAttackScene() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        phaseLabel.setText("Current phase: " + controller.getCurrentPhase());
        controller.setPhase(new AttackPhase());
        int partySize = controller.getPartySize();
        int ind = rng.nextInt(partySize);
        controller.tryToAttack(controller.getActualCharacter(), controller.getPlayer(ind));
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        actualScene = createMainScene();
      }
    };
    timer.start();
  }


  //EQUIP WEAPON SCENE

  private Scene createEquipWeaponScene() {

    Group root = new Group();
    Scene scene = new Scene(root, 400, 400);

    Label label = new Label("Choose one weapon to equip");
    label.setAlignment(Pos.TOP_CENTER);
    label.setMinSize(336,40);
    root.getChildren().add(label);

    FlowPane pane = new FlowPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(30, 20, 30, 20));
    pane.setHgap(5);
    pane.setVgap(5);
    pane.setMinWidth(400);
    pane.setPrefWidth(400);
    pane.setMaxWidth(400);
    root.getChildren().add(pane);

    List<String> buttons = Arrays.asList("Axe", "Bow", "Knife", "Staff", "Sword");

    for (String button : buttons) {
      Button b = new Button(button);
      b.setMinSize(60, 60);
      pane.getChildren().add(b);
      switch(button) {
        case "Axe":
          b.setOnAction((e) -> {controller.setActualWeapon(controller.getWeapon(0));
            b.setDisable(true);});
          break;
        case "Bow":
          b.setOnAction((e) -> {controller.setActualWeapon(controller.getWeapon(1));
            b.setDisable(true);});
          break;
        case "Knife":
          b.setOnAction((e) -> {controller.setActualWeapon(controller.getWeapon(2));
            b.setDisable(true);});
          break;
        case "Staff":
          b.setOnAction((e) -> {controller.setActualWeapon(controller.getWeapon(3));
            b.setDisable(true);});
          break;
        case "Sword":
          b.setOnAction((e) -> {controller.setActualWeapon(controller.getWeapon(4));
            b.setDisable(true);});
          break;
      }
    }

    phaseLabel = new Label("Current phase: " + controller.getCurrentPhase());
    phaseLabel.setLayoutX(20);
    phaseLabel.setLayoutY(100);
    phaseLabel.setMinSize(336,40);
    root.getChildren().add(phaseLabel);

    startAnimatorEquipWeaponScene();

    return scene;
  }

  private void startAnimatorEquipWeaponScene() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        phaseLabel.setText("Current phase: " + controller.getCurrentPhase());
        if (controller.getActualWeapon() != null) {
          controller.tryToEquip((IPlayerCharacter) controller.getActualCharacter(), controller.getActualWeapon());
          actualScene = createAttackScene();
          controller.setActualWeapon(null);
        }
      }
    };
    timer.start();
  }


  //ATTACK SCENE

  private Scene createAttackScene() {
    Group root = new Group();
    Scene scene = new Scene(root, 400, 400);

    Label label = new Label("Choose one enemy to attack");
    label.setAlignment(Pos.TOP_CENTER);
    label.setMinSize(336,40);
    root.getChildren().add(label);

    FlowPane pane = new FlowPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(30, 20, 30, 20));
    pane.setHgap(5);
    pane.setVgap(5);
    pane.setMinWidth(400);
    pane.setPrefWidth(400);
    pane.setMaxWidth(400);
    root.getChildren().add(pane);

    List<String> buttons = Arrays.asList(
            "Enemies",
            controller.getNameCharacter(controller.getEnemies().get(0)) +
                    ": Life = " + controller.getLifeCharacter(controller.getEnemies().get(0)),
            controller.getNameCharacter(controller.getEnemies().get(1)) +
                    ": Life = " + controller.getLifeCharacter(controller.getEnemies().get(1)),
            controller.getNameCharacter(controller.getEnemies().get(2)) +
                    ": Life = " + controller.getLifeCharacter(controller.getEnemies().get(2)),
            "",
            "Player Character",
            controller.getNameCharacter(controller.getParty().get(0)) +
                    ": Life = " + controller.getLifeCharacter(controller.getParty().get(0)) +
                    ", Defense = "+ controller.getDefenseCharacter(controller.getParty().get(0)) +
                    ", Equipped Weapon = " + controller.getEquippedWeaponCharacter(controller.getParty().get(0)));*/

    /*for (String button : buttons) {
      Button b = new Button(button);
      b.setMinSize(60, 60);
      pane.getChildren().add(b);
      switch (button) {
        case buttons.get(0):
          b.setOnAction((e) -> {
            controller.setActualWeapon(controller.getWeapon(0));
            b.setDisable(true);
          });
          break;
        case "Bow":
          b.setOnAction((e) -> {
            controller.setActualWeapon(controller.getWeapon(1));
            b.setDisable(true);
          });
          break;
        case "Knife":
          b.setOnAction((e) -> {
            controller.setActualWeapon(controller.getWeapon(2));
            b.setDisable(true);
          });
          break;
        case "Staff":
          b.setOnAction((e) -> {
            controller.setActualWeapon(controller.getWeapon(3));
            b.setDisable(true);
          });
          break;
        case "Sword":
          b.setOnAction((e) -> {
            controller.setActualWeapon(controller.getWeapon(4));
            b.setDisable(true);
          });
          break;
      }
    }*/






    /*phaseLabel = new Label("Current phase: " + controller.getCurrentPhase());
    phaseLabel.setLayoutX(20);
    phaseLabel.setLayoutY(100);
    phaseLabel.setMinSize(336,40);
    root.getChildren().add(phaseLabel);

    startAnimatorAttackScene();

    return scene;
  }

  private void startAnimatorAttackScene() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        phaseLabel.setText("Current phase: " + controller.getCurrentPhase());
        if (controller.getActualWeapon() != null) {
          controller.equipWeapon((IPlayerCharacter) controller.getActualCharacter(), controller.getActualWeapon());
          actualScene = createAttackScene();
        }
      }
    };
    timer.start();
  }
}*/