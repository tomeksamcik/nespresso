package com.nespresso.sofa.recruitement.tournament;

public class Armor {

	private final Integer reduceDamageReceivedBy = 3;

	private final Integer reduceDamageGivenBy = 1;

	/**
	 * Reduces damge received as a result of a hit
	 * 
	 * @param damage
	 *            Damage before armor protection
	 * @return Damage after armor protection
	 */
	public Integer reduceDamageReceived(Integer damage) {
		return damage - reduceDamageReceivedBy;
	}

	/**
	 * Decreases damage incurred by warrior due to increased mass
	 * 
	 * @param damage
	 *            Damage before armor penalty
	 * @return Damage after applying armor penalty
	 */
	public Integer reduceDamageGiven(Integer damage) {
		return damage - reduceDamageGivenBy;
	}

}
