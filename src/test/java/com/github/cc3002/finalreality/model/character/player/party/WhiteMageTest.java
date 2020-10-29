package com.github.cc3002.finalreality.model.character.player.party;

import com.github.estebanzuniga.finalreality.model.character.player.party.Thief;
import com.github.estebanzuniga.finalreality.model.character.player.party.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class WhiteMageTest extends AbstractMageTest {

    private List<WhiteMage> testWhiteMageList;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        testCharacters.add(new WhiteMage(WHITE_MAGE_NAME, turns));
        testWhiteMageList = new ArrayList<>();
        testWhiteMageList.add(testWhiteMage);
    }

    @Test
    void constructorTest() {
        checkConstruction(new WhiteMage(WHITE_MAGE_NAME, turns),
                testWhiteMageList.get(0),
                testEnemy,
                new Thief(THIEF_NAME, turns));
    }

    @Test
    void waitTurnTest() {
        checkWaitTurn();
    }

    @Test
    void EquipWeaponTest() {
        checkEquipWeapon(testWhiteMage, testWeapon);
    }

    @Test
    void getManaTest() {
        checkGetMana(testWhiteMage, testWhiteMage.getMana());
    }

    @Test
    void attackTest() {
        testWhiteMage.equip(testWeapon);
        checkAttack(testEnemy, testWhiteMage);
    }
}