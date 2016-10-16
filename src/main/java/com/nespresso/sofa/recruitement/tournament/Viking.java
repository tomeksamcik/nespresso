package com.nespresso.sofa.recruitement.tournament;

public class Viking extends Warrior {

	public Viking() {
		setStamina(120);
		setName("Viking");
		setRank("");
		getEquipment().add("axe");
	}

	@Override
	public Viking equip(String weapon) {
		getEquipment().add(weapon);
		return this;
	}

	@Override
	protected Integer applyRankPerks(Integer damage, Warrior victim) {
		return damage;
	}

}
