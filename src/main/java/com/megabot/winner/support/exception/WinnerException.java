package com.megabot.winner.support.exception;

import java.util.ArrayList;
import java.util.Collection;

import com.megabot.winner.support.model.MessageFail;

public class WinnerException extends Exception {

	private static final long serialVersionUID = -1820514792985663925L;

	private final Collection<MessageFail> messages = new ArrayList<>();

	public WinnerException(final MessageFail... msgs) {

		for (final MessageFail m : msgs) {
			messages.add(m);
		}
	}

	public void addMessages(final Collection<MessageFail> msgs) {

		if (msgs == null || msgs.isEmpty()) {
			return;
		}

		messages.addAll(msgs);
	}

	public Collection<MessageFail> getMessages() {
		return messages;
	}
}
