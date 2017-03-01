package com.megabot.winner.inteface.model;

import java.util.TreeSet;

public class Lotomania extends Ticket
{
	public Lotomania()
	{
		setType(TicketType.LOTOMANIA);
		setNumbers(new TreeSet<>());
	}
}
