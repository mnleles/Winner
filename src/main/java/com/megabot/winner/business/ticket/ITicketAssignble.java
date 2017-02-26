package com.megabot.winner.business.ticket;

import com.megabot.winner.inteface.model.TicketType;

public interface ITicketAssignble
{
	boolean isAssignbleTo(TicketType type);
}
