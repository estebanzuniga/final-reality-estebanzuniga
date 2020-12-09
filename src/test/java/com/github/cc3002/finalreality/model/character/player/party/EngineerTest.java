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
 * Set of tests for the engineers.
 *
 * @author Esteban Zúñiga Salamanca.
 * @see Engineer
 */
class EngineerTest extends AbstractPlayerCharacterTest {

    private Engineer engineer;
    private List<IWeapon> testEngineerWeaponList;
    private List<IWeapon> testEngineerNotWeaponList;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        engineer = new Engineer(turns, OTHER_NAME,500,100);
        testEngineerWeaponList = new ArrayList<>();
        testEngineerWeaponList.add(testAxe);
        testEngineerWeaponList.add(testBow);
        testEngineerNotWeaponList = new ArrayList<>();
        testEngineerNotWeaponList.add(testKnife);
        testEngineerNotWeaponList.add(testStaff);
        testEngineerNotWeaponList.add(testSword);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Engineer(turns, ENGINEER_NAME,500,100),
                testEngineer,
                new Thief(turns, THIEF_NAME,500,100),
                engineer,
                new Engineer(turns, ENGINEER_NAME, 501, 100),
                new Engineer(turns, ENGINEER_NAME, 500, 101));
    }

    @Test
    void waitTurnTest() {
        for (IWeapon weapon : testEngineerWeaponList) {
            engineer.equip(weapon);
            checkWaitTurn(engineer);
        }
    }

    @Test
    void equipWeaponTest() {
        for (IWeapon weapon : testEngineerWeaponList) {
            checkEquipWeapon(engineer, weapon);
        }
    }

    @Test
    void equipNotWeaponTest() {
        for (IWeapon weapon : testEngineerNotWeaponList) {
            checkNotEquipWeapon(engineer, weapon);
        }
    }

    @Test
    void attackTest() {
        for (IWeapon weapon : testEngineerWeaponList) {
            engineer.equip(weapon);
            checkAttack(testEngineer, engineer);
            checkAttack(testKnight, engineer);
            checkAttack(testThief, engineer);
            checkAttack(testWhiteMage, engineer);
            checkAttack(testBlackMage, engineer);
            checkAttack(testEnemy, engineer);
        }
    }
}