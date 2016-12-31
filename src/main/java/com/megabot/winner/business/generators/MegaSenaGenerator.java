package com.megabot.winner.business.generators;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.megabot.winner.business.TicketGenerator;
import com.megabot.winner.business.rule.black.IBlackRule;
import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketType;

@Component
public class MegaSenaGenerator implements TicketGenerator {

	@Autowired
	private Collection<IBlackRule> blackRules;

	@Override
	public Ticket create(final Integer amountNumber) {
		return null;
	}

	@Override
	public boolean isAssignbleTo(final TicketType type) {
		return TicketType.MEGA_SENA == type;
	}

}
