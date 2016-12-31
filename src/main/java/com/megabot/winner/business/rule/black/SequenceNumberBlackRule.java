package com.megabot.winner.business.rule.black;

import org.springframework.stereotype.Component;

import com.megabot.winner.inteface.model.Ticket;

@Component
public class SequenceNumberBlackRule implements IBlackRule {

	@Override
	public boolean isAppliable(final Ticket ticket) {
		// TODO Auto-generated method stub
		return false;
	}

}
