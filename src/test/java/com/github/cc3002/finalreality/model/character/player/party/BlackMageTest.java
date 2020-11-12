package com.github.cc3002.finalreality.model.character.player.party;

import com.github.estebanzuniga.finalreality.model.character.player.party.BlackMage;
import com.github.estebanzuniga.finalreality.model.character.player.party.Engineer;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class BlackMageTest extends AbstractMageTest {

    private BlackMage blackMage;
    private List<IWeapon> testBlackMageWeaponList;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        blackMage = new BlackMage(turns, OTHER_NAME);
        testBlackMageWeaponList = new ArrayList<>();
        testBlackMageWeaponList.add(testKnife);
        testBlackMageWeaponList.add(testStaff);
    }

    @Test
    void constructorTest() {
        checkConstruction(new BlackMage(turns, BLACK_MAGE_NAME),
                testBlackMage,
                blackMage,
                new Engineer(turns, ENGINEER_NAME));
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
    void attackTest() {
        for (IWeapon weapon : testBlackMageWeaponList) {
            blackMage.equip(weapon);
            checkAttack(testEnemy, blackMage);
        }
    }

    @Test
    void getManaTest() {
        checkGetMana(testBlackMage);
    }


}
