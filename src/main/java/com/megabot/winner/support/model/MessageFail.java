package com.megabot.winner.support.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MessageFail {

	private Collection<Object> args;

	private FailCode failCode;

	public MessageFail(final FailCode failCode, final List<Object> args) {
		this.failCode = failCode;
		this.args = args;
	}

	public Collection<Object> getArgs() {
		return args;
	}

	public FailCode getFailCode() {
		return failCode;
	}

	public void setArgs(final Collection<Object> args) {
		this.args = args;
	}

	public void setFailCode(final FailCode failCode) {
		this.failCode = failCode;
	}

	public static MessageFail builder(final FailCode failCode, final Object... args) {

		return new MessageFail(failCode, Arrays.asList(args));
	}
}
