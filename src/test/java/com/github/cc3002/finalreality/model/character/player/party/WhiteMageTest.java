package com.github.cc3002.finalreality.model.character.player.party;

import com.github.estebanzuniga.finalreality.model.character.Enemy;
import com.github.estebanzuniga.finalreality.model.character.player.party.Thief;
import com.github.estebanzuniga.finalreality.model.character.player.party.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class WhiteMageTest extends AbstractMageTest {

    private List<WhiteMage> testWhiteMageList;
    private WhiteMage testWhiteMage;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        testCharacters.add(new WhiteMage(WHITE_MAGE_NAME, turns));
        testWhiteMage = new WhiteMage(WHITE_MAGE_NAME, turns);
        testWhiteMageList = new ArrayList<>();
        testWhiteMageList.add(testWhiteMage);
    }

    @Test
    void constructorTest() {
        checkConstruction(new WhiteMage(WHITE_MAGE_NAME, turns),
                testWhiteMageList.get(0),
                new Enemy(ENEMY_NAME, 11, 500, 250, 100, turns),
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
}