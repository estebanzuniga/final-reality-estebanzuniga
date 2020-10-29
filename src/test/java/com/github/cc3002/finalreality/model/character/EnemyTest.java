package com.github.cc3002.finalreality.model.character;

import com.github.estebanzuniga.finalreality.model.character.Enemy;
import com.github.estebanzuniga.finalreality.model.character.player.party.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";
  private List<Enemy> testEnemies;

  @BeforeEach
  void setUp() {
    super.basicSetUp();
    testCharacters.add(new Enemy(ENEMY_NAME, 10, 500, 250, 100, turns));
    testEnemies = new ArrayList<>();
    testEnemies.add(new Enemy(ENEMY_NAME, 10, 500, 250, 100, turns));
  }

  @Test
  void constructorTest() {
    checkConstruction(new Enemy(ENEMY_NAME, 10, 500, 250, 100, turns),
        testEnemies.get(0),
        new Enemy(ENEMY_NAME, 11, 500, 250, 100, turns),
        new Thief(THIEF_NAME, turns));
  }

  @Test
  void waitTurnTest() {
    checkWaitTurn();
  }

}