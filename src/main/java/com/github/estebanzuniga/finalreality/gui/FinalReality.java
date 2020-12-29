package com.github.estebanzuniga.finalreality.gui;

import com.github.estebanzuniga.finalreality.controller.GameController;
import com.github.estebanzuniga.finalreality.controller.phases.InvalidMovementException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.control.Button;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
  private Scene actualScene = createInitialScene();
  private final Random rng = new Random();
  private static final String RESOURCE_PATH = "src/main/resources/";

  public FinalReality() throws FileNotFoundException {
  }

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(@NotNull Stage primaryStage) {
    primaryStage.setTitle("Final reality");
    primaryStage.setResizable(false);
    primaryStage.setX(500);
    primaryStage.setY(150);
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

  private Scene createInitialScene() throws FileNotFoundException {
    Group root = new Group();
    Scene scene = new Scene(root, 500, 500);
    var background =
            new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "background.jpg")));
    root.getChildren().add(background);

    Label mainLabel = new Label("Final Reality");
    mainLabel.setMinSize(300, 200);
    mainLabel.setLayoutX(90);
    mainLabel.setLayoutY(30);
    mainLabel.setTextFill(Color.WHITE);
    mainLabel.setFont(new Font("Serif", 50));
    root.getChildren().add(mainLabel);

    Label bestWinLabel = new Label("MAY THE BEST WIN...");
    bestWinLabel.setLayoutX(230);
    bestWinLabel.setLayoutY(170);
    bestWinLabel.setFont(new Font("Serif", 20));
    bestWinLabel.setTextFill(Color.WHITE);
    root.getChildren().add(bestWinLabel);

    Label label = new Label("Choose three different characters");
    label.setMinSize(100, 20);
    label.setLayoutX(135);
    label.setLayoutY(250);
    label.setFont(new Font("Serif", 15));
    label.setTextFill(Color.WHITE);
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

    partySizeLabel = new Label("");
    partySizeLabel.setMinSize(100, 10);
    partySizeLabel.setLayoutX(300);
    partySizeLabel.setLayoutY(350);
    partySizeLabel.setTextFill(Color.WHITE);
    root.getChildren().add(partySizeLabel);

    phaseLabel = new Label("Current phase: " + controller.getCurrentPhase());
    phaseLabel.setLayoutX(20);
    phaseLabel.setLayoutY(470);
    phaseLabel.setMinSize(100, 10);
    root.getChildren().add(phaseLabel);

    startAnimatorInitialScene();

    return scene;
  }

  private void startAnimatorInitialScene() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        int partySize = controller.getParty().size();
        partySizeLabel.setText("You have chosen " + partySize + " characters");
        phaseLabel.setText("Current phase: " + controller.getCurrentPhase());
        if (partySize == 3) {
          controller.tryToSetEnemies();
          controller.completeInventory();
          controller.setCurrentCharacter(controller.getTurns().peek());
          try {
            actualScene = createMainScene();
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
          stop();
        }
      }
    };
    timer.start();
  }


  ///MAIN SCENE

  private Scene createMainScene() throws FileNotFoundException {
    mainRoot = new Group();
    Scene scene = new Scene(mainRoot, 500, 500);
    var background =
            new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "background.jpg")));
    mainRoot.getChildren().add(background);
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
          try {
            actualScene = createDefeatScene();
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
          stop();
        } else if (aliveEnemies == 0) {
          try {
            actualScene = createVictoryScene();
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
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
    Label enemiesLabel = new Label("Enemies");
    enemiesLabel.setLayoutX(222);
    enemiesLabel.setLayoutY(15);
    enemiesLabel.setFont(new Font(Font.getDefault().getName(), 15));
    enemiesLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(enemiesLabel);

    Label enemy1NameLabel = new Label("Venom");
    enemy1NameLabel.setLayoutX(115);
    enemy1NameLabel.setLayoutY(45);
    enemy1NameLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(enemy1NameLabel);
    enemy1LifeLabel = new Label("Life: " + controller.getLifeCharacter(controller.getAllEnemies(0)));
    enemy1LifeLabel.setLayoutX(105);
    enemy1LifeLabel.setLayoutY(115);
    enemy1LifeLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(enemy1LifeLabel);
    Label enemy1DefenseLabel = new Label("Defense: " + controller.getDefenseCharacter(controller.getAllEnemies(0)));
    enemy1DefenseLabel.setLayoutX(105);
    enemy1DefenseLabel.setLayoutY(130);
    enemy1DefenseLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(enemy1DefenseLabel);
    Label enemy1AttackLabel = new Label("Attack: " + controller.getAttackEnemy(controller.getAllEnemies(0)));
    enemy1AttackLabel.setLayoutX(105);
    enemy1AttackLabel.setLayoutY(145);
    enemy1AttackLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(enemy1AttackLabel);

    Label enemy2NameLabel = new Label("Dr. Octopus");
    enemy2NameLabel.setLayoutX(216);
    enemy2NameLabel.setLayoutY(45);
    enemy2NameLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(enemy2NameLabel);
    enemy2LifeLabel = new Label("Life: " + controller.getLifeCharacter(controller.getAllEnemies(1)));
    enemy2LifeLabel.setLayoutX(220);
    enemy2LifeLabel.setLayoutY(115);
    enemy2LifeLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(enemy2LifeLabel);
    Label enemy2DefenseLabel = new Label("Defense: " + controller.getDefenseCharacter(controller.getAllEnemies(1)));
    enemy2DefenseLabel.setLayoutX(220);
    enemy2DefenseLabel.setLayoutY(130);
    enemy2DefenseLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(enemy2DefenseLabel);
    Label enemy2AttackLabel = new Label("Attack: " + controller.getAttackEnemy(controller.getAllEnemies(1)));
    enemy2AttackLabel.setLayoutX(220);
    enemy2AttackLabel.setLayoutY(145);
    enemy2AttackLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(enemy2AttackLabel);

    Label enemy3NameLabel = new Label("Green Goblin");
    enemy3NameLabel.setLayoutX(328);
    enemy3NameLabel.setLayoutY(45);
    enemy3NameLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(enemy3NameLabel);
    enemy3LifeLabel = new Label("Life: " + controller.getLifeCharacter(controller.getAllEnemies(2)));
    enemy3LifeLabel.setLayoutX(335);
    enemy3LifeLabel.setLayoutY(115);
    enemy3LifeLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(enemy3LifeLabel);
    Label enemy3DefenseLabel = new Label("Defense: " + controller.getDefenseCharacter(controller.getAllEnemies(2)));
    enemy3DefenseLabel.setLayoutX(335);
    enemy3DefenseLabel.setLayoutY(130);
    enemy3DefenseLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(enemy3DefenseLabel);
    Label enemy3AttackLabel = new Label("Attack: " + controller.getAttackEnemy(controller.getAllEnemies(2)));
    enemy3AttackLabel.setLayoutX(335);
    enemy3AttackLabel.setLayoutY(145);
    enemy3AttackLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(enemy3AttackLabel);

    Label turnLabel = new Label("Character in turn:");
    turnLabel.setLayoutX(20);
    turnLabel.setLayoutY(180);
    turnLabel.setFont(new Font(Font.getDefault().getName(), 15));
    turnLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(turnLabel);

    characterNameLabel = new Label("");
    characterNameLabel.setLayoutX(20);
    characterNameLabel.setLayoutY(205);
    characterNameLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(characterNameLabel);
    characterLifeLabel = new Label("");
    characterLifeLabel.setLayoutX(20);
    characterLifeLabel.setLayoutY(220);
    characterLifeLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(characterLifeLabel);
    characterDefenseLabel = new Label("");
    characterDefenseLabel.setLayoutX(20);
    characterDefenseLabel.setLayoutY(235);
    characterDefenseLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(characterDefenseLabel);
    characterAttackLabel = new Label("");
    characterAttackLabel.setLayoutX(20);
    characterAttackLabel.setLayoutY(250);
    characterAttackLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(characterAttackLabel);

    centralLabel = new Label("");
    centralLabel.setLayoutX(165);
    centralLabel.setLayoutY(205);
    centralLabel.setFont(new Font(Font.getDefault().getName(), 15));
    centralLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(centralLabel);

    central2Label = new Label("");
    central2Label.setLayoutX(165);
    central2Label.setLayoutY(230);
    central2Label.setFont(new Font(Font.getDefault().getName(), 15));
    central2Label.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(central2Label);

    aliveEnemiesLabel = new Label("");
    aliveEnemiesLabel.setLayoutX(350);
    aliveEnemiesLabel.setLayoutY(180);
    aliveEnemiesLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(aliveEnemiesLabel);

    alivePlayerCharacterLabel = new Label("");
    alivePlayerCharacterLabel.setLayoutX(350);
    alivePlayerCharacterLabel.setLayoutY(195);
    alivePlayerCharacterLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(alivePlayerCharacterLabel);

    Label inventoryLabel = new Label("Inventory");
    inventoryLabel.setLayoutX(222);
    inventoryLabel.setLayoutY(280);
    inventoryLabel.setFont(new Font(Font.getDefault().getName(), 15));
    inventoryLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(inventoryLabel);

    Label axeNameLabel = new Label("Axe");
    axeNameLabel.setLayoutX(47);
    axeNameLabel.setLayoutY(310);
    axeNameLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(axeNameLabel);
    Label axeDamageLabel = new Label("Damage: " + controller.getDamageWeapon(0));
    axeDamageLabel.setLayoutY(380);
    axeDamageLabel.setLayoutX(25);
    axeDamageLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(axeDamageLabel);

    Label bowNameLabel = new Label("Bow");
    bowNameLabel.setLayoutX(142);
    bowNameLabel.setLayoutY(310);
    bowNameLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(bowNameLabel);
    Label bowDamageLabel = new Label("Damage: " + controller.getDamageWeapon(1));
    bowDamageLabel.setLayoutY(380);
    bowDamageLabel.setLayoutX(120);
    bowDamageLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(bowDamageLabel);

    Label knifeNameLabel = new Label("Knife");
    knifeNameLabel.setLayoutX(235);
    knifeNameLabel.setLayoutY(310);
    knifeNameLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(knifeNameLabel);
    Label knifeDamageLabel = new Label("Damage: " + controller.getDamageWeapon(2));
    knifeDamageLabel.setLayoutY(380);
    knifeDamageLabel.setLayoutX(215);
    knifeDamageLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(knifeDamageLabel);

    Label staffNameLabel = new Label("Staff");
    staffNameLabel.setLayoutX(330);
    staffNameLabel.setLayoutY(310);
    staffNameLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(staffNameLabel);
    Label staffDamageLabel = new Label("Damage: " + controller.getDamageWeapon(3));
    staffDamageLabel.setLayoutY(380);
    staffDamageLabel.setLayoutX(310);
    staffDamageLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(staffDamageLabel);

    Label swordNameLabel = new Label("Sword");
    swordNameLabel.setLayoutX(420);
    swordNameLabel.setLayoutY(310);
    swordNameLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(swordNameLabel);
    Label swordDamageLabel = new Label("Damage: " + controller.getDamageWeapon(4));
    swordDamageLabel.setLayoutY(380);
    swordDamageLabel.setLayoutX(405);
    swordDamageLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(swordDamageLabel);

    Label playerCharactersLabel = new Label("Your characters:");
    playerCharactersLabel.setLayoutX(20);
    playerCharactersLabel.setLayoutY(410);
    playerCharactersLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(playerCharactersLabel);

    Label player1NameLabel = new Label(controller.getNameCharacter(controller.getAllPlayers(0)));
    player1NameLabel.setLayoutX(125);
    player1NameLabel.setLayoutY(410);
    player1NameLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(player1NameLabel);
    player1LifeLabel = new Label("Life: " + controller.getLifeCharacter(controller.getAllPlayers(0)));
    player1LifeLabel.setLayoutX(125);
    player1LifeLabel.setLayoutY(430);
    player1LifeLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(player1LifeLabel);
    Label player1DefenseLabel = new Label("Defense: " + controller.getDefenseCharacter(controller.getAllPlayers(0)));
    player1DefenseLabel.setLayoutX(125);
    player1DefenseLabel.setLayoutY(445);
    player1DefenseLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(player1DefenseLabel);

    Label player2NameLabel = new Label(controller.getNameCharacter(controller.getAllPlayers(1)));
    player2NameLabel.setLayoutX(220);
    player2NameLabel.setLayoutY(410);
    player2NameLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(player2NameLabel);
    player2LifeLabel = new Label("Life: " + controller.getLifeCharacter(controller.getAllPlayers(1)));
    player2LifeLabel.setLayoutX(220);
    player2LifeLabel.setLayoutY(430);
    player2LifeLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(player2LifeLabel);
    Label player2DefenseLabel = new Label("Defense: " + controller.getDefenseCharacter(controller.getAllPlayers(1)));
    player2DefenseLabel.setLayoutX(220);
    player2DefenseLabel.setLayoutY(445);
    player2DefenseLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(player2DefenseLabel);

    Label player3NameLabel = new Label(controller.getNameCharacter(controller.getAllPlayers(2)));
    player3NameLabel.setLayoutX(315);
    player3NameLabel.setLayoutY(410);
    player3NameLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(player3NameLabel);
    player3LifeLabel = new Label("Life: " + controller.getLifeCharacter(controller.getAllPlayers(2)));
    player3LifeLabel.setLayoutX(315);
    player3LifeLabel.setLayoutY(430);
    player3LifeLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(player3LifeLabel);
    Label player3DefenseLabel = new Label("Defense: " + controller.getDefenseCharacter(controller.getAllPlayers(2)));
    player3DefenseLabel.setLayoutX(315);
    player3DefenseLabel.setLayoutY(445);
    player3DefenseLabel.setTextFill(Color.WHITE);
    mainRoot.getChildren().add(player3DefenseLabel);

    phaseLabel = new Label("Current phase: " + controller.getCurrentPhase());
    phaseLabel.setLayoutX(20);
    phaseLabel.setLayoutY(470);
    mainRoot.getChildren().add(phaseLabel);
  }

  private void createButtons() throws FileNotFoundException {

    enemyAttackButton = new Button("Simulate Enemy Turn");
    enemyAttackButton.setMinSize(120, 20);
    enemyAttackButton.setLayoutX(185);
    enemyAttackButton.setLayoutY(175);
    enemyAttackButton.setOnAction((e) -> enemyAttack());
    mainRoot.getChildren().add(enemyAttackButton);

    var venomImage =
            new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "venom.png")));
    venomImage.setFitHeight(40);
    venomImage.setFitWidth(40);
    enemy1Button = new Button("", venomImage);
    enemy1Button.setMinSize(40, 40);
    enemy1Button.setLayoutX(105);
    enemy1Button.setLayoutY(65);
    enemy1Button.setOnAction((e) -> {
      controller.setCurrentOpponentToAttack(controller.getAllEnemies(0));
      attackingAnEnemy();
    });
    mainRoot.getChildren().add(enemy1Button);

    var drOctopusImage =
            new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "droctopus.png")));
    drOctopusImage.setFitHeight(40);
    drOctopusImage.setFitWidth(40);
    enemy2Button = new Button("", drOctopusImage);
    enemy2Button.setMinSize(40, 40);
    enemy2Button.setLayoutX(220);
    enemy2Button.setLayoutY(65);
    enemy2Button.setOnAction((e) -> {
      controller.setCurrentOpponentToAttack(controller.getAllEnemies(1));
      attackingAnEnemy();
    });
    mainRoot.getChildren().add(enemy2Button);

    var greenGoblinImage =
            new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "greengoblin.png")));
    greenGoblinImage.setFitHeight(40);
    greenGoblinImage.setFitWidth(40);
    enemy3Button = new Button("", greenGoblinImage);
    enemy3Button.setMinSize(40, 40);
    enemy3Button.setLayoutX(335);
    enemy3Button.setLayoutY(65);
    enemy3Button.setOnAction((e) -> {
      controller.setCurrentOpponentToAttack(controller.getAllEnemies(2));
      attackingAnEnemy();
    });
    mainRoot.getChildren().add(enemy3Button);

    var axeImage =
            new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "axe.png")));
    axeImage.setFitHeight(40);
    axeImage.setFitWidth(40);
    axeButton = new Button("", axeImage);
    axeButton.setMinSize(40, 40);
    axeButton.setLayoutX(30);
    axeButton.setLayoutY(330);
    axeButton.setOnAction((e) -> {
      controller.setCurrentWeapon(controller.getWeapon(0));
      equipAWeapon(axeButton);
    });
    mainRoot.getChildren().add(axeButton);

    var bowImage =
            new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "bow.png")));
    bowImage.setFitHeight(40);
    bowImage.setFitWidth(40);
    bowButton = new Button("", bowImage);
    bowButton.setMinSize(40, 40);
    bowButton.setLayoutX(125);
    bowButton.setLayoutY(330);
    bowButton.setOnAction((e) -> {
      controller.setCurrentWeapon(controller.getWeapon(1));
      equipAWeapon(bowButton);
    });
    mainRoot.getChildren().add(bowButton);

    var knifeImage =
            new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "knife.png")));
    knifeImage.setFitHeight(40);
    knifeImage.setFitWidth(40);
    knifeButton = new Button("", knifeImage);
    knifeButton.setMinSize(40, 40);
    knifeButton.setLayoutX(220);
    knifeButton.setLayoutY(330);
    knifeButton.setOnAction((e) -> {
      controller.setCurrentWeapon(controller.getWeapon(2));
      equipAWeapon(knifeButton);
    });
    mainRoot.getChildren().add(knifeButton);

    var staffImage =
            new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "staff.png")));
    staffImage.setFitHeight(40);
    staffImage.setFitWidth(40);
    staffButton = new Button("", staffImage);
    staffButton.setMinSize(40, 40);
    staffButton.setLayoutX(315);
    staffButton.setLayoutY(330);
    staffButton.setOnAction((e) -> {
      controller.setCurrentWeapon(controller.getWeapon(3));
      equipAWeapon(staffButton);
    });
    mainRoot.getChildren().add(staffButton);

    var swordImage =
            new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "sword.png")));
    swordImage.setFitHeight(40);
    swordImage.setFitWidth(40);
    swordButton = new Button("", swordImage);
    swordButton.setMinSize(40, 40);
    swordButton.setLayoutX(410);
    swordButton.setLayoutY(330);
    swordButton.setOnAction((e) -> {
      controller.setCurrentWeapon(controller.getWeapon(4));
      equipAWeapon(swordButton);
    });
    mainRoot.getChildren().add(swordButton);
  }

  //crear variable isAttacking boolean, para ver si está atacando y actualizar la etiqueta

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
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
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

  private Scene createVictoryScene() throws FileNotFoundException {
    Group root = new Group();
    Scene scene = new Scene(root, 500, 500);
    var background =
            new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "background.jpg")));
    root.getChildren().add(background);


    Label mainLabel = new Label("Final Reality");
    mainLabel.setMinSize(300, 200);
    mainLabel.setLayoutX(90);
    mainLabel.setLayoutY(30);
    mainLabel.setTextFill(Color.WHITE);
    mainLabel.setFont(new Font("Serif", 50));
    root.getChildren().add(mainLabel);

    Label bestWinLabel = new Label("MAY THE BEST WIN...");
    bestWinLabel.setLayoutX(230);
    bestWinLabel.setLayoutY(170);
    bestWinLabel.setFont(new Font("Serif", 20));
    bestWinLabel.setTextFill(Color.WHITE);
    root.getChildren().add(bestWinLabel);

    Label main2Label = new Label("CONGRATULATIONS");
    main2Label.setMinSize(400, 100);
    main2Label.setLayoutX(132);
    main2Label.setLayoutY(210);
    main2Label.setFont(new Font("Serif", 25));
    main2Label.setTextFill(Color.WHITE);
    root.getChildren().add(main2Label);

    Label winLabel = new Label("YOU WON!");
    winLabel.setMinSize(400,100);
    winLabel.setLayoutX(200);
    winLabel.setLayoutY(250);
    winLabel.setFont(new Font("Serif", 20));
    winLabel.setTextFill(Color.WHITE);
    root.getChildren().add(winLabel);

    Button playAgainButton = new Button("Play Again");
    playAgainButton.setMinSize(80, 40);
    playAgainButton.setLayoutX(210);
    playAgainButton.setLayoutY(350);
    playAgainButton.setOnAction((e) -> {
      try {
        actualScene = createInitialScene();
      } catch (FileNotFoundException fileNotFoundException) {
        fileNotFoundException.printStackTrace();
      }
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

  private Scene createDefeatScene() throws FileNotFoundException {
    Group root = new Group();
    Scene scene = new Scene(root, 500, 500);
    var background =
            new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "background.jpg")));
    root.getChildren().add(background);

    Label mainLabel = new Label("Final Reality");
    mainLabel.setMinSize(300, 200);
    mainLabel.setLayoutX(90);
    mainLabel.setLayoutY(30);
    mainLabel.setTextFill(Color.WHITE);
    mainLabel.setFont(new Font("Serif", 50));
    root.getChildren().add(mainLabel);

    Label bestWinLabel = new Label("MAY THE BEST WIN...");
    bestWinLabel.setLayoutX(230);
    bestWinLabel.setLayoutY(170);
    bestWinLabel.setFont(new Font("Serif", 20));
    bestWinLabel.setTextFill(Color.WHITE);
    root.getChildren().add(bestWinLabel);

    Label loseLabel = new Label("YOU LOST...");
    loseLabel.setMinSize(400,100);
    loseLabel.setLayoutX(200);
    loseLabel.setLayoutY(210);
    loseLabel.setFont(new Font("Serif", 20));
    loseLabel.setTextFill(Color.WHITE);
    root.getChildren().add(loseLabel);

    Button playAgainButton = new Button("Play Again");
    playAgainButton.setMinSize(80, 40);
    playAgainButton.setLayoutX(210);
    playAgainButton.setLayoutY(320);
    playAgainButton.setOnAction((e) -> {
      try {
        actualScene = createInitialScene();
      } catch (FileNotFoundException fileNotFoundException) {
        fileNotFoundException.printStackTrace();
      }
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