package sMySQLappTemplate.Core;

public class UserAccount 
{
	private String user;
	private String pass;
	
	public UserAccount(String user, String pass)
	{
		this.setUser(user);
		this.setPass(pass);
	}

	private void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	private void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass() {
		return pass;
	}
}
