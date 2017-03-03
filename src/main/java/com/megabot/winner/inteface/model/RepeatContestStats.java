package com.megabot.winner.inteface.model;

import java.util.Map;

public class RepeatContestStats extends AbstractModel
{
	private Map<Integer, Integer> repeatNumbers;

	public RepeatContestStats()
	{}

	public RepeatContestStats(final Map<Integer, Integer> repeatNumbersInput)
	{
		this.repeatNumbers = repeatNumbersInput;
	}

	public Map<Integer, Integer> getRepeatNumbers()
	{
		return repeatNumbers;
	}

	public void setRepeatNumbers(final Map<Integer, Integer> repeatNumbers)
	{
		this.repeatNumbers = repeatNumbers;
	}

}
