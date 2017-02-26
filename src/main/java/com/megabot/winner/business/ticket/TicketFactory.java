package com.megabot.winner.business.ticket;

import static com.megabot.winner.support.Constraints.isNotEmpty;
import static com.megabot.winner.support.Constraints.isNotNull;
import static com.megabot.winner.support.model.FailCode.MSG_001;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.megabot.winner.inteface.model.TicketRequest;
import com.megabot.winner.inteface.model.TicketResponse;
import com.megabot.winner.inteface.model.TicketType;
import com.megabot.winner.support.exception.WinnerException;

@Component
public class TicketFactory
{
	@Autowired
	private Collection<TicketGenerator> generators;

	@Autowired
	private Collection<TicketLoader> loaders;

	public TicketResponse createTicket(final TicketRequest request) throws WinnerException
	{
		isNotNull(generators, MSG_001, request.getTicketType());
		TicketGenerator generator = findGenerator(request.getTicketType());
		isNotNull(generator, MSG_001, request.getTicketType());

		TicketResponse response = new TicketResponse();
		for (int i = 0; i < request.getAmountTickets(); i++)
		{
			response.addTicket(generator.create(request.getAmountTickets()));
		}

		return response;
	}

	public void loadData(final Collection<String> rawData, final TicketType type) throws WinnerException
	{
		isNotEmpty(loaders, MSG_001, rawData);
		TicketLoader loader = findLoader(type);
		isNotNull(loader, MSG_001, type);
		loader.load(rawData);
	}

	private TicketGenerator findGenerator(final TicketType type)
	{
		return generators.stream().filter(e -> e.isAssignbleTo(type)).findFirst().get();
	}

	private TicketLoader findLoader(final TicketType type)
	{
		return loaders.stream().filter(e -> e.isAssignbleTo(type)).findFirst().get();
	}
}
