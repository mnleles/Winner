package com.megabot.winner.inteface.model;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.megabot.winner.support.model.Response;

public class StatsResponse extends Response
{
	private final Collection<Stats> statsList = new ArrayList<>();

	public void addTicket(final Stats stats)
	{
		statsList.add(stats);
	}

	public Collection<Stats> getStatsList()
	{
		return statsList;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}

}
