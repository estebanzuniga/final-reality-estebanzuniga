package com.github.cc3002.finalreality.model.character.player.party;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.estebanzuniga.finalreality.model.character.player.party.Engineer;
import com.github.estebanzuniga.finalreality.model.character.player.party.Knight;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Set of tests for the knights.
 *
 * @author Esteban Zúñiga Salamanca.
 * @see Knight
 */
class KnightTest extends AbstractPlayerCharacterTest {

    private Knight knight;
    private List<IWeapon> testKnightWeaponList;
    private List<IWeapon> testKnightNotWeaponList;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        knight = new Knight(turns, OTHER_NAME,500,100);
        testKnightWeaponList = new ArrayList<>();
        testKnightWeaponList.add(testSword);
        testKnightWeaponList.add(testAxe);
        testKnightWeaponList.add(testKnife);
        testKnightNotWeaponList = new ArrayList<>();
        testKnightNotWeaponList.add(testBow);
        testKnightNotWeaponList.add(testStaff);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Knight(turns, KNIGHT_NAME,500,100),
                testKnight,
                new Engineer(turns, ENGINEER_NAME,500,100),
                knight,
                new Knight(turns, KNIGHT_NAME, 501, 100),
                new Knight(turns, KNIGHT_NAME, 500, 101));
    }

    @Test
    void waitTurnTest() {
        for (IWeapon weapon : testKnightWeaponList) {
            knight.equip(weapon);
            checkWaitTurn(knight);
        }
    }

    @Test
    void equipWeaponTest() {
        for (IWeapon weapon : testKnightWeaponList) {
            checkEquipWeapon(knight, weapon);
        }
    }

    @Test
    void equipNotWeaponTest() {
        for (IWeapon weapon : testKnightNotWeaponList) {
            checkNotEquipWeapon(knight, weapon);
        }
    }

    @Test
    void attackTest() {
        for (IWeapon weapon : testKnightWeaponList) {
            knight.equip(weapon);
            checkAttack(testEngineer, knight);
            checkAttack(testKnight, knight);
            checkAttack(testThief, knight);
            checkAttack(testWhiteMage, knight);
            checkAttack(testBlackMage, knight);
            checkAttack(testEnemy, knight);
        }
    }
}