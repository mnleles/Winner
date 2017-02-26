package com.megabot.winner.business.rule;

import com.megabot.winner.business.ticket.ITicketAssignble;
import com.megabot.winner.inteface.model.Ticket;

public interface IRule extends ITicketAssignble {

	boolean isAppliable(Ticket ticket);
}
