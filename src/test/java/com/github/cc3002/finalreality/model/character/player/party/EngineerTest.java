package com.github.cc3002.finalreality.model.character.player.party;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.estebanzuniga.finalreality.model.character.Enemy;
import com.github.estebanzuniga.finalreality.model.character.player.party.Engineer;
import com.github.estebanzuniga.finalreality.model.character.player.party.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class EngineerTest extends AbstractPlayerCharacterTest {

    private List<Engineer> testEngineerList;
    private Engineer testEngineer;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        testCharacters.add(new Engineer(ENGINEER_NAME, turns));
        testEngineer = new Engineer(ENGINEER_NAME, turns);
        testEngineerList = new ArrayList<>();
        testEngineerList.add(testEngineer);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Engineer(ENGINEER_NAME, turns),
                testEngineerList.get(0),
                new Enemy(ENEMY_NAME, 11, 500, 250, 100, turns),
                new Thief(THIEF_NAME, turns));
    }

    @Test
    void waitTurnTest() {
        checkWaitTurn();
    }

    @Test
    void EquipWeaponTest() {
        checkEquipWeapon(testEngineer, testWeapon);
    }
}