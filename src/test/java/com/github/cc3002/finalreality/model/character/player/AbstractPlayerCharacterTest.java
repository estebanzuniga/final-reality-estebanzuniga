package com.github.cc3002.finalreality.model.character.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.github.cc3002.finalreality.model.character.AbstractCharacterTest;
import com.github.estebanzuniga.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.character.player.IPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;

/**
 * Set of tests for the {@code GameCharacter} class.
 *
 * @author Esteban Zúñiga Salamanca.
 * @see AbstractPlayerCharacter
 */
public abstract class AbstractPlayerCharacterTest extends AbstractCharacterTest {

  protected void checkEquipWeapon(IPlayerCharacter playerCharacter, IWeapon weapon) {
      assertNull(playerCharacter.getEquippedWeapon());
      playerCharacter.equip(weapon);
      assertEquals(weapon, playerCharacter.getEquippedWeapon());
  }
}
