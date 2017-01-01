package com.megabot.winner.support.model;

public enum FailCode
{
	MSG_000("", "%s cannot be null"),
	MSG_001("", "Missing generator to %s"),
	MSG_002("", "Missing loader to %s"),
	SYS_ERROR("", "Unexpected error ocurred, please contact administrator.");

	private String code;
	private String defaultMsg;

	private FailCode(final String codeInput, final String defaultMsgInput)
	{
		code = codeInput;
		defaultMsg = defaultMsgInput;
	}

	public String getCode()
	{
		return code;
	}

	public String getDefaultMsg()
	{
		return defaultMsg;
	}
}
