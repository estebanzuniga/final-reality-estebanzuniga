package com.github.cc3002.finalreality.model.weapon.party;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KnifeTest extends AbstractWeaponTest {

    private Knife expectedKnife;
    private Knife difDamageExpectedKnife;
    private Knife difSpeedExpectedKnife;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        expectedKnife = new Knife(DAMAGE, SPEED);
        difDamageExpectedKnife = new Knife(DAMAGE+1, SPEED);
        difSpeedExpectedKnife = new Knife(DAMAGE, SPEED+1);
    }

    @Test
    void constructorTest() {
        checkConstruction(testKnife, expectedKnife,
                difDamageExpectedKnife, difSpeedExpectedKnife,
                testStaff, testSword, testAxe, testBow);
    }
}
