package com.megabot.winner.inteface.model;

import java.util.TreeSet;

public class Lotofacil extends Ticket
{
	public Lotofacil()
	{
		setType(TicketType.LOTOFACIL);
		setNumbers(new TreeSet<>());
	}
}
