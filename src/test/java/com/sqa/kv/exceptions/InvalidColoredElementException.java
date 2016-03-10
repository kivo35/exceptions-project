package com.sqa.kv.exceptions;

public class InvalidColoredElementException extends Exception
{
	private String css;

	public InvalidColoredElementException(String css)
	{
		this.css = css;
	}

	public String getCss()
	{
		return this.css;
	}

}
