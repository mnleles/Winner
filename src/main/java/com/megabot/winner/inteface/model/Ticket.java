package com.megabot.winner.inteface.model;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Ticket extends AbstractModel {

	private LocalDate date;
	private Set<Integer> numbers;
	private TicketType type;
	private Boolean winningTicket;

	public LocalDate getDate() {
		return date;
	}

	public Set<Integer> getNumbers() {
		return numbers;
	}

	public TicketType getType() {
		return type;
	}

	public Boolean getWinningTicket() {
		return winningTicket;
	}

	public void setDate(final LocalDate date) {
		this.date = date;
	}

	public void setNumbers(final Set<Integer> numbers) {
		this.numbers = numbers;
	}

	public void setType(final TicketType type) {
		this.type = type;
	}

	public void setWinningTicket(final Boolean winningTicket) {
		this.winningTicket = winningTicket;
	}
}
