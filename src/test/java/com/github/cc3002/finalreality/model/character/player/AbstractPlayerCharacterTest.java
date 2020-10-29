package com.github.cc3002.finalreality.model.character.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.github.cc3002.finalreality.model.character.AbstractCharacterTest;
import com.github.estebanzuniga.finalreality.model.character.player.AbstractPlayerCharacter;

import java.util.ArrayList;
import java.util.List;

import com.github.estebanzuniga.finalreality.model.character.player.IPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.character.player.party.*;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Set of tests for the {@code GameCharacter} class.
 *
 * @author Esteban Zúñiga Salamanca.
 * @see AbstractPlayerCharacter
 */
public class AbstractPlayerCharacterTest extends AbstractCharacterTest {

  protected List<IPlayerCharacter> testAbstractPlayerCharacters;

  /**
   * Setup method.
   * Creates a new character named Vivi with 10 speed and links it to a turn queue.
   */
  @BeforeEach
  void setUp() {
    super.basicSetUp();

    testAbstractPlayerCharacters = new ArrayList<>();
    var testBlackMage = new BlackMage(BLACK_MAGE_NAME, turns);
    var testKnight = new Knight(THIEF_NAME, turns);
    var testWhiteMage = new WhiteMage(WHITE_MAGE_NAME, turns);
    var testEngineer = new Engineer(ENGINEER_NAME, turns);
    var testThief = new Thief(THIEF_NAME, turns);
    testAbstractPlayerCharacters.add(testBlackMage);
    testAbstractPlayerCharacters.add(testKnight);
    testAbstractPlayerCharacters.add(testWhiteMage);
    testAbstractPlayerCharacters.add(testEngineer);
    testAbstractPlayerCharacters.add(testThief);
    }

  public void checkEquipWeapon(IPlayerCharacter playerCharacter, IWeapon testWeapon) {
      assertNull(playerCharacter.getEquippedWeapon());
      playerCharacter.equip(testWeapon);
      assertEquals(testWeapon, playerCharacter.getEquippedWeapon());
  }
}
