package sMySQLappTemplate.Core;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;

import org.apache.commons.dbcp.BasicDataSource;

import com.sun.rowset.CachedRowSetImpl;

import sMySQLappTemplate.Exceptions.InvalidDataBase;
import sMySQLappTemplate.Exceptions.InvalidHost;
import sMySQLappTemplate.Exceptions.InvalidPortNumber;
import sMySQLappTemplate.Exceptions.InvalidUserOrPass;
import sMySQLappTemplate.Exceptions.UntestedConnectionException;

public class ComModule 
{
	protected String driver = "com.mysql.jdbc.Driver";
	protected BasicDataSource dataSource;
	protected boolean connEstablished = false;
	
	protected ConnectionCfg connInfo;
	protected UserAccount userInfo;
	
	// CONTRUCTOR
	// CARGA DRIVERS E INICIALIZA EL ORIGEN DE DATOS
	
	public ComModule()
	{	
		try
		{
			Class.forName(driver);			 			
			dataSource = new BasicDataSource();
			dataSource.setDriverClassName(driver);
		}
		catch (Exception e)
		{
			// TODO cartelito con los errores
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	// PRIMITIVAS DE COMUNICACION
	
	// Retorna el resultado de ejecutar una consulta SQL sobre 
	// la conexion establecida

	public CachedRowSet execConsult(String SQLquery) 
	throws UntestedConnectionException, SQLException
	{
		if (connEstablished)
		{
			Connection conn = dataSource.getConnection();
			CachedRowSetImpl crs = new CachedRowSetImpl();
			crs.setCommand(SQLquery);
			crs.execute(conn);
			this.closeConn(conn);
			return crs;
		}
		else
		{
			throw new UntestedConnectionException();
		}
	}
	
	// Ejecuta comandos SQL sobre la conexion establecida
	
	public void execCommand(String SQLcommand)
	throws UntestedConnectionException, SQLException 
	{
		if (this.connEstablished)
		{
			Connection conn = dataSource.getConnection();
			Statement sqlCommand = conn.createStatement();
			sqlCommand.executeUpdate(SQLcommand);
			this.closeConn(conn);
		}
		else
		{
			throw new UntestedConnectionException();
		}
	}
	
	// PRIMITIVAS DE CONEXION
	
	// Intenta establecer una conexion de prueba con los datos
	// suministrados de usuario y configuracion de conexion
	// como efecto colateral setea los campos correspondientes
	// para usar la conexion mas adelante.
	
	@SuppressWarnings("deprecation")
	public void establishConnection(UserAccount who, ConnectionCfg where) 
	throws InvalidUserOrPass, InvalidPortNumber, InvalidHost, InvalidDataBase
	{
		setConnURL(where);
		dataSource.setUsername(who.getUser());
		dataSource.setPassword(who.getPass());
		Connection connTest = null;
		try {
			System.out.println(dataSource.getUsername() + " " + dataSource.getPassword());
			System.out.println(dataSource.getUrl());
			connTest = dataSource.getConnection();
			this.connEstablished = true;
		} catch (org.apache.commons.dbcp.SQLNestedException e) {
			this.closeConn(connTest);
			this.connEstablished = false;
			throw new InvalidUserOrPass();
		} catch (Exception f) {
			// TODO cartelito de falla de conexion
			f.printStackTrace();
			this.closeConn(connTest);
			this.connEstablished = false;
		}
	}
	
	private void closeConn(Connection conn)
	{
		try {if (conn != null) conn.close();}
		catch (SQLException e) 
		{
			// TODO Cartelitos de Error
			e.printStackTrace();
		}
	}
	
	// UTILIDADES
	
	private void setConnURL(ConnectionCfg connectTo) 
	{
		this.connInfo = connectTo;
		dataSource.setUrl("jdbc:mysql://" + connInfo.getHost() + ":" + connInfo.getPort() + "/" + connInfo.getDataBase());
	}

	public boolean isConnectionEstablished() {
		return connEstablished;
	}
}
