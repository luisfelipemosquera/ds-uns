package sanidadApp.Features;

import java.util.HashMap;

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
			
			TablaEspExistentes = new TablaEspecialidades();
			TablaEspPoseidas = new TablaEspecialidades();
			TablaMedicosExistentes = new TablaMedicos();
			
			wizardWindow0 = new WizardDatosMedico_Seleccionar((Modificacion_Medicos)this.receiver);
			
			wizardWindow1 = new WizardDatosMedico_Persona((Modificacion_Medicos)this.receiver);
			wizardWindow2 = new WizardDatosMedico_Especialidades((Modificacion_Medicos)this.receiver);
			
			wizardWindow0.viewMedicos(TablaMedicosExistentes);
			wizardWindow2.viewEspExistentes(TablaEspExistentes);
			wizardWindow2.viewEspPoseidas(TablaEspPoseidas);
			
			wizardWindow0.setVisible(true);
			
			// Datos de Prueba
			TablaEspExistentes.setRowCount(3);
			TablaEspExistentes.setValueAt("Especialidad X", 0, 0);
			TablaEspExistentes.setValueAt("Especialidad Y", 1, 0);
			TablaEspExistentes.setValueAt("Especialidad Z", 2, 0);
			TablaEspPoseidas.setRowCount(1);
			TablaEspPoseidas.setValueAt("Especialidad R", 0, 0);
			
			TablaMedicosExistentes.setRowCount(1);
			TablaMedicosExistentes.setValueAt("Gonzalez", 0, 0);
			TablaMedicosExistentes.setValueAt("Juan Domingo", 0, 1);
			TablaMedicosExistentes.setValueAt("DNI", 0, 2);
			TablaMedicosExistentes.setValueAt("31298030", 0, 3);
			// Fin Datos de Prueba
			
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
			String tipoDNI = TablaMedicosExistentes.getValueAt(medicoSeleccionado, 2).toString();
			String numeroDNI = TablaMedicosExistentes.getValueAt(medicoSeleccionado, 3).toString();
			if(cargarDatos(tipoDNI, numeroDNI, apellido, nombre))
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
		System.out.println("Guardando Cambios");
	}
	
	public void cancelar()
	{
		wizardWindow0.dispose();
		wizardWindow1.dispose();
		wizardWindow2.dispose();
	}

}
