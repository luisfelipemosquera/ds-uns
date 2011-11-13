package sanidadApp.Features;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import sMySQLappTemplate.Core.Command;
import sMySQLappTemplate.Core.FeatureTemplate;
import sanidadApp.Core.sanidadAppCore;
import sanidadApp.GUI.WizardDatosMedico_Persona;
import sanidadApp.GUI.WizardDatosMedico_Especialidades;
import sanidadApp.GUI.WizardDatosMedico_Seleccionar;

public class Modificacion_Medicos extends Alta_Medicos 
{
	protected WizardDatosMedico_Seleccionar wizardWindow0;
	
	protected TablaMedicos TablaMedicosExistentes;
	
	protected int medico;
	
	protected long lastDocNumber;
	protected String lastDocType;
	
	public Modificacion_Medicos(){}

	public Modificacion_Medicos(sanidadAppCore app) 
	{	
		super();
		this.appCore = app;
		app.registerButtonForTools(new GestionMedicos(this), "/images/Admin_Medicos_64.png", "Modificar Personal Medico");
	}
	
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
			TablaMedicosExistentes = new TablaMedicos();
			docTypes = new DefaultComboBoxModel();
			
			wizardWindow0 = new WizardDatosMedico_Seleccionar((Modificacion_Medicos)this.receiver);			
			wizardWindow1 = new WizardDatosMedico_Persona((Modificacion_Medicos)this.receiver);
			wizardWindow2 = new WizardDatosMedico_Especialidades((Modificacion_Medicos)this.receiver);
			
			loadMedicos();
			
			wizardWindow0.viewMedicos(TablaMedicosExistentes);
			wizardWindow1.setDocTypeData(docTypes);
			wizardWindow2.viewEspExistentes(tablaEspExistentes);
			wizardWindow2.viewEspPoseidas(tablaEspPoseidas);
			
			wizardWindow0.setVisible(true);
			
			return null;
		}		
	}
	
	public void siguiente(int medicoSeleccionado)
	{
		loadTiposDocumento();
		wizardWindow0.setTitle(" -- Seleccion de Medico -- ");
		try 
		{
			String apellido = TablaMedicosExistentes.getValueAt(medicoSeleccionado, 0).toString();
			String nombre = TablaMedicosExistentes.getValueAt(medicoSeleccionado, 1).toString();
			String tipoDoc = TablaMedicosExistentes.getValueAt(medicoSeleccionado, 2).toString();
			String numeroDoc = TablaMedicosExistentes.getValueAt(medicoSeleccionado, 3).toString();
			
			wizardWindow1.setDocType(tipoDoc);
			wizardWindow1.setDocNumber(numeroDoc);
			wizardWindow1.setApellido(apellido);
			wizardWindow1.setNombre(nombre);
			
			if(cargarDatos(tipoDoc, numeroDoc, apellido, nombre))
			{
				lastDocType = this.tipoDoc;
				lastDocNumber = this.numeroDoc;
				
				wizardWindow0.setVisible(false);
				wizardWindow1.setVisible(true);
			}
		}
		catch(Exception e)
		{
			wizardWindow0.setTitle(" -- ERROR: Debe Seleccionar un Medico -- ");
		}		
	}
	
	public void siguiente(String tipoDNI, String numeroDNI, String apellido, String nombre)
	{
		if (this.cargarDatos(tipoDNI, numeroDNI, apellido, nombre))
		{
			loadEspecialidadesPoseidas();
			loadEspecialidadesExistentes();
			wizardWindow1.setVisible(false);
			wizardWindow2.setVisible(true);
		}
	}
	
	// NOTA: si queda tiempo combertir todo esto en transaccion
	
	public void guardar()
	{		
		if(updateMedic()) updateEspecialidades();
	    
	    wizardWindow1.dispose();
		wizardWindow2.dispose();
	}
	
	@SuppressWarnings("static-access")
	private boolean updateMedic() 
	{
		JOptionPane contingencia = new JOptionPane();
		
		String SQLQueryUpdateMedic =
			"UPDATE Persona " +
			"  SET Nombre = '" + this.nombre + "', " +
			"      Apellido = '" + this.apellido + "', " +
			"      Doc_tipo = '" + this.tipoDoc + "', " +
			"      Doc_Numero = " + this.numeroDoc + " " +
			"  WHERE Doc_Tipo = '" + lastDocType + "' AND " +
			"        Doc_Numero = " + lastDocNumber + ";";
		try {
			appCore.sendCommand(SQLQueryUpdateMedic);
			return true;
		} catch (SQLException e) {
			contingencia.showMessageDialog(	
					null, e.getMessage(), 
					"ERROR: " + e.getErrorCode(), 
					JOptionPane.ERROR_MESSAGE
			);
		}
		return false;
	}

	public void cancelar()
	{
		wizardWindow0.dispose();
		wizardWindow1.dispose();
		wizardWindow2.dispose();
	}
	
	public void encontrarPersona(String docTipo, String docNumero){}
	
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
	
	private void loadEspecialidadesPoseidas() {
		String SQLquery = "SELECT Especialidad_Nombre" +
						  "	 FROM Especializa" +
						  "  WHERE Medico_Doc_Tipo = '" + lastDocType + "' AND" +
						  "  	   Medico_Doc_Numero = '" + lastDocNumber + "';";
		try {
			this.appCore.populateTable(tablaEspPoseidas, SQLquery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	protected void loadEspecialidadesExistentes()
	{
		String SQLquery = "SELECT Nombre" +
						  "  FROM Especialidad" +
						  "  WHERE NOT EXISTS (" +
						  "    SELECT Especialidad_Nombre FROM Especializa" +
						  "    WHERE Medico_Doc_Tipo = '" + lastDocType + "' AND" +
						  "  	     Medico_Doc_Numero = '" + lastDocNumber + "' AND" +
						  "          Especialidad.Nombre = Especializa.Especialidad_Nombre);";
		System.out.println(SQLquery);
		try {
			this.appCore.populateTable(tablaEspExistentes, SQLquery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}







