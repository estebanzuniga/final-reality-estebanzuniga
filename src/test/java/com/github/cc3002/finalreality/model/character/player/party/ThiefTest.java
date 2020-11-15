package com.github.cc3002.finalreality.model.character.player.party;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.estebanzuniga.finalreality.model.character.player.party.Engineer;
import com.github.estebanzuniga.finalreality.model.character.player.party.Thief;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Set of tests for the thieves.
 *
 * @author Esteban Zúñiga Salamanca.
 * @see Thief
 */
class ThiefTest extends AbstractPlayerCharacterTest {

    private Thief thief;
    private List<IWeapon> testThiefWeaponList;
    private List<IWeapon> testThiefNotWeaponList;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        thief = new Thief(turns, OTHER_NAME, 500, 100);
        testThiefWeaponList = new ArrayList<>();
        testThiefWeaponList.add(testSword);
        testThiefWeaponList.add(testStaff);
        testThiefWeaponList.add(testBow);
        testThiefNotWeaponList = new ArrayList<>();
        testThiefNotWeaponList.add(testAxe);
        testThiefNotWeaponList.add(testKnife);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Thief(turns, THIEF_NAME, 500, 100),
                testThief,
                thief,
                new Engineer(turns, ENGINEER_NAME, 500, 100));
    }

    @Test
    void waitTurnTest() {
        for (IWeapon weapon : testThiefWeaponList) {
            thief.equip(weapon);
            checkWaitTurn(thief);
        }
    }

    @Test
    void equipWeaponTest() {
        for (IWeapon weapon : testThiefWeaponList) {
            checkEquipWeapon(thief, weapon);
        }
    }

    @Test
    void equipNotWeaponTest() {
        for (IWeapon weapon : testThiefNotWeaponList) {
            checkNotEquipWeapon(thief, weapon);
        }
    }

    @Test
    void attackTest() {
        for (IWeapon weapon : testThiefWeaponList) {
            thief.equip(weapon);
            checkAttack(testEngineer, thief);
            checkAttack(testKnight, thief);
            checkAttack(testThief, thief);
            checkAttack(testWhiteMage, thief);
            checkAttack(testBlackMage, thief);
            checkAttack(testEnemy, thief);
        }
    }
}