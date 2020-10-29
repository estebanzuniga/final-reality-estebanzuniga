package com.github.cc3002.finalreality.model.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.Enemy;
import com.github.estebanzuniga.finalreality.model.character.player.IPlayerCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.github.estebanzuniga.finalreality.model.character.player.party.*;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import com.github.estebanzuniga.finalreality.model.weapon.party.Axe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

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
  protected IWeapon testWeapon;
  protected static final String BLACK_MAGE_NAME = "Vivi";
  protected static final String KNIGHT_NAME = "Adelbert";
  protected static final String WHITE_MAGE_NAME = "Eiko";
  protected static final String ENGINEER_NAME = "Cid";
  protected static final String THIEF_NAME = "Zidane";
  protected static final String ENEMY_NAME = "Goblin";
  protected Enemy testEnemy;
  protected Engineer testEngineer;
  protected Knight testKnight;
  protected Thief testThief;
  protected WhiteMage testWhiteMage;
  protected BlackMage testBlackMage;

  @BeforeEach
  protected void basicSetUp() {
    turns = new LinkedBlockingQueue<>();
    testWeapon = new Axe(15, 10);
    testCharacters = new ArrayList<>();
    testEnemy = new Enemy(ENEMY_NAME, 10, 500, 250, 100, turns);
    testEngineer = new Engineer(ENGINEER_NAME, turns);
    testKnight = new Knight(KNIGHT_NAME, turns);
    testThief = new Thief(THIEF_NAME, turns);
    testWhiteMage = new WhiteMage(WHITE_MAGE_NAME, turns);
    testBlackMage = new BlackMage(BLACK_MAGE_NAME, turns);
  }

  /**
   * Checks that the character waits the appropriate amount of time for it's turn.
   */
  protected void checkWaitTurn() {
    Assertions.assertTrue(turns.isEmpty());
    tryToEquip(testCharacters.get(0));
    testCharacters.get(0).waitTurn();
    try {
      // Thread.sleep is not accurate so this values may be changed to adjust the
      // acceptable error margin.
      // We're testing that the character waits approximately 1 second.
      Thread.sleep(900);
      Assertions.assertEquals(0, turns.size());
      Thread.sleep(200);
      Assertions.assertEquals(1, turns.size());
      Assertions.assertEquals(testCharacters.get(0), turns.peek());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void tryToEquip(ICharacter character) {
    if (!(character instanceof Enemy)) {
    ((IPlayerCharacter) character).equip(testWeapon);
    }
  }

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

  protected void checkAttack(final ICharacter attacked, final ICharacter attacker) {
    int life = attacked.getLife();
    attacker.attack(attacked);
    assertNotEquals(life, attacked.getLife());
  }
}
