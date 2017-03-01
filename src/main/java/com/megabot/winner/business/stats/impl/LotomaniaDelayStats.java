package com.megabot.winner.business.stats.impl;

import org.springframework.stereotype.Component;

import com.megabot.winner.inteface.model.TicketType;

@Component
public class LotomaniaDelayStats extends AbstractDelayStats
{
	@Override
	public boolean isAssignbleTo(final TicketType type)
	{
		return TicketType.LOTOMANIA == type;
	}

}
