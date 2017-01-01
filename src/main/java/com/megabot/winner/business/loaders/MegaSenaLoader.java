package com.megabot.winner.business.loaders;

import static com.megabot.winner.support.Constraints.*;
import static com.megabot.winner.support.model.FailCode.*;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.megabot.winner.inteface.model.MegaSena;
import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketType;
import com.megabot.winner.support.exception.WinnerException;

@Component
public class MegaSenaLoader extends AbstractLoader
{
	@Override
	public boolean isAssignbleTo(final TicketType type)
	{
		return TicketType.MEGA_SENA == type;
	}

	@Override
	protected Ticket toTicket(final String line)
	{
		String[] data = line.split(";");
		Ticket megaSena = new MegaSena();
		megaSena.setId(data[0]);
		megaSena.setDate(LocalDate.parse(data[1], getDatePattern()));
		megaSena.getNumbers().add(Integer.parseInt(data[2]));
		megaSena.getNumbers().add(Integer.parseInt(data[3]));
		megaSena.getNumbers().add(Integer.parseInt(data[4]));
		megaSena.getNumbers().add(Integer.parseInt(data[5]));
		megaSena.getNumbers().add(Integer.parseInt(data[6]));
		megaSena.getNumbers().add(Integer.parseInt(data[7]));
		megaSena.setWinningTicket(Integer.parseInt(data[8]) > 0);
		return megaSena;
	}

	@Override
	protected void validateData(final String line) throws WinnerException
	{
		isNotNull(line, MSG_000, "line");
	}
}
