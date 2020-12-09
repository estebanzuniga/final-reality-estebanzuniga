package com.github.cc3002.finalreality.model.character.player.party;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractMageTest extends AbstractPlayerCharacterTest {

    @BeforeEach
    void setUp() {
        super.basicSetUp();
    }

    /*protected void checkGetMana(AbstractMage mage) {
        int mana = mage.getMana();
        mage.setMana(mana);
        assertEquals(mana, mage.getMana());
    }*/
}