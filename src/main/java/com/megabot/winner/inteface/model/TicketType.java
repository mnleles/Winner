package com.megabot.winner.inteface.model;

public enum TicketType {

	MEGA_SENA(6);

	public int amountNumbers;

	private TicketType(int input) {
		amountNumbers = input;
	}
}
