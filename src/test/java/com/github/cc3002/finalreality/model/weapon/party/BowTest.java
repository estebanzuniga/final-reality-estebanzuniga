package com.github.cc3002.finalreality.model.weapon.party;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BowTest extends AbstractWeaponTest {

    private Bow expectedBow;
    private Bow difDamageExpectedBow;
    private Bow difSpeedExpectedBow;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        expectedBow = new Bow(DAMAGE, SPEED);
        difDamageExpectedBow = new Bow(DAMAGE+1, SPEED);
        difSpeedExpectedBow = new Bow(DAMAGE, SPEED+1);
    }

    @Test
    void constructorTest() {
        checkConstruction(testBow, expectedBow,
                difDamageExpectedBow, difSpeedExpectedBow,
                testKnife, testStaff, testSword, testAxe);
    }
}
