package com.megabot.winner.business.ticket.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.megabot.winner.business.ticket.TicketBuilder;
import com.megabot.winner.inteface.model.DelayNumbersStats;
import com.megabot.winner.inteface.model.RepeatNumbersStats;
import com.megabot.winner.inteface.model.StatsType;
import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketRequest;
import com.megabot.winner.inteface.model.TicketType;
import com.megabot.winner.repository.StatsRepository;
import com.megabot.winner.support.Combinations;

@Component
public class LotomaniaTicketBuilder implements TicketBuilder
{
	@Resource
	private StatsRepository statsRepository;

	@Override
	@SuppressWarnings("unchecked")
	public Collection<Ticket> build(final TicketRequest request)
	{
		DelayNumbersStats delayStats = fetchDelayStats(request.getTicketType());
		RepeatNumbersStats repeatStats = fetchRepeatStats(request.getTicketType());

		Map<Integer, Integer> delayNumbersLatest = delayStats.getContestStatsLatest().getDelayNumbers();
		Collection<Integer> numbersLatest = delayNumbersLatest.entrySet().stream().filter(e -> e.getValue() == 0).map(e -> e.getKey()).collect(Collectors.toList());

		Map<Integer, Integer> repetitionNumbersLatest = repeatStats.getContestStatsLatest().getRepeatNumbers();
		List<Integer> numbersLatestSorted = repetitionNumbersLatest.entrySet().stream().filter(e -> numbersLatest.contains(e.getKey())).sorted(getRepetitionNumbersComparator())
				.map(e -> e.getKey()).collect(Collectors.toList());

		List<Integer> otherNumbersLatestSorted = delayNumbersLatest.entrySet().stream().filter(e -> !numbersLatest.contains(e.getKey())).sorted(getDelayNumbersLatestComparator())
				.map(e -> e.getKey()).collect(Collectors.toList());

		Map<String, List<Integer>> groups = new HashMap<>();
		groups.put("1", joinLists(numbersLatestSorted.subList(0, 5), otherNumbersLatestSorted.subList(0, 20)));
		groups.put("2", joinLists(numbersLatestSorted.subList(5, 10), otherNumbersLatestSorted.subList(10, 30)));
		groups.put("3", joinLists(numbersLatestSorted.subList(10, 15), otherNumbersLatestSorted.subList(20, 40)));
		groups.put("4", joinLists(numbersLatestSorted.subList(15, 20), otherNumbersLatestSorted.subList(30, 50)));

		List<String[]> combinations = Combinations.generate(groups.keySet().toArray(new String[groups.size()]), 2);
		List<Ticket> tickets = new ArrayList<>();
		for (String[] c : combinations)
		{
			Set<Integer> numbers = new TreeSet<>(joinLists(groups.get(c[0]), groups.get(c[1])));

			if (numbers.size() < request.getTicketType().amountNumbers)
			{
				continue;
			}

			Ticket ticket = new Ticket();
			ticket.setType(request.getTicketType());
			ticket.setNumbers(numbers);
			tickets.add(ticket);
		}
		return tickets;
	}

	@Override
	public boolean isAssignbleTo(final TicketType type)
	{
		return TicketType.LOTOMANIA == type;
	}

	private DelayNumbersStats fetchDelayStats(final TicketType ticketType)
	{
		DelayNumbersStats delayStats = new DelayNumbersStats();
		delayStats.setTicketType(ticketType);
		delayStats.setStatsType(StatsType.DELAY_NUMBERS);
		return statsRepository.findOne(Example.of(delayStats));
	}

	private RepeatNumbersStats fetchRepeatStats(final TicketType ticketType)
	{
		RepeatNumbersStats repeatStats = new RepeatNumbersStats();
		repeatStats.setTicketType(ticketType);
		repeatStats.setStatsType(StatsType.REPEAT_NUMBERS);
		return statsRepository.findOne(Example.of(repeatStats));
	}

	private Comparator<Entry<Integer, Integer>> getDelayNumbersLatestComparator()
	{
		return (input1, input2) -> Integer.compare(input1.getValue(), input2.getValue());
	}

	private Comparator<Entry<Integer, Integer>> getRepetitionNumbersComparator()
	{
		return (input1, input2) -> Integer.compare(input1.getValue(), input2.getValue());
	}

	@SuppressWarnings("unchecked")
	private List<Integer> joinLists(final List<Integer>... lists)
	{

		List<Integer> allLists = new ArrayList<>();
		for (List<Integer> list : lists)
		{
			allLists.addAll(list);
		}

		return allLists;
	}

}
