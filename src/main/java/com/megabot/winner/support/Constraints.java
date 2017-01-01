package com.megabot.winner.support;

import static com.megabot.winner.support.model.MessageFail.*;

import java.util.Collection;

import com.megabot.winner.support.exception.WinnerException;
import com.megabot.winner.support.model.FailCode;

public final class Constraints
{
	private Constraints()
	{}

	public static <T> boolean isEmpty(final Collection<T> input, final FailCode msg, final Object... args) throws WinnerException
	{
		if (isNotNull(input, msg) && input.isEmpty())
		{
			return true;
		}

		throw new WinnerException(builder(msg, args));
	}

	public static <T> boolean isFalse(final boolean input, final FailCode msg, final Object... args) throws WinnerException
	{
		if (!input)
		{
			throw new WinnerException(builder(msg, args));
		}

		return true;
	}

	public static <T> boolean isNotEmpty(final Collection<T> input, final FailCode msg, final Object... args) throws WinnerException
	{
		if (isNotNull(input, msg) && input.isEmpty())
		{
			throw new WinnerException(builder(msg, args));
		}

		return true;
	}

	public static <T> boolean isNotNull(final T input, final FailCode msg, final Object... args) throws WinnerException
	{
		if (input == null)
		{
			throw new WinnerException(builder(msg, args));
		}

		return true;
	}

	public static <T> boolean isNull(final T input, final FailCode msg, final Object... args) throws WinnerException
	{
		if (input == null)
		{
			return true;
		}

		throw new WinnerException(builder(msg, args));
	}

	public static <T> boolean isTrue(final boolean input, final FailCode msg, final Object... args) throws WinnerException
	{
		if (input)
		{
			return true;
		}

		throw new WinnerException(builder(msg, args));
	}
}
