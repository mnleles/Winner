package com.megabot.winner.business.ticket.impl;

import org.springframework.stereotype.Component;

import com.megabot.winner.business.ticket.TicketGenerator;
import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketType;

@Component
public class MegaSenaGenerator implements TicketGenerator
{
	@Override
	public Ticket create(final Integer amountNumber)
	{
		return null;
	}

	@Override
	public boolean isAssignbleTo(final TicketType type)
	{
		return TicketType.MEGA_SENA == type;
	}

}
