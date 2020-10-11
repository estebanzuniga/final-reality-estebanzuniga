package com.github.cc3002.finalreality.model.character;

import com.github.estebanzuniga.finalreality.model.character.player.Enemy;
import com.github.estebanzuniga.finalreality.model.character.CharacterClass;
import com.github.estebanzuniga.finalreality.model.character.player.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";
  private List<Enemy> testEnemies;

  @BeforeEach
  void setUp() {
    super.basicSetUp();
    testEnemies = new ArrayList<>();
    testEnemies.add(new Enemy(ENEMY_NAME, 10, turns));
  }


  @Test
  void constructorTest() {
    checkConstruction(new Enemy(ENEMY_NAME, 10, turns),
        testEnemies.get(0),
        new Enemy(ENEMY_NAME, 11, turns),
        new PlayerCharacter(ENEMY_NAME, turns, CharacterClass.THIEF));
  }
}