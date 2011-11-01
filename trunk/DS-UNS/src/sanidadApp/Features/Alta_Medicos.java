package sanidadApp.Features;

import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

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
			/*
			try {
				today = (Date)appCore.getValue("SELECT CURDATE();");
			} catch (SQLException e) {
				// TODO	JOptionPane.showMessageDialog(null, "Se produjo un Error al intentar consultar la fecha actual", "- ERROR MOLESTO -", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return null;
			}
			*/
			cambioEspecialidades = new HashMap<String, String>();
			tablaEspExistentes = new TablaEspecialidades();
			tablaEspPoseidas = new TablaEspecialidades();
			docTypes = new DefaultComboBoxModel();
			
			wizardWindow1 = new WizardDatosMedico_Persona((Alta_Medicos)this.receiver);
			wizardWindow2 = new WizardDatosMedico_Especialidades((Alta_Medicos)this.receiver);
			
			// datos de prueba
			tablaEspExistentes.setRowCount(3);
			tablaEspExistentes.setValueAt("Especialidad X", 0, 0);
			tablaEspExistentes.setValueAt("Especialidad Y", 1, 0);
			tablaEspExistentes.setValueAt("Especialidad Z", 2, 0);
			
			docTypes.addElement("DNI");
			docTypes.addElement("LE");
			docTypes.addElement("LC");
			
			// fin datos de prueba
			
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
		// Debug Prints
		System.out.println("Creando Nueva Entrada Para Medico:");
		System.out.println("-- Comienzo de Entrada --"); 
		System.out.println("Tipo DNI: " + tipoDoc);
		System.out.println("Numero DNI: " + numeroDoc);
		System.out.println("Apellido: " + apellido);
		System.out.println("Nombres: " + nombre);
		System.out.println();
		Iterator it = cambioEspecialidades.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String,String> pairs = (Map.Entry<String,String>)it.next();
	        System.out.println(pairs.getValue() + " " + pairs.getKey());
	    }
	    System.out.println("-- Fin de Entrada --"); 
	    System.out.println();
	    
	    wizardWindow1.dispose();
		wizardWindow2.dispose();
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
}













