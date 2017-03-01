package com.megabot.winner.business.ticket.loaders;

import static com.megabot.winner.support.Constraints.*;
import static com.megabot.winner.support.model.FailCode.*;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.megabot.winner.inteface.model.Lotomania;
import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketType;
import com.megabot.winner.support.exception.WinnerException;

@Component
public class LotomaniaLoader extends AbstractLoader
{
	@Override
	public boolean isAssignbleTo(final TicketType type)
	{
		return TicketType.LOTOMANIA == type;
	}

	@Override
	protected Ticket toTicket(final String line) throws WinnerException
	{
		validateData(line);

		String[] data = line.split(";");
		Ticket lotomania = new Lotomania();
		lotomania.setId(UUID.randomUUID());
		lotomania.setContestId(Integer.parseInt(data[0]));
		lotomania.setDate(LocalDate.parse(data[1], getDatePattern()));
		for (int i = 2; i <= 21; i++)
		{
			lotomania.getNumbers().add(Integer.parseInt(data[i]));
		}
		lotomania.setWinningTicket(Integer.parseInt(data[22]) > 0);
		return lotomania;
	}

	@Override
	protected void validateData(final String line) throws WinnerException
	{
		isNotNull(line, MSG_000, "line");
	}
}
