package com.github.cc3002.finalreality.model.weapon.party;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SwordTest extends AbstractWeaponTest {

    private Sword expectedSword;
    private Sword difDamageExpectedSword;
    private Sword difSpeedExpectedSword;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        expectedSword = new Sword(DAMAGE, SPEED);
        difDamageExpectedSword = new Sword(DAMAGE+1, SPEED);
        difSpeedExpectedSword = new Sword(DAMAGE, SPEED+1);
    }

    @Test
    void constructorTest() {
        checkConstruction(testSword, expectedSword,
                difDamageExpectedSword, difSpeedExpectedSword,
                testAxe, testBow, testKnife, testStaff);
    }
}
