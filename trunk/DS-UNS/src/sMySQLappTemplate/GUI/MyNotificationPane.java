package sMySQLappTemplate.GUI;

import java.util.HashMap;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JPanel;

public class MyNotificationPane extends JPanel
{
	protected HashMap<Integer, MyNotification> Notifications;
	
	public void addNotification(String docTipo, String docNumero, String nTipo, String nMsg)
	{
		String SQLqueryInsertNotification =
			"INSERT " + 
			  "INTO Notification (Tipo_Notificacion, Persona_Doc_Tipo, " +
			  "					 Persona_Doc_Numero, Mensage) " +
			  " VALUES ('" + nTipo + "', '" + docTipo + "', " +
			                 docNumero + ", '" + nMsg + "');";
		System.out.println(SQLqueryInsertNotification);
	}
	
	public void remNotification(int nid)
	{
		
	}
	
	/*
	 * 	public void actualizarNotificaciones()
	{
		String SQLqueryNotificaciones =
			"SELECT ID, Tipo_Notificacion, Mensage FROM Notificacion";
		
		try
		{
			CachedRowSet crs = this.sendConsult(SQLqueryNotificaciones);	       
		    while (crs.next())
		    {
		    	int nid = crs.getInt(1);
		    	String nType = crs.getString(2);
		    	String nMsg = crs.getString(3);
		    	masterWindow.getNotificationPane().addNotification(nid, nType, nMsg);
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		    
	}
	 */
}
