package com.nespresso.sofa.recruitement.tournament;

public class Swordsman extends Warrior {

	private final Integer INITIAL_STAMINA = 100;

	public Swordsman() {
		setStamina(INITIAL_STAMINA);
		setName("Swordsman");
		setRank("");
		setWeapon(new Sword());
	}

	public Swordsman(String rank) {
		this();
		setRank(rank);
	}

	@Override
	public Swordsman equip(String name) {
		wear(name);
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
