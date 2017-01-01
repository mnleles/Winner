package com.megabot.winner.inteface.model;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.megabot.winner.support.model.Response;

public class TicketResponse extends Response
{
	private final Collection<Ticket> tickets = new ArrayList<>();

	public void addTicket(final Ticket ticketInput)
	{
		tickets.add(ticketInput);
	}

	public Collection<Ticket> getTickets()
	{
		return tickets;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}

}
