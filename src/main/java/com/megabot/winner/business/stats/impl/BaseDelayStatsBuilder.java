package com.megabot.winner.business.stats.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.megabot.winner.inteface.model.DelayContestStats;
import com.megabot.winner.inteface.model.DelayNumbersStats;
import com.megabot.winner.inteface.model.StatsType;
import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketType;
import com.megabot.winner.repository.StatsRepository;

@Component
public class BaseDelayStatsBuilder extends BaseStatsBuilder
{
	@Autowired
	private StatsRepository statsRepository;

	@Override
	public void build(final TicketType type, final Collection<Ticket> tickets)
	{
		Map<Integer, Integer> maxDelay = loadNumbersWithIntValue(type);
		Map<Integer, Integer> countDelay = loadNumbersWithIntValue(type);
		Map<Integer, Float> avgDelay = loadNumbersWithFloatValue(type);
		Map<Integer, DelayContestStats> contestsStats = new HashMap<>();

		for (int i = 0; i < tickets.size(); i++)
		{
			Ticket current = ((List<Ticket>) tickets).get(i);
			if (i == 0)
			{
				contestsStats.put(current.getContestId(), new DelayContestStats(loadNumbersWithIntValue(type)));
				continue;
			}

			Map<Integer, Integer> currentContest = calculateDelayContestStats(current, contestsStats.get(current.getContestId() - 1));
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
		delay.setTicketType(type);
		delay.setStartDate(tickets.iterator().next().getDate());
		delay.setEndDate(((List<Ticket>) tickets).get(tickets.size() - 1).getDate());
		delay.setAvgDelay(avgDelay);
		delay.setCountDelay(countDelay);
		delay.setMaxDelay(maxDelay);
		delay.setContestsStats(contestsStats);
		statsRepository.save(delay);
	}

	@Override
	public boolean isAssignbleTo(final TicketType type)
	{
		return Arrays.asList(TicketType.values()).contains(type);
	}

	@Override
	public boolean isStatsAssignbleTo(final StatsType type)
	{
		return StatsType.DELAY_NUMBERS == type;
	}

	private Map<Integer, Integer> calculateDelayContestStats(final Ticket current, final DelayContestStats latestDelay)
	{
		// Load all numbers
		Map<Integer, Integer> contestStats = new HashMap<>();
		latestDelay.getDelayNumbers().entrySet().stream().forEach(entry -> {

			Integer n = entry.getKey();
			contestStats.put(n, current.getNumbers().contains(n) ? 0 : entry.getValue() + 1);
		});

		return contestStats;
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
