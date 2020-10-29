package com.github.cc3002.finalreality.model.character.player.party;

import com.github.cc3002.finalreality.model.character.player.AbstractPlayerCharacterTest;
import com.github.estebanzuniga.finalreality.model.character.player.party.AbstractMage;
import com.github.estebanzuniga.finalreality.model.character.player.party.BlackMage;
import com.github.estebanzuniga.finalreality.model.character.player.party.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AbstractMageTest extends AbstractPlayerCharacterTest {

    private List<AbstractMage> testAbstractMage;
    int mana = 100;

    @BeforeEach
    void setUp() {
        super.basicSetUp();
        testAbstractMage = new ArrayList<>();
        testAbstractMage.add(new WhiteMage(WHITE_MAGE_NAME, turns));
        testAbstractMage.add(new BlackMage(BLACK_MAGE_NAME, turns));
    }

    public void checkGetMana(AbstractMage mage, int mana) {
        mage.setMana(mana);
        assertEquals(mana, mage.getMana());
    }
}