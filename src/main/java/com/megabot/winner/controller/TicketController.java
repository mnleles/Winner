package com.megabot.winner.controller;

import static com.megabot.winner.support.model.FailCode.*;

import java.io.File;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.megabot.winner.business.TicketFactory;
import com.megabot.winner.inteface.model.TicketType;
import com.megabot.winner.support.exception.WinnerException;
import com.megabot.winner.support.model.MessageFail;
import com.megabot.winner.support.model.Response;

@RestController
@RequestMapping("ticket")
public class TicketController
{
	@Autowired
	private TicketFactory ticketFactory;

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
}
