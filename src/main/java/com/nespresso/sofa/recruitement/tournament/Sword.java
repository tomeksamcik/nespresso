package com.nespresso.sofa.recruitement.tournament;

public class Sword extends Weapon {

	public Sword() {
		this.setDamage(5);
	}

	@Override
	public Boolean readyToHit() {
		return true;
	}

}
