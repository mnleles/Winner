package com.megabot.winner.inteface.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class StatsRequest {
	
	private TicketType ticketType;

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
