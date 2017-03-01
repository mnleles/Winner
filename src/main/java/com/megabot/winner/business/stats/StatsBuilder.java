package com.megabot.winner.business.stats;

import java.util.Collection;

import com.megabot.winner.business.ticket.ITicketAssignble;
import com.megabot.winner.inteface.model.StatsType;
import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketType;

public interface StatsBuilder extends ITicketAssignble
{
	void build(TicketType type, Collection<Ticket> tickets);

	boolean isStatsAssignbleTo(StatsType type);
}
