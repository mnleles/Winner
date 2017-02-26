package com.megabot.winner.business.loaders;

import static com.megabot.winner.support.Constraints.isNotNull;
import static com.megabot.winner.support.model.FailCode.MSG_000;

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
	protected Ticket toTicket(final String line)
	{
		String[] data = line.split(";");
		Ticket lotofacil = new Lotofacil();
		lotofacil.setId(UUID.randomUUID());
		lotofacil.setContestId(Integer.parseInt(data[0]));
		lotofacil.setDate(LocalDate.parse(data[1], getDatePattern()));
		lotofacil.getNumbers().add(Integer.parseInt(data[2]));
		lotofacil.getNumbers().add(Integer.parseInt(data[3]));
		lotofacil.getNumbers().add(Integer.parseInt(data[4]));
		lotofacil.getNumbers().add(Integer.parseInt(data[5]));
		lotofacil.getNumbers().add(Integer.parseInt(data[6]));
		lotofacil.getNumbers().add(Integer.parseInt(data[7]));
		lotofacil.getNumbers().add(Integer.parseInt(data[8]));
		lotofacil.getNumbers().add(Integer.parseInt(data[9]));
		lotofacil.getNumbers().add(Integer.parseInt(data[10]));
		lotofacil.getNumbers().add(Integer.parseInt(data[11]));
		lotofacil.getNumbers().add(Integer.parseInt(data[12]));
		lotofacil.getNumbers().add(Integer.parseInt(data[13]));
		lotofacil.getNumbers().add(Integer.parseInt(data[14]));
		lotofacil.getNumbers().add(Integer.parseInt(data[15]));
		lotofacil.getNumbers().add(Integer.parseInt(data[16]));
		lotofacil.setWinningTicket(Integer.parseInt(data[17]) > 0);
		return lotofacil;
	}

	@Override
	protected void validateData(final String line) throws WinnerException
	{
		isNotNull(line, MSG_000, "line");
	}
}
