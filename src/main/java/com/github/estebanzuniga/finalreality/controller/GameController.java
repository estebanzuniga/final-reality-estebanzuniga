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
    private final List<Enemy> enemies = new ArrayList<>();
    private final List<IPlayerCharacter> party = new ArrayList<>();
    private final List<IWeapon> inventory = new ArrayList<>();
    private final List<Enemy> allEnemies = new ArrayList<>();
    private final List<IPlayerCharacter> allPlayers = new ArrayList<>();
    private final Random rng = new Random();
    private ICharacter currentCharacter = null;
    private IWeapon currentWeapon = null;
    private ICharacter currentOpponentToAttack = null;
    private Phase phase;

    /**
     * Creates the controller of the game.
     */
    public GameController() {
        this.setPhase(new InitialPhase());
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
        engineer.addCharacterIsDeadListener(characterIsDeadHandler);
        engineer.addPlayerEndsTurnListener(characterEndsTurnHandler);
        engineer.equip(new Axe("DefaultWeapon", 0, rng.nextInt(10) + 1));
        party.add(engineer);
        allPlayers.add(engineer);
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
        knight.equip(new Sword("DefaultWeapon", 0, rng.nextInt(10) + 1));
        party.add(knight);
        allPlayers.add(knight);
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
        thief.equip(new Sword("DefaultWeapon", 0, rng.nextInt(10) + 1));
        party.add(thief);
        allPlayers.add(thief);
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
        whiteMage.equip(new Staff("DefaultWeapon", 0, rng.nextInt(10) + 1));
        party.add(whiteMage);
        allPlayers.add(whiteMage);
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
        blackMage.equip(new Staff("DefaultWeapon", 0, rng.nextInt(10) + 1));
        party.add(blackMage);
        allPlayers.add(blackMage);
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

    /**
     * Creates three enemies with random stats.
     */
    public void setEnemies() {
        Enemy createdEnemy1 = createEnemy("Venom", rng.nextInt(10) + 1,
                rng.nextInt(50) + 450, rng.nextInt(50) + 100,
                rng.nextInt(30) + 20);
        allEnemies.add(createdEnemy1);
        Enemy createdEnemy2 = createEnemy("Dr. Octopus", rng.nextInt(10) + 1,
                rng.nextInt(50) + 450, rng.nextInt(50) + 100,
                rng.nextInt(30) + 20);
        allEnemies.add(createdEnemy2);
        Enemy createdEnemy3 = createEnemy("Green Goblin", rng.nextInt(10) + 1,
                rng.nextInt(50) + 450, rng.nextInt(50) + 100,
                rng.nextInt(30) + 20);
        allEnemies.add(createdEnemy3);
    }

    /**
     * Calls phase equipWeapon method.
     *
     * @param character the character that will equip the weapon.
     * @param weapon    the weapon that will be equipped.
     */
    public void tryToEquip(ICharacter character, IWeapon weapon) {
        phase.equipWeapon((IPlayerCharacter) character, weapon);
    }

    /**
     * Equips a weapon to a player character.
     *
     * @param character the character that will equip the weapon.
     * @param weapon    the weapon that will be equipped.
     */
    public void equipWeapon(ICharacter character, IWeapon weapon) {
        if (inventory.contains(weapon)) {
            ((IPlayerCharacter) character).equip(weapon);
        }
    }

    /**
     * Calls phase attack method.
     *
     * @param attacker the enemy that is attacking.
     * @param attacked the attacked character.
     */
    public void tryToAttack(ICharacter attacker, ICharacter attacked) {
        phase.attack(attacker, attacked);
    }

    /**
     * Represents the attack from one character to other.
     *
     * @param attacker the enemy that is attacking.
     * @param attacked the attacked character.
     */
    public void attack(ICharacter attacker, ICharacter attacked) {
        attacker.attack(attacked);
        characterEndsTurnNotification.firePropertyChange(
                "CHARACTER_ENDS_TURN", null, this);
        if (isPlayer(attacker) && isDead(attacked)) {
            enemies.remove(attacked);
        } else if (!isPlayer(attacker) && isDead(attacked)) {
            party.remove(attacked);
        }
    }

    /**
     * Calls phase extractCharacter method.
     */
    public void tryToExtractCharacter() {
        phase.extractCharacter();
    }

    /**
     * Extracts the first character from the turns queue and put in it again if is alive.
     */
    public void extractCharacter() {
        ICharacter character = turns.poll();
        assert character != null;
        if (!isDead(character)) {
            character.waitTurn();
        }
    }

    /**
     * Calls phase newGame method.
     */
    public void tryToNewGame() {
        phase.newGame();
    }

    /**
     * Empties all the lists and set null some variables.
     */
    public void newGame() {
        turns.clear();
        party.clear();
        enemies.clear();
        inventory.clear();
        allEnemies.clear();
        allPlayers.clear();
        currentCharacter = null;
        currentWeapon = null;
        currentOpponentToAttack = null;
    }

    /**
     * Calls the phase partyIsComplete method
     */
    public void tryToPartyIsComplete() {
        phase.partyIsComplete();
    }

    /**
     * Checks if the party list is complete.
     */
    public void partyIsComplete() {
        if (party.size() == 3) {
            completeInventory();
        }
    }

    /**
     * Checks if the player won.
     *
     * @return true if enemies list is empty.
     */
    public boolean playerWon() {
        return enemies.isEmpty();
    }

    /**
     * Checks if the enemy won.
     *
     * @return true if party list is empty.
     */
    public boolean enemyWon() {
        return party.isEmpty();
    }

    /**
     * Checks if a character is dead.
     *
     * @param character the character in question.
     * @return true if a character is dead.
     */
    public boolean isDead(ICharacter character) {
        if (!character.isAlive()) {
            characterIsDeadNotification.firePropertyChange(
                    "CHARACTER_IS_DEAD", null, this);
            return true;
        }
        return false;
    }

    /**
     * Puts a character on the turns queue.
     *
     * @param character the character in question.
     */
    public void waitTurn(ICharacter character) {
        character.waitTurn();
    }

    /**
     * Returns true if the character is a player character.
     *
     * @param character the character in question.
     * @return true if the character is a player character.
     */
    public boolean isPlayer(ICharacter character) {
        return allPlayers.contains(character);
    }

    /**
     * Completes the inventory with five weapons with random stats.
     */
    public void completeInventory() {
        createAxe("Axe", rng.nextInt(50) + 100, rng.nextInt(10) + 1);
        createBow("Bow", rng.nextInt(50) + 100, rng.nextInt(10) + 1);
        createKnife("Knife", rng.nextInt(50) + 100, rng.nextInt(10) + 1);
        createStaff("Staff", rng.nextInt(50) + 100, rng.nextInt(10) + 1);
        createSword("Sword", rng.nextInt(50) + 100, rng.nextInt(10) + 1);
    }

    /**
     * Gets the character's name.
     *
     * @param character the character in question.
     * @return the character's name.
     */
    public String getNameCharacter(ICharacter character) {
        return character.getName();
    }

    /**
     * Gets the character's life.
     *
     * @param character the character in question.
     * @return the character's life.
     */
    public String getLifeCharacter(ICharacter character) {
        return String.valueOf(character.getLife());
    }

    /**
     * Sets the character's life.
     *
     * @param character the character in question.
     * @param life      the character's new life.
     */
    public void setLifeCharacter(ICharacter character, int life) {
        character.setLife(life);
    }

    /**
     * Gets the character's defense.
     *
     * @param character the character in question.
     * @return the character's defense.
     */
    public String getDefenseCharacter(ICharacter character) {
        return String.valueOf(character.getDefense());
    }

    /**
     * Gets the character's attack.
     *
     * @param character the character in question.
     * @return the enemy's attack.
     */
    public String getAttackEnemy(ICharacter character) {
        return String.valueOf(((Enemy) character).getAttack());
    }

    /**
     * Gets the character's attack.
     *
     * @param character the character in question.
     * @return the enemy's attack.
     */
    public String getDamageEquippedWeapon(ICharacter character) {
        return String.valueOf(((IPlayerCharacter) character).getEquippedWeapon().getDamage());
    }

    /**
     * Gets the character's equipped weapon.
     *
     * @param character the character in question.
     * @return the character's equipped weapon.
     */
    public IWeapon getEquippedWeaponCharacter(ICharacter character) {
        return ((IPlayerCharacter) character).getEquippedWeapon();
    }

    /**
     * Gets the weapon's name.
     *
     * @param weapon the weapon in question.
     * @return the weapon´s name.
     */
    public String getNameWeapon(IWeapon weapon) {
        return weapon.getName();
    }

    /**
     * Gets the inventory weapon's damage.
     *
     * @param index the weapon's index in the inventory.
     * @return the weapon's damage.
     */
    public String getDamageWeapon(int index) {
        return String.valueOf(inventory.get(index).getDamage());
    }

    /**
     * Sets the character's equipped weapon as null.
     *
     * @param character the character in question.
     */
    public void setEquippedWeaponCharacterNull(ICharacter character) {
        ((IPlayerCharacter) character).setEquippedWeaponNull();
    }

    /**
     * Gets the enemies list.
     *
     * @return the enemies list.
     */
    public List<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Gets the party list.
     *
     * @return the party list.
     */
    public List<IPlayerCharacter> getParty() {
        return party;
    }

    /**
     * Gets a enemy from enemies list.
     *
     * @param index the index in enemies of the enemy.
     * @return the enemy in the indicated index.
     */
    public Enemy getEnemy(int index) {
        return enemies.get(index);
    }

    /**
     * Gets a character from party list.
     *
     * @param index the index in party of the character.
     * @return the character in the indicated index.
     */
    public IPlayerCharacter getPlayer(int index) {
        return party.get(index);
    }

    /**
     * Gets the allEnemies list.
     *
     * @return the allEnemies list.
     */
    public List<Enemy> getAllEnemiesList() {
        return allEnemies;
    }

    /**
     * Gets the allPlayers list.
     *
     * @return the allPlayers list.
     */
    public List<IPlayerCharacter> getAllPlayersList() {
        return allPlayers;
    }

    /**
     * Gets a weapon from the inventory.
     *
     * @param index the index in the inventory of the weapon.
     * @return the weapon in the indicated index.
     */
    public IWeapon getWeapon(int index) {
        return getInventory().get(index);
    }

    /**
     * Gets the inventory.
     *
     * @return the inventory.
     */
    public List<IWeapon> getInventory() {
        return inventory;
    }

    /**
     * Get the turns queue.
     *
     * @return the turns queue.
     */
    public BlockingQueue<ICharacter> getTurns() {
        return turns;
    }

    /**
     * Gets a enemy from allEnemies list.
     *
     * @param index the index in enemies of the enemy.
     * @return the enemy in the indicated index.
     */
    public Enemy getAllEnemies(int index) {
        return allEnemies.get(index);
    }

    /**
     * Gets a character from allPlayers list.
     *
     * @param index the index in party of the character.
     * @return the character in the indicated index.
     */
    public IPlayerCharacter getAllPlayers(int index) {
        return allPlayers.get(index);
    }

    /**
     * Gets the character in the currentCharacter variable.
     *
     * @return the current character.
     */
    public ICharacter getCurrentCharacter() {
        return currentCharacter;
    }

    /**
     * Sets the character in the currentCharacter variable.
     */
    public void setCurrentCharacter(ICharacter character) {
        this.currentCharacter = character;
    }

    /**
     * Gets the weapon in the currentWeapon variable.
     *
     * @return the current weapon.
     */
    public IWeapon getCurrentWeapon() {
        return currentWeapon;
    }

    /**
     * Sets the character in the currentWeapon variable.
     */
    public void setCurrentWeapon(IWeapon weapon) {
        this.currentWeapon = weapon;
    }

    /**
     * Gets the character in the currentOpponentToAttack variable.
     *
     * @return the current opponent to attack.
     */
    public ICharacter getCurrentOpponentToAttack() {
        return currentOpponentToAttack;
    }

    /**
     * Sets the character in the currentOpponentToAttack variable.
     */
    public void setCurrentOpponentToAttack(ICharacter character) {
        this.currentOpponentToAttack = character;
    }

    /**
     * Sets a new phase to the controller.
     * @param phase
     *       the new phase.
     */
    public void setPhase(final @NotNull Phase phase) {
        this.phase = phase;
        phase.setController(this);
    }

    /**
     * Gets the name of the current phase.
     * @return
     *        the current phase's name.
     */
    public String getCurrentPhase() {
        return phase.toString();
    }

    public Phase getPhase() {
        return phase;
    }
}