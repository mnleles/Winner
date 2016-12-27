package com.megabot.winner.rule;

import com.megabot.winner.model.Ticket;

public interface IRule {

	boolean isAppliable(Ticket ticket);
}
