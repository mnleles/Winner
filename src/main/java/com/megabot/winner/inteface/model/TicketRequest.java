package com.megabot.winner.inteface.model;

import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TicketRequest
{

	private Integer amountNumbers;
	private Integer amountTickets;
	private Collection<Integer> baseNumbers;
	private TicketType ticketType;

	public Integer getAmountNumbers()
	{
		return amountNumbers;
	}

	public Integer getAmountTickets()
	{
		return amountTickets;
	}

	public Collection<Integer> getBaseNumbers()
	{
		return baseNumbers;
	}

	public TicketType getTicketType()
	{
		return ticketType;
	}

	public void setAmountNumbers(final Integer amountNumbers)
	{
		this.amountNumbers = amountNumbers;
	}

	public void setAmountTickets(final Integer amountTickets)
	{
		this.amountTickets = amountTickets;
	}

	public void setBaseNumbers(final Collection<Integer> baseNumbers)
	{
		this.baseNumbers = baseNumbers;
	}

	public void setTicketType(final TicketType gameType)
	{
		this.ticketType = gameType;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}
}
