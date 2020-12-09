package com.github.cc3002.finalreality.model.weapon.party;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StaffTest extends AbstractWeaponTest {

    private Staff expectedStaff;
    private Staff difNameExpectedStaff;
    private Staff difDamageExpectedStaff;
    private Staff difWeightExpectedStaff;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        expectedStaff = new Staff(STAFF_NAME, DAMAGE, WEIGHT);
        difNameExpectedStaff = new Staff(OTHER_NAME, DAMAGE, WEIGHT);
        difDamageExpectedStaff = new Staff(STAFF_NAME, DAMAGE+1, WEIGHT);
        difWeightExpectedStaff = new Staff(STAFF_NAME, DAMAGE, WEIGHT +1);
    }

    @Test
    void constructorTest() {
        checkConstruction(testStaff, expectedStaff, difNameExpectedStaff,
                difDamageExpectedStaff, difWeightExpectedStaff,
                testSword, testAxe, testBow, testKnife);
    }
}
