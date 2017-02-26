package com.megabot.winner.business.loaders;

import java.time.format.DateTimeFormatter;
import java.util.Collection;

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

		rawData.stream().forEach(t -> {

			try
			{
				saveTicket(t);

			} catch (WinnerException e)
			{
				exception.addMessages(e.getMessages());
			}

		});

		if (!exception.getMessages().isEmpty())
		{
			throw exception;
		}
	}

	protected void saveTicket(final String ticketRawData) throws WinnerException
	{
		validateData(ticketRawData);
		ticketRepository.save(toTicket(ticketRawData));
	}

	protected abstract Ticket toTicket(String line);

	protected abstract void validateData(final String line) throws WinnerException;

	DateTimeFormatter getDatePattern()
	{
		return DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}
}
