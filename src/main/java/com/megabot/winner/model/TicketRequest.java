package com.megabot.winner.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TicketRequest {

	private Integer amountNumbers;
	private Integer amountTickets;
	private TicketType ticketType;

	public Integer getAmountNumbers() {
		return amountNumbers;
	}

	public Integer getAmountTickets() {
		return amountTickets;
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setAmountNumbers(final Integer amountNumbers) {
		this.amountNumbers = amountNumbers;
	}

	public void setAmountTickets(final Integer amountTickets) {
		this.amountTickets = amountTickets;
	}

	public void setTicketType(final TicketType gameType) {
		this.ticketType = gameType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
