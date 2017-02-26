package com.megabot.winner.business.stats;

import java.util.Collection;

import com.megabot.winner.business.ticket.ITicketAssignble;
import com.megabot.winner.inteface.model.StatsRequest;
import com.megabot.winner.inteface.model.StatsResponse;
import com.megabot.winner.inteface.model.Ticket;

public interface IStatsGenerator extends ITicketAssignble {

	void generateStas(Collection<Ticket> tickets);
	
	StatsResponse fetchAll(StatsRequest request);
}
