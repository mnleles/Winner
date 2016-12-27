package com.megabot.winner.model;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TicketResponse {

	private final Collection<Ticket> tickets = new ArrayList<>();

	public void addTicket(final Ticket ticketInput) {
		tickets.add(ticketInput);
	}

	public Collection<Ticket> getTickets() {
		return tickets;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
