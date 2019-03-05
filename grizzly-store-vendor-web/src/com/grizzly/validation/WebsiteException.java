package com.grizzly.validation;

public class WebsiteException extends Exception{


	private static final long serialVersionUID = 1L;
	String message;
	public WebsiteException(String message)
	{
		this.message=message;
		
	}
	public String getMessage()
	{
		return message;
	}
}
