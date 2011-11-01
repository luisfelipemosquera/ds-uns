package sanidadApp.Features;


import javax.swing.JOptionPane;

import sMySQLappTemplate.Core.Command;
import sMySQLappTemplate.Core.FeatureTemplate;
import sanidadApp.Core.sanidadAppCore;
import sanidadApp.GUI.WizardDatosMedico_Seleccionar;

public class Eliminacion_Medicos extends Modificacion_Medicos 
{
	protected WizardDatosMedico_Seleccionar wizardWindow0;
	
	protected TablaMedicos TablaMedicosExistentes;
	
	protected int medico;

	public Eliminacion_Medicos(sanidadAppCore app) 
	{	
		this.appCore = app;
		app.registerButtonForTools(new GestionMedicos(this), "/images/rem_medic_64.png", "Eliminar Personal Medico");
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
			
			TablaMedicosExistentes = new TablaMedicos();
			
			wizardWindow0 = new WizardDatosMedico_Seleccionar((Eliminacion_Medicos)this.receiver);			
			
			wizardWindow0.setSigButtonText("Finalizar");
									
			// Datos de Prueba
			TablaMedicosExistentes.setRowCount(1);
			TablaMedicosExistentes.setValueAt("Gonzalez", 0, 0);
			TablaMedicosExistentes.setValueAt("Juan Domingo", 0, 1);
			TablaMedicosExistentes.setValueAt("LE", 0, 2);
			TablaMedicosExistentes.setValueAt("31298030", 0, 3);
			// Fin Datos de Prueba			
			
			wizardWindow0.viewMedicos(TablaMedicosExistentes);			
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
			
			if(cargarDatos(tipoDoc, numeroDoc, apellido, nombre))
			{
				this.guardar();
			}
		}
		catch(Exception e)
		{
			wizardWindow0.setTitle(" -- ERROR: Debe Seleccionar un Medico -- ");
		}		
	}
	
	@SuppressWarnings("static-access")
	public void guardar()
	{
		JOptionPane confirm = new JOptionPane();
		int res = confirm.showConfirmDialog(null, "Esta seguro que desea eliminar al medico " + apellido);
		if (res == JOptionPane.YES_OPTION)
		{
			// Debug Prints
			System.out.println("Eliminando Entrada Para Medico:");
			System.out.println("-- Comienzo de Entrada --"); 
			System.out.println("Tipo DNI: " + tipoDoc);
			System.out.println("Numero DNI: " + numeroDoc);
			System.out.println("Apellido: " + apellido);
			System.out.println("Nombres: " + nombre);
		    System.out.println("-- Fin de Entrada --"); 
		    System.out.println();
		    wizardWindow0.dispose();
		}
		else if (res == JOptionPane.CANCEL_OPTION)
		{
			wizardWindow0.dispose();
		}
	}
	
	public void cancelar()
	{
		wizardWindow0.dispose();
	}

}
