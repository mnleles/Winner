package com.megabot.winner.business.stats.impl;

import org.springframework.stereotype.Component;

import com.megabot.winner.inteface.model.TicketType;

@Component
public class LotomaniaFrequencyStats extends AbstractFrequencyStats
{
	@Override
	public boolean isAssignbleTo(final TicketType type)
	{
		return TicketType.LOTOMANIA == type;
	}

}
