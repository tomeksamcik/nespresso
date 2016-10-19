package com.nespresso.sofa.recruitement.tournament;

import org.apache.log4j.Logger;

/**
 * @author Tomek Samcik
 *
 */
public abstract class Warrior {

	final static Logger logger = Logger.getLogger(Warrior.class);

	/**
	 * Type of warrior
	 */
	private String name;

	/**
	 * Rank of warrior
	 */
	private String rank;

	/**
	 * Warriors stamina points (0 equals death)
	 */
	private Integer stamina;

	/**
	 * Number of hits received
	 */
	private Integer hit = 0;

	/**
	 * Weapon carried by Warrior (assuming one item)
	 */
	private Weapon weapon;

	/**
	 * Buckler carried in hand
	 */
	private Buckler buckler;

	/**
	 * Worn armor
	 */
	private Armor armor;

	/**
	 * Equipping Warrior with an armor or weapon
	 * 
	 * @param armour
	 *            Armor or weapon
	 * @return Armored Warrior
	 */
	public abstract Warrior equip(String armor);

	/**
	 * Warrior wears weapon
	 * 
	 * @param armor
	 *            Armor or weapon
	 */
	protected void wear(String name) {
		Weapon weapon = Weapon.getWeapon(name);
		if (weapon != null) {
			setWeapon(weapon);
		} else {
			if (name.equals("armor")) {
				setArmor(new Armor());
			}
			if (name.equals("buckler")) {
				setBuckler(new Buckler(this));
			}
		}
	}

	/**
	 * Apply Warrior-specific rank bonus
	 * 
	 * @param damage
	 *            Weapon damage before bonus
	 * @return Damage after bonus
	 */
	protected abstract Integer applyRankPerks(Integer damage, Warrior victim);

	/**
	 * Getting potential damage incurred from carried equipment
	 * 
	 * @return
	 */
	private Integer getPotentiallyIncurredDamage(Warrior victim) {
		return applyArmorPenalty(applyRankPerks(weapon.getDamage(), victim),
				victim);
	}

	/**
	 * Gets damage suffered from a hit given the potential damage incurred by
	 * the attacker and worn armors (again happens to be generic to all
	 * Warriors)
	 * 
	 * @param damage
	 *            Damage incurred by the attacker
	 * @return Damage suffered given the armor
	 */
	private Integer getSufferedDamage(Integer damage) {
		if (hasArmor()) {
			return armor.reduceDamageReceived(damage);
		}
		return damage;
	}

	/**
	 * Returns damage incurred after applying armor penalty (happens to be
	 * generic to all Warriors based on equipment)
	 * 
	 * @param damage
	 *            Damage before armor penalty
	 * @param victim
	 *            Victim of incurred damage
	 * @return Damage incurred after armor penalty
	 */
	private Integer applyArmorPenalty(Integer damage, Warrior victim) {
		if (hasArmor()) {
			return armor.reduceDamageGiven(damage);
		}
		return damage;
	}

	/**
	 * Engages warrior in a battle
	 * 
	 * @param opponent
	 */
	public void engage(Warrior opponent) {
		do {
			blow(opponent);
			if (!opponent.isDead()) {
				opponent.blow(this);
			}
			logger.info(getDisplayName() + " hitPoints: " + getStamina() + ", "
					+ opponent.getDisplayName() + " hitPoints: "
					+ opponent.getStamina());
		} while (!opponent.isDead() && !isDead());
		if (isDead()) {
			logger.info(getDisplayName() + " defeated.");
		} else if (opponent.isDead()) {
			logger.info(opponent.getDisplayName() + " defeated.");
		}
	}

	/**
	 * Throws a blow and incurrs given damage to the opponent
	 * 
	 * @param warrior
	 *            Warrior that receives a blow
	 */
	public void blow(Warrior warrior) {
		logger.debug(getDisplayName()
				+ " blows "
				+ warrior.getDisplayName()
				+ " ("
				+ getDisplayName()
				+ " armors: ["
				+ ((hasArmor() ? "armor " : "") + (hasBuckler() ? "buckler"
						: "")).trim() + "], weapon: " + weapon + ")");

		if (weapon.readyToHit()) {
			hit(warrior);
		}
	}

	/**
	 * Hits opponent
	 * 
	 * @param warrior
	 *            Attacker
	 */
	private void hit(Warrior warrior) {
		if (!warrior.canBlock(this)) {
			warrior.sufferDamage(warrior
					.getSufferedDamage(getPotentiallyIncurredDamage(warrior)));
		} else {
			warrior.getBuckler().block(this);
		}
		warrior.hit++;
	}

	/**
	 * Returns true and uses up armor if attack has been blocked, false
	 * otherwise
	 * 
	 * @param attacker
	 *            Attacker being blocked
	 * @return Blocked or not blocked
	 */
	private Boolean canBlock(Warrior attacker) {
		return hasBuckler() && buckler.canBlock();
	}

	/**
	 * Warrior gets damage
	 * 
	 * @param incurredDamage
	 *            Number of story points lost
	 */
	private void sufferDamage(Integer damage) {
		stamina = stamina - damage >= 0 ? stamina - damage : 0;
	}

	/**
	 * Do we have buckler ?
	 * 
	 * @return Yer or no
	 */
	private Boolean hasBuckler() {
		return buckler != null;
	}

	/**
	 * Do we have armor ?
	 * 
	 * @return Yer or no
	 */
	private Boolean hasArmor() {
		return armor != null;
	}

	/**
	 * Am I dead yet ?
	 * 
	 * @return Yes or no
	 */
	private Boolean isDead() {
		return getStamina() == 0;
	}

	/*
	 * Getters & Setters
	 */

	public String getDisplayName() {
		return rank.trim().isEmpty() ? name : rank + " " + name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

	public void setStamina(Integer stamina) {
		this.stamina = stamina;
	}

	public Integer getStamina() {
		return stamina;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Buckler getBuckler() {
		return buckler;
	}

	public void setBuckler(Buckler buckler) {
		this.buckler = buckler;
	}

	public void setArmor(Armor armor) {
		this.armor = armor;
	}

}
