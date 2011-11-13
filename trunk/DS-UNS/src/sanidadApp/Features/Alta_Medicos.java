package sanidadApp.Features;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.sql.rowset.CachedRowSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import com.mysql.jdbc.RowData;

import sMySQLappTemplate.Core.Command;
import sMySQLappTemplate.Core.FeatureTemplate;
import sanidadApp.Core.sanidadAppCore;
import sanidadApp.GUI.WizardDatosMedico_Persona;
import sanidadApp.GUI.WizardDatosMedico_Especialidades;

public class Alta_Medicos extends FeatureTemplate
{	
	protected Date today;
	
	protected WizardDatosMedico_Persona wizardWindow1;
	protected WizardDatosMedico_Especialidades wizardWindow2;
	
	protected String tipoDoc;
	protected long numeroDoc;
	protected String apellido;
	protected String nombre;
	
	protected TablaEspecialidades tablaEspExistentes;
	protected TablaEspecialidades tablaEspPoseidas;
	
	protected DefaultComboBoxModel docTypes;
	
	protected HashMap<String, String> cambioEspecialidades;
	
	public Alta_Medicos(){};
	
	public Alta_Medicos(sanidadAppCore app)
	{
		this.appCore = app;
		app.registerButtonForTools(new GestionMedicos(this), "/images/add_medic_64.png", "Agregar Personal Medico");
	}	
	
	@SuppressWarnings("unchecked")
	protected class GestionMedicos extends Command
	{

		public GestionMedicos(FeatureTemplate feature) {
			super(feature);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Object ExecCommand(Object... args)
		{
			cambioEspecialidades = new HashMap<String, String>();
			tablaEspExistentes = new TablaEspecialidades();
			tablaEspPoseidas = new TablaEspecialidades();
			docTypes = new DefaultComboBoxModel();
			
			wizardWindow1 = new WizardDatosMedico_Persona((Alta_Medicos)this.receiver);
			wizardWindow2 = new WizardDatosMedico_Especialidades((Alta_Medicos)this.receiver);
			
			loadEspecialidadesExistentes();
			loadTiposDocumento();
			
			wizardWindow1.setDocTypeData(docTypes);
			wizardWindow2.viewEspExistentes(tablaEspExistentes);
			wizardWindow2.viewEspPoseidas(tablaEspPoseidas);
			wizardWindow1.setVisible(true);
			
			return null;
		}	
	}
	
	public void siguiente(String tipoDNI, String numeroDNI, String apellido, String nombre)
	{	
		if (this.cargarDatos(tipoDNI, numeroDNI, apellido, nombre))
		{
			wizardWindow1.setVisible(false);
			wizardWindow2.setVisible(true);
		}
	}

