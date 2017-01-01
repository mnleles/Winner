package com.megabot.winner.inteface.model;

import java.util.TreeSet;

public class MegaSena extends Ticket
{
	public MegaSena()
	{
		setType(TicketType.MEGA_SENA);
		setNumbers(new TreeSet<>());
	}
}
