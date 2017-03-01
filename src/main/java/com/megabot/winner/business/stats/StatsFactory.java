package com.megabot.winner.business.stats;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.megabot.winner.inteface.model.StatsType;
import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketType;
import com.megabot.winner.support.exception.WinnerException;

@Component
public class StatsFactory
{
	@Autowired
	private Collection<StatsBuilder> generators;

	public void loadStats(final TicketType type, final Collection<Ticket> tickets) throws WinnerException
	{
		findDefaultGenerators(type, null).stream().forEach(g -> {
			g.build(type, tickets);
		});
	}
	public void loadStats(final TicketType type, final StatsType statsType, final Collection<Ticket> tickets) throws WinnerException
	{
		findGenerators(type, statsType).stream().forEach(g -> {
			g.build(type, tickets);
		});
	}

	private Collection<StatsBuilder> findDefaultGenerators(final TicketType ticketType, final StatsType statsType)
	{
		Collection<StatsBuilder> defaultGenerators = new ArrayList<>();

		for (StatsType statstype : StatsType.values())
		{
			if (statstype.isDefault())
			{
				defaultGenerators.addAll(findGenerators(ticketType, statsType));
			}
		}

		return defaultGenerators;
	}

	private Collection<StatsBuilder> findGenerators(final TicketType type, final StatsType statsType)
	{
		return generators.stream().filter(s -> s.isAssignbleTo(type) && statsType == null ? true : s.isStatsAssignbleTo(statsType)).collect(Collectors.toList());
	}
}
