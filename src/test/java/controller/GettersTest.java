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
        checkEnemyGetters(testEnemy, ENEMY_NAME, 10, 400, 200, 50);
        checkCharacterGetters(testEngineer, ENGINEER_NAME, 500, 100);
        checkCharacterGetters(testKnight, KNIGHT_NAME, 500, 100);
        checkCharacterGetters(testThief, THIEF_NAME, 500, 100);
        checkCharacterGetters(testWhiteMage, WHITE_MAGE_NAME, 500, 100);
        checkCharacterGetters(testBlackMage, BLACK_MAGE_NAME, 500, 100);
    }
}
