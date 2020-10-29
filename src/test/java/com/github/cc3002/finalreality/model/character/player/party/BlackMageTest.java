package com.github.cc3002.finalreality.model.character.player.party;

import com.github.estebanzuniga.finalreality.model.character.Enemy;
import com.github.estebanzuniga.finalreality.model.character.player.party.BlackMage;
import com.github.estebanzuniga.finalreality.model.character.player.party.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class BlackMageTest extends AbstractMageTest {

    private List<BlackMage> testBlackMageList;
    private BlackMage testBlackMage;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        testCharacters.add(new BlackMage(BLACK_MAGE_NAME,turns));
        testBlackMage = new BlackMage(BLACK_MAGE_NAME, turns);
        testBlackMageList = new ArrayList<>();
        testBlackMageList.add(testBlackMage);
    }

    @Test
    void constructorTest() {
        checkConstruction(new BlackMage(BLACK_MAGE_NAME, turns),
                testBlackMageList.get(0),
                new Enemy(ENEMY_NAME, 11, 500, 250, 100, turns),
                new Thief(THIEF_NAME, turns));
    }

    @Test
    void waitTurnTest() {
        checkWaitTurn();
    }

    @Test
    void EquipWeaponTest() {
        checkEquipWeapon(testBlackMage, testWeapon);
    }

    @Test
    void getManaTest() {
        checkGetMana(testBlackMage, testBlackMage.getMana());
    }
}
