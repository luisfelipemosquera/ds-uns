package sanidadApp.Features;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import sMySQLappTemplate.Core.Command;
import sMySQLappTemplate.Core.FeatureTemplate;
import sanidadApp.Core.sanidadAppCore;
import sanidadApp.GUI.WizardDatosTurno_Fecha;
import sanidadApp.GUI.WizardDatosTurno_Medico;
import sanidadApp.GUI.WizardReporteTurno_Fecha;
import sanidadApp.GUI.WizardReporteTurno_Reporte;

public class Reporte_Turnos_Dia extends FeatureTemplate
{
	protected WizardReporteTurno_Fecha wizardWindow1;
	protected WizardReporteTurno_Reporte wizardWindow2;
	
	protected JTextArea reporte;

	
	public Reporte_Turnos_Dia(){};
	
	public Reporte_Turnos_Dia(sanidadAppCore app)
	{
		this.appCore = app;
		app.registerButtonForTools(new GestionTurnos(this), "/images/timeReport64-v1.png", "Reporte Turnos por Dia");
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
			wizardWindow1 = new WizardReporteTurno_Fecha((Reporte_Turnos_Dia) this.receiver);
			wizardWindow2 = new WizardReporteTurno_Reporte((Reporte_Turnos_Dia) this.receiver);
			
			reporte = new JTextArea();
			reporte.setEditable(false);
			wizardWindow2.setReporte(reporte);
			
			wizardWindow1.setTitle("Reporte Turnos por Dia: Seleccion de Fecha");
			wizardWindow2.setTitle("Reporte Turnos por Dia");
			wizardWindow1.setVisible(true);
			
			return null;
		}		
	}
	
	public void anterior() 
	{
		wizardWindow2.setVisible(false);
		wizardWindow1.setVisible(true);
	}
	
	public void aceptar(Date date)
	{
		wizardWindow1.setTitle("Reporte Turnos por Dia: Seleccion de Fecha");
		reporte.setText("");
		String fechaInicio = calcularFechaInicio(date);
		if (fechaInicio == null)
		{
			wizardWindow1.setTitle("ERROR: Debe seleccionar una fecha para el turno");
		}
		else
		{			
			String SQLqueryReporte = 
				"SELECT DATE_FORMAT(Turno.FechaHoraInicio, '%T'), Apellido, Nombre, Turno.ID, DATE_FORMAT(Turno.FechaHoraInicio, '%Y-%m-%d') "+
				"FROM Turno NATURAL JOIN IntraConsulta NATURAL JOIN Persona "+
				"WHERE "+
				"  Turno_ID = Turno.ID AND "+
				"  Medico_Doc_Tipo = Doc_Tipo AND "+
				"  Medico_Doc_Numero = Doc_Numero AND "+
				"  DATE_FORMAT(Turno.FechaHoraInicio, '%Y-%m-%d') = '" + fechaInicio + "';";
			
			try {
				CachedRowSet rs = appCore.sendConsult(SQLqueryReporte);
				while(rs.next())
				{
					reporte.append("ID de Turno:  " + rs.getString(4) + "  --  " +
							       "Hora:  " + rs.getString(1) + "  --  " +
							       "Medico:  " + rs.getString(2) + " " + rs.getString(3) + "\n");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			wizardWindow2.setDia(fechaInicio);
			
			wizardWindow1.setVisible(false);
			wizardWindow2.setVisible(true);
		}
	}
	
	public String calcularFechaInicio(Date date) 
	{
		String dateTime = null;
		if (date != null)
		{		
			SimpleDateFormat transformerDate = new SimpleDateFormat("yyyy-MM-dd");
			dateTime = transformerDate.format(date);
		}		
		return dateTime;
	}
	
	public void cancelar()
	{
		wizardWindow1.dispose();
		wizardWindow2.dispose();
	}
}













