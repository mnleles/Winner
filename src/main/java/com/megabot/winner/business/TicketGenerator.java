package com.megabot.winner.business;

import com.megabot.winner.inteface.model.Ticket;

public interface TicketGenerator extends IAssignble
{
	Ticket create(Integer amountNumber);
}
