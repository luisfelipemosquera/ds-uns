package sMySQLappTemplate.Exceptions;

@SuppressWarnings("serial")
public class InvalidUserOrPass extends Exception 
{
	public InvalidUserOrPass()
	{
		super("Usuario o Contrase�a Invalido");
	}
}
