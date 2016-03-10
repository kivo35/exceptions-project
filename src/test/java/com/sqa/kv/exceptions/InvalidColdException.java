package com.sqa.kv.exceptions;

public class InvalidColdException extends Exception
{
	private String color;

	public InvalidColdException(String color)
	{
		this.color = color;
	}

	public String getColor()
	{
		return this.color;
	}
}
