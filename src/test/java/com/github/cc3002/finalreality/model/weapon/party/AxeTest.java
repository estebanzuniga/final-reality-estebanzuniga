package com.github.cc3002.finalreality.model.weapon.party;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AxeTest extends AbstractWeaponTest {

    private Axe expectedAxe;
    private Axe difDamageExpectedAxe;
    private Axe difSpeedExpectedAxe;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        expectedAxe = new Axe(DAMAGE, SPEED);
        difDamageExpectedAxe = new Axe(DAMAGE+1, SPEED);
        difSpeedExpectedAxe = new Axe(DAMAGE, SPEED+1);
    }

    @Test
    void constructorTest() {
        checkConstruction(testAxe, expectedAxe,
                difDamageExpectedAxe, difSpeedExpectedAxe,
                testBow, testKnife, testStaff, testSword);
    }
}
