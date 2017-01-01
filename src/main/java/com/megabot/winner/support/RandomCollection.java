package com.megabot.winner.support;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RandomCollection<E extends IWeightRandom>
{
	private final NavigableMap<Double, E> map = new TreeMap<>();
	private final Random random;
	private double total = 0;

	public RandomCollection()
	{
		this(new Random());
	}

	public RandomCollection(final Random random)
	{
		this.random = random;
	}

	public void add(final E result)
	{
		if (result.getWeight() <= 0)
		{
			return;
		}
		total += result.getWeight();
		map.put(total, result);
	}

	public E next()
	{
		double value = random.nextDouble() * total;
		return map.ceilingEntry(value).getValue();
	}
}