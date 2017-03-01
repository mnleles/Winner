package com.megabot.winner.business.stats.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.megabot.winner.business.stats.StatsBuilder;
import com.megabot.winner.inteface.model.DelayContestStats;
import com.megabot.winner.inteface.model.DelayNumbersStats;
import com.megabot.winner.inteface.model.StatsType;
import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketType;
import com.megabot.winner.repository.StatsRepository;

public abstract class AbstractDelayStats implements StatsBuilder
{
	@Autowired
	private StatsRepository statsRepository;

	@Override
	public void build(final TicketType type, final Collection<Ticket> tickets)
	{
		int maxTicketNumber = type.maxNumber;
		Map<Integer, Integer> maxDelay = getNewDelayNumbers(maxTicketNumber);
		Map<Integer, Integer> countDelay = getNewDelayNumbers(maxTicketNumber);
		Map<Integer, Float> avgDelay = getNewAvgDelayNumbers(maxTicketNumber);
		Map<Integer, DelayContestStats> contestsStats = new HashMap<>();

		for (int i = 0; i < tickets.size(); i++)
		{
			Ticket current = ((List<Ticket>) tickets).get(i);
			if (i == 0)
			{
				contestsStats.put(current.getContestId(), new DelayContestStats(getNewDelayNumbers(maxTicketNumber)));
				continue;
			}

			Map<Integer, Integer> currentContest = getContestStats(current, contestsStats.get(current.getContestId() - 1));
			contestsStats.put(current.getContestId(), new DelayContestStats(currentContest));
			populateMaxDelay(maxDelay, currentContest);
			populateAvgDelay(avgDelay, currentContest);
			populateCountDelay(countDelay, currentContest);
		}

		// Calculate avg after get all data
		avgDelay.entrySet().stream().forEach(entry -> {
			entry.setValue(entry.getValue() / countDelay.get(entry.getKey()));
		});

		DelayNumbersStats delay = new DelayNumbersStats();
		delay.setId(UUID.randomUUID());
		delay.setType(type);
		delay.setStartDate(tickets.iterator().next().getDate());
		delay.setEndDate(((List<Ticket>) tickets).get(tickets.size() - 1).getDate());
		delay.setAvgDelay(avgDelay);
		delay.setCountDelay(countDelay);
		delay.setMaxDelay(maxDelay);
		delay.setContestsStats(contestsStats);
		statsRepository.save(delay);
	}

	@Override
	public boolean isStatsAssignbleTo(final StatsType type)
	{
		return StatsType.DELAY_NUMBERS == type;
	}

	private Map<Integer, Integer> getContestStats(final Ticket current, final DelayContestStats latestDelay)
	{
		// Load all numbers
		Map<Integer, Integer> contestStats = new HashMap<>();
		latestDelay.getDelayNumbers().entrySet().stream().forEach(entry -> {

			Integer n = entry.getKey();
			contestStats.put(n, current.getNumbers().contains(n) ? 0 : entry.getValue() + 1);
		});

		return contestStats;
	}

	private Map<Integer, Float> getNewAvgDelayNumbers(final Integer maxNumber)
	{
		Map<Integer, Float> delayNumbers = new HashMap<>();
		for (int i = 1; i <= maxNumber; i++)
		{
			delayNumbers.put(i, 0F);
		}

		return delayNumbers;
	}
	private Map<Integer, Integer> getNewDelayNumbers(final Integer maxNumber)
	{
		Map<Integer, Integer> delayNumbers = new HashMap<>();
		for (int i = 1; i <= maxNumber; i++)
		{
			delayNumbers.put(i, 0);
		}

		return delayNumbers;
	}

	private void populateAvgDelay(final Map<Integer, Float> avgDelay, final Map<Integer, Integer> currentContest)
	{
		currentContest.entrySet().stream().forEach(entry -> {

			Integer n = entry.getKey();
			avgDelay.put(n, avgDelay.get(n) + entry.getValue());
		});
	}

	private void populateCountDelay(final Map<Integer, Integer> countDelay, final Map<Integer, Integer> currentContest)
	{
		currentContest.entrySet().stream().forEach(entry -> {

			Integer n = entry.getKey();
			countDelay.put(n, entry.getValue() > 0 ? countDelay.get(n) + 1 : countDelay.get(n));
		});
	}

	private void populateMaxDelay(final Map<Integer, Integer> maxDelay, final Map<Integer, Integer> currentContest)
	{
		currentContest.entrySet().stream().forEach(entry -> {

			Integer n = entry.getKey();
			maxDelay.put(n, entry.getValue() > maxDelay.get(n) ? entry.getValue() : maxDelay.get(n));
		});
	}

}
