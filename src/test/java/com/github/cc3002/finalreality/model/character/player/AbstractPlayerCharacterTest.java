package com.github.cc3002.finalreality.model.character.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.github.cc3002.finalreality.model.character.AbstractCharacterTest;
import com.github.estebanzuniga.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.character.player.IPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;

/**
 * Set of tests for the player characters.
 *
 * @author Esteban Zúñiga Salamanca.
 * @see AbstractPlayerCharacter
 */
public abstract class AbstractPlayerCharacterTest extends AbstractCharacterTest {

    /**
     * Checks that the characters can equip some weapons.
     */
    protected void checkEquipWeapon(IPlayerCharacter playerCharacter, IWeapon weapon) {
        playerCharacter.setEquippedWeaponNull();
        assertNull(playerCharacter.getEquippedWeapon());
        playerCharacter.equip(weapon);
        assertEquals(weapon, playerCharacter.getEquippedWeapon());
    }

    /**
     * Checks that the characters can't equip some weapons.
     */
    protected void checkNotEquipWeapon(IPlayerCharacter playerCharacter, IWeapon weapon) {
        playerCharacter.setEquippedWeaponNull();
        assertNull(playerCharacter.getEquippedWeapon());
        playerCharacter.equip(weapon);
        assertNull(playerCharacter.getEquippedWeapon());
    }
}
