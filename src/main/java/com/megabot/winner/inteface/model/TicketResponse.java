package com.megabot.winner.inteface.model;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.megabot.winner.support.model.Response;

public class TicketResponse extends Response {
	private final Collection<Ticket> tickets = new ArrayList<>();
	private Long totalItems;

	public void addTicket(final Ticket ticketInput) {
		tickets.add(ticketInput);
	}

	public void addAllTickets(final Collection<Ticket> ticketsInput) {
		tickets.addAll(ticketsInput);
	}

	public Collection<Ticket> getTickets() {
		return tickets;
	}

	public Long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
