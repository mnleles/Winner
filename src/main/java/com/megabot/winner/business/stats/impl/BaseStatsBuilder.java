package com.megabot.winner.business.stats.impl;

import java.util.HashMap;
import java.util.Map;

import com.megabot.winner.business.stats.StatsBuilder;
import com.megabot.winner.inteface.model.TicketType;

public abstract class BaseStatsBuilder implements StatsBuilder
{
	protected Map<Integer, Float> loadNumbersWithFloatValue(final TicketType type)
	{
		Map<Integer, Float> delayNumbers = new HashMap<>();
		for (int i = type.minNumber; i <= type.maxNumber; i++)
		{
			delayNumbers.put(i, 0F);
		}

		return delayNumbers;
	}

	protected Map<Integer, Integer> loadNumbersWithIntValue(final TicketType type)
	{
		Map<Integer, Integer> delayNumbers = new HashMap<>();
		for (int i = type.minNumber; i <= type.maxNumber; i++)
		{
			delayNumbers.put(i, 0);
		}

		return delayNumbers;
	}
}
