package sanidadApp.Features;

import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;

import sMySQLappTemplate.Core.Command;
import sMySQLappTemplate.Core.FeatureTemplate;
import sanidadApp.Core.sanidadAppCore;
import sanidadApp.GUI.WizardDatosTurno_Fecha;
import sanidadApp.GUI.WizardDatosTurno_Medico;

public class Modificacion_Turnos extends Alta_Turnos
{	
	private int turnoSeleccionado;

	public Modificacion_Turnos(sanidadAppCore app)
	{
		super();
		this.appCore = app;
		docType = new String();
		docNumber = new String();
		app.registerButtonForTools(new GestionTurnos(this), "/images/MedicTurn64_update.png", "Alterar Turno");
	}	

	protected class GestionTurnos extends Command
	{
		public GestionTurnos(FeatureTemplate feature) {
			super(feature);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Object ExecCommand(Object... args)
		{
			turnoSeleccionado = ((sanidadAppCore)appCore).getSelectedTurno();
			if (turnoSeleccionado < 0)
			{
				JOptionPane confirm = new JOptionPane();
				String msg = "Debe Seleccionar un turno para modificar";
				confirm.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				TablaMedicosExistentes = new TablaMedicos();
				tablaTiposConsulta = new TablaTiposConsulta();
				
				loadMedicos(); 					
				
				wizardWindow1 = new WizardDatosTurno_Medico((Modificacion_Turnos)this.receiver);
				wizardWindow1.setTitle("Modificar Turno: Medico");
				
				wizardWindow1.viewMedicos(TablaMedicosExistentes);
				
				wizardWindow2 = new WizardDatosTurno_Fecha((Modificacion_Turnos)this.receiver);
				wizardWindow2.setTitle("Modificar Turno: Fecha, Hora y Duración");
				
				reloadFullData();
				
				wizardWindow1.setVisible(true);
			}
			return null;
		}		
	}
	
	@SuppressWarnings("static-access")
	public void guardar(Date date, GregorianCalendar calendar, int duracion)
	{
		JOptionPane contingencia = new JOptionPane();
		
		calcularFechaHoraInicio(date, calendar);
		if (this.fechaHoraInicio == null) return;
		
		try {
			calcularFechaHoraFin(duracion);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String SQLqueryTurnosPasados =
			"SELECT '" + fechaHoraInicio + "' < NOW()";
		
		String SQLquerySuperposicion =
			"SELECT * FROM Turno " +
			"  WHERE Turno.ID <> " + turnoSeleccionado + " AND" +
			"		 Turno.Medico_Doc_Tipo = '" + this.docType + "' AND" +
			"        Turno.Medico_Doc_Numero = " + this.docNumber + " AND " +
			"(('" + fechaHoraInicio + "' BETWEEN Turno.FechaHoraInicio AND Turno.FechaHoraFin) OR" +
			"('"  + fechaHoraFin + "' BETWEEN Turno.FechaHoraInicio AND Turno.FechaHorafin) OR" +
			"('"  + fechaHoraInicio + "' <= Turno.FechaHoraInicio AND '" + fechaHoraFin + "' >= Turno.FechaHoraFin))";
		
		try {
			if (appCore.getValue(SQLqueryTurnosPasados).toString().compareTo("0") != 0)
			{
				contingencia.showMessageDialog(	
						null, "Todavia no puedes enviar turnos al pasado. \nSorry!", 
						"ERROR", 
						JOptionPane.ERROR_MESSAGE
				);
			}
			else if (appCore.getValue(SQLquerySuperposicion) != null)
			{
				contingencia.showMessageDialog(	
						null, "El medico ya tiene un compromiso en ese momento", 
						"ERROR", 
						JOptionPane.ERROR_MESSAGE
				);
			}
			else
			{
				String SQLqueryUpdate =
					"UPDATE Turno " +
					"SET FechaHoraInicio = '" + fechaHoraInicio + "', " +
					"    FechaHoraFin = '" + fechaHoraFin + "', " +
					"    Medico_Doc_Tipo = '" + docType + "', " +
					"    Medico_Doc_Numero = " + docNumber + " " + 
					"WHERE Turno.ID = " + turnoSeleccionado + ";";
				appCore.sendCommand(SQLqueryUpdate);
			
				((sanidadAppCore)appCore).actualizarTablaTurnos();
				
				wizardWindow1.dispose();
				wizardWindow2.dispose();
			}
		} catch (SQLException e) {
			contingencia.showMessageDialog(	
					null, e.getMessage(), 
					"ERROR: " + e.getErrorCode(), 
					JOptionPane.ERROR_MESSAGE
			);
		} 
	}

	public void reloadFullData() 
	{
		String SQLqueryTurnData = 
			"SELECT Medico_Doc_Tipo, Medico_Doc_Numero, " +
			"       FechaHoraInicio, FechaHoraFin, " +
			"		DATE_FORMAT(FechaHoraInicio, '%Y-%m-%d'), " +
			"	    DATE_FORMAT(FechaHoraInicio, '%H'),"	+
			"	    DATE_FORMAT(FechaHoraInicio, '%i'),"	+
			"	    DATE_FORMAT(FechaHoraInicio, '%s')"	+
			"  FROM Turno " +
			"  WHERE Turno.ID = " + this.turnoSeleccionado + ";";
		
		try {
			CachedRowSet rs = appCore.sendConsult(SQLqueryTurnData);
			if(rs.next())
			{
				appCore.setSelectedMedic(rs.getString(1), rs.getString(2));
				wizardWindow2.setDate(rs.getDate(3));
				GregorianCalendar g = new GregorianCalendar();
				g.set(1, 1, 1, rs.getInt(6), rs.getInt(7), rs.getInt(8));
				wizardWindow2.setTime(g);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}













