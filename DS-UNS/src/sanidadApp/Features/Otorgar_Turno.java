package sanidadApp.Features;

import java.sql.SQLException;

import javax.swing.DefaultButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import sMySQLappTemplate.Core.Command;
import sMySQLappTemplate.Core.FeatureTemplate;
import sanidadApp.Core.sanidadAppCore;
import sanidadApp.GUI.WizardDatosMedico_Especialidades;
import sanidadApp.GUI.WizardDatosMedico_Persona;
import sanidadApp.GUI.WizardDatosReserva_Paciente;
import sanidadApp.GUI.WizardDatosReserva_Turno;

public class Otorgar_Turno extends FeatureTemplate
{	
	// Datos de la Base
	protected TablaTurnosDisponibles turnosDisponibles;
	protected DefaultComboBoxModel relacionesUNS;
	protected DefaultComboBoxModel tiposConsulta;
	protected DefaultComboBoxModel tiposDoc;
	
	// Datos Paciente
	protected String tipoDoc;
	protected String numeroDoc;
	protected String apellido;
	protected String nombre;
	protected String relUNS;
	
	// Datos Turno
	protected int turnoID;
	
	// Ventanas Wizard
	protected WizardDatosReserva_Paciente wizardWindow1;
	protected WizardDatosReserva_Turno wizardWindow2;
	
	public Otorgar_Turno(sanidadAppCore app)
	{
		this.appCore = app;
		app.registerButtonForTools(new GestionTurnos(this), "/images/Otorgar_Turno.png", "Otorgar Turno");
	}	
	
	/*
	 * 			JOptionPane confirm = new JOptionPane();
			String msg = "Debe Seleccionar un turno para eliminar";
			confirm.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);

	 */

	protected class GestionTurnos extends Command
	{

		public GestionTurnos(FeatureTemplate feature) {
			super(feature);
		}

		@Override
		public Object ExecCommand(Object... args)
		{
			tiposDoc = new DefaultComboBoxModel();
			relacionesUNS = new DefaultComboBoxModel();
			
			tiposConsulta = new DefaultComboBoxModel();
			turnosDisponibles = new TablaTurnosDisponibles();
			
			loadTiposDoc();
			loadRelacionesUNS();
			
			loadTiposConsulta();
			
			wizardWindow1 = new WizardDatosReserva_Paciente((Otorgar_Turno)this.receiver);
			wizardWindow2 = new WizardDatosReserva_Turno((Otorgar_Turno)this.receiver);
			
			wizardWindow1.setDocTypeData(tiposDoc);
			wizardWindow1.setUNSrelData(relacionesUNS);
			wizardWindow2.setTiposConsultaData(tiposConsulta);
			wizardWindow2.setTurnosDisponiblesData(turnosDisponibles);
			
			wizardWindow1.setVisible(true);
			
			return null;
		}		
	}
	
	public void cancelar()
	{
		wizardWindow1.dispose();
		wizardWindow2.dispose();
	}	
	
	public void anterior()
	{
		wizardWindow2.setVisible(false);
		wizardWindow1.setVisible(true);
	}
	
	public void siguiente(String tipoDNI, String numeroDNI, String apellido, 
			              String nombre, String relacionUns)
	{	
		if (this.cargarDatos(tipoDNI, numeroDNI, apellido, nombre, relacionUns))
		{
			wizardWindow1.setVisible(false);
			wizardWindow2.setVisible(true);
		}
	}
	
	public void guardar()
	{		
		wizardWindow1.dispose();
		wizardWindow2.dispose();
	}
	
	protected boolean cargarDatos(String tipoDNI, String numeroDNI, String apellido, 
			                      String nombre, String relacionUns)
	{
		try {
			Integer.parseInt(numeroDNI);
			this.numeroDoc = numeroDNI;
			this.tipoDoc = sanitize(tipoDNI);
			this.apellido = sanitize(apellido);
			this.nombre = sanitize(nombre);
			this.relUNS = relacionUns;
			return true;
		}
		catch (NumberFormatException ne)
		{
			wizardWindow1.setTitle(" -- ERROR: Numero de DNI Mal Formado -- ");
		}
		catch (SQLInjectionAttemptException sqle)
		{
			wizardWindow1.setTitle(" -- SQL Injection Attempt (DO NOT TRY THAT AGAIN) -- ");
		}
		catch (NullStringException nse)
		{
			wizardWindow1.setTitle(" -- ERROR: Complete Todos los Campos -- ");
		}
		return false;
	}
	
	protected String sanitize(String string)
	throws SQLInjectionAttemptException,
		   NullStringException
	{
		if(string == null || string.isEmpty())
			throw new NullStringException();
		if(string.contains("'") || string.contains(";"))
			throw new SQLInjectionAttemptException();
		return string;
	}
	
	@SuppressWarnings("static-access")
	protected void createPersona()
	{
		JOptionPane contingencia = new JOptionPane();
		
		// Crear persona de ser necesario
		String SQLQueryPersonaExiste = 
			"SELECT * FROM Persona WHERE " +
			"  Doc_Tipo = '" + tipoDoc + "' AND " +
			"  Doc_Numero = " + numeroDoc + ";";		
		try {
			if (appCore.getValue(SQLQueryPersonaExiste) == null)
			{
				String SQLQueryPersona = 
					"INSERT INTO Persona (`Doc_Tipo`, `Doc_Numero`, `Apellido`, `Nombre`, `UNS_Relacion_Relacion`) " +
					"VALUES ('" + tipoDoc + "', " + numeroDoc + ", '" + apellido + "', '" + nombre + "', 'Empleado');";
				appCore.sendCommand(SQLQueryPersona);
			}			  	
		} catch (SQLException e) {
			contingencia.showMessageDialog(	
				null, e.getMessage(), 
				"ERROR: " + e.getErrorCode(), 
				JOptionPane.ERROR_MESSAGE
			);
		}
	}

	public void loadTiposDoc() {
		String SQLquery = "SELECT Tipo FROM Doc_Tipo;";
		try {
			this.appCore.populateComboBox(tiposDoc, SQLquery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void loadTiposConsulta() {
		String SQLquery = "SELECT Nombre FROM Tipo_Consulta;";
		try {
			this.appCore.populateComboBox(tiposConsulta, SQLquery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void loadRelacionesUNS() {
		String SQLquery = "SELECT Relacion FROM UNS_Relacion;";
		try {
			this.appCore.populateComboBox(relacionesUNS, SQLquery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void loadTurnosDisponibles(String tipoConsulta)
	{
		String SQLqueryTurnosDisponibles =
			"SELECT DATE_FORMAT(FechaHoraInicio, '%Y-%m-%d') AS Dia," +
			"   DATE_FORMAT(FechaHoraInicio, '%T') AS Hora," +
			"   Apellido, Nombre, Turno.ID AS 'Turno ID'" +
			
			"   FROM Turno NATURAL JOIN Medico NATURAL JOIN Persona NATURAL JOIN " +
			"        Especializa NATURAL JOIN Realiza " +
			   
			"   WHERE " +
			"     Medico_Doc_Tipo = Medico.Doc_Tipo AND" +
			"	  Medico_Doc_Numero = Medico.Doc_Numero AND" +
			"	  NOT EXISTS (" +
			"	    SELECT * FROM IntraConsulta WHERE Turno_ID = Turno.ID" +
			"	  ) AND" +
			"	  Realiza.Tipo_Consulta_Nombre = '" + tipoConsulta + "';";
		
		try {
			appCore.populateTable(turnosDisponibles, SQLqueryTurnosDisponibles);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
