package com.github.estebanzuniga.finalreality.gui;

import com.github.estebanzuniga.finalreality.controller.GameController;
import com.github.estebanzuniga.finalreality.controller.phases.InvalidMovementException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
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
  private Group mainRoot;
  private Label partySizeLabel;
  private Label phaseLabel;
  private Label mainLabel;
  private Label centralLabel;
  private Label central2Label;
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
  private Button enemy1Button;
  private Button enemy2Button;
  private Button enemy3Button;
  private Button axeButton;
  private Button bowButton;
  private Button knifeButton;
  private Button staffButton;
  private Button swordButton;
  private Scene actualScene = createSetPartyScene();
  private final Random rng = new Random();

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(@NotNull Stage primaryStage) {
    primaryStage.setTitle("Final reality");
    primaryStage.setResizable(false);
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
          b.setOnAction((e) -> {
            controller.createEngineer("Engineer", rng.nextInt(100) + 400, rng.nextInt(30) + 20);
            b.setDisable(true);});
          break;
        case "Knight":
          b.setOnAction((e) -> {
            controller.createKnight("Knight", rng.nextInt(100) + 400, rng.nextInt(30) + 20);
            b.setDisable(true);});
          break;
        case "Thief":
          b.setOnAction((e) -> {
            controller.createThief("Thief", rng.nextInt(100) + 400, rng.nextInt(30) + 20);
            b.setDisable(true);});
          break;
        case "White Mage":
          b.setOnAction((e) -> {
            controller.createKnight("White Mage", rng.nextInt(100) + 400, rng.nextInt(30) + 20);
            b.setDisable(true);});
          break;
        case "Black Mage":
          b.setOnAction((e) -> {
            controller.createKnight("Black Mage", rng.nextInt(100) + 400, rng.nextInt(30) + 20);
            b.setDisable(true);});
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
          controller.setCurrentCharacter(controller.getTurns().peek());
          actualScene = createMainScene();
          stop();
        }
      }
    };
    timer.start();
  }


  ///MAIN SCENE

  private Scene createMainScene() {
    mainRoot = new Group();
    Scene scene = new Scene(mainRoot, 400, 400);
    createLabels();
    createButtons();
    startAnimatorMainScene();
    return scene;
  }

  private void startAnimatorMainScene() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {

        controller.setCurrentCharacter(controller.getTurns().peek());

        /*if (controller.isPlayerCharacter(controller.getCurrentCharacter())) {
          activateWeaponButtons();
        }
        else if (!controller.isPlayerCharacter(controller.getCurrentCharacter())) {
          disableWeaponButtons();
          disableEnemyButtons();
        }*/

        int alivePlayerCharacters = controller.getParty().size();
        alivePlayerCharacterLabel.setText("Alive player characters: " + alivePlayerCharacters);
        int aliveEnemies = controller.getEnemies().size();
        aliveEnemiesLabel.setText("Alive enemies: " + aliveEnemies);

        if (alivePlayerCharacters == 0) {
          actualScene = createDefeatScene();
        }
        else if (aliveEnemies == 0) {
          actualScene = createVictoryScene();
        }

        String characters = "Your characters: ";
        for (int i=0; i<controller.getParty().size(); i++) {
          characters += controller.getNameCharacter(controller.getPlayer(i));
          if (i != controller.getParty().size()-1) {
            characters += ", ";
          }
        }
        playerCharactersLabel.setText(characters);

        characterNameLabel.setText("Name: " + controller.getNameCharacter(controller.getCurrentCharacter()));
        characterLifeLabel.setText("Life: " + controller.getLifeCharacter(controller.getCurrentCharacter()));
        characterDefenseLabel.setText("Defense: " + controller.getDefenseCharacter(controller.getCurrentCharacter()));
        phaseLabel.setText("Current phase: " + controller.getCurrentPhase());

        if (controller.isPlayerCharacter(controller.getCurrentCharacter()) &&
                controller.getEquippedWeaponCharacter(controller.getCurrentCharacter()) != null) {
          characterAttackLabel.setText("Attack: " + controller.getDamageWeapon(controller.getCurrentCharacter()));
        }
        else if (!controller.isPlayerCharacter(controller.getCurrentCharacter())) {
          characterAttackLabel.setText("Attack: " + controller.getAttackEnemy(controller.getCurrentCharacter()));
        }
      }
    };
    timer.start();
  }

  //LABELS AND BUTTONS

  private void createLabels() {
    mainLabel = new Label("MAY THE BEST WIN...");
    mainLabel.setLayoutX(150);
    mainLabel.setLayoutY(10);
    mainLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(mainLabel);

    central2Label = new Label("");
    central2Label.setLayoutX(130);
    central2Label.setLayoutY(190);
    central2Label.setMinSize(100, 20);
    mainRoot.getChildren().add(central2Label);

    centralLabel = new Label("");
    centralLabel.setLayoutX(130);
    centralLabel.setLayoutY(170);
    centralLabel.setMinSize(100, 20);
    mainRoot.getChildren().add(centralLabel);

    enemiesLabel = new Label("Enemies");
    enemiesLabel.setLayoutX(178);
    enemiesLabel.setLayoutY(40);
    enemiesLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(enemiesLabel);

    String characters = "Your characters: ";
    for (int i=0; i<controller.getParty().size(); i++) {
      characters += controller.getNameCharacter(controller.getPlayer(i));
      if (i != controller.getParty().size()-1) {
        characters += ", ";
      }
    }
    playerCharactersLabel = new Label(characters);
    playerCharactersLabel.setLayoutX(10);
    playerCharactersLabel.setLayoutY(350);
    playerCharactersLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(playerCharactersLabel);

    inventoryLabel = new Label("Inventory");
    inventoryLabel.setLayoutX(175);
    inventoryLabel.setLayoutY(240);
    inventoryLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(inventoryLabel);

    aliveEnemiesLabel = new Label("");
    aliveEnemiesLabel.setLayoutX(260);
    aliveEnemiesLabel.setLayoutY(365);
    aliveEnemiesLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(aliveEnemiesLabel);

    alivePlayerCharacterLabel = new Label("");
    alivePlayerCharacterLabel.setLayoutX(260);
    alivePlayerCharacterLabel.setLayoutY(380);
    alivePlayerCharacterLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(alivePlayerCharacterLabel);

    turnLabel = new Label("Character in turn:");
    turnLabel.setLayoutX(10);
    turnLabel.setLayoutY(150);
    turnLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(turnLabel);

    characterNameLabel = new Label("");
    characterNameLabel.setLayoutX(10);
    characterNameLabel.setLayoutY(165);
    characterNameLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(characterNameLabel);

    characterLifeLabel = new Label("");
    characterLifeLabel.setLayoutX(10);
    characterLifeLabel.setLayoutY(180);
    characterLifeLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(characterLifeLabel);

    characterDefenseLabel = new Label("");
    characterDefenseLabel.setLayoutX(10);
    characterDefenseLabel.setLayoutY(195);
    characterDefenseLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(characterDefenseLabel);

    characterAttackLabel = new Label("");
    characterAttackLabel.setLayoutX(10);
    characterAttackLabel.setLayoutY(210);
    characterAttackLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(characterAttackLabel);

    phaseLabel = new Label("Current phase: " + controller.getCurrentPhase());
    phaseLabel.setLayoutX(10);
    phaseLabel.setLayoutY(380);
    phaseLabel.setMinSize(100,10);
    mainRoot.getChildren().add(phaseLabel);
  }

  private void createButtons() {
    enemy1Button = new Button("Enemy 1");
    enemy1Button.setMinSize(60, 60);
    enemy1Button.setLayoutX(100);
    enemy1Button.setLayoutY(70);
    enemy1Button.setOnAction((e) -> {
      controller.setCurrentOpponentToAttack(controller.getEnemy(0));
      attackingEnemy();
    });
    mainRoot.getChildren().add(enemy1Button);

    enemy2Button = new Button("Enemy 2");
    enemy2Button.setMinSize(60, 60);
    enemy2Button.setLayoutX(170);
    enemy2Button.setLayoutY(70);
    enemy2Button.setOnAction((e) -> {
      controller.setCurrentOpponentToAttack(controller.getEnemy(1));
      attackingEnemy();
    });
    mainRoot.getChildren().add(enemy2Button);

    enemy3Button = new Button("Enemy 3");
    enemy3Button.setMinSize(60, 60);
    enemy3Button.setLayoutX(240);
    enemy3Button.setLayoutY(70);
    enemy3Button.setOnAction((e) -> {
      controller.setCurrentOpponentToAttack(controller.getEnemy(2));
      attackingEnemy();
    });
    mainRoot.getChildren().add(enemy3Button);

    axeButton = new Button("Axe");
    axeButton.setMinSize(60, 60);
    axeButton.setLayoutX(30);
    axeButton.setLayoutY(270);
    axeButton.setOnAction((e) -> {
      controller.setCurrentWeapon(controller.getWeapon(0));
      equipAWeapon(axeButton);
    });
    mainRoot.getChildren().add(axeButton);

    bowButton = new Button("Bow");
    bowButton.setMinSize(60, 60);
    bowButton.setLayoutX(100);
    bowButton.setLayoutY(270);
    bowButton.setOnAction((e) -> {
      controller.setCurrentWeapon(controller.getWeapon(1));
      equipAWeapon(bowButton);
    });
    mainRoot.getChildren().add(bowButton);

    knifeButton = new Button("Knife");
    knifeButton.setMinSize(60, 60);
    knifeButton.setLayoutX(170);
    knifeButton.setLayoutY(270);
    knifeButton.setOnAction((e) -> {
      controller.setCurrentWeapon(controller.getWeapon(2));
      equipAWeapon(knifeButton);
    });
    mainRoot.getChildren().add(knifeButton);

    staffButton = new Button("Staff");
    staffButton.setMinSize(60, 60);
    staffButton.setLayoutX(240);
    staffButton.setLayoutY(270);
    staffButton.setOnAction((e) -> {
      controller.setCurrentWeapon(controller.getWeapon(3));
      equipAWeapon(staffButton);
    });
    mainRoot.getChildren().add(staffButton);

    swordButton = new Button("Sword");
    swordButton.setMinSize(60, 60);
    swordButton.setLayoutX(310);
    swordButton.setLayoutY(270);
    swordButton.setOnAction((e) -> {
      controller.setCurrentWeapon(controller.getWeapon(4));
      equipAWeapon(swordButton);
    });
    mainRoot.getChildren().add(swordButton);
  }

  private void attackingEnemy() {
    centralLabel.setText(controller.getNameCharacter(controller.getCurrentCharacter()) +
            " is attacking " + controller.getNameCharacter(controller.getCurrentOpponentToAttack()) +
            " with the " + controller.getNameWeapon(controller.getEquippedWeaponCharacter(controller.getCurrentCharacter())));
    controller.tryToAttack(controller.getCurrentCharacter(), controller.getCurrentOpponentToAttack());
    controller.setCurrentWeapon(null);
    controller.setCurrentOpponentToAttack(null);
    try {
      controller.tryToExtractCharacter();
      controller.setCurrentCharacter(controller.getTurns().peek());
    } catch (InvalidMovementException e) {
      e.printStackTrace();
    }
    disableEnemyButtons();
  }

  private void equipAWeapon(Button button) {
    controller.setEquippedWeaponCharacterNull(controller.getCurrentCharacter());
    controller.tryToEquip(controller.getCurrentCharacter(), controller.getCurrentWeapon());
    if (controller.getEquippedWeaponCharacter(controller.getCurrentCharacter()) == null) {
      centralLabel.setText(
              controller.getNameCharacter(controller.getCurrentCharacter()) + " can not equip the " +
                      controller.getNameWeapon(controller.getCurrentWeapon()));
      central2Label.setText("Please, choose another weapon");
      button.setDisable(true);
    }
    else {
      disableWeaponButtons();
      activateEnemyButtons();
      centralLabel.setText("Choose an enemy to attack");
      central2Label.setText("");
      controller.setCurrentWeapon(null);
      controller.setCurrentOpponentToAttack(null);
    }
  }

  private void disableEnemyButtons() {
    enemy1Button.setDisable(true);
    enemy2Button.setDisable(true);
    enemy3Button.setDisable(true);
  }

  private void disableWeaponButtons() {
    axeButton.setDisable(true);
    bowButton.setDisable(true);
    knifeButton.setDisable(true);
    staffButton.setDisable(true);
    swordButton.setDisable(true);
  }

  private void activateEnemyButtons() {
    enemy1Button.setDisable(false);
    enemy2Button.setDisable(false);
    enemy3Button.setDisable(false);
  }

  private void activateWeaponButtons() {
    axeButton.setDisable(false);
    bowButton.setDisable(false);
    knifeButton.setDisable(false);
    staffButton.setDisable(false);
    swordButton.setDisable(false);
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

    mainLabel = new Label("YOU LOST...");
    mainLabel.setLayoutX(150);
    mainLabel.setLayoutY(150);
    mainLabel.setMinSize(150, 30);
    root.getChildren().add(mainLabel);

    return scene;
  }

}

  /*private void createWeaponPane() {

    List<String> buttons = Arrays.asList("Enemy 1", "Enemy 2", "Enemy 3", "Axe", "Bow", "Knife", "Staff", "Sword");

    int i = 0;
    int x = 100;
    int y = 70;
    for (String button : buttons) {
      if (i == 3) {
        x = 30;
        y = 280;
      }
      if (i == 8) {
        i = 0;
      }
      Button b = new Button(button);
      b.setLayoutX(x);
      b.setLayoutY(y);
      x += 70;
      i += 1;
      b.setMinSize(60, 60);
      weaponPane.getChildren().add(b);
      switch(button) {
        case "Enemy 1":
          b.setOnAction((e) -> {
            controller.setCurrentOpponentToAttack(controller.getEnemy(0));});
            //b.setDisable(true);});
          break;
        case "Enemy 2":
          b.setOnAction((e) -> {
            controller.setCurrentOpponentToAttack(controller.getEnemy(1));});
            //b.setDisable(true);});
          break;
        case "Enemy 3":
          b.setOnAction((e) -> {
            controller.setCurrentOpponentToAttack(controller.getEnemy(2));});
            //b.setDisable(true);});
          break;
        case "Axe":
          b.setOnAction((e) -> {
            controller.setCurrentWeapon(controller.getWeapon(0));});
            //b.setDisable(true);});
          break;
        case "Bow":
          b.setOnAction((e) -> {
            controller.setCurrentWeapon(controller.getWeapon(1));});
            //b.setDisable(true);});
          break;
        case "Knife":
          b.setOnAction((e) -> {
            controller.setCurrentWeapon(controller.getWeapon(2));});
            //b.setDisable(true);});
          break;
        case "Staff":
          b.setOnAction((e) -> {
            controller.setCurrentWeapon(controller.getWeapon(3));});
            //b.setDisable(true);});
          break;
        case "Sword":
          b.setOnAction((e) -> {
            controller.setCurrentWeapon(controller.getWeapon(4));});
            //b.setDisable(true);});
          break;
      }
    }
  }*/

  /*private void createEnemyAttackPane() {

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
        case "Enemy 1":
          b.setOnAction((e) -> {
            controller.setActualEnemyToAttack(controller.getEnemy(0));
            b.setDisable(true);});
          break;
        case "Enemy 2":
          b.setOnAction((e) -> {
            controller.setActualEnemyToAttack(controller.getEnemy(1));
            b.setDisable(true);});
          break;
        case "Enemy 3":
          b.setOnAction((e) -> {
            controller.setActualEnemyToAttack(controller.getEnemy(2));
            b.setDisable(true);});
          break;
      }
    }
  }*/

  /*private void createEquipPane() {

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
  }*/



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

  /*Pane weaponPane = new Pane();
    weaponPane.setMinWidth(400);
            weaponPane.setPrefWidth(400);
            weaponPane.setMaxWidth(400);
            root.getChildren().add(weaponPane);

            //createWeaponPane();

            List<String> buttons = Arrays.asList("Enemy 1", "Enemy 2", "Enemy 3", "Axe", "Bow", "Knife", "Staff", "Sword");
        int i = 0;
        int x = 100;
        int y = 70;
        for (String button : buttons) {
        if (i == 3) {
        x = 30;
        y = 280;
        }
        if (i == 8) {
        i = 0;
        }
        Button b = new Button(button);
        b.setLayoutX(x);
        b.setLayoutY(y);
        x += 70;
        i += 1;
        b.setMinSize(60, 60);
        weaponPane.getChildren().add(b);
        switch(button) {
        case "Enemy 1":
        b.setOnAction((e) -> {
        controller.setCurrentOpponentToAttack(controller.getEnemy(0));});
        //b.setDisable(true);});
        break;
        case "Enemy 2":
        b.setOnAction((e) -> {
        controller.setCurrentOpponentToAttack(controller.getEnemy(1));});
        //b.setDisable(true);});
        break;
        case "Enemy 3":
        b.setOnAction((e) -> {
        controller.setCurrentOpponentToAttack(controller.getEnemy(2));});
        //b.setDisable(true);});
        break;
        case "Axe":
        b.setOnAction((e) -> {
        controller.setCurrentWeapon(controller.getWeapon(0));});
        //b.setDisable(true);});
        break;
        case "Bow":
        b.setOnAction((e) -> {
        controller.setCurrentWeapon(controller.getWeapon(1));});
        //b.setDisable(true);});
        break;
        case "Knife":
        b.setOnAction((e) -> {
        controller.setCurrentWeapon(controller.getWeapon(2));});
        //b.setDisable(true);});
        break;
        case "Staff":
        b.setOnAction((e) -> {
        controller.setCurrentWeapon(controller.getWeapon(3));});
        //b.setDisable(true);});
        break;
        case "Sword":
        b.setOnAction((e) -> {
        controller.setCurrentWeapon(controller.getWeapon(4));});
        //b.setDisable(true);});
        break;
        }
        }*/
