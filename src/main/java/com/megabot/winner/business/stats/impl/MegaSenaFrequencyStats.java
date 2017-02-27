package com.megabot.winner.business.stats.impl;

import com.megabot.winner.inteface.model.TicketType;

public class MegaSenaFrequencyStats extends AbstractFrequencyStats
{
	@Override
	public boolean isAssignbleTo(final TicketType type)
	{
		return TicketType.MEGA_SENA == type;
	}

}
