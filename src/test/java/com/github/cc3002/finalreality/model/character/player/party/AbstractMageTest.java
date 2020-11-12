package com.github.cc3002.finalreality.model.character.player.party;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.estebanzuniga.finalreality.model.character.player.party.AbstractMage;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractMageTest extends AbstractPlayerCharacterTest {

    @BeforeEach
    void setUp() {
        super.basicSetUp();
    }

    protected void checkGetMana(AbstractMage mage) {
        int mana = mage.getMana();
        mage.setMana(mana);
        assertEquals(mana, mage.getMana());
    }
}