package com.github.estebanzuniga.finalreality.gui;

import com.github.estebanzuniga.finalreality.controller.GameController;
import com.github.estebanzuniga.finalreality.controller.phases.InvalidMovementException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.control.Button;
import org.jetbrains.annotations.NotNull;

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
  private Label mainLabel;
  private Label enemy1LifeLabel;
  private Label enemy2LifeLabel;
  private Label enemy3LifeLabel;
  private Label characterNameLabel;
  private Label characterLifeLabel;
  private Label characterDefenseLabel;
  private Label characterAttackLabel;
  private Label centralLabel;
  private Label central2Label;
  private Label player1LifeLabel;
  private Label player2LifeLabel;
  private Label player3LifeLabel;
  private Label aliveEnemiesLabel;
  private Label alivePlayerCharacterLabel;
  private Label phaseLabel;
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
  //private Scene actualScene = createVictoryScene();
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
    Scene scene = new Scene(root, 500, 500);

    Label mainLabel = new Label("Final Reality");
    mainLabel.setMinSize(300, 200);
    mainLabel.setLayoutX(115);
    mainLabel.setLayoutY(40);
    mainLabel.setFont(new Font("Serif", 50));
    root.getChildren().add(mainLabel);

    Label label = new Label("Choose three different characters");
    label.setMinSize(100, 10);
    label.setLayoutX(160);
    label.setLayoutY(260);
    root.getChildren().add(label);

    Button engineerButton = new Button("Engineer");
    engineerButton.setMinSize(80, 40);
    engineerButton.setLayoutX(30);
    engineerButton.setLayoutY(290);
    root.getChildren().add(engineerButton);
    engineerButton.setOnAction((e) -> {
      controller.createEngineer("Engineer", rng.nextInt(50) + 450, rng.nextInt(30) + 20);
      engineerButton.setDisable(true);
    });

    Button knightButton = new Button("Knight");
    knightButton.setMinSize(80, 40);
    knightButton.setLayoutX(118);
    knightButton.setLayoutY(290);
    root.getChildren().add(knightButton);
    knightButton.setOnAction((e) -> {
      controller.createKnight("Knight", rng.nextInt(50) + 450, rng.nextInt(30) + 20);
      knightButton.setDisable(true);
    });

    Button thiefButton = new Button("Thief");
    thiefButton.setMinSize(80, 40);
    thiefButton.setLayoutX(206);
    thiefButton.setLayoutY(290);
    root.getChildren().add(thiefButton);
    thiefButton.setOnAction((e) -> {
      controller.createThief("Thief", rng.nextInt(50) + 450, rng.nextInt(30) + 20);
      thiefButton.setDisable(true);
    });

    Button whiteMageButton = new Button("White mage");
    whiteMageButton.setMinSize(80, 40);
    whiteMageButton.setLayoutX(294);
    whiteMageButton.setLayoutY(290);
    root.getChildren().add(whiteMageButton);
    whiteMageButton.setOnAction((e) -> {
      controller.createKnight("White Mage", rng.nextInt(50) + 450, rng.nextInt(30) + 20);
      whiteMageButton.setDisable(true);
    });

    Button blackMageButton = new Button("Black mage");
    blackMageButton.setMinSize(80, 40);
    blackMageButton.setLayoutX(382);
    blackMageButton.setLayoutY(290);
    root.getChildren().add(blackMageButton);
    blackMageButton.setOnAction((e) -> {
      controller.createKnight("Black Mage", rng.nextInt(50) + 450, rng.nextInt(30) + 20);
      blackMageButton.setDisable(true);
    });

    partySizeLabel = new Label("Party size: " + controller.getParty().size());
    partySizeLabel.setMinSize(100, 10);
    partySizeLabel.setLayoutX(420);
    partySizeLabel.setLayoutY(260);
    root.getChildren().add(partySizeLabel);

    phaseLabel = new Label("Current phase: " + controller.getCurrentPhase());
    phaseLabel.setLayoutX(20);
    phaseLabel.setLayoutY(470);
    phaseLabel.setMinSize(100, 10);
    root.getChildren().add(phaseLabel);

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
          controller.completeInventory();
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
    Scene scene = new Scene(mainRoot, 500, 500);
    createLabels();
    createButtons();
    disableEnemyButtons();
    startAnimatorMainScene();
    return scene;
  }

  private void startAnimatorMainScene() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {

        enemy1LifeLabel.setText("Life: " + controller.getLifeCharacter(controller.getAllEnemies(0)));
        enemy2LifeLabel.setText("Life: " + controller.getLifeCharacter(controller.getAllEnemies(1)));
        enemy3LifeLabel.setText("Life: " + controller.getLifeCharacter(controller.getAllEnemies(2)));
        player1LifeLabel.setText("Life: " + controller.getLifeCharacter(controller.getAllPlayers(0)));
        player2LifeLabel.setText("Life: " + controller.getLifeCharacter(controller.getAllPlayers(1)));
        player3LifeLabel.setText("Life: " + controller.getLifeCharacter(controller.getAllPlayers(2)));

        if (nextTurn) {
          centralLabel.setText("Next turn is for " + controller.getNameCharacter(controller.getCurrentCharacter()));
        }

        if (controller.isPlayerCharacter(controller.getCurrentCharacter())) {
          enemyAttackButton.setDisable(true);
        }
        else if (!controller.isPlayerCharacter(controller.getCurrentCharacter())) {
          disableWeaponButtons();
        }

        if (!controller.isPlayerCharacter(controller.getCurrentCharacter()) && enemyTurn) {
          enemyAttackButton.setDisable(false);
        }

        int alivePlayerCharacters = controller.getParty().size();
        alivePlayerCharacterLabel.setText("Alive player characters: " + alivePlayerCharacters);
        int aliveEnemies = controller.getEnemies().size();
        aliveEnemiesLabel.setText("Alive enemies: " + aliveEnemies);

        if (alivePlayerCharacters == 0) {
          actualScene = createDefeatScene();
          stop();
        } else if (aliveEnemies == 0) {
          actualScene = createVictoryScene();
          stop();
        }

        characterNameLabel.setText("Name: " + controller.getNameCharacter(controller.getCurrentCharacter()));
        characterLifeLabel.setText("Life: " + controller.getLifeCharacter(controller.getCurrentCharacter()));
        characterDefenseLabel.setText("Defense: " + controller.getDefenseCharacter(controller.getCurrentCharacter()));
        phaseLabel.setText("Current phase: " + controller.getCurrentPhase());

        if (controller.isPlayerCharacter(controller.getCurrentCharacter()) &&
                controller.getEquippedWeaponCharacter(controller.getCurrentCharacter()) != null) {
          characterAttackLabel.setText("Attack: " + controller.getDamageEquippedWeapon(controller.getCurrentCharacter()));
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
    mainLabel.setLayoutX(200);
    mainLabel.setLayoutY(10);
    mainLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(mainLabel);

    Label enemiesLabel = new Label("Enemies");
    enemiesLabel.setLayoutX(230);
    enemiesLabel.setLayoutY(40);
    enemiesLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(enemiesLabel);

    enemy1LifeLabel = new Label("Life: " + controller.getLifeCharacter(controller.getAllEnemies(0)));
    enemy1LifeLabel.setMinSize(60, 10);
    enemy1LifeLabel.setLayoutX(125);
    enemy1LifeLabel.setLayoutY(115);
    mainRoot.getChildren().add(enemy1LifeLabel);
    Label enemy1DefenseLabel = new Label("Defense: " + controller.getDefenseCharacter(controller.getAllEnemies(0)));
    enemy1DefenseLabel.setMinSize(60, 10);
    enemy1DefenseLabel.setLayoutX(125);
    enemy1DefenseLabel.setLayoutY(130);
    mainRoot.getChildren().add(enemy1DefenseLabel);
    Label enemy1AttackLabel = new Label("Attack: " + controller.getAttackEnemy(controller.getAllEnemies(0)));
    enemy1AttackLabel.setMinSize(60, 10);
    enemy1AttackLabel.setLayoutX(125);
    enemy1AttackLabel.setLayoutY(145);
    mainRoot.getChildren().add(enemy1AttackLabel);

    enemy2LifeLabel = new Label("Life: " + controller.getLifeCharacter(controller.getAllEnemies(1)));
    enemy2LifeLabel.setMinSize(60, 10);
    enemy2LifeLabel.setLayoutX(220);
    enemy2LifeLabel.setLayoutY(115);
    mainRoot.getChildren().add(enemy2LifeLabel);
    Label enemy2DefenseLabel = new Label("Defense: " + controller.getDefenseCharacter(controller.getAllEnemies(1)));
    enemy2DefenseLabel.setMinSize(60, 10);
    enemy2DefenseLabel.setLayoutX(220);
    enemy2DefenseLabel.setLayoutY(130);
    mainRoot.getChildren().add(enemy2DefenseLabel);
    Label enemy2AttackLabel = new Label("Attack: " + controller.getAttackEnemy(controller.getAllEnemies(1)));
    enemy2AttackLabel.setMinSize(60, 10);
    enemy2AttackLabel.setLayoutX(220);
    enemy2AttackLabel.setLayoutY(145);
    mainRoot.getChildren().add(enemy2AttackLabel);

    enemy3LifeLabel = new Label("Life: " + controller.getLifeCharacter(controller.getAllEnemies(2)));
    enemy3LifeLabel.setMinSize(60, 10);
    enemy3LifeLabel.setLayoutX(315);
    enemy3LifeLabel.setLayoutY(115);
    mainRoot.getChildren().add(enemy3LifeLabel);
    Label enemy3DefenseLabel = new Label("Defense: " + controller.getDefenseCharacter(controller.getAllEnemies(2)));
    enemy3DefenseLabel.setMinSize(60, 10);
    enemy3DefenseLabel.setLayoutX(315);
    enemy3DefenseLabel.setLayoutY(130);
    mainRoot.getChildren().add(enemy3DefenseLabel);
    Label enemy3AttackLabel = new Label("Attack: " + controller.getAttackEnemy(controller.getAllEnemies(2)));
    enemy3AttackLabel.setMinSize(60, 10);
    enemy3AttackLabel.setLayoutX(315);
    enemy3AttackLabel.setLayoutY(145);
    mainRoot.getChildren().add(enemy3AttackLabel);

    Label turnLabel = new Label("Character in turn:");
    turnLabel.setLayoutX(10);
    turnLabel.setLayoutY(190);
    turnLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(turnLabel);

    characterNameLabel = new Label("");
    characterNameLabel.setLayoutX(10);
    characterNameLabel.setLayoutY(205);
    characterNameLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(characterNameLabel);

    characterLifeLabel = new Label("");
    characterLifeLabel.setLayoutX(10);
    characterLifeLabel.setLayoutY(220);
    characterLifeLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(characterLifeLabel);

    characterDefenseLabel = new Label("");
    characterDefenseLabel.setLayoutX(10);
    characterDefenseLabel.setLayoutY(235);
    characterDefenseLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(characterDefenseLabel);

    characterAttackLabel = new Label("");
    characterAttackLabel.setLayoutX(10);
    characterAttackLabel.setLayoutY(250);
    characterAttackLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(characterAttackLabel);

    centralLabel = new Label("");
    centralLabel.setLayoutX(185);
    centralLabel.setLayoutY(220);
    centralLabel.setMinSize(100, 20);
    mainRoot.getChildren().add(centralLabel);

    central2Label = new Label("");
    central2Label.setLayoutX(185);
    central2Label.setLayoutY(240);
    central2Label.setMinSize(100, 20);
    mainRoot.getChildren().add(central2Label);

    Label inventoryLabel = new Label("Inventory");
    inventoryLabel.setLayoutX(225);
    inventoryLabel.setLayoutY(280);
    inventoryLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(inventoryLabel);

    Label axeDamageLabel = new Label("Damage: " + controller.getDamageWeapon(0));
    axeDamageLabel.setLayoutY(350);
    axeDamageLabel.setLayoutX(30);
    axeDamageLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(axeDamageLabel);
    Label bowDamageLabel = new Label("Damage: " + controller.getDamageWeapon(1));
    bowDamageLabel.setLayoutY(350);
    bowDamageLabel.setLayoutX(125);
    bowDamageLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(bowDamageLabel);
    Label knifeDamageLabel = new Label("Damage: " + controller.getDamageWeapon(2));
    knifeDamageLabel.setLayoutY(350);
    knifeDamageLabel.setLayoutX(220);
    knifeDamageLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(knifeDamageLabel);
    Label staffDamageLabel = new Label("Damage: " + controller.getDamageWeapon(3));
    staffDamageLabel.setLayoutY(350);
    staffDamageLabel.setLayoutX(315);
    staffDamageLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(staffDamageLabel);
    Label swordDamageLabel = new Label("Damage: " + controller.getDamageWeapon(4));
    swordDamageLabel.setLayoutY(350);
    swordDamageLabel.setLayoutX(410);
    swordDamageLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(swordDamageLabel);

    Label playerCharactersLabel = new Label("Your characters:");
    playerCharactersLabel.setLayoutX(10);
    playerCharactersLabel.setLayoutY(390);
    playerCharactersLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(playerCharactersLabel);

    Label player1NameLabel = new Label(controller.getNameCharacter(controller.getAllPlayers(0)));
    player1NameLabel.setMinSize(60, 10);
    player1NameLabel.setLayoutX(125);
    player1NameLabel.setLayoutY(390);
    mainRoot.getChildren().add(player1NameLabel);
    player1LifeLabel = new Label("Life: " + controller.getLifeCharacter(controller.getAllPlayers(0)));
    player1LifeLabel.setMinSize(60, 10);
    player1LifeLabel.setLayoutX(125);
    player1LifeLabel.setLayoutY(410);
    mainRoot.getChildren().add(player1LifeLabel);
    Label player1DefenseLabel = new Label("Defense: " + controller.getDefenseCharacter(controller.getAllPlayers(0)));
    player1DefenseLabel.setMinSize(60, 10);
    player1DefenseLabel.setLayoutX(125);
    player1DefenseLabel.setLayoutY(425);
    mainRoot.getChildren().add(player1DefenseLabel);

    Label player2NameLabel = new Label(controller.getNameCharacter(controller.getAllPlayers(1)));
    player2NameLabel.setMinSize(60, 10);
    player2NameLabel.setLayoutX(220);
    player2NameLabel.setLayoutY(390);
    mainRoot.getChildren().add(player2NameLabel);
    player2LifeLabel = new Label("Life: " + controller.getLifeCharacter(controller.getAllPlayers(1)));
    player2LifeLabel.setMinSize(60, 10);
    player2LifeLabel.setLayoutX(220);
    player2LifeLabel.setLayoutY(410);
    mainRoot.getChildren().add(player2LifeLabel);
    Label player2DefenseLabel = new Label("Defense: " + controller.getDefenseCharacter(controller.getAllPlayers(1)));
    player2DefenseLabel.setMinSize(60, 10);
    player2DefenseLabel.setLayoutX(220);
    player2DefenseLabel.setLayoutY(425);
    mainRoot.getChildren().add(player2DefenseLabel);

    Label player3NameLabel = new Label(controller.getNameCharacter(controller.getAllPlayers(2)));
    player3NameLabel.setMinSize(60, 10);
    player3NameLabel.setLayoutX(315);
    player3NameLabel.setLayoutY(390);
    mainRoot.getChildren().add(player3NameLabel);
    player3LifeLabel = new Label("Life: " + controller.getLifeCharacter(controller.getAllPlayers(2)));
    player3LifeLabel.setMinSize(60, 10);
    player3LifeLabel.setLayoutX(315);
    player3LifeLabel.setLayoutY(410);
    mainRoot.getChildren().add(player3LifeLabel);
    Label player3DefenseLabel = new Label("Defense: " + controller.getDefenseCharacter(controller.getAllPlayers(2)));
    player3DefenseLabel.setMinSize(60, 10);
    player3DefenseLabel.setLayoutX(315);
    player3DefenseLabel.setLayoutY(425);
    mainRoot.getChildren().add(player3DefenseLabel);

    aliveEnemiesLabel = new Label("");
    aliveEnemiesLabel.setLayoutX(360);
    aliveEnemiesLabel.setLayoutY(465);
    aliveEnemiesLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(aliveEnemiesLabel);

    alivePlayerCharacterLabel = new Label("");
    alivePlayerCharacterLabel.setLayoutX(360);
    alivePlayerCharacterLabel.setLayoutY(480);
    alivePlayerCharacterLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(alivePlayerCharacterLabel);

    phaseLabel = new Label("Current phase: " + controller.getCurrentPhase());
    phaseLabel.setLayoutX(10);
    phaseLabel.setLayoutY(480);
    phaseLabel.setMinSize(100, 10);
    mainRoot.getChildren().add(phaseLabel);
  }

  private void createButtons() {

    enemyAttackButton = new Button("Simulate Enemy Turn");
    enemyAttackButton.setMinSize(120, 20);
    enemyAttackButton.setLayoutX(185);
    enemyAttackButton.setLayoutY(175);
    enemyAttackButton.setOnAction((e) -> enemyAttack());
    mainRoot.getChildren().add(enemyAttackButton);

    enemy1Button = new Button("Enemy 1");
    enemy1Button.setMinSize(60, 40);
    enemy1Button.setLayoutX(125);
    enemy1Button.setLayoutY(70);
    enemy1Button.setOnAction((e) -> {
      controller.setCurrentOpponentToAttack(controller.getAllEnemies(0));
      attackingAnEnemy();
    });
    mainRoot.getChildren().add(enemy1Button);

    enemy2Button = new Button("Enemy 2");
    enemy2Button.setMinSize(60, 40);
    enemy2Button.setLayoutX(220);
    enemy2Button.setLayoutY(70);
    enemy2Button.setOnAction((e) -> {
      controller.setCurrentOpponentToAttack(controller.getAllEnemies(1));
      attackingAnEnemy();
    });
    mainRoot.getChildren().add(enemy2Button);

    enemy3Button = new Button("Enemy 3");
    enemy3Button.setMinSize(60, 40);
    enemy3Button.setLayoutX(315);
    enemy3Button.setLayoutY(70);
    enemy3Button.setOnAction((e) -> {
      controller.setCurrentOpponentToAttack(controller.getAllEnemies(2));
      attackingAnEnemy();
    });
    mainRoot.getChildren().add(enemy3Button);

    axeButton = new Button("Axe");
    axeButton.setMinSize(60, 40);
    axeButton.setLayoutX(30);
    axeButton.setLayoutY(310);
    axeButton.setOnAction((e) -> {
      controller.setCurrentWeapon(controller.getWeapon(0));
      equipAWeapon(axeButton);
    });
    mainRoot.getChildren().add(axeButton);

    bowButton = new Button("Bow");
    bowButton.setMinSize(60, 40);
    bowButton.setLayoutX(125);
    bowButton.setLayoutY(310);
    bowButton.setOnAction((e) -> {
      controller.setCurrentWeapon(controller.getWeapon(1));
      equipAWeapon(bowButton);
    });
    mainRoot.getChildren().add(bowButton);

    knifeButton = new Button("Knife");
    knifeButton.setMinSize(60, 40);
    knifeButton.setLayoutX(220);
    knifeButton.setLayoutY(310);
    knifeButton.setOnAction((e) -> {
      controller.setCurrentWeapon(controller.getWeapon(2));
      equipAWeapon(knifeButton);
    });
    mainRoot.getChildren().add(knifeButton);

    staffButton = new Button("Staff");
    staffButton.setMinSize(60, 40);
    staffButton.setLayoutX(315);
    staffButton.setLayoutY(310);
    staffButton.setOnAction((e) -> {
      controller.setCurrentWeapon(controller.getWeapon(3));
      equipAWeapon(staffButton);
    });
    mainRoot.getChildren().add(staffButton);

    swordButton = new Button("Sword");
    swordButton.setMinSize(60, 40);
    swordButton.setLayoutX(410);
    swordButton.setLayoutY(310);
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
    controller.setCurrentOpponentToAttack(controller.getPlayer(i));
    controller.tryToAttack(controller.getCurrentCharacter(), controller.getCurrentOpponentToAttack());
    centralLabel.setText(controller.getNameCharacter(controller.getCurrentCharacter()) + " is attacking " +
            controller.getNameCharacter(controller.getCurrentOpponentToAttack()));
    if (controller.isDead(controller.getCurrentOpponentToAttack())) {
      controller.getTurns().remove(controller.getCurrentOpponentToAttack());
    }
    disableWeaponButtons();
    disableEnemyButtons();
    controller.setCurrentWeapon(null);
    controller.setCurrentOpponentToAttack(null);
    try {
      controller.tryToExtractCharacter();
    } catch (InvalidMovementException e) {
      e.printStackTrace();
    }
    /*try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }*/
    controller.setCurrentCharacter(controller.getTurns().peek());
    nextTurn = true;
    if (controller.isPlayerCharacter(controller.getCurrentCharacter())) {
      activateWeaponButtons();
    }
  }

  //problemas con centralLabel

  private void attackingAnEnemy() {
    centralLabel.setText(controller.getNameCharacter(controller.getCurrentCharacter()) +
            " is attacking " + controller.getNameCharacter(controller.getCurrentOpponentToAttack()) +
            " with the " + controller.getNameWeapon(controller.getEquippedWeaponCharacter(controller.getCurrentCharacter())));
    controller.tryToAttack(controller.getCurrentCharacter(), controller.getCurrentOpponentToAttack());
    if (controller.isDead(controller.getCurrentOpponentToAttack())) {
      controller.getTurns().remove(controller.getCurrentOpponentToAttack());
    }
    controller.setCurrentWeapon(null);
    controller.setCurrentOpponentToAttack(null);
    try {
      controller.tryToExtractCharacter();
    } catch (InvalidMovementException e) {
      e.printStackTrace();
    }
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    controller.setCurrentCharacter(controller.getTurns().peek());
    disableEnemyButtons();
    activateWeaponButtons();
    nextTurn = true;
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
    if (!controller.isDead(controller.getAllEnemies(0))) {
      enemy1Button.setDisable(false);
    }
    if (!controller.isDead(controller.getAllEnemies(1))) {
      enemy2Button.setDisable(false);
    }
    if (!controller.isDead(controller.getAllEnemies(2))) {
      enemy3Button.setDisable(false);
    }
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
    Scene scene = new Scene(root, 500, 500);

    mainLabel = new Label("Final Reality");
    mainLabel.setMinSize(300, 150);
    mainLabel.setLayoutX(115);
    mainLabel.setLayoutY(30);
    mainLabel.setFont(new Font("Serif", 50));
    root.getChildren().add(mainLabel);

    Label main2Label = new Label("CONGRATULATIONS");
    main2Label.setMinSize(400, 100);
    main2Label.setLayoutX(130);
    main2Label.setLayoutY(180);
    main2Label.setFont(new Font("Serif", 25));
    root.getChildren().add(main2Label);

    Label winLabel = new Label("YOU WON!");
    winLabel.setMinSize(400,100);
    winLabel.setLayoutX(200);
    winLabel.setLayoutY(220);
    winLabel.setFont(new Font("Serif", 20));
    root.getChildren().add(winLabel);

    Button playAgainButton = new Button("Play Again");
    playAgainButton.setMinSize(80, 40);
    playAgainButton.setLayoutX(210);
    playAgainButton.setLayoutY(350);
    playAgainButton.setOnAction((e) -> {
      actualScene = createSetPartyScene();
      controller.tryToNewGame();
      playAgainButton.setDisable(true);
    });
    root.getChildren().add(playAgainButton);

    phaseLabel = new Label("Current phase: " + controller.getCurrentPhase());
    phaseLabel.setLayoutX(20);
    phaseLabel.setLayoutY(470);
    phaseLabel.setMinSize(100, 10);
    root.getChildren().add(phaseLabel);

    return scene;
  }

  private Scene createDefeatScene() {
    Group root = new Group();
    Scene scene = new Scene(root, 500, 500);

    mainLabel = new Label("Final Reality");
    mainLabel.setMinSize(300, 150);
    mainLabel.setLayoutX(115);
    mainLabel.setLayoutY(50);
    mainLabel.setFont(new Font("Serif", 50));
    root.getChildren().add(mainLabel);

    Label winLabel = new Label("YOU LOST...");
    winLabel.setMinSize(400,100);
    winLabel.setLayoutX(200);
    winLabel.setLayoutY(200);
    winLabel.setFont(new Font("Serif", 20));
    root.getChildren().add(winLabel);

    Button playAgainButton = new Button("Play Again");
    playAgainButton.setMinSize(80, 40);
    playAgainButton.setLayoutX(210);
    playAgainButton.setLayoutY(350);
    playAgainButton.setOnAction((e) -> {
      actualScene = createSetPartyScene();
      controller.tryToNewGame();
      playAgainButton.setDisable(true);
    });
    root.getChildren().add(playAgainButton);

    phaseLabel = new Label("Current phase: " + controller.getCurrentPhase());
    phaseLabel.setLayoutX(20);
    phaseLabel.setLayoutY(470);
    phaseLabel.setMinSize(100, 10);
    root.getChildren().add(phaseLabel);

    return scene;
  }

}