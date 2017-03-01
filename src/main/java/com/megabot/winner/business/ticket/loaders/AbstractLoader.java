package com.megabot.winner.business.ticket.loaders;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.megabot.winner.business.ticket.TicketLoader;
import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.repository.TicketRepository;
import com.megabot.winner.support.exception.WinnerException;

public abstract class AbstractLoader implements TicketLoader
{
	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public void load(final Collection<String> rawData) throws WinnerException
	{
		WinnerException exception = new WinnerException();
		List<Ticket> tickets = rawData.stream().map(t -> {

			try
			{
				return toTicket(t);

			} catch (WinnerException e)
			{
				exception.addMessages(e.getMessages());
			}
			return null;

		}).collect(Collectors.toList());

		if (!exception.getMessages().isEmpty())
		{
			throw exception;
		}

		ticketRepository.save(tickets);

	}

	protected abstract Ticket toTicket(String line) throws WinnerException;

	protected abstract void validateData(final String line) throws WinnerException;

	DateTimeFormatter getDatePattern()
	{
		return DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}
}
