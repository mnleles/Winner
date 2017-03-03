package com.megabot.winner.inteface.model;

import java.util.Map;

public class FrequencyNumbersStats extends Stats
{
	private Map<Integer, Integer> frequency;

	public FrequencyNumbersStats()
	{
		setStatsType(StatsType.FREQUENCY_NUMBERS);
	}

	public Map<Integer, Integer> getFrequency()
	{
		return frequency;
	}

	public void setFrequency(final Map<Integer, Integer> frequency)
	{
		this.frequency = frequency;
	}

}
