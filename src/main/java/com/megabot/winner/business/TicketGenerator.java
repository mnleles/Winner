package com.megabot.winner.business;

import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketType;

public interface TicketGenerator {

	Ticket create(Integer amountNumber);

	boolean isAssignbleTo(TicketType type);
}
