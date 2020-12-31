package com.github.cc3002.finalreality.model.character.player.party;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.estebanzuniga.finalreality.model.character.player.party.Engineer;
import com.github.estebanzuniga.finalreality.model.character.player.party.WhiteMage;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Set of tests for the white mages.
 *
 * @author Esteban Zúñiga Salamanca.
 * @see WhiteMage
 */
class WhiteMageTest extends AbstractPlayerCharacterTest {

    private WhiteMage whiteMage;
    private List<IWeapon> testWhiteMageWeaponList;
    private List<IWeapon> testWhiteMageNotWeaponList;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        whiteMage = new WhiteMage(turns, OTHER_NAME,500,100);
        testWhiteMageWeaponList = new ArrayList<>();
        testWhiteMageWeaponList.add(testStaff);
        testWhiteMageNotWeaponList = new ArrayList<>();
        testWhiteMageNotWeaponList.add(testAxe);
        testWhiteMageNotWeaponList.add(testBow);
        testWhiteMageNotWeaponList.add(testKnife);
        testWhiteMageNotWeaponList.add(testSword);
    }

    @Test
    void constructorTest() {
        checkConstruction(new WhiteMage(turns, WHITE_MAGE_NAME,500,100),
                testWhiteMage,
                new Engineer(turns, ENGINEER_NAME,500,100),
                whiteMage,
                new WhiteMage(turns, WHITE_MAGE_NAME, 501, 100),
                new WhiteMage(turns, WHITE_MAGE_NAME, 500, 101));
    }

    @Test
    void waitTurnTest() {
        for (IWeapon weapon : testWhiteMageWeaponList) {
            whiteMage.equip(weapon);
            checkWaitTurn(whiteMage);
        }
    }

    @Test
    void equipWeaponTest() {
        for (IWeapon weapon : testWhiteMageWeaponList) {
            checkEquipWeapon(whiteMage, weapon);
        }
    }

    @Test
    void equipNotWeaponTest() {
        for (IWeapon weapon : testWhiteMageNotWeaponList) {
            checkNotEquipWeapon(whiteMage, weapon);
        }
    }

    @Test
    void attackTest() {
        for (IWeapon weapon : testWhiteMageWeaponList) {
            whiteMage.equip(weapon);
            checkAttack(testEngineer, whiteMage);
            checkAttack(testKnight, whiteMage);
            checkAttack(testThief, whiteMage);
            checkAttack(testWhiteMage, whiteMage);
            checkAttack(testBlackMage, whiteMage);
            checkAttack(testEnemy, whiteMage);
        }
    }

    /*@Test
    void getManaTest() {
        checkGetMana(testWhiteMage);
    }*/
}