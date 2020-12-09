package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GettersTest extends GameControllerTest {

    @BeforeEach
    void setup() {
        super.basicSetUp();
    }

    @Test
    public void gettersTest() {
        checkEnemyGetters(testEnemy);
        checkCharacterGetters(testEngineer, ENGINEER_NAME);
        checkCharacterGetters(testKnight, KNIGHT_NAME);
        checkCharacterGetters(testThief, THIEF_NAME);
        checkCharacterGetters(testWhiteMage, WHITE_MAGE_NAME);
        checkCharacterGetters(testBlackMage, BLACK_MAGE_NAME);
    }
}
