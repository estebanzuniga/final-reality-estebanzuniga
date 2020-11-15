package com.github.cc3002.finalreality.model.character.player.party;

import com.github.estebanzuniga.finalreality.model.character.player.party.BlackMage;
import com.github.estebanzuniga.finalreality.model.character.player.party.Engineer;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Set of tests for the black mages.
 *
 * @author Esteban Zúñiga Salamanca.
 * @see BlackMage
 */
class BlackMageTest extends AbstractMageTest {

    private BlackMage blackMage;
    private List<IWeapon> testBlackMageWeaponList;
    private List<IWeapon> testBlackMageNotWeaponList;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        blackMage = new BlackMage(turns, OTHER_NAME,500,100);
        testBlackMageWeaponList = new ArrayList<>();
        testBlackMageWeaponList.add(testKnife);
        testBlackMageWeaponList.add(testStaff);
        testBlackMageNotWeaponList = new ArrayList<>();
        testBlackMageNotWeaponList.add(testAxe);
        testBlackMageNotWeaponList.add(testBow);
        testBlackMageNotWeaponList.add(testSword);
    }

    @Test
    void constructorTest() {
        checkConstruction(new BlackMage(turns, BLACK_MAGE_NAME,500,100),
                testBlackMage,
                blackMage,
                new Engineer(turns, ENGINEER_NAME,500,100));
    }

    @Test
    void waitTurnTest() {
        for (IWeapon weapon : testBlackMageWeaponList) {
            blackMage.equip(weapon);
            checkWaitTurn(blackMage);
        }
    }

    @Test
    void equipWeaponTest() {
        for (IWeapon weapon : testBlackMageWeaponList) {
            checkEquipWeapon(blackMage, weapon);
        }
    }

    @Test
    void equipNotWeaponTest() {
        for (IWeapon weapon : testBlackMageNotWeaponList) {
            checkNotEquipWeapon(blackMage, weapon);
        }
    }

    @Test
    void attackTest() {
        for (IWeapon weapon : testBlackMageWeaponList) {
            blackMage.equip(weapon);
            checkAttack(testEngineer, blackMage);
            checkAttack(testKnight, blackMage);
            checkAttack(testThief, blackMage);
            checkAttack(testWhiteMage, blackMage);
            checkAttack(testBlackMage, blackMage);
            checkAttack(testEnemy, blackMage);
        }
    }

    /*@Test
    void getManaTest() {
        checkGetMana(testBlackMage);
    }*/
}
