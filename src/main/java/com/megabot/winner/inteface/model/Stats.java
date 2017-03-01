package com.megabot.winner.inteface.model;

import java.time.LocalDate;

public class Stats extends AbstractModel
{
	private LocalDate endDate;
	private LocalDate startDate;
	private TicketType type;

	public LocalDate getEndDate()
	{
		return endDate;
	}

	public LocalDate getStartDate()
	{
		return startDate;
	}

	public TicketType getType()
	{
		return type;
	}

	public void setEndDate(final LocalDate endDate)
	{
		this.endDate = endDate;
	}

	public void setStartDate(final LocalDate startDate)
	{
		this.startDate = startDate;
	}

	public void setType(final TicketType type)
	{
		this.type = type;
	}

}
