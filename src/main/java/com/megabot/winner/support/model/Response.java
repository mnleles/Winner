package com.megabot.winner.support.model;

import java.util.ArrayList;
import java.util.Collection;

public class Response
{
	private final Collection<MessageFail> fails = new ArrayList<>();

	public void addAllFail(final MessageFail fail)
	{
		this.fails.add(fail);
	}

	public void addAllFails(final Collection<MessageFail> fails)
	{
		this.fails.addAll(fails);
	}

	public Collection<MessageFail> getFails()
	{
		return fails;
	}

	public Boolean isSuccess()
	{
		return fails.isEmpty();
	}
}
