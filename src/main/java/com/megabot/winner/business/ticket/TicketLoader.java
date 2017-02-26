package com.megabot.winner.business.ticket;

import java.util.Collection;

import com.megabot.winner.support.exception.WinnerException;

public interface TicketLoader extends ITicketAssignble
{
	void load(Collection<String> rawData) throws WinnerException;
}
