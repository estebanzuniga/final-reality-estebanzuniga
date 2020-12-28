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
  private Label characterAttackLabel;
  private Button enemyAttackButton;
  private Button enemy1Button;
  private Button enemy2Button;
  private Button enemy3Button;
  private Button axeButton;
  private Button bowButton;
  private Button knifeButton;
  private Button staffButton;
  private Button swordButton;
  private boolean nextTurn = true;
  private boolean enemyTurn = false;
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
    label.setMinSize(336, 40);
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
      switch (button) {
        case "Engineer":
          b.setOnAction((e) -> {
            controller.createEngineer("Engineer", rng.nextInt(100) + 400, rng.nextInt(30) + 20);
            b.setDisable(true);
          });
          break;
        case "Knight":
          b.setOnAction((e) -> {
            controller.createKnight("Knight", rng.nextInt(100) + 400, rng.nextInt(30) + 20);
            b.setDisable(true);
          });
          break;
        case "Thief":
          b.setOnAction((e) -> {
            controller.createThief("Thief", rng.nextInt(100) + 400, rng.nextInt(30) + 20);
            b.setDisable(true);
          });
          break;
        case "White Mage":
          b.setOnAction((e) -> {
            controller.createKnight("White Mage", rng.nextInt(100) + 400, rng.nextInt(30) + 20);
            b.setDisable(true);
          });
          break;
        case "Black Mage":
          b.setOnAction((e) -> {
            controller.createKnight("Black Mage", rng.nextInt(100) + 400, rng.nextInt(30) + 20);
            b.setDisable(true);
          });
          break;
      }
    }

    partySizeLabel = new Label("Party size: " + controller.getParty().size());
    partySizeLabel.setAlignment(Pos.TOP_RIGHT);
    partySizeLabel.setMinSize(336, 40);
    root.getChildren().add(partySizeLabel);

    phaseLabel = new Label("Current phase: " + controller.getCurrentPhase());
    phaseLabel.setLayoutX(20);
    phaseLabel.setLayoutY(100);
    phaseLabel.setMinSize(336, 40);
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

        if (nextTurn == true) {
          centralLabel.setText("Next turn is for " + controller.getNameCharacter(controller.getCurrentCharacter()));
        }

        if (controller.isPlayerCharacter(controller.getCurrentCharacter())) {
          enemyAttackButton.setDisable(true);
        }
        else if (!controller.isPlayerCharacter(controller.getCurrentCharacter())) {
          disableWeaponButtons();
        }

        if (!controller.isPlayerCharacter(controller.getCurrentCharacter()) && enemyTurn == true) {
          enemyAttackButton.setDisable(false);
        }

        int alivePlayerCharacters = controller.getParty().size();
        alivePlayerCharacterLabel.setText("Alive player characters: " + alivePlayerCharacters);
        int aliveEnemies = controller.getEnemies().size();
        aliveEnemiesLabel.setText("Alive enemies: " + aliveEnemies);

        if (alivePlayerCharacters == 0) {
          actualScene = createDefeatScene();
        } else if (aliveEnemies == 0) {
          actualScene = createVictoryScene();
        }

        String characters = "Your characters: ";
        for (int i = 0; i < controller.getParty().size(); i++) {
          characters += controller.getNameCharacter(controller.getPlayer(i));
          if (i != controller.getParty().size() - 1) {
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
        } else if (!controller.isPlayerCharacter(controller.getCurrentCharacter())) {
          characterAttackLabel.setText("Attack: " + controller.getAttackEnemy(controller.getCurrentCharacter()));
          enemyTurn = true;
        }
      }
    };
    timer.start();
  }

  //LABELS AND BUTTONS

  private void createLabels() {
    mainLabel = new Label("MAY THE BEST WIN...");
    mainLabel.setLayoutX(170);
    mainLabel.setLayoutY(10);
    mainLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(mainLabel);

    centralLabel = new Label("");
    centralLabel.setLayoutX(130);
    centralLabel.setLayoutY(180);
    centralLabel.setMinSize(100, 20);
    mainRoot.getChildren().add(centralLabel);

    central2Label = new Label("");
    central2Label.setLayoutX(130);
    central2Label.setLayoutY(200);
    central2Label.setMinSize(100, 20);
    mainRoot.getChildren().add(central2Label);

    enemiesLabel = new Label("Enemies");
    enemiesLabel.setLayoutX(178);
    enemiesLabel.setLayoutY(40);
    enemiesLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(enemiesLabel);

    String characters = "Your characters: ";
    for (int i = 0; i < controller.getParty().size(); i++) {
      characters += controller.getNameCharacter(controller.getPlayer(i));
      if (i != controller.getParty().size() - 1) {
        characters += ", ";
      }
    }
    playerCharactersLabel = new Label(characters);
    playerCharactersLabel.setLayoutX(10);
    playerCharactersLabel.setLayoutY(340);
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
    turnLabel.setLayoutY(160);
    turnLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(turnLabel);

    characterNameLabel = new Label("");
    characterNameLabel.setLayoutX(10);
    characterNameLabel.setLayoutY(175);
    characterNameLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(characterNameLabel);

    characterLifeLabel = new Label("");
    characterLifeLabel.setLayoutX(10);
    characterLifeLabel.setLayoutY(190);
    characterLifeLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(characterLifeLabel);

    characterDefenseLabel = new Label("");
    characterDefenseLabel.setLayoutX(10);
    characterDefenseLabel.setLayoutY(205);
    characterDefenseLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(characterDefenseLabel);

    characterAttackLabel = new Label("");
    characterAttackLabel.setLayoutX(10);
    characterAttackLabel.setLayoutY(220);
    characterAttackLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(characterAttackLabel);

    phaseLabel = new Label("Current phase: " + controller.getCurrentPhase());
    phaseLabel.setLayoutX(10);
    phaseLabel.setLayoutY(380);
    phaseLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(phaseLabel);
  }

  private void createButtons() {

    if (!controller.isPlayerCharacter(controller.getCurrentCharacter())) {
    }
    enemyAttackButton = new Button("Simulate Enemy Turn");
    enemyAttackButton.setMinSize(80, 20);
    enemyAttackButton.setLayoutX(130);
    enemyAttackButton.setLayoutY(140);
    enemyAttackButton.setOnAction((e) -> enemyAttack());
    mainRoot.getChildren().add(enemyAttackButton);

    enemy1Button = new Button("Enemy 1");
    enemy1Button.setMinSize(60, 60);
    enemy1Button.setLayoutX(100);
    enemy1Button.setLayoutY(70);
    enemy1Button.setOnAction((e) -> {
      controller.setCurrentOpponentToAttack(controller.getEnemy(0));
      attackingAnEnemy();
    });
    mainRoot.getChildren().add(enemy1Button);

    enemy2Button = new Button("Enemy 2");
    enemy2Button.setMinSize(60, 60);
    enemy2Button.setLayoutX(170);
    enemy2Button.setLayoutY(70);
    enemy2Button.setOnAction((e) -> {
      controller.setCurrentOpponentToAttack(controller.getEnemy(1));
      attackingAnEnemy();
    });
    mainRoot.getChildren().add(enemy2Button);

    enemy3Button = new Button("Enemy 3");
    enemy3Button.setMinSize(60, 60);
    enemy3Button.setLayoutX(240);
    enemy3Button.setLayoutY(70);
    enemy3Button.setOnAction((e) -> {
      controller.setCurrentOpponentToAttack(controller.getEnemy(2));
      attackingAnEnemy();
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

  private void enemyAttack() {
    enemyAttackButton.setDisable(true);
    nextTurn = false;
    int i = rng.nextInt(controller.getParty().size());
    controller.tryToAttack(controller.getCurrentCharacter(), controller.getPlayer(i));
    controller.setCurrentOpponentToAttack(controller.getPlayer(i));
    centralLabel.setText(controller.getNameCharacter(controller.getCurrentCharacter()) + " is attacking " +
            controller.getNameCharacter(controller.getCurrentOpponentToAttack()));
    disableWeaponButtons();
    disableEnemyButtons();
    controller.setCurrentWeapon(null);
    controller.setCurrentOpponentToAttack(null);
    try {
      controller.tryToExtractCharacter();
      controller.setCurrentCharacter(controller.getTurns().peek());
    } catch (InvalidMovementException e) {
      e.printStackTrace();
    }
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    nextTurn = true;
    if (controller.isPlayerCharacter(controller.getCurrentCharacter())) {
      activateWeaponButtons();
    }
  }

  private void attackingAnEnemy() {
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
    try {
      Thread.sleep(1000);
      disableEnemyButtons();
      activateWeaponButtons();
      nextTurn = true;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void equipAWeapon(Button button) {
    nextTurn = false;
    controller.setEquippedWeaponCharacterNull(controller.getCurrentCharacter());
    controller.tryToEquip(controller.getCurrentCharacter(), controller.getCurrentWeapon());
    if (controller.getEquippedWeaponCharacter(controller.getCurrentCharacter()) == null) {
      centralLabel.setText(
              controller.getNameCharacter(controller.getCurrentCharacter()) + " can not equip the " +
                      controller.getNameWeapon(controller.getCurrentWeapon()));
      central2Label.setText("Please, choose another weapon");
      button.setDisable(true);
    } else {
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