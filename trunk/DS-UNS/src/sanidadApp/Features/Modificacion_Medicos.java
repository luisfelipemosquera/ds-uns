package sanidadApp.Features;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;

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
			TablaMedicosExistentes = new TablaMedicos();
			docTypes = new DefaultComboBoxModel();
			
			wizardWindow0 = new WizardDatosMedico_Seleccionar((Modificacion_Medicos)this.receiver);			
			wizardWindow1 = new WizardDatosMedico_Persona((Modificacion_Medicos)this.receiver);
			wizardWindow2 = new WizardDatosMedico_Especialidades((Modificacion_Medicos)this.receiver);
			
			// Datos de Prueba
			tablaEspExistentes.setRowCount(3);
			tablaEspExistentes.setValueAt("Especialidad X", 0, 0);
			tablaEspExistentes.setValueAt("Especialidad Y", 1, 0);
			tablaEspExistentes.setValueAt("Especialidad Z", 2, 0);
			tablaEspPoseidas.setRowCount(1);
			tablaEspPoseidas.setValueAt("Especialidad R", 0, 0);
			
			docTypes.addElement("DNI");
			docTypes.addElement("LE");
			docTypes.addElement("LC");
			
			TablaMedicosExistentes.setRowCount(1);
			TablaMedicosExistentes.setValueAt("Gonzalez", 0, 0);
			TablaMedicosExistentes.setValueAt("Juan Domingo", 0, 1);
			TablaMedicosExistentes.setValueAt("LE", 0, 2);
			TablaMedicosExistentes.setValueAt("31298030", 0, 3);
			// Fin Datos de Prueba			
			
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
				wizardWindow0.setVisible(false);
				wizardWindow1.setVisible(true);
			}
		}
		catch(Exception e)
		{
			wizardWindow0.setTitle(" -- ERROR: Debe Seleccionar un Medico -- ");
		}		
	}
	
	public void guardar()
	{
		// Debug Prints
		System.out.println("Modificando Entrada Para Medico:");
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
		
		wizardWindow0.dispose();
		wizardWindow1.dispose();
		wizardWindow2.dispose();
	}
	
	public void cancelar()
	{
		wizardWindow0.dispose();
		wizardWindow1.dispose();
		wizardWindow2.dispose();
	}

}
