package com.megabot.winner.business.ticket;

import static com.megabot.winner.support.Constraints.*;
import static com.megabot.winner.support.model.FailCode.*;

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
	private Collection<TicketBuilder> generators;

	@Autowired
	private Collection<TicketLoader> loaders;

	public TicketResponse createTicket(final TicketRequest request) throws WinnerException
	{
		isNotNull(generators, MSG_001, request.getTicketType());
		TicketBuilder generator = findGenerator(request.getTicketType());
		isNotNull(generator, MSG_001, request.getTicketType());

		TicketResponse response = new TicketResponse();
		response.addTickets(generator.build(request));
		return response;
	}

	public void loadData(final Collection<String> rawData, final TicketType type) throws WinnerException
	{
		isNotEmpty(loaders, MSG_001, rawData);
		TicketLoader loader = findLoader(type);
		isNotNull(loader, MSG_001, type);
		loader.load(rawData);
	}

	private TicketBuilder findGenerator(final TicketType type)
	{
		return generators.stream().filter(e -> e.isAssignbleTo(type)).findFirst().get();
	}

	private TicketLoader findLoader(final TicketType type)
	{
		return loaders.stream().filter(e -> e.isAssignbleTo(type)).findFirst().get();
	}
}
