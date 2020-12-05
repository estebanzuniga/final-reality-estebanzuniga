package com.github.cc3002.finalreality.model.weapon.party;

import com.github.cc3002.finalreality.model.weapon.AbstractWeaponTest;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KnifeTest extends AbstractWeaponTest {

    private Knife expectedKnife;
    private Knife difNameExpectedKnife;
    private Knife difDamageExpectedKnife;
    private Knife difWeightExpectedKnife;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        expectedKnife = new Knife(KNIFE_NAME, DAMAGE, WEIGHT);
        difNameExpectedKnife = new Knife(OTHER_NAME, DAMAGE, WEIGHT);
        difDamageExpectedKnife = new Knife(KNIFE_NAME,DAMAGE+1, WEIGHT);
        difWeightExpectedKnife = new Knife(KNIFE_NAME, DAMAGE, WEIGHT +1);
    }

    @Test
    void constructorTest() {
        checkConstruction(testKnife, expectedKnife, difNameExpectedKnife,
                difDamageExpectedKnife, difWeightExpectedKnife,
                testStaff, testSword, testAxe, testBow);
    }
}
