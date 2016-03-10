package com.sqa.kv.exceptions;

public class UnlikedColorException extends Exception
{
	private String color;

	public UnlikedColorException(String color)
	{
		this.color = color;
	}

	public String getColor()
	{
		return this.color;
	}
}
