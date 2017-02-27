package com.megabot.winner.business.ticket.rule.black;

import org.springframework.stereotype.Component;

import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketType;

@Component
public class SequenceNumberBlackRule implements IBlackRule {

	@Override
	public boolean isAppliable(final Ticket ticket) {
		return false;
	}

	@Override
	public boolean isAssignbleTo(TicketType type) {
		return TicketType.MEGA_SENA == type;
	}

}
