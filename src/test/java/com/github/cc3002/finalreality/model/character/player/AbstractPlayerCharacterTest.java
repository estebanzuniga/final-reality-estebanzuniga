package com.github.cc3002.finalreality.model.character.player;

import com.github.cc3002.finalreality.model.character.AbstractCharacterTest;
import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.character.player.IPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Set of tests for the player characters.
 *
 * @author Esteban Zúñiga Salamanca.
 * @see AbstractPlayerCharacter
 */
public abstract class AbstractPlayerCharacterTest extends AbstractCharacterTest {

    protected void checkConstruction(final ICharacter expectedCharacter, final ICharacter testEqualCharacter,
                                     final ICharacter differentClassCharacter, final ICharacter sameClassDifferentName,
                                     final ICharacter sameClassDifferentLife, final ICharacter sameClassDifferentDefense) {
        assertEquals(expectedCharacter, expectedCharacter);
        assertEquals(expectedCharacter, testEqualCharacter);
        assertNotEquals(testEqualCharacter, differentClassCharacter);
        assertNotEquals(sameClassDifferentName, testEqualCharacter);
        assertNotEquals(sameClassDifferentLife, testEqualCharacter);
        assertNotEquals(sameClassDifferentDefense, testEqualCharacter);
        assertEquals(expectedCharacter.hashCode(), testEqualCharacter.hashCode());
    }

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
