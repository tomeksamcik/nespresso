package com.nespresso.sofa.recruitement.tournament;

public class Viking extends Warrior {

	private final Integer INITIAL_STAMINA = 120;

	public Viking() {
		setStamina(INITIAL_STAMINA);
		setName("Viking");
		setRank("");
		setWeapon(new Axe());
	}

	@Override
	public Viking equip(String name) {
		wear(name);
		return this;
	}

	@Override
	protected Integer applyRankPerks(Integer damage, Warrior victim) {
		return damage;
	}

}
