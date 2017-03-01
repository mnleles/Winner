package com.megabot.winner.business.stats.impl;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.megabot.winner.business.stats.StatsBuilder;
import com.megabot.winner.inteface.model.RepeatNumbersStats;
import com.megabot.winner.inteface.model.StatsType;
import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketType;
import com.megabot.winner.repository.StatsRepository;

public abstract class AbstractRepeatStats implements StatsBuilder
{
	@Autowired
	private StatsRepository statsRepository;

	@Override
	public void build(final TicketType type, final Collection<Ticket> tickets)
	{
		int sum = 0;
		for (int i = 1; i < tickets.size(); i++)
		{
			Ticket current = ((List<Ticket>) tickets).get(i);
			Ticket latest = ((List<Ticket>) tickets).get(i - 1);
			sum += CollectionUtils.intersection(current.getNumbers(), latest.getNumbers()).size();
		}

		float avgRepeatition = sum / (tickets.size() - 1);

		RepeatNumbersStats repeat = new RepeatNumbersStats();
		repeat.setId(UUID.randomUUID());
		repeat.setType(type);
		repeat.setStartDate(tickets.iterator().next().getDate());
		repeat.setEndDate(((List<Ticket>) tickets).get(tickets.size() - 1).getDate());
		repeat.setAvgRepetition(avgRepeatition);
		statsRepository.save(repeat);
	}

	@Override
	public boolean isStatsAssignbleTo(final StatsType type)
	{
		return StatsType.REPEAT_NUMBERS == type;
	}

}
