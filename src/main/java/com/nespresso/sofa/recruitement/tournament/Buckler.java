package com.nespresso.sofa.recruitement.tournament;

public class Buckler {

	private Integer bucklerAxeHit = 0;

	private Integer hit = 0;

	private Warrior owner;

	public Buckler(Warrior owner) {
		this.owner = owner;
	}

	/**
	 * Performs a block if possible and wears up buckler
	 * 
	 * @param attacker
	 *            Attacker to block from
	 * @return True if block is possible in the given round, false otherwise
	 */
	public Boolean block(Warrior attacker) {
		if (hit++ % 2 == 0) {
			if (attacker.getWeapon() instanceof Axe) {
				bucklerAxeHit++;
				if (bucklerAxeHit == 3) {
					owner.setBuckler(null);
				}
			}
			return true;
		} else {
			return false;
		}
	}
}
