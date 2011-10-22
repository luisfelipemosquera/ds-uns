package sMySQLappTemplate.Core;

import sMySQLappTemplate.Exceptions.InvalidPortFormat;

public class ConnectionCfg 
{
	private String host;
	private String port;
	private String dataBase;
	
	public ConnectionCfg(String host, String port, String dataBase)
	throws InvalidPortFormat
	{
		this.setHost(host);
		this.setPort(port);
		this.setDataBase(dataBase);
	}
	
	private void setHost(String host) {
		this.host = host;
	}
	
	public String getHost() {
		return host;
	}
	
	private void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}
	
	public String getDataBase() {
		return dataBase;
	}
	
	private void setPort(String port) 
	throws InvalidPortFormat
	{
		int pNumber;
		try
		{
			pNumber = Integer.parseInt(port);
		}
		catch(NumberFormatException e)
		{
			throw new InvalidPortFormat();
		}
		if (pNumber < 1 || pNumber > 65000)
			throw new InvalidPortFormat();
		
		this.port = port;
	}
	
	public String getPort() {
		return port;
	}	
}
