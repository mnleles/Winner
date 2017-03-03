package com.megabot.winner.inteface.model;

import java.time.LocalDate;

public class Stats extends AbstractModel
{
	private LocalDate endDate;
	private LocalDate startDate;
	private StatsType statsType;
	private TicketType ticketType;

	public LocalDate getEndDate()
	{
		return endDate;
	}

	public LocalDate getStartDate()
	{
		return startDate;
	}

	public StatsType getStatsType()
	{
		return statsType;
	}

	public TicketType getTicketType()
	{
		return ticketType;
	}

	public void setEndDate(final LocalDate endDate)
	{
		this.endDate = endDate;
	}

	public void setStartDate(final LocalDate startDate)
	{
		this.startDate = startDate;
	}

	public void setStatsType(final StatsType statsType)
	{
		this.statsType = statsType;
	}

	public void setTicketType(final TicketType type)
	{
		this.ticketType = type;
	}

}