	protected boolean cargarDatos(String tipoDNI, String numeroDNI, String apellido, String nombre)
	{
		try {
			this.numeroDoc = Integer.parseInt(numeroDNI);
			this.tipoDoc = sanitize(tipoDNI);
			this.apellido = sanitize(apellido);
			this.nombre = sanitize(nombre);
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

	public void anterior()
	{
		wizardWindow2.setVisible(false);
		wizardWindow1.setVisible(true);
	}
	
	public void guardar()
	{		
		createMedic();		
		updateEspecialidades();
	    
	    wizardWindow1.dispose();
		wizardWindow2.dispose();
	}
	
	@SuppressWarnings({ "unchecked", "static-access" })
	protected void updateEspecialidades()
	{
		JOptionPane contingencia = new JOptionPane();
		
		Iterator it = cambioEspecialidades.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String,String> pairs = (Map.Entry<String,String>)it.next();	         	        
	        String SQLQueryUpdateEspecialidad = null;
	        if (pairs.getValue() == "add")
	        {
	        	SQLQueryUpdateEspecialidad =
	        		"INSERT INTO Especializa (`Medico_Doc_Tipo`, `Medico_Doc_Numero`, `Especialidad_Nombre`) " +
	        		"VALUES ('" + tipoDoc + "'," + numeroDoc + ", '" + pairs.getKey() + "');";
	        }
	        else
	        {
	        	SQLQueryUpdateEspecialidad =
	        		"DELETE FROM Especializa " +
	        		"WHERE Medico_Doc_Tipo = '" + tipoDoc + "' AND " +
	        	    "	   Medico_Doc_Numero = " + numeroDoc + " AND " +
	        	    "      Especialidad_Nombre = '" + pairs.getKey() + "';";
	        }
	        try {
				appCore.sendCommand(SQLQueryUpdateEspecialidad);
			} catch (SQLException e) {
				contingencia.showMessageDialog(	
						null, e.getMessage(), 
						"ERROR: " + e.getErrorCode(), 
						JOptionPane.ERROR_MESSAGE
				);
			}
	    }
	}
	
	public void encontrarPersona(String docTipo, String docNumero)
	{
		String SQLQueryPersonaExiste = 
			"SELECT Apellido, Nombre, UNS_Relacion_Relacion FROM Persona WHERE " +
			"  Doc_Tipo = '" + docTipo + "' AND " +
			"  Doc_Numero = " + docNumero + ";";
		try {
			CachedRowSet rs = appCore.sendConsult(SQLQueryPersonaExiste);
			if (rs.next())
			{
				wizardWindow1.setApellido(rs.getString(1));
				wizardWindow1.setNombre(rs.getString(2));
				wizardWindow1.disableNonKey();
			}
			else
			{
				wizardWindow1.enableNonKey();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("static-access")
	protected void createMedic()
	{
		JOptionPane contingencia = new JOptionPane();
		
		createPersona();
		
		// Crear medico a partir de la persona si es posible
		String SQLQueryMedicoExiste = 
			"SELECT * FROM Medico WHERE " +
			"  Doc_Tipo = '" + tipoDoc + "' AND " +
			"  Doc_Numero = " + numeroDoc + ";";
		try {
			if (appCore.getValue(SQLQueryMedicoExiste) != null)
			{
				contingencia.showMessageDialog (	
						null, "Ya existe un medico con ese documento", 
						"ERROR", JOptionPane.ERROR_MESSAGE
				);
			}
			else
			{
				String SQLQueryMedico = 
					"INSERT INTO Medico (`Doc_Tipo`, `Doc_Numero`) " +
					"VALUES ('" + tipoDoc + "', " + numeroDoc + ");";
				appCore.sendCommand(SQLQueryMedico);
			}			  	
		} catch (SQLException e) {
			contingencia.showMessageDialog(	
				null, e.getMessage(), 
				"ERROR: " + e.getErrorCode(), 
				JOptionPane.ERROR_MESSAGE
			);
		}
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
	
	public void cancelar()
	{
		wizardWindow1.dispose();
		wizardWindow2.dispose();
	}	
	
	public void addEsp(int espSelecionada)
	{
		wizardWindow2.setTitle("Especialidades");
		try {
			String esp = tablaEspExistentes.getValueAt(espSelecionada, 0).toString();
			
			String espAction = cambioEspecialidades.get(esp); 
			if (espAction == null)
				cambioEspecialidades.put(esp, "add");
			else if(espAction == "rem")
				cambioEspecialidades.remove(esp);
			
			tablaEspExistentes.removeRow(espSelecionada);
			int rc = tablaEspPoseidas.getRowCount();
			tablaEspPoseidas.setRowCount(rc+1);
			tablaEspPoseidas.setValueAt(esp, rc, 0);
		}
		catch(Exception e)
		{
			wizardWindow2.setTitle(" -- ERROR: Debe Seleccionar Una Especialidad --");
		}
	}
	
	public void remEsp(int espSelecionada)
	{
		wizardWindow2.setTitle("Especialidades");
		try {
			String esp = tablaEspPoseidas.getValueAt(espSelecionada, 0).toString();
			
			String espAction = cambioEspecialidades.get(esp); 
			if (espAction == null)
				cambioEspecialidades.put(esp, "rem");
			else if(espAction == "add")
				cambioEspecialidades.remove(esp);
			
			tablaEspPoseidas.removeRow(espSelecionada);
			int rc = tablaEspExistentes.getRowCount();
			tablaEspExistentes.setRowCount(rc+1);
			tablaEspExistentes.setValueAt(esp, rc, 0);
		}
		catch(Exception e)
		{
			wizardWindow2.setTitle(" -- ERROR: Debe Seleccionar Una Especialidad --");
		}
	}
	
	protected void loadEspecialidadesExistentes() 
	{
		String SQLquery = "SELECT Nombre FROM Especialidad;";
		try {
			this.appCore.populateTable(tablaEspExistentes, SQLquery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	
	protected void loadTiposDocumento() {
		String SQLquery = "SELECT Tipo FROM Doc_Tipo;";
		try {
			this.appCore.populateComboBox(docTypes, SQLquery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}













