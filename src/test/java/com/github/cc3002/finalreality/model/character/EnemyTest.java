package com.github.cc3002.finalreality.model.character;

import com.github.estebanzuniga.finalreality.model.character.Enemy;
import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.player.party.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

  /**
   * Checks that the enemy's constructor works.
   */
  protected void checkConstruction(final ICharacter expectedCharacter, final ICharacter testEqualCharacter,
                                   final ICharacter differentClassCharacter, final ICharacter sameClassDifferentName,
                                   final ICharacter sameClassDifferentWeight, final ICharacter sameClassDifferentLife,
                                   final ICharacter sameClassDifferentAttack, final ICharacter sameClassDifferentDefense) {
    assertEquals(expectedCharacter, expectedCharacter);
    assertEquals(expectedCharacter, testEqualCharacter);
    assertNotEquals(testEqualCharacter, differentClassCharacter);
    assertNotEquals(sameClassDifferentName, testEqualCharacter);
    assertNotEquals(sameClassDifferentWeight, testEqualCharacter);
    assertNotEquals(sameClassDifferentLife, testEqualCharacter);
    assertNotEquals(sameClassDifferentAttack, testEqualCharacter);
    assertNotEquals(sameClassDifferentDefense, testEqualCharacter);
    assertEquals(expectedCharacter.hashCode(), testEqualCharacter.hashCode());
  }

  @Test
  void constructorTest() {
    checkConstruction(new Enemy(turns, ENEMY_NAME, 10, 400, 200, 50),
                      testEnemy,
                      new Thief(turns, THIEF_NAME, 500, 100),
                      new Enemy(turns, OTHER_NAME, 10, 400, 200, 50),
                      new Enemy(turns, ENEMY_NAME, 11, 400, 200, 50),
                      new Enemy(turns, ENEMY_NAME, 10, 401, 200, 50),
                      new Enemy(turns, ENEMY_NAME, 10, 400, 201, 50),
                      new Enemy(turns, ENEMY_NAME, 10, 400, 200, 51));
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