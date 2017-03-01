package com.megabot.winner.business.ticket;

import com.megabot.winner.inteface.model.Ticket;

public interface TicketBuilder extends ITicketAssignble
{
	Ticket build(Integer amountNumber);
}
