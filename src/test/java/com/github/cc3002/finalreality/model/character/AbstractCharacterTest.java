package com.github.cc3002.finalreality.model.character;

import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.github.estebanzuniga.finalreality.model.character.player.party.*;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class containing the common tests for all the types of characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author Esteban Zúñiga Salamanca.
 * @see ICharacter
 */
public abstract class AbstractCharacterTest {

  protected BlockingQueue<ICharacter> turns;
  protected List<ICharacter> testCharacters;
  protected static final String BLACK_MAGE_NAME = "Vivi";
  protected static final String KNIGHT_NAME = "Adelbert";
  protected static final String WHITE_MAGE_NAME = "Eiko";
  protected static final String ENGINEER_NAME = "Cid";
  protected static final String THIEF_NAME = "Zidane";
  protected static final String ENEMY_NAME = "Goblin";
  protected static final String OTHER_NAME = "Esteban";
  protected Enemy testEnemy;
  protected Engineer testEngineer;
  protected Knight testKnight;
  protected Thief testThief;
  protected WhiteMage testWhiteMage;
  protected BlackMage testBlackMage;
  protected Axe testAxe;
  protected Bow testBow;
  protected Knife testKnife;
  protected Staff testStaff;
  protected Sword testSword;

  @BeforeEach
  protected void basicSetUp() {
    turns = new LinkedBlockingQueue<>();
    testCharacters = new ArrayList<>();
    testEnemy = new Enemy(turns, ENEMY_NAME, 10, 400, 200, 50);
    testEngineer = new Engineer(turns, ENGINEER_NAME, 500, 100);
    testKnight = new Knight(turns, KNIGHT_NAME, 500, 100);
    testThief = new Thief(turns, THIEF_NAME, 500, 100);
    testWhiteMage = new WhiteMage(turns, WHITE_MAGE_NAME, 500, 100);
    testBlackMage = new BlackMage(turns, BLACK_MAGE_NAME, 500, 100);
    testAxe = new Axe(151, 10);
    testBow = new Bow(151, 10);
    testKnife = new Knife(151, 10);
    testStaff = new Staff(151, 10);
    testSword = new Sword(151, 10);
  }

  /**
   * Checks that the character waits the appropriate amount of time for it's turn.
   */
  protected void checkWaitTurn(ICharacter character) {
    Assertions.assertTrue(turns.isEmpty());
    character.waitTurn();
    try {
      // Thread.sleep is not accurate so this values may be changed to adjust the
      // acceptable error margin.
      // We're testing that the character waits approximately 1 second.
      Thread.sleep(900);
      Assertions.assertEquals(0, turns.size());
      Thread.sleep(200);
      Assertions.assertEquals(1, turns.size());
      Assertions.assertEquals(character, turns.peek());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    turns.clear();
  }

  /**
   * Checks that the character's constructor works.
   */
  protected void checkConstruction(final ICharacter expectedCharacter,
                                   final ICharacter testEqualCharacter,
                                   final ICharacter sameClassDifferentCharacter,
                                   final ICharacter differentClassCharacter) {
    assertEquals(expectedCharacter, expectedCharacter);
    assertEquals(expectedCharacter, testEqualCharacter);
    assertNotEquals(sameClassDifferentCharacter, testEqualCharacter);
    assertNotEquals(testEqualCharacter, differentClassCharacter);
    assertEquals(expectedCharacter.hashCode(), testEqualCharacter.hashCode());
  }

  /**
   * Checks that the characters can attack to another one.
   */
  protected void checkAttack(final ICharacter attacked, final ICharacter attacker) {
    int life = attacked.getLife();
    assertTrue(attacked.isAlive());
    attacker.attack(attacked);
    assertNotEquals(life, attacked.getLife());
    while (attacked.isAlive()) {
      attacker.attack(attacked);
    }
    assertFalse(attacked.isAlive());
    attacked.setLife(life);
    assertEquals(life, attacked.getLife());
  }
}
