package com.github.cc3002.finalreality.model.character.player.party;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.estebanzuniga.finalreality.model.character.Enemy;
import com.github.estebanzuniga.finalreality.model.character.player.party.Engineer;
import com.github.estebanzuniga.finalreality.model.character.player.party.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ThiefTest extends AbstractPlayerCharacterTest {

    private List<Thief> testThiefList;
    private Thief testThief;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        testCharacters.add(new Thief(THIEF_NAME, turns));
        testThief = new Thief(THIEF_NAME, turns);
        testThiefList = new ArrayList<>();
        testThiefList.add(testThief);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Thief(THIEF_NAME, turns),
                testThiefList.get(0),
                new Enemy(ENEMY_NAME, 11, 500, 250, 100, turns),
                new Engineer(ENGINEER_NAME, turns));
    }

    @Test
    void waitTurnTest() {
        checkWaitTurn();
    }

    @Test
    void EquipWeaponTest() {
        checkEquipWeapon(testThief, testWeapon);
    }
}