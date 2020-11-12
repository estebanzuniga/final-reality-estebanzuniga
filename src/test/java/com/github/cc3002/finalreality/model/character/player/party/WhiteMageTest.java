package com.github.cc3002.finalreality.model.character.player.party;

import com.github.estebanzuniga.finalreality.model.character.player.party.Engineer;
import com.github.estebanzuniga.finalreality.model.character.player.party.WhiteMage;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class WhiteMageTest extends AbstractMageTest {

    private WhiteMage whiteMage;
    private List<IWeapon> testWhiteMageWeaponList;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        whiteMage = new WhiteMage(turns, OTHER_NAME);
        testWhiteMageWeaponList = new ArrayList<>();
        testWhiteMageWeaponList.add(testStaff);
    }

    @Test
    void constructorTest() {
        checkConstruction(new WhiteMage(turns, WHITE_MAGE_NAME),
                testWhiteMage,
                whiteMage,
                new Engineer(turns, ENGINEER_NAME));
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
    void attackTest() {
        for (IWeapon weapon : testWhiteMageWeaponList) {
            whiteMage.equip(weapon);
            checkAttack(testEnemy, whiteMage);
        }
    }

    @Test
    void getManaTest() {
        checkGetMana(testWhiteMage);
    }
}