package com.github.estebanzuniga.finalreality.gui;

import com.github.estebanzuniga.finalreality.controller.GameController;
import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.player.IPlayerCharacter;
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
  private Scene actualScene = createSetPartyScene();

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(@NotNull Stage primaryStage) {

    //SET PARTY
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

    partySizeLabel = new Label("Party List size: " + controller.getPartyList().size());
    partySizeLabel.setAlignment(Pos.TOP_RIGHT);
    partySizeLabel.setMinSize(336,40);
    root.getChildren().add(partySizeLabel);

    startAnimatorSetPartyScene();

    return scene;
  }

  private void startAnimatorSetPartyScene() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        int partySize = controller.getPartyList().size();
        partySizeLabel.setText("Party Size: " + partySize);
        if (partySize == 3) {
          //SET ENEMIES
          controller.setEnemies();
          actualScene = createEquipWeaponScene();
        }
      }
    };
    timer.start();
  }

  //EQUIP

  private Scene createEquipWeaponScene() {

    Group root = new Group();
    Scene scene = new Scene(root, 400, 400);

    Label label = new Label("Choose one weapon to equip");
    label.setAlignment(Pos.TOP_CENTER);
    label.setMinSize(336,40);
    root.getChildren().add(label);

    Label label2 = new Label("");
    label2.setAlignment(Pos.TOP_RIGHT);
    label2.setMinSize(336,40);
    root.getChildren().add(label2);

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
          b.setOnAction((e) -> {controller.equipWeapon((IPlayerCharacter) controller.removeCharacter(), controller.getInventory().get(0));
            b.setDisable(true);});
          break;
        case "Bow":
          b.setOnAction((e) -> {controller.equipWeapon((IPlayerCharacter) controller.removeCharacter(), controller.getInventory().get(1));
            b.setDisable(true);});
          break;
        case "Knife":
          b.setOnAction((e) -> {controller.equipWeapon((IPlayerCharacter) controller.removeCharacter(), controller.getInventory().get(2));
            b.setDisable(true);});
          break;
        case "Staff":
          b.setOnAction((e) -> {controller.equipWeapon((IPlayerCharacter) controller.removeCharacter(), controller.getInventory().get(3));
            b.setDisable(true);});
          break;
        case "Sword":
          b.setOnAction((e) -> {controller.equipWeapon((IPlayerCharacter) controller.removeCharacter(), controller.getInventory().get(4));
            b.setDisable(true);});
          break;
      }
    }
    return scene;
  }

  private void startAnimatorEquipWeaponScene() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(final long now) {
        int partySize = controller.getPartyList().size();
        partySizeLabel.setText("Party Size: " + partySize);
        if (partySize == 3) {
          //SET ENEMIES
          controller.setEnemies();
          //COMPLETE INVENTORY
          controller.completeInventory();
          actualScene = createEquipWeaponScene();
        }
      }
    };
    timer.start();
  }

  private void createAttackScene() {
    List<String> buttons = Arrays.asList(
            "Enemies",
            controller.getNameCharacter(controller.getEnemiesList().get(0)) +
                    ": Life = " + controller.getLifeCharacter(controller.getEnemiesList().get(0)),
            controller.getNameCharacter(controller.getEnemiesList().get(1)) +
                    ": Life = " + controller.getLifeCharacter(controller.getEnemiesList().get(1)),
            controller.getNameCharacter(controller.getEnemiesList().get(2)) +
                    ": Life = " + controller.getLifeCharacter(controller.getEnemiesList().get(2)) +

                    "Player Characters",
            controller.getNameCharacter(controller.getPartyList().get(0)) +
                    ": Life = " + controller.getLifeCharacter(controller.getPartyList().get(0)),
            //", Defense = "+ controller.getDefenseCharacter(controller.getPartyList().get(0)) +
            //", Equipped Weapon = " + controller.getEquippedWeaponCharacter(controller.getPartyList().get(0)),
            controller.getNameCharacter(controller.getPartyList().get(1)) +
                    ": Life = " + controller.getLifeCharacter(controller.getPartyList().get(1)),
            //", Defense = "+ controller.getDefenseCharacter(controller.getPartyList().get(1)) +
            //", Equipped Weapon = " + controller.getEquippedWeaponCharacter(controller.getPartyList().get(1)),
            controller.getNameCharacter(controller.getPartyList().get(2)) +
                    ": Life = " + controller.getLifeCharacter(controller.getPartyList().get(2)));
    //", Defense = "+ controller.getDefenseCharacter(controller.getPartyList().get(2)) +
    //", Equipped Weapon = " + controller.getEquippedWeaponCharacter(controller.getPartyList().get(2)));
  }
}