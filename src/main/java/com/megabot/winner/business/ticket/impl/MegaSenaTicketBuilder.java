package com.megabot.winner.business.ticket.impl;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.megabot.winner.business.ticket.TicketBuilder;
import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketRequest;
import com.megabot.winner.inteface.model.TicketType;

@Component
public class MegaSenaTicketBuilder implements TicketBuilder
{
	@Override
	public Collection<Ticket> build(final TicketRequest request)
	{
		return null;
	}

	@Override
	public boolean isAssignbleTo(final TicketType type)
	{
		return TicketType.MEGA_SENA == type;
	}

}
