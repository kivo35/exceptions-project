package com.sqa.kv.exceptions;

public class InvalidWarmthException extends Exception
{
	private String color;

	public InvalidWarmthException(String color)
	{
		this.color = color;
	}

	public String getColor()
	{
		return this.color;
	}
}
