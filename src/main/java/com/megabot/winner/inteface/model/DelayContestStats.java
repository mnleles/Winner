package com.megabot.winner.inteface.model;

import java.util.Map;

public class DelayContestStats extends AbstractModel
{
	private Map<Integer, Integer> delayNumbers;

	public DelayContestStats()
	{}

	public DelayContestStats(final Map<Integer, Integer> delayNumbersInput)
	{
		this.delayNumbers = delayNumbersInput;
	}

	public Map<Integer, Integer> getDelayNumbers()
	{
		return delayNumbers;
	}

	public void setDelayNumbers(final Map<Integer, Integer> delayNumbers)
	{
		this.delayNumbers = delayNumbers;
	}

}
