package com.megabot.winner.inteface.model;

import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;

public abstract class AbstractModel
{
	@Id
	public UUID id;

	public UUID getId()
	{
		return id;
	}

	public void setId(final UUID id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}
}