package com.megabot.winner.inteface.model;

import java.util.Map;

public class DelayNumbersStats extends Stats
{
	private Map<Integer, Float> avgDelay;
	private Map<Integer, DelayContestStats> contestsStats;
	private Map<Integer, Integer> countDelay;
	private Map<Integer, Integer> maxDelay;

	public DelayNumbersStats()
	{
		setStatsType(StatsType.DELAY_NUMBERS);
	}

	public Map<Integer, Float> getAvgDelay()
	{
		return avgDelay;
	}

	public Map<Integer, DelayContestStats> getContestsStats()
	{
		return contestsStats;
	}

	public Map<Integer, Integer> getCountDelay()
	{
		return countDelay;
	}

	public DelayContestStats getContestStatsLatest()
	{
		if (contestsStats == null || contestsStats.isEmpty())
		{
			return null;
		}

		Integer maxContestId = contestsStats.keySet().stream().max(Integer::compare).get();
		return contestsStats.get(maxContestId);
	}

	public Map<Integer, Integer> getMaxDelay()
	{
		return maxDelay;
	}

	public void setAvgDelay(final Map<Integer, Float> avgDelay)
	{
		this.avgDelay = avgDelay;
	}

	public void setContestsStats(final Map<Integer, DelayContestStats> contestsStats)
	{
		this.contestsStats = contestsStats;
	}

	public void setCountDelay(final Map<Integer, Integer> countDelay)
	{
		this.countDelay = countDelay;
	}

	public void setMaxDelay(final Map<Integer, Integer> maxDelay)
	{
		this.maxDelay = maxDelay;
	}
}
