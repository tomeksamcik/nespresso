package com.nespresso.sofa.recruitement.tournament;

public abstract class Weapon {

	private Integer damage;

	/**
	 * Factory methods returning specific weapon based on input name
	 * 
	 * @param name
	 *            Weapon name
	 * @return Specific weapon
	 */
	public static Weapon getWeapon(String name) {
		switch (name) {
		case "axe":
			return new Axe();
		case "sword":
			return new Sword();
		case "Great Sword":
			return new GreatSword();
		default:
			return null;
		}
	}

	/**
	 * Checks if weapon is ready to hit in the given round
	 * 
	 * @return True or false
	 */
	public abstract Boolean readyToHit();

	/**
	 * Checks if weapon can break buckler
	 * 
	 * @return True of false
	 */
	public abstract Boolean canBreakBuckler();

	public Integer getDamage() {
		return damage;
	}

	public void setDamage(Integer damage) {
		this.damage = damage;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}
