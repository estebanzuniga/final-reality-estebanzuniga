package com.github.cc3002.finalreality.model.weapon.party;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BowTest extends AbstractWeaponTest {

    private Bow expectedBow;
    private Bow difNameExpectedBow;
    private Bow difDamageExpectedBow;
    private Bow difWeightExpectedBow;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        expectedBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
        difNameExpectedBow = new Bow(OTHER_NAME, DAMAGE, WEIGHT);
        difDamageExpectedBow = new Bow(BOW_NAME, DAMAGE+1, WEIGHT);
        difWeightExpectedBow = new Bow(BOW_NAME, DAMAGE, WEIGHT +1);
    }

    @Test
    void constructorTest() {
        checkConstruction(testBow, expectedBow, difNameExpectedBow,
                difDamageExpectedBow, difWeightExpectedBow,
                testKnife, testStaff, testSword, testAxe);
    }
}
