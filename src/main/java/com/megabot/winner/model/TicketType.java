package com.megabot.winner.model;

public enum TicketType {

	MEGA_SENA(6);

	public int amountNumbers;

	private TicketType(int input) {
		amountNumbers = input;
	}
}
