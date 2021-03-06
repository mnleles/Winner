package com.megabot.winner.business.ticket.loaders;

import static com.megabot.winner.support.Constraints.*;
import static com.megabot.winner.support.model.FailCode.*;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.megabot.winner.inteface.model.Lotofacil;
import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketType;
import com.megabot.winner.support.exception.WinnerException;

@Component
public class LotofacilLoader extends AbstractLoader
{
	@Override
	public boolean isAssignbleTo(final TicketType type)
	{
		return TicketType.LOTOFACIL == type;
	}

	@Override
	protected Ticket toTicket(final String line) throws WinnerException
	{
		validateData(line);

		String[] data = line.split(";");
		Ticket lotofacil = new Lotofacil();
		lotofacil.setId(UUID.randomUUID());
		lotofacil.setContestId(Integer.parseInt(data[0]));
		lotofacil.setDate(LocalDate.parse(data[1], getDatePattern()));
		for (int i = 2; i <= 16; i++)
		{
			lotofacil.getNumbers().add(Integer.parseInt(data[i]));
		}
		lotofacil.setWinningTicket(Integer.parseInt(data[17]) > 0);
		return lotofacil;
	}

	@Override
	protected void validateData(final String line) throws WinnerException
	{
		isNotNull(line, MSG_000, "line");
	}
}
