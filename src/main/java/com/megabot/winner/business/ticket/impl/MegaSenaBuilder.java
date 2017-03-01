package com.megabot.winner.business.ticket.impl;

import org.springframework.stereotype.Component;

import com.megabot.winner.business.ticket.TicketBuilder;
import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketType;

@Component
public class MegaSenaBuilder implements TicketBuilder
{
	@Override
	public Ticket build(final Integer amountNumber)
	{
		return null;
	}

	@Override
	public boolean isAssignbleTo(final TicketType type)
	{
		return TicketType.MEGA_SENA == type;
	}

}
