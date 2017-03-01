package com.megabot.winner.controller;

import static com.megabot.winner.support.model.FailCode.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.megabot.winner.business.stats.StatsFactory;
import com.megabot.winner.inteface.model.StatsType;
import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketType;
import com.megabot.winner.repository.TicketRepository;
import com.megabot.winner.support.exception.WinnerException;
import com.megabot.winner.support.model.MessageFail;
import com.megabot.winner.support.model.Response;

@RestController
@RequestMapping("stats")
public class StatsController
{
	@Autowired
	private StatsFactory statsFactory;

	@Autowired
	private TicketRepository ticketRepository;

	@GetMapping("load/{stats}/{type}")
	@ResponseBody
	public Response load(@PathVariable(value = "stats") final StatsType statsType, @PathVariable(value = "type") final TicketType type)
	{
		Response response = new Response();

		try
		{
			Ticket ticket = new Ticket();
			ticket.setType(type);
			statsFactory.loadStats(type, statsType, ticketRepository.findAll(Example.of(ticket)));

		} catch (WinnerException we)
		{
			response.addAllFails(we.getMessages());
		} catch (Exception e)
		{
			response.addAllFail(MessageFail.builder(SYS_ERROR));
		}
		return response;
	}

	@GetMapping("load/default/{type}")
	@ResponseBody
	public Response load(@PathVariable(value = "type") final TicketType type)
	{
		Response response = new Response();

		try
		{
			Ticket ticket = new Ticket();
			ticket.setType(type);
			statsFactory.loadStats(type, ticketRepository.findAll(Example.of(ticket)));

		} catch (WinnerException we)
		{
			response.addAllFails(we.getMessages());
		} catch (Exception e)
		{
			response.addAllFail(MessageFail.builder(SYS_ERROR));
		}
		return response;
	}

}
