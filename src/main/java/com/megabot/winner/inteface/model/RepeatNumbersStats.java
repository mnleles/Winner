package com.megabot.winner.inteface.model;

import java.util.Map;

public class RepeatNumbersStats extends Stats
{
	private Float avgNumbersBetweenContests;
	private Map<Integer, Float> avgRepetition;
	private Map<Integer, RepeatContestStats> contestsStats;
	private Map<Integer, Integer> countRepetition;

	private Map<Integer, Integer> maxRepetition;

	public RepeatNumbersStats()
	{
		setStatsType(StatsType.REPEAT_NUMBERS);
	}

	public Float getAvgNumbersBetweenContests()
	{
		return avgNumbersBetweenContests;
	}

	public Map<Integer, Float> getAvgRepetition()
	{
		return avgRepetition;
	}

	public Map<Integer, RepeatContestStats> getContestsStats()
	{
		return contestsStats;
	}

	public RepeatContestStats getContestStatsLatest()
	{
		if (contestsStats == null || contestsStats.isEmpty())
		{
			return null;
		}

		Integer maxContestId = contestsStats.keySet().stream().max(Integer::compare).get();
		return contestsStats.get(maxContestId);
	}

	public Map<Integer, Integer> getCountRepetition()
	{
		return countRepetition;
	}

	public Map<Integer, Integer> getMaxRepetition()
	{
		return maxRepetition;
	}

	public void setAvgNumbersBetweenContests(final Float avgNumbersBetweenContests)
	{
		this.avgNumbersBetweenContests = avgNumbersBetweenContests;
	}

	public void setAvgRepetition(final Map<Integer, Float> avgRepetition)
	{
		this.avgRepetition = avgRepetition;
	}

	public void setContestsStats(final Map<Integer, RepeatContestStats> contestsStats)
	{
		this.contestsStats = contestsStats;
	}

	public void setCountRepetition(final Map<Integer, Integer> countRepetition)
	{
		this.countRepetition = countRepetition;
	}

	public void setMaxRepetition(final Map<Integer, Integer> maxRepetition)
	{
		this.maxRepetition = maxRepetition;
	}

}
