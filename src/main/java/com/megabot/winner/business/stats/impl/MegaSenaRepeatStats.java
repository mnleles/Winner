package com.megabot.winner.business.stats.impl;

import org.springframework.stereotype.Component;

import com.megabot.winner.inteface.model.TicketType;

@Component
public class MegaSenaRepeatStats extends AbstractRepeatStats
{
	@Override
	public boolean isAssignbleTo(final TicketType type)
	{
		return TicketType.MEGA_SENA == type;
	}

}
