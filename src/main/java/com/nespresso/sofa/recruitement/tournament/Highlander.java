package com.nespresso.sofa.recruitement.tournament;

public class Highlander extends Warrior {

	private final Integer INITIAL_STAMINA = 150;

	public Highlander() {
		setStamina(INITIAL_STAMINA);
		setName("Highlander");
		setRank("");
		setWeapon(new GreatSword());
	}

	public Highlander(String rank) {
		this();
		setRank(rank);
	}

	@Override
	public Highlander equip(String name) {
		wear(name);
		return this;
	}

	@Override
	protected Integer applyRankPerks(Integer damage, Warrior victim) {
		if (getRank() != null && getRank().equals("Veteran")
				&& getStamina() <= 0.3 * INITIAL_STAMINA) {
			damage *= 2;
		}
		return damage;
	}

}
