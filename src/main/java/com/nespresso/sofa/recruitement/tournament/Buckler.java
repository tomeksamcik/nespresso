package com.nespresso.sofa.recruitement.tournament;

public class Buckler {

	private Integer bucklerAxeHit = 0;

	private Integer hit = 0;

	private Warrior owner;

	public Buckler(Warrior owner) {
		this.owner = owner;
	}

	/**
	 * Uses up buckler during defend
	 * 
	 * @param attacker
	 *            Attacker
	 */
	public void block(Warrior attacker) {
		if (attacker.getWeapon().canBreakBuckler()) {
			bucklerAxeHit++;
			if (bucklerAxeHit == 3) {
				owner.setBuckler(null);
			}
		}
	}

	/**
	 * Checks if buckler is ready to use in this round
	 * 
	 * @return Yes or no
	 */
	public Boolean canBlock() {
		return hit++ % 2 == 0;
	}

}
