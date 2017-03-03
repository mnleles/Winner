package com.megabot.winner.business.stats.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.megabot.winner.business.stats.StatsBuilder;
import com.megabot.winner.inteface.model.FrequencyNumbersStats;
import com.megabot.winner.inteface.model.StatsType;
import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketType;
import com.megabot.winner.repository.StatsRepository;

@Component
public class BaseFrequencyStatsBuilder implements StatsBuilder
{
	@Autowired
	private StatsRepository statsRepository;

	@Override
	public void build(final TicketType type, final Collection<Ticket> tickets)
	{
		Map<Integer, Integer> frequency = new HashMap<>();
		tickets.stream().forEach(t -> {

			if (t.getNumbers() == null)
			{
				return;
			}

			populate(frequency, t.getNumbers());
		});

		FrequencyNumbersStats frequencyStats = new FrequencyNumbersStats();
		frequencyStats.setId(UUID.randomUUID());
		frequencyStats.setTicketType(type);
		frequencyStats.setStartDate(tickets.iterator().next().getDate());
		frequencyStats.setEndDate(((List<Ticket>) tickets).get(tickets.size() - 1).getDate());
		frequencyStats.setFrequency(frequency);
		statsRepository.save(frequencyStats);
	}
	@Override
	public boolean isAssignbleTo(final TicketType type)
	{
		return Arrays.asList(TicketType.values()).contains(type);
	}

	@Override
	public boolean isStatsAssignbleTo(final StatsType type)
	{
		return StatsType.FREQUENCY_NUMBERS == type;
	}

	protected void populate(final Map<Integer, Integer> frequency, final Set<Integer> numbers)
	{
		numbers.stream().forEach(n -> {

			if (n == null)
			{
				return;
			}

			frequency.put(n, frequency.get(n) == null ? 1 : frequency.get(n) + 1);
		});
	}
}
