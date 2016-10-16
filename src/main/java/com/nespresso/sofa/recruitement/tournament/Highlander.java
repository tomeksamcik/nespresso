package com.nespresso.sofa.recruitement.tournament;

public class Highlander extends Warrior {

	public Highlander() {
		setStamina(150);
		setName("Highlander");
		setRank("");
		getEquipment().add("Great Sword");
	}

	public Highlander(String rank) {
		this();
		setRank(rank);
	}

	@Override
	public Highlander equip(String weapon) {
		getEquipment().add(weapon);
		return this;
	}

	@Override
	protected Integer applyRankPerks(Integer damage, Warrior victim) {
		if (getRank() != null && getRank().equals("Veteran")
				&& getStamina() <= 45) {
			damage *= 2;
		}
		return damage;
	}

}
