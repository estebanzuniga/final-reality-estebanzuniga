package com.github.estebanzuniga.finalreality.controller;

import com.github.estebanzuniga.finalreality.controller.handlers.CharacterIsDeadHandler;
import com.github.estebanzuniga.finalreality.controller.handlers.EnemyEndsTurnHandler;
import com.github.estebanzuniga.finalreality.controller.handlers.IEventHandler;
import com.github.estebanzuniga.finalreality.controller.handlers.PlayerEndsTurnHandler;
import com.github.estebanzuniga.finalreality.controller.phases.*;
import com.github.estebanzuniga.finalreality.model.character.Enemy;
import com.github.estebanzuniga.finalreality.model.character.ICharacter;
import com.github.estebanzuniga.finalreality.model.character.player.IPlayerCharacter;
import com.github.estebanzuniga.finalreality.model.character.player.party.*;
import com.github.estebanzuniga.finalreality.model.weapon.IWeapon;
import com.github.estebanzuniga.finalreality.model.weapon.party.*;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Random;

/**
 * A class that works as the controller of the game.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class GameController {

    private final IEventHandler playerEndsTurnHandler = new PlayerEndsTurnHandler(this);
    private final IEventHandler enemyEndsTurnHandler = new EnemyEndsTurnHandler(this);
    private final IEventHandler characterIsDeadHandler = new CharacterIsDeadHandler(this);

    private final PropertyChangeSupport enemyEndsTurnNotification = new PropertyChangeSupport(this);
    private final PropertyChangeSupport playerEndsTurnNotification = new PropertyChangeSupport(this);
    private final PropertyChangeSupport characterIsDeadNotification = new PropertyChangeSupport(this);

    private final BlockingQueue<ICharacter> turns = new LinkedBlockingQueue<>();
    private final ArrayList<Enemy> enemies = new ArrayList<>();
    private final ArrayList<IPlayerCharacter> party = new ArrayList<>();
    private final ArrayList<IWeapon> inventory = new ArrayList<>();
    private final Random rng = new Random();
    private ICharacter actualCharacter;
    private IWeapon actualWeapon = null;
    private Enemy actualEnemyToAttack = null;

    private Phase phase;

    /**
     * Creates the controller of the game.
     */
    public GameController() {
        this.setPhase(new SelectingPartyPhase());
    }

    public ICharacter getActualCharacter() {
        return actualCharacter;
    }

    public void setActualCharacter(ICharacter character) {
        this.actualCharacter = character;
    }

    public IWeapon getActualWeapon() {
        return actualWeapon;
    }

    public void setActualWeapon(IWeapon weapon) {
        this.actualWeapon = weapon;
    }

    public Enemy getActualEnemyToAttack() {
        return actualEnemyToAttack;
    }

    public void setActualEnemyToAttack(Enemy enemy) {
        this.actualEnemyToAttack = enemy;
    }

    /**
     * Remove and return the first character of the turns queue.
     * @return
     *        the firs element of the turns queue.
     */
    public ICharacter extractCharacter() {
        return turns.poll();
    }

    public ICharacter tryToExtractCharacter() throws InvalidMovementException {
        return phase.extractCharacter();
    }

    /**
     * Equips a weapon to a character.
     *
     * @param character the character that will equip the weapon.
     * @param weapon    the weapon that will be equipped.
     */
    public void equipWeapon(IPlayerCharacter character, IWeapon weapon) {
        if (inventory.contains(weapon)) {
            character.equip(weapon);
        }
    }

    public void tryToEquip(IPlayerCharacter character, IWeapon weapon) {
        try {
            phase.equipWeapon(character, weapon);
        } catch (InvalidMovementException e) {
            e.printStackTrace();
        }
    }

    /**
     * Simulates the player turn.
     *
     * @param character   the character that the player use.
     * @param attacked    the attacked enemy.
     */
    public void playerTurn(IPlayerCharacter character, Enemy attacked, IWeapon weapon) {
        this.setPhase(new SelectingWeaponPhase());
        equipWeapon(character, weapon);
        this.setPhase(new SelectingAttackTargetPhase());
        attack(character, attacked);
        endTurn(character);
        playerEndsTurnNotification.firePropertyChange("PLAYER_ENDS_TURN", null, this);
    }

    /**
     *
     * Simulates the enemy turn.
     *
     * @param enemy       the enemy in turn.
     * @param attacked    the attacked character.
     */
    public void enemyTurn(Enemy enemy, IPlayerCharacter attacked) {
        setPhase(new EnemyAttackingPhase());
        attack(enemy, attacked);
        endTurn(enemy);
        enemyEndsTurnNotification.firePropertyChange("ENEMY_ENDS_TURN", null, this);
    }

    /**
     * Indicates the end of a turn.
     *
     * @param character the character that finish its turn.
     */
    public void endTurn(ICharacter character) {
        turns.remove(character);
        character.waitTurn();
    }

    /**
     * Notify if the player won.
     *
     * @return true if enemies list is empty.
     */
    public boolean playerWon() {
        return enemies.isEmpty();
    }

    /**
     * Notify if the enemy won.
     *
     * @return true if party list is empty.
     */
    public boolean enemyWon() {
        return party.isEmpty();
    }

    /**
     * Notify if a character is dead.
     *
     * @param character the character in question.
     * @return true if a character is dead.
     */
    public boolean isDead(ICharacter character) {
        if (!character.isAlive()) {
            characterIsDeadNotification.firePropertyChange("CHARACTER_IS_DEAD", null, this);
            return true;
        }
        return false;
    }

    public void attack(ICharacter attacker, ICharacter attacked) {
        attacker.attack(attacked);
        attacker.waitTurn();
    }

     /**
     * Represents the attack of a character.
     *
     * @param attacker    the enemy that is attacking.
     * @param attacked    the attacked character.
     */
    public void tryToAttack(ICharacter attacker, ICharacter attacked) {
        try {
            phase.attack(attacker, attacked);
        } catch (InvalidMovementException e) {
            e.printStackTrace();
        }
    }



    //CONSTRUCTORS

    /**
     * Creates three random enemies and complete the enemies list.
     */
    public void setEnemies() {
        Enemy enemy1 = createEnemy("Enemy1", rng.nextInt(10) + 1, rng.nextInt(100) + 400,
                rng.nextInt(100) + 100, rng.nextInt(30) + 30);
        Enemy enemy2 = createEnemy("Enemy2", rng.nextInt(10) + 1, rng.nextInt(100) + 400,
                rng.nextInt(100) + 100, rng.nextInt(30) + 30);
        Enemy enemy3 = createEnemy("Enemy3", rng.nextInt(10) + 1, rng.nextInt(100) + 400,
                rng.nextInt(100) + 100, rng.nextInt(30) + 30);
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);
    }

    public void tryToSetEnemies() {
        try {
            phase.setEnemies();
        } catch (InvalidMovementException e) {
            e.printStackTrace();
        }

    }

    /**
     * Creates an enemy.
     *
     * @param name    the enemy's name.
     * @param weight  the enemy's weight.
     * @param life    the enemy's life.
     * @param attack  the enemy's attack.
     * @param defense the enemy's defense.
     * @return the created enemy
     */
    public Enemy createEnemy(String name, int weight, int life, int attack, int defense) {
        Enemy enemy = new Enemy(turns, name, weight, life, attack, defense);
        enemies.add(enemy);
        enemy.addCharacterIsDeadListener(characterIsDeadHandler);
        enemy.addEnemyEndsTurnListener(enemyEndsTurnHandler);
        return enemy;
    }

    /**
     * Creates an engineer.
     *
     * @param name    the engineer's name.
     * @param life    the engineer's life.
     * @param defense the engineer's defense.
     * @return the created engineer.
     */
    public IPlayerCharacter createEngineer(String name, int life, int defense) {
        IPlayerCharacter engineer = new Engineer(turns, name, life, defense);
        party.add(engineer);
        engineer.addCharacterIsDeadListener(characterIsDeadHandler);
        engineer.addPlayerEndsTurnListener(playerEndsTurnHandler);
        return engineer;
    }

    /**
     * Creates a knight.
     *
     * @param name    the knight's name.
     * @param life    the knight's life.
     * @param defense the knight's defense.
     * @return the created knight.
     */
    public IPlayerCharacter createKnight(String name, int life, int defense) {
        IPlayerCharacter knight = new Knight(turns, name, life, defense);
        knight.addCharacterIsDeadListener(characterIsDeadHandler);
        knight.addPlayerEndsTurnListener(playerEndsTurnHandler);
        party.add(knight);
        return knight;
    }

    /**
     * Creates a thief.
     *
     * @param name    the thief's name.
     * @param life    the thief's life.
     * @param defense the thief's defense.
     * @return the created thief.
     */
    public IPlayerCharacter createThief(String name, int life, int defense) {
        IPlayerCharacter thief = new Thief(turns, name, life, defense);
        thief.addCharacterIsDeadListener(characterIsDeadHandler);
        thief.addPlayerEndsTurnListener(playerEndsTurnHandler);
        party.add(thief);
        return thief;
    }

    /**
     * Creates a white mage.
     *
     * @param name    the white mage's name.
     * @param life    the white mage's life.
     * @param defense the white mage's defense.
     * @return the created white mage.
     */
    public IPlayerCharacter createWhiteMage(String name, int life, int defense) {
        IPlayerCharacter whiteMage = new WhiteMage(turns, name, life, defense);
        whiteMage.addCharacterIsDeadListener(characterIsDeadHandler);
        whiteMage.addPlayerEndsTurnListener(playerEndsTurnHandler);
        party.add(whiteMage);
        return whiteMage;
    }

    /**
     * Creates a black mage.
     *
     * @param name    the black mage's name.
     * @param life    the black mage's life.
     * @param defense the black mage's defense.
     * @return the created black mage.
     */
    public IPlayerCharacter createBlackMage(String name, int life, int defense) {
        IPlayerCharacter blackMage = new BlackMage(turns, name, life, defense);
        blackMage.addCharacterIsDeadListener(characterIsDeadHandler);
        blackMage.addPlayerEndsTurnListener(playerEndsTurnHandler);
        party.add(blackMage);
        return blackMage;
    }

    /**
     * Creates an axe.
     *
     * @param name   the axe's name.
     * @param damage the axe's damage.
     * @param weight the axe's damage.
     * @return the created axe.
     */
    public IWeapon createAxe(String name, int damage, int weight) {
        IWeapon axe = new Axe(name, damage, weight);
        inventory.add(axe);
        return axe;
    }

    /**
     * Creates a bow.
     *
     * @param name   the bow's name.
     * @param damage the bow's damage.
     * @param weight the bow's damage.
     * @return the created bow.
     */
    public IWeapon createBow(String name, int damage, int weight) {
        IWeapon bow = new Bow(name, damage, weight);
        inventory.add(bow);
        return bow;
    }

    /**
     * Creates a knife.
     *
     * @param name   the knife's name.
     * @param damage the knife's damage.
     * @param weight the knife's damage.
     * @return the created knife.
     */
    public IWeapon createKnife(String name, int damage, int weight) {
        IWeapon knife = new Knife(name, damage, weight);
        inventory.add(knife);
        return knife;
    }

    /**
     * Creates a staff.
     *
     * @param name   the staff's name.
     * @param damage the staff's damage.
     * @param weight the staff's damage.
     * @return the created staff.
     */
    public IWeapon createStaff(String name, int damage, int weight) {
        IWeapon staff = new Staff(name, damage, weight);
        inventory.add(staff);
        return staff;
    }

    /**
     * Creates a sword.
     *
     * @param name   the sword's name.
     * @param damage the sword's damage.
     * @param weight the sword's damage.
     * @return the created sword.
     */
    public IWeapon createSword(String name, int damage, int weight) {
        IWeapon sword = new Sword(name, damage, weight);
        inventory.add(sword);
        return sword;
    }


    //GETTERS

    /**
     * Get the character's name.
     *
     * @param character the character in question.
     * @return the character's name.
     */
    public String getNameCharacter(ICharacter character) {
        return character.getName();
    }

    /**
     * Get the character's life.
     *
     * @param character the character in question.
     * @return the character's life.
     */
    public int getLifeCharacter(ICharacter character) {
        return character.getLife();
    }

    /**
     * Set the character's life.
     *
     * @param character the character in question.
     * @param life      the character's new life.
     */
    public void setLifeCharacter(ICharacter character, int life) {
        character.setLife(life);
    }

    /**
     * Get the character's defense.
     *
     * @param character the character in question.
     * @return the character's defense.
     */
    public int getDefenseCharacter(ICharacter character) {
        return character.getDefense();
    }

    /**
     * Get the enemy's weight.
     *
     * @param enemy the enemy in question.
     * @return the enemy's weight.
     */
    public int getWeightEnemy(Enemy enemy) {
        return enemy.getWeight();
    }

    /**
     * Get the enemy's attack.
     *
     * @param enemy the enemy in question.
     * @return the enemy's attack.
     */
    public int getAttackEnemy(Enemy enemy) {
        return enemy.getAttack();
    }

    /**
     * Get the character's equipped weapon.
     *
     * @param character the character in question.
     * @return the character's equipped weapon.
     */
    public IWeapon getEquippedWeaponCharacter(IPlayerCharacter character) {
        return character.getEquippedWeapon();
    }

    /**
     * Set the character's equipped weapon as null.
     *
     * @param character the character in question.
     */
    public void setEquippedWeaponCharacterNull(IPlayerCharacter character) {
        character.setEquippedWeaponNull();
    }

    /**
     * Get the character's equipped weapon damage.
     *
     * @param character the character in question.
     * @return the character's equipped weapon damage.
     */
    public int getDamageCharacter(IPlayerCharacter character) {
        return character.getEquippedWeapon().getDamage();
    }

    /**
     * Get the character's equipped weapon weight.
     *
     * @param character the character in question.
     * @return the character's equipped weapon weight.
     */
    public int getWeightCharacter(IPlayerCharacter character) {
        return character.getEquippedWeapon().getWeight();
    }

    /**
     * Get the enemies list.
     * @return
     *        the enemies list.
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Get the party list.
     * @return
     *        the party list.
     */
    public ArrayList<IPlayerCharacter> getParty() {
        return party;
    }

    /**
     * Get a enemy from enemies list.
     *
     * @param index the index in enemies of the enemy.
     * @return the enemy in the indicated index.
     */
    public Enemy getEnemy(int index) {
        return enemies.get(index);
    }

    /**
     * Get a character from party list.
     *
     * @param index the index in party of the character.
     * @return the character in the indicated index.
     */
    public IPlayerCharacter getPlayer(int index) {
        return party.get(index);
    }

    /**
     * Get a weapon from the inventory.
     *
     * @param index the index in the inventory of the weapon.
     * @return the weapon in the indicated index.
     */
    public IWeapon getWeapon(int index) {
        return getInventory().get(index);
    }

    /**
     * Get the inventory.
     *
     * @return the inventory.
     */
    public ArrayList<IWeapon> getInventory() {
        return inventory;
    }

    public void completeInventory() {
        IWeapon axe = createAxe("Axe", rng.nextInt(20) + 100, rng.nextInt(5) + 5);
        IWeapon bow = createBow("Bow", rng.nextInt(20) + 100, rng.nextInt(5) + 5);
        IWeapon knife = createKnife("Knife", rng.nextInt(20) + 100, rng.nextInt(5) + 5);
        IWeapon staff = createStaff("Staff", rng.nextInt(20) + 100, rng.nextInt(5) + 5);
        IWeapon sword = createSword("Sword", rng.nextInt(20) + 100, rng.nextInt(5) + 5);
        inventory.add(axe);
        inventory.add(bow);
        inventory.add(knife);
        inventory.add(staff);
        inventory.add(sword);
    }

    /**
     * Get the turns queue.
     * @return
     *        the turns queue.
     */
    public BlockingQueue<ICharacter> getTurns() {
        return turns;
    }


    /**
     * Returns true if the character is a player character.
     * @param character
     *       the character in question.
     * @return
     *        true if the character is a player character.
     */
    public boolean isPlayer(ICharacter character) {
        return character.isPlayerCharacter(character);
    }






    //PHASES

    public void setPhase(final @NotNull Phase phase) {
        this.phase = phase;
        phase.setController(this);
    }

    public String getCurrentPhase() {
        return phase.toString();
    }
}