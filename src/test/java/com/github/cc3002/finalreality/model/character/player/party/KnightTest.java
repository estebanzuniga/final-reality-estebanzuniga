package com.github.cc3002.finalreality.model.character.player.party;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.estebanzuniga.finalreality.model.character.player.party.Engineer;
import com.github.estebanzuniga.finalreality.model.character.player.party.Knight;
import com.github.estebanzuniga.finalreality.model.character.player.party.Thief;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import com.github.estebanzuniga.finalreality.model.weapon.party.Knife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class KnightTest extends AbstractPlayerCharacterTest {

    private Knight knight;
    private List<IWeapon> testKnightWeaponList;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        knight = new Knight(turns, OTHER_NAME);
        testKnightWeaponList = new ArrayList<>();
        testKnightWeaponList.add(testSword);
        testKnightWeaponList.add(testAxe);
        testKnightWeaponList.add(testKnife);
    }

    @Test
    void constructorTest() {
        checkConstruction(new Knight(turns, KNIGHT_NAME),
                testKnight,
                knight,
                new Engineer(turns, ENGINEER_NAME));
    }

    @Test
    void waitTurnTest() {
        for (IWeapon weapon : testKnightWeaponList) {
            knight.equip(weapon);
            checkWaitTurn(knight);
        }
    }

    @Test
    void equipWeaponTest() {
        for (IWeapon weapon : testKnightWeaponList) {
            checkEquipWeapon(knight, weapon);
        }
    }

    @Test
    void attackTest() {
        for (IWeapon weapon : testKnightWeaponList) {
            knight.equip(weapon);
            checkAttack(testEnemy, knight);
        }
    }
}