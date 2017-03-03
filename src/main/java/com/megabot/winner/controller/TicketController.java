package com.megabot.winner.controller;

import static com.megabot.winner.support.model.FailCode.*;

import java.io.File;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.megabot.winner.business.ticket.TicketFactory;
import com.megabot.winner.inteface.model.Ticket;
import com.megabot.winner.inteface.model.TicketRequest;
import com.megabot.winner.inteface.model.TicketResponse;
import com.megabot.winner.inteface.model.TicketType;
import com.megabot.winner.repository.TicketRepository;
import com.megabot.winner.support.exception.WinnerException;
import com.megabot.winner.support.model.MessageFail;
import com.megabot.winner.support.model.Response;

@RestController
@RequestMapping("ticket")
public class TicketController
{
	@Resource
	private TicketFactory ticketFactory;

	@Resource
	private TicketRepository ticketRepository;

	@GetMapping("create/lotofacil")
	@ResponseBody
	public Response createLotofacilTickets()
	{
		TicketResponse response = new TicketResponse();

		try
		{
			TicketRequest request = new TicketRequest();
			request.setAmountNumbers(15);
			request.setTicketType(TicketType.LOTOFACIL);
			response = ticketFactory.createTicket(request);

		} catch (WinnerException we)
		{
			response.addAllFails(we.getMessages());
		}
		return response;
	}
	@GetMapping("fetchById")
	@ResponseBody
	public Response fetchById(@RequestParam("id") final String id)
	{

		TicketResponse response = new TicketResponse();
		response.addTicket(ticketRepository.findOne(UUID.fromString(id)));
		return response;
	}

	@GetMapping("fetchByType/{type}/{page}/{pageSize}")
	@ResponseBody
	public Response fetchByType(@PathVariable(value = "type") final TicketType type, @PathVariable(value = "page", required = false) final Integer page,
			@PathVariable(value = "pageSize", required = false) final Integer pageSize)
	{

		return fetchAllTickets(type, page == null ? 1 : page, pageSize == null ? 10 : pageSize);
	}

	@PostMapping("load")
	@ResponseBody
	public Response load(@RequestParam("type") final TicketType type, @RequestParam("file") final MultipartFile file, final RedirectAttributes redirectAttributes)
	{
		Response response = new Response();

		try
		{
			File csv = File.createTempFile("ticketRawData_", UUID.randomUUID().toString());
			file.transferTo(csv);
			List<String> lines = FileUtils.readLines(csv, Charset.defaultCharset());
			lines.remove(BigInteger.ZERO.intValue()); // exclude header
			ticketFactory.loadData(lines, type);

		} catch (WinnerException we)
		{
			response.addAllFails(we.getMessages());
		} catch (Exception e)
		{
			response.addAllFail(MessageFail.builder(SYS_ERROR));
		}

		return response;
	}

	private Response fetchAllTickets(final TicketType type, final Integer page, final Integer pageSize)
	{

		PageRequest pageRequest = new PageRequest(page == 0 ? page : page - 1, pageSize);

		TicketResponse response = new TicketResponse();
		Ticket ticket = new Ticket();
		ticket.setType(type);
		Page<Ticket> tickets = ticketRepository.findAll(Example.of(ticket), pageRequest);
		response.addAllTickets(tickets.getContent());
		response.setTotalItems(tickets.getTotalElements());
		return response;
	}
}
