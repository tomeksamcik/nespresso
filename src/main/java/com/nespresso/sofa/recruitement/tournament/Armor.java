package com.nespresso.sofa.recruitement.tournament;

public class Armor {

	private final Integer reduceDamageReceivedBy = 3;

	private final Integer reduceDamageGivenBy = 1;

	public Integer reduceDamageReceived(Integer damage) {
		return damage - reduceDamageReceivedBy;
	}

	public Integer reduceDamageGiven(Integer damage) {
		return damage - reduceDamageGivenBy;
	}

}
