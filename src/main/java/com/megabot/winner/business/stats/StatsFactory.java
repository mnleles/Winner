package com.megabot.winner.business.stats;

import java.util.ArrayList;
import java.util.Collection;

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
	private Collection<StatsGenerator> generators;

	public void loadDefaultStats(final TicketType type, final Collection<Ticket> tickets) throws WinnerException
	{
		findDefaultGenerators(type).stream().forEach(g -> {
			g.generateStats(type, tickets);
		});
	}

	private Collection<StatsGenerator> findDefaultGenerators(final TicketType ticketType)
	{
		Collection<StatsGenerator> defaultGenerators = new ArrayList<>();

		for (StatsType statstype : StatsType.values())
		{
			if (statstype.isDefault())
			{
				defaultGenerators.add(findGenerator(ticketType));
			}
		}

		return defaultGenerators;
	}

	private StatsGenerator findGenerator(final TicketType type)
	{
		return generators.stream().filter(s -> s.isAssignbleTo(type)).findFirst().get();
	}
}
