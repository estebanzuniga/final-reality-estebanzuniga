package com.github.cc3002.finalreality.model.character;

import com.github.estebanzuniga.finalreality.model.character.Enemy;
import com.github.estebanzuniga.finalreality.model.character.player.party.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Set of tests for the enemies.
 *
 * @author Esteban Zúñiga Salamanca.
 * @see Enemy
 */
class EnemyTest extends AbstractCharacterTest {

  private Enemy enemy;

  @BeforeEach
  void setUp() {
    basicSetUp();
    enemy = new Enemy(turns, OTHER_NAME, 10, 400, 200, 50);
  }

  @Test
  void constructorTest() {
    checkConstruction(new Enemy(turns, ENEMY_NAME, 10, 500, 250, 100),
        testEnemy,
        new Enemy(turns, ENEMY_NAME, 11, 500, 250, 100),
        new Thief(turns, THIEF_NAME, 500, 100));
  }

  @Test
  void waitTurnTest() {
    checkWaitTurn(testEnemy);
  }

  @Test
  void attackTest() {
    checkAttack(testEngineer, testEnemy);
    checkAttack(testKnight, testEnemy);
    checkAttack(testThief, testEnemy);
    checkAttack(testWhiteMage, testEnemy);
    checkAttack(testBlackMage, testEnemy);
    checkAttack(enemy, testEnemy);
  }
}