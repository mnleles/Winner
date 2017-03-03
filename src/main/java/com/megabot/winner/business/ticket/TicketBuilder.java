package com.megabot.winner.business.ticket;

import java.util.Collection;

import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketRequest;

public interface TicketBuilder extends ITicketAssignble
{
	Collection<Ticket> build(final TicketRequest request);
}
