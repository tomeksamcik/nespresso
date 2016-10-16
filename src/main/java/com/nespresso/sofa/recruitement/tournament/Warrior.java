package com.nespresso.sofa.recruitement.tournament;

import java.util.ArrayList;
import java.util.List;

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
	 * Equipment carried by Warrior
	 */
	private List<String> equipment = new ArrayList<String>();

	/**
	 * Number of hits received
	 */
	private Integer hit = 0;

	/**
	 * Number of hits with an axe
	 */
	private Integer bucklerAxeHit = 0;

	/**
	 * Equipping Warrior with an armor or weapon
	 * 
	 * @param armour
	 *            Armour or weapon
	 * @return Armored Warrior
	 */
	public abstract Warrior equip(String armor);

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
		return applyArmorPenalty(applyRankPerks(getWeaponDamage(), victim),
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
		if (equipment.contains("armor")) {
			damage -= 3;
		}
		if (equipment.contains("buckler") && hit % 2 == 0) {
			damage = 0;
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
		if (equipment.contains("armor")) {
			damage -= 1;
		}
		return damage;
	}

	/**
	 * Returns damage incurred based on weapon, function assumes that Warrior
	 * always selects weapon causing the greatest damage (happens to be generic
	 * to all Warriors)
	 * 
	 * @return Damage incurred
	 */
	private Integer getWeaponDamage() {
		int damage = 0;
		if (equipment.contains("sword")) {
			damage = 5;
		}
		if (equipment.contains("axe")) {
			damage = 6;
		}
		if (equipment.contains("Great Sword")) {
			damage = 12;
		}
		return damage;
	}

	/**
	 * Am I dead yet ?
	 * 
	 * @return Yes or no
	 */
	private Boolean isDead() {
		return getStamina() == 0;
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
		logger.info(getDisplayName() + " blows " + warrior.getDisplayName()
				+ " (" + getDisplayName() + " armors: " + equipment + ")");

		if (readyToHit()) {
			if (!warrior.block(this)) {
				warrior.sufferDamage(warrior
						.getSufferedDamage(getPotentiallyIncurredDamage(warrior)));
			}
			warrior.hit++;
		}
	}

	/**
	 * Warrior may not be ready to hit each round for certain weapons (Warrior
	 * type agnostic, only reliant on weapon type)
	 * 
	 * @return Ready or not
	 */
	private Boolean readyToHit() {
		return !(equipment.contains("Great Sword") && hit % 3 == 0);
	}

	/**
	 * Returns true and uses up armor if attack has been blocked, false
	 * otherwise
	 * 
	 * @param attacker
	 *            Attacker being blocked
	 * @return Blocked or not blocked
	 */
	private Boolean block(Warrior attacker) {
		if (bucklerAxeHit == 3 && equipment.contains("buckler")) {
			equipment.remove("buckler");
		}
		if (equipment.contains("buckler") && hit % 2 == 0) {
			if (attacker.equipment.contains("axe")) {
				bucklerAxeHit++;
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Warrior gets damage
	 * 
	 * @param incurredDamage
	 *            Number of story points lost
	 */
	public void sufferDamage(Integer damage) {
		stamina = stamina - damage >= 0 ? stamina - damage : 0;
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

	public List<String> getEquipment() {
		return equipment;
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

}
