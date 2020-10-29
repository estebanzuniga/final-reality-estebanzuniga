package com.github.cc3002.finalreality.model.weapon.party;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StaffTest extends AbstractWeaponTest {

    private Staff expectedStaff;
    private Staff difDamageExpectedStaff;
    private Staff difSpeedExpectedStaff;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        expectedStaff = new Staff(DAMAGE, SPEED);
        difDamageExpectedStaff = new Staff(DAMAGE+1, SPEED);
        difSpeedExpectedStaff = new Staff(DAMAGE, SPEED+1);
    }

    @Test
    void constructorTest() {
        checkConstruction(testStaff, expectedStaff,
                difDamageExpectedStaff, difSpeedExpectedStaff,
                testSword, testAxe, testBow, testKnife);
    }
}
