package com.megabot.winner;

import com.megabot.winner.model.TicketType;
import com.megabot.winner.model.Ticket;

public interface TicketGenerator {

	Ticket create(Integer amountNumber);

	boolean isAssignbleTo(TicketType type);
}
