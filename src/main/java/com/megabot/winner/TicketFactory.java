package com.megabot.winner;

import static com.megabot.winner.support.Constraints.isNotNull;
import static com.megabot.winner.support.model.FailCode.MSG_001;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.megabot.winner.model.TicketType;
import com.megabot.winner.model.TicketRequest;
import com.megabot.winner.model.TicketResponse;
import com.megabot.winner.support.exception.WinnerException;

@Component
public class TicketFactory {

	@Autowired
	private Collection<TicketGenerator> generators;

	public TicketResponse createGame(final TicketRequest request) throws WinnerException {

		isNotNull(generators, MSG_001, request.getTicketType());
		TicketGenerator generator = findGenerator(request.getTicketType());
		isNotNull(generator, MSG_001, request.getTicketType());

		TicketResponse response = new TicketResponse();
		for (int i = 0; i < request.getAmountTickets(); i++) {
			response.addTicket(generator.create(request.getAmountTickets()));
		}

		return response;
	}

	private TicketGenerator findGenerator(final TicketType type) {

		return generators.stream().filter(e -> e.isAssignbleTo(type)).findFirst().get();
	}
}
