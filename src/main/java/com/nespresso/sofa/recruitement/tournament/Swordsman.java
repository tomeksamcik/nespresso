package com.nespresso.sofa.recruitement.tournament;

public class Swordsman extends Warrior {

	public Swordsman() {
		setStamina(100);
		setName("Swordsman");
		setRank("");
		getEquipment().add("sword");
	}

	public Swordsman(String rank) {
		this();
		setRank(rank);
	}

	@Override
	public Swordsman equip(String weapon) {
		getEquipment().add(weapon);
		return this;
	}

	@Override
	protected Integer applyRankPerks(Integer damage, Warrior victim) {
		if (getRank() != null && getRank().equals("Vicious")
				&& victim.getHit() < 2) {
			damage += 20;
		}
		return damage;
	}

}
