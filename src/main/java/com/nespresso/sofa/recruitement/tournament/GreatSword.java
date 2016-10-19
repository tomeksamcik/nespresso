package com.nespresso.sofa.recruitement.tournament;

public class GreatSword extends Weapon {

	private Integer hit = 0;

	public GreatSword() {
		setDamage(12);
	}

	@Override
	public Boolean readyToHit() {
		return ++hit % 3 != 0;
	}

	@Override
	public Boolean canBreakBuckler() {
		return false;
	}

}
