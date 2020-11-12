package com.github.cc3002.finalreality.model.character;

import com.github.estebanzuniga.finalreality.model.character.Enemy;
import com.github.estebanzuniga.finalreality.model.character.player.party.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnemyTest extends AbstractCharacterTest {

  @BeforeEach
  void setUp() {
    basicSetUp();
  }

  @Test
  void constructorTest() {
    checkConstruction(new Enemy(turns, ENEMY_NAME, 10, 500, 250, 100),
        testEnemy,
        new Enemy(turns, ENEMY_NAME, 11, 500, 250, 100),
        new Thief(turns, THIEF_NAME));
  }

  @Test
  void waitTurnTest() {
    checkWaitTurn(testEnemy);
  }

  @Test
  void attackTest() {
    checkAttack(testEngineer, testEnemy);
    testEngineer.setLife(500);
    checkAttack(testKnight, testEnemy);
    testKnight.setLife(500);
    checkAttack(testThief, testEnemy);
    testThief.setLife(500);
    checkAttack(testWhiteMage, testEnemy);
    testWhiteMage.setLife(500);
    checkAttack(testBlackMage, testEnemy);
    testBlackMage.setLife(500);
  }
}