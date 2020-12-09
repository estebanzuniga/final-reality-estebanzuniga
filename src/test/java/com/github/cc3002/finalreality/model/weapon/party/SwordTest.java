package com.github.cc3002.finalreality.model.weapon.party;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SwordTest extends AbstractWeaponTest {

    private Sword expectedSword;
    private Sword difNameExpectedSword;
    private Sword difDamageExpectedSword;
    private Sword difWeightExpectedSword;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        expectedSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
        difNameExpectedSword = new Sword(OTHER_NAME,DAMAGE, WEIGHT);
        difDamageExpectedSword = new Sword(SWORD_NAME,DAMAGE+1, WEIGHT);
        difWeightExpectedSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT +1);
    }

    @Test
    void constructorTest() {
        checkConstruction(testSword, expectedSword, difNameExpectedSword,
                difDamageExpectedSword, difWeightExpectedSword,
                testAxe, testBow, testKnife, testStaff);
    }
}
