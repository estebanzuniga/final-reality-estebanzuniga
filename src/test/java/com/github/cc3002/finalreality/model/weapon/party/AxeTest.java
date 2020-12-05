package com.github.cc3002.finalreality.model.weapon.party;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AxeTest extends AbstractWeaponTest {

    private Axe expectedAxe;
    private Axe difNameExpectedAxe;
    private Axe difDamageExpectedAxe;
    private Axe difWeightExpectedAxe;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        expectedAxe = new Axe(AXE_NAME, DAMAGE, WEIGHT);
        difNameExpectedAxe = new Axe(OTHER_NAME, DAMAGE, WEIGHT);
        difDamageExpectedAxe = new Axe(AXE_NAME, DAMAGE+1, WEIGHT);
        difWeightExpectedAxe = new Axe(AXE_NAME, DAMAGE, WEIGHT +1);
    }

    @Test
    void constructorTest() {
        checkConstruction(testAxe, expectedAxe, difNameExpectedAxe,
                difDamageExpectedAxe, difWeightExpectedAxe,
                testBow, testKnife, testStaff, testSword);
    }
}
