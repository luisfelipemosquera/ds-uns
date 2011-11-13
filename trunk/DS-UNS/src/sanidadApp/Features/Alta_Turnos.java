package sanidadApp.Features;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import sMySQLappTemplate.Core.Command;
import sMySQLappTemplate.Core.FeatureTemplate;
import sanidadApp.Core.sanidadAppCore;
import sanidadApp.GUI.WizardDatosTurno_Fecha;
import sanidadApp.GUI.WizardDatosTurno_Medico;

public class Alta_Turnos extends FeatureTemplate
{
	protected WizardDatosTurno_Medico wizardWindow1;
	protected WizardDatosTurno_Fecha wizardWindow2;
	
	protected TablaMedicos TablaMedicosExistentes;
	protected TablaTiposConsulta tablaTiposConsulta;

	// Datos de turno
	private String docType;
	private String docNumber;
	
	private String fechaHoraInicio;
	private String fechaHoraFin;
	
	public Alta_Turnos(sanidadAppCore app)
	{
		this.appCore = app;
		docType = new String();
		docNumber = new String();
		app.registerButtonForTools(new GestionTurnos(this), "/images/MedicTurn64_add.png", "Nuevo Turno");
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
			TablaMedicosExistentes = new TablaMedicos();
			tablaTiposConsulta = new TablaTiposConsulta();
			
			loadMedicos(); 					
			
			wizardWindow1 = new WizardDatosTurno_Medico((Alta_Turnos)this.receiver);
			wizardWindow1.setTitle("Nuevo Turno: Medico");
			
			wizardWindow1.viewMedicos(TablaMedicosExistentes);
			
			wizardWindow2 = new WizardDatosTurno_Fecha((Alta_Turnos)this.receiver);
			wizardWindow2.setTitle("Nuevo Turno: Fecha, Hora y Duración");
			
			wizardWindow1.setVisible(true);
			return null;
		}		
	}
	
	public void siguiente() 
	{
		wizardWindow1.setTitle("Nuevo Turno: Medico");
		wizardWindow2.setTitle("Nuevo Turno: Fecha, Hora y Duración");
		
		if(docType.isEmpty() || docNumber.isEmpty())
		{
			wizardWindow1.setTitle("ERROR: Debe seleccionar un medico");
			return;
		}
		
		wizardWindow1.setVisible(false);
		wizardWindow2.setVisible(true);

	}
	
	public void anterior() 
	{
		wizardWindow1.setTitle("Nuevo Turno: Medico");
		wizardWindow2.setTitle("Nuevo Turno: Fecha, Hora y Duración");
		
		wizardWindow2.setVisible(false);
		wizardWindow1.setVisible(true);		
	}
	
	/*
	 * ((" +
			"   '" + fechaHoraInicio + "' <= Turno.FechaHoraFin AND" +
			"   '" + fechaHoraInicio + "' >= Turno.FechaHoraInicio ) OR (" +
			"   '" + fechaHoraFin + "' >= Turno.FechaHoraInicio";
	 */
	
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
			"  WHERE Turno.Medico_Doc_Tipo = '" + this.docType + "' AND" +
			"        Turno.Medico_Doc_Numero = " + this.docNumber + " AND " +
			"(('" + fechaHoraInicio + "' BETWEEN Turno.FechaHoraInicio AND Turno.FechaHoraFin) OR" +
			"('"  + fechaHoraFin + "' BETWEEN Turno.FechaHoraInicio AND Turno.FechaHorafin) OR" +
			"('"  + fechaHoraInicio + "' <= Turno.FechaHoraInicio AND '" + fechaHoraFin + "' >= Turno.FechaHoraFin))";
		
		try {
			if (appCore.getValue(SQLqueryTurnosPasados).toString().compareTo("0") != 0)
			{
				contingencia.showMessageDialog(	
						null, "Todavia no puedes crear turnos en el pasado. \nSorry!", 
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
				String SQLqueryInsert =
					"INSERT INTO Turno (FechaHoraInicio, FechaHoraFin, " +
					"                   Medico_Doc_Tipo, Medico_Doc_Numero) " +
					"       VALUES ('" + fechaHoraInicio + "', '" + fechaHoraFin + "', " +
					"               '" + docType + "', " + docNumber + ");";
				appCore.sendCommand(SQLqueryInsert);
			
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
	
	public void cancelar()
	{
		wizardWindow1.dispose();
		wizardWindow2.dispose();
	}
	
	protected void loadMedicos() 
	{
		String SQLquery = "SELECT Apellido, Nombre, Doc_Tipo, Doc_Numero " +
						  "  FROM Persona NATURAL JOIN Medico;";
		try {
			this.appCore.populateTable(TablaMedicosExistentes, SQLquery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/*
	private void loadTipoConsulta() {
		String SQLquery = "SELECT DISTINCT Tipo_Consulta_Nombre" +
						  "	 FROM Realiza" +
						  "  WHERE EXISTS (" +
						  "    SELECT * FROM Especializa " +
						  "      WHERE Medico_Doc_Tipo = '" + docType + "' AND" +
						  "  	       Medico_Doc_Numero = " + docNumber + " AND" +
						  "            Especializa.Especialidad_Nombre = Realiza.Especialidad_Nombre" +
						  "  );";
		try {
			this.appCore.populateTable(tablaTiposConsulta, SQLquery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	*/
	
	public void medico_seleccionado(int medic)
	{
		docType = TablaMedicosExistentes.getValueAt(medic, 2).toString();
		docNumber = TablaMedicosExistentes.getValueAt(medic, 3).toString();
	}

	public void calcularFechaHoraInicio(Date date, GregorianCalendar calendar) 
	{
		String dateTime = null;
		if (date == null)
		{
			wizardWindow2.setTitle("ERROR: Debe seleccionar una fecha para el turno");
		}
		else
		{		
			SimpleDateFormat transformerDate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat transformerTime = new SimpleDateFormat("HH:mm:ss");
			dateTime = transformerDate.format(date) + " " + 
					   transformerTime.format(calendar.getTime());
		}		
		this.fechaHoraInicio = dateTime;
	}
	
	public void calcularFechaHoraFin(int duracion) 
	throws SQLException
	{
		String SQLquerySumarTiempo = 
			"SELECT DATE_FORMAT(" +
			"  DATE_ADD('" + fechaHoraInicio + "', INTERVAL " + duracion + " MINUTE)," +
			"  '%Y-%m-%d %T');";

		fechaHoraFin = appCore.getValue(SQLquerySumarTiempo).toString();
	}
}













