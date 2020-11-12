package com.github.cc3002.finalreality.model.character.player.party;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.estebanzuniga.finalreality.model.character.player.party.Engineer;
import com.github.estebanzuniga.finalreality.model.character.player.party.Thief;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ThiefTest extends AbstractPlayerCharacterTest {

    private Thief thief;
    private List<IWeapon> testThiefWeaponList;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        thief = new Thief(turns, OTHER_NAME);
        testThiefWeaponList = new ArrayList<>();
        testThiefWeaponList.add(testSword);
        testThiefWeaponList.add(testStaff);
        testThiefWeaponList.add(testBow);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Thief(turns, THIEF_NAME),
                testThief,
                thief,
                new Engineer(turns, ENGINEER_NAME));
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
    void attackTest() {
        for (IWeapon weapon : testThiefWeaponList) {
            thief.equip(weapon);
            checkAttack(testEnemy, thief);
        }
    }
}