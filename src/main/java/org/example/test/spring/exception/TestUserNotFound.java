package org.example.test.spring.exception;

public class TestUserNotFound extends RuntimeException
{

	public TestUserNotFound()
	{
	}

	public TestUserNotFound(String message)
	{
		super(message);
	}

	public TestUserNotFound(String message, Throwable cause)
	{
		super(message, cause);
	}
}
