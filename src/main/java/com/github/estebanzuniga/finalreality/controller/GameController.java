package com.github.estebanzuniga.finalreality.controller;

import com.github.estebanzuniga.finalreality.controller.handlers.CharacterIsDeadHandler;
import com.github.estebanzuniga.finalreality.controller.handlers.IEventHandler;
import com.github.estebanzuniga.finalreality.controller.handlers.CharacterEndsTurnHandler;
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
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Random;

/**
 * A class that works as the controller of the game.
 *
 * @author Esteban Zúñiga Salamanca.
 */
public class GameController {

    private final IEventHandler characterEndsTurnHandler = new CharacterEndsTurnHandler(this);
    private final IEventHandler characterIsDeadHandler = new CharacterIsDeadHandler(this);

    private final PropertyChangeSupport characterEndsTurnNotification = new PropertyChangeSupport(this);
    private final PropertyChangeSupport characterIsDeadNotification = new PropertyChangeSupport(this);

    private final BlockingQueue<ICharacter> turns = new LinkedBlockingQueue<>();
    private List<Enemy> enemies = new ArrayList<>();
    private List<IPlayerCharacter> party = new ArrayList<>();
    private List<IWeapon> inventory = new ArrayList<>();
    private final Random rng = new Random();

    private ICharacter actualCharacter = null;
    private IWeapon actualWeapon = null;
    private Enemy actualEnemyToAttack = null;

    private Phase phase;

    /**
     * Creates the controller of the game.
     */
    public GameController() {
        this.setPhase(new InitialPhase());
    }

    /**
     * Remove and return the first character of the turns queue.
     */
    public void extractCharacter() {
        ICharacter character = turns.poll();
        character.waitTurn();
    }

    public void tryToExtractCharacter() throws InvalidMovementException {
        phase.extractCharacter();
    }

    /**
     * Equips a weapon to a character.
     *
     * @param character the character that will equip the weapon.
     * @param weapon    the weapon that will be equipped.
     */
    public void equipWeapon(ICharacter character, IWeapon weapon) {
        if (inventory.contains(weapon)) {
            ((IPlayerCharacter) character).equip(weapon);
        }
    }

    public void tryToEquip(ICharacter character, IWeapon weapon) {
        try {
            phase.equipWeapon((IPlayerCharacter) character, weapon);
        } catch (InvalidMovementException e) {
            e.printStackTrace();
        }
    }

    /**
     * Indicates the end of a turn.
     *
     * @param character the character that finish its turn.
     */
    public void endTurn(ICharacter character) {
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
        characterEndsTurnNotification.firePropertyChange("CHARACTER_ENDS_TURN", null, this);
        if (isPlayerCharacter(attacker) && isDead(attacked)) {
            getEnemies().remove(attacked);
        }
        else if (!isPlayerCharacter(attacker) && isDead(attacked)){
            getParty().remove(attacked);
        }
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
    public void setEnemies(){
        createEnemy("Enemy1", rng.nextInt(10) + 1, rng.nextInt(100) + 400,
                rng.nextInt(100) + 100, rng.nextInt(30) + 30);
        createEnemy("Enemy2", rng.nextInt(10) + 1, rng.nextInt(100) + 400,
                rng.nextInt(100) + 100, rng.nextInt(30) + 30);
        createEnemy("Enemy3", rng.nextInt(10) + 1, rng.nextInt(100) + 400,
                rng.nextInt(100) + 100, rng.nextInt(30) + 30);
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
        enemy.waitTurn();
        enemy.addCharacterIsDeadListener(characterIsDeadHandler);
        enemy.addEnemyEndsTurnListener(characterEndsTurnHandler);
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
        engineer.addPlayerEndsTurnListener(characterEndsTurnHandler);
        engineer.equip(new Axe("DefaultWeapon", 0, 10));
        engineer.waitTurn();
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
        knight.addPlayerEndsTurnListener(characterEndsTurnHandler);
        party.add(knight);
        knight.equip(new Sword("DefaultWeapon", 0, 10));
        knight.waitTurn();
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
        thief.addPlayerEndsTurnListener(characterEndsTurnHandler);
        party.add(thief);
        thief.equip(new Sword("DefaultWeapon", 0, 10));
        thief.waitTurn();
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
        whiteMage.addPlayerEndsTurnListener(characterEndsTurnHandler);
        party.add(whiteMage);
        whiteMage.equip(new Staff("DefaultWeapon", 0, 10));
        whiteMage.waitTurn();
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
        blackMage.addPlayerEndsTurnListener(characterEndsTurnHandler);
        party.add(blackMage);
        blackMage.equip(new Staff("DefaultWeapon", 0, 10));
        blackMage.waitTurn();
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
    public String getLifeCharacter(ICharacter character) {
        return String.valueOf(character.getLife());
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
    public String getDefenseCharacter(ICharacter character) {
        return String.valueOf(character.getDefense());
    }

    /**
     * Get the character's attack.
     *
     * @param character the character in question.
     * @return the enemy's attack.
     */
    public String getAttackEnemy(ICharacter character) {
        return String.valueOf(((Enemy) character).getAttack());
    }

    /**
     * Get the character's attack.
     *
     * @param character the character in question.
     * @return the enemy's attack.
     */
    public String getDamageWeapon(ICharacter character) {
        return String.valueOf(((IPlayerCharacter) character).getEquippedWeapon().getDamage());
    }

    /**
     * Get the character's equipped weapon.
     *
     * @param character the character in question.
     * @return the character's equipped weapon.
     */
    public IWeapon getEquippedWeaponCharacter(ICharacter character) {
        return ((IPlayerCharacter) character).getEquippedWeapon();
    }

    public String getNameEquippedWeapon(ICharacter character) {
        return ((IPlayerCharacter) character).getEquippedWeapon().getName();
    }

    /**
     * Set the character's equipped weapon as null.
     *
     * @param character the character in question.
     */
    public void setEquippedWeaponCharacterNull(ICharacter character) {
        ((IPlayerCharacter) character).setEquippedWeaponNull();
    }

    /**
     * Get the enemies list.
     * @return
     *        the enemies list.
     */
    public List<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Get the party list.
     * @return
     *        the party list.
     */
    public List<IPlayerCharacter> getParty() {
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
    public List<IWeapon> getInventory() {
        return inventory;
    }

    public void completeInventory() {
        createAxe("Axe", rng.nextInt(20) + 100, rng.nextInt(5) + 5);
        createBow("Bow", rng.nextInt(20) + 100, rng.nextInt(5) + 5);
        createKnife("Knife", rng.nextInt(20) + 100, rng.nextInt(5) + 5);
        createStaff("Staff", rng.nextInt(20) + 100, rng.nextInt(5) + 5);
        createSword("Sword", rng.nextInt(20) + 100, rng.nextInt(5) + 5);
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
    public boolean isPlayerCharacter(ICharacter character) {
        return party.contains(character);
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

    //PHASES

    public void setPhase(final @NotNull Phase phase) {
        this.phase = phase;
        phase.setController(this);
    }

    public String getCurrentPhase() {
        return phase.toString();
    }

    public Phase getPhase() {
        return phase;
    }
}