package com.github.cc3002.finalreality.model.character.player.party;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.estebanzuniga.finalreality.model.character.player.party.Engineer;
import com.github.estebanzuniga.finalreality.model.character.player.party.Thief;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class EngineerTest extends AbstractPlayerCharacterTest {

    private Engineer engineer;
    private List<IWeapon> testEngineerWeaponList;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        engineer = new Engineer(turns, OTHER_NAME);
        testEngineerWeaponList = new ArrayList<>();
        testEngineerWeaponList.add(testAxe);
        testEngineerWeaponList.add(testBow);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Engineer(turns, ENGINEER_NAME),
                testEngineer,
                engineer,
                new Thief(turns, THIEF_NAME));
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
    void attackTest() {
        for (IWeapon weapon : testEngineerWeaponList) {
            engineer.equip(weapon);
            checkAttack(testEnemy, engineer);
        }
    }
}