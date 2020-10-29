package com.github.cc3002.finalreality.model.character.player.party;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.estebanzuniga.finalreality.model.character.player.party.Knight;
import com.github.estebanzuniga.finalreality.model.character.player.party.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class KnightTest extends AbstractPlayerCharacterTest {

    private List<Knight> testKnightList;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        testCharacters.add(new Knight(KNIGHT_NAME, turns));
        testKnightList = new ArrayList<>();
        testKnightList.add(testKnight);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Knight(KNIGHT_NAME, turns),
                testKnightList.get(0),
                testEnemy,
                new Thief(THIEF_NAME, turns));
    }

    @Test
    void waitTurnTest() {
        checkWaitTurn();
    }

    @Test
    void EquipWeaponTest() {
        checkEquipWeapon(testKnight, testWeapon);
    }

    @Test
    void attackTest() {
        testKnight.equip(testWeapon);
        checkAttack(testEnemy, testKnight);
    }
}