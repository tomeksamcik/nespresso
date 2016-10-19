package com.nespresso.sofa.recruitement.tournament;

public class Axe extends Weapon {

	public Axe() {
		setDamage(6);
	}

	@Override
	public Boolean readyToHit() {
		return true;
	}

	@Override
	public Boolean canBreakBuckler() {
		return true;
	}

}
