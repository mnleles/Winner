package com.megabot.winner.support.model;

public enum FailCode {

	MSG_001("", "Missing generator to %s");

	private String code;
	private String defaultMsg;

	private FailCode(final String codeInput, final String defaultMsgInput) {
		code = codeInput;
		defaultMsg = defaultMsgInput;
	}

	public String getCode() {
		return code;
	}

	public String getDefaultMsg() {
		return defaultMsg;
	}
}
