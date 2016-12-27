package com.megabot.winner.model;

import java.time.LocalDate;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Ticket {

	private TicketType type;
	private LocalDate date;
	private Set<Integer> numbers;
	private Boolean winningTicket;

	public TicketType getType() {
		return type;
	}

	public void setType(TicketType type) {
		this.type = type;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Set<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(Set<Integer> numbers) {
		this.numbers = numbers;
	}

	public Boolean getWinningTicket() {
		return winningTicket;
	}

	public void setWinningTicket(Boolean winningTicket) {
		this.winningTicket = winningTicket;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
