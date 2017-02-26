package com.megabot.winner.business.ticket;

import com.megabot.winner.inteface.model.Ticket;

public interface TicketGenerator extends ITicketAssignble
{
	Ticket create(Integer amountNumber);
}
