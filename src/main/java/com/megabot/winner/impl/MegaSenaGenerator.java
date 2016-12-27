package com.megabot.winner.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.megabot.winner.TicketGenerator;
import com.megabot.winner.model.TicketType;
import com.megabot.winner.model.Ticket;
import com.megabot.winner.rule.black.IBlackRule;

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
