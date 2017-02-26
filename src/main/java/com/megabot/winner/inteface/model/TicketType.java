package com.megabot.winner.inteface.model;

public enum TicketType {

	MEGA_SENA(6, 1, 60),
	LOTOFACIL(15, 1, 25);

	public int amountNumbers;
	public int maxNumber;
	public int minNumber;

	private TicketType(final int amountNumbersParam, final int minNumberParam, final int maxNumberParam) {
		amountNumbers = amountNumbersParam;
		minNumber = minNumberParam;
		maxNumber = maxNumberParam;
	}

	public int getAmountNumbers() {

		return amountNumbers;
	}

	public int getMaxNumbers() {

		return maxNumber;
	}

	public int getMinNumber() {

		return minNumber;
	}
}
