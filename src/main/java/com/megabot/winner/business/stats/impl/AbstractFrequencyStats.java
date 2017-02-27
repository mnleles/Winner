package com.megabot.winner.business.stats.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.megabot.winner.business.stats.StatsGenerator;
import com.megabot.winner.inteface.model.FrequencyNumbersStats;
import com.megabot.winner.inteface.model.StatsType;
import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketType;
import com.megabot.winner.repository.StatsRepository;

public abstract class AbstractFrequencyStats implements StatsGenerator
{
	@Autowired
	private StatsRepository statsRepository;

	@Override
	public void generateStats(final TicketType type, final Collection<Ticket> tickets)
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
		frequencyStats.setType(type);
		frequencyStats.setFrequency(frequency);
		statsRepository.save(frequencyStats);
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