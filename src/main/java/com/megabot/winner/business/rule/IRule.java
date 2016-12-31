package com.megabot.winner.business.rule;

import com.megabot.winner.inteface.model.Ticket;

public interface IRule {

	boolean isAppliable(Ticket ticket);
}
