package com.megabot.winner.business.stats.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.megabot.winner.inteface.model.RepeatContestStats;
import com.megabot.winner.inteface.model.RepeatNumbersStats;
import com.megabot.winner.inteface.model.StatsType;
import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketType;
import com.megabot.winner.repository.StatsRepository;

@Component
public class BaseRepeatStatsBuilder extends BaseStatsBuilder
{
	@Autowired
	private StatsRepository statsRepository;

	@Override
	public void build(final TicketType type, final Collection<Ticket> tickets)
	{
		Map<Integer, Integer> maxRepeat = loadNumbersWithIntValue(type);
		Map<Integer, Integer> countRepeat = loadNumbersWithIntValue(type);
		Map<Integer, Float> avgRepeat = loadNumbersWithFloatValue(type);
		Map<Integer, RepeatContestStats> contestsStats = new HashMap<>();

		for (int i = 0; i < tickets.size(); i++)
		{
			Ticket current = ((List<Ticket>) tickets).get(i);
			if (i == 0)
			{
				contestsStats.put(current.getContestId(), new RepeatContestStats(loadNumbersWithIntValue(type)));
				continue;
			}

			Map<Integer, Integer> currentContest = calculateRepetitionContestStats(current, contestsStats.get(current.getContestId() - 1));
			contestsStats.put(current.getContestId(), new RepeatContestStats(currentContest));
			populateMaxRepeat(maxRepeat, currentContest);
			populateAvgRepetition(avgRepeat, currentContest);
			populateCountRepeat(countRepeat, currentContest);
		}

		RepeatNumbersStats repeat = new RepeatNumbersStats();
		repeat.setId(UUID.randomUUID());
		repeat.setTicketType(type);
		repeat.setStartDate(tickets.iterator().next().getDate());
		repeat.setEndDate(((List<Ticket>) tickets).get(tickets.size() - 1).getDate());
		repeat.setAvgNumbersBetweenContests(calculateRepetitionBetweenContests(tickets));
		repeat.setAvgRepetition(calculatAvg(countRepeat, avgRepeat));
		repeat.setCountRepetition(countRepeat);
		repeat.setMaxRepetition(maxRepeat);
		repeat.setContestsStats(contestsStats);
		statsRepository.save(repeat);
	}

	@Override
	public boolean isAssignbleTo(final TicketType type)
	{
		return Arrays.asList(TicketType.values()).contains(type);
	}

	@Override
	public boolean isStatsAssignbleTo(final StatsType type)
	{
		return StatsType.REPEAT_NUMBERS == type;
	}

	private Map<Integer, Float> calculatAvg(final Map<Integer, Integer> countRepeat, final Map<Integer, Float> avgRepeat)
	{
		// Calculate avg after get all data
		avgRepeat.entrySet().stream().forEach(entry -> {
			entry.setValue(entry.getValue() / countRepeat.get(entry.getKey()));
		});

		return avgRepeat;
	}

	private float calculateRepetitionBetweenContests(final Collection<Ticket> tickets)
	{
		int sum = 0;
		for (int i = 1; i < tickets.size(); i++)
		{
			Ticket current = ((List<Ticket>) tickets).get(i);
			Ticket latest = ((List<Ticket>) tickets).get(i - 1);
			sum += CollectionUtils.intersection(current.getNumbers(), latest.getNumbers()).size();
		}

		float avgRepeatition = sum / (tickets.size() - 1);
		return avgRepeatition;
	}

	private Map<Integer, Integer> calculateRepetitionContestStats(final Ticket current, final RepeatContestStats latestRepetition)
	{
		// Load all numbers
		Map<Integer, Integer> contestStats = new HashMap<>();
		latestRepetition.getRepeatNumbers().entrySet().stream().forEach(entry -> {

			Integer n = entry.getKey();
			contestStats.put(n, current.getNumbers().contains(n) ? entry.getValue() + 1 : 0);
		});

		return contestStats;
	}

	private void populateAvgRepetition(final Map<Integer, Float> avgRepeat, final Map<Integer, Integer> currentContest)
	{
		currentContest.entrySet().stream().forEach(entry -> {

			Integer n = entry.getKey();
			avgRepeat.put(n, avgRepeat.get(n) + entry.getValue());
		});
	}

	private void populateCountRepeat(final Map<Integer, Integer> countRepeat, final Map<Integer, Integer> currentContest)
	{
		currentContest.entrySet().stream().forEach(entry -> {

			Integer n = entry.getKey();
			countRepeat.put(n, entry.getValue() > 0 ? countRepeat.get(n) + 1 : countRepeat.get(n));
		});

	}

	private void populateMaxRepeat(final Map<Integer, Integer> maxRepeat, final Map<Integer, Integer> currentContest)
	{
		currentContest.entrySet().stream().forEach(entry -> {

			Integer n = entry.getKey();
			maxRepeat.put(n, entry.getValue() > maxRepeat.get(n) ? entry.getValue() : maxRepeat.get(n));
		});
	}

}
