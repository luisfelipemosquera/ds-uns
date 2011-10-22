package sMySQLappTemplate.Exceptions;

@SuppressWarnings("serial")
public class InvalidPortFormat extends Exception 
{
	public InvalidPortFormat()
	{
		super("Formato de Puerto Incorrecto");
	}
}
