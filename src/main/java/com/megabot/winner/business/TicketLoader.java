package com.megabot.winner.business;

import java.util.Collection;

import com.megabot.winner.support.exception.WinnerException;

public interface TicketLoader extends IAssignble
{
	void load(Collection<String> rawData) throws WinnerException;
}
