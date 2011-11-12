package sanidadApp.Features;


import java.sql.SQLException;

import javax.swing.JOptionPane;

import sMySQLappTemplate.Core.Command;
import sMySQLappTemplate.Core.FeatureTemplate;
import sanidadApp.Core.sanidadAppCore;
import sanidadApp.GUI.WizardDatosMedico_Seleccionar;

public class Eliminacion_Medicos extends Modificacion_Medicos 
{
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
			TablaMedicosExistentes = new TablaMedicos();
			
			wizardWindow0 = new WizardDatosMedico_Seleccionar((Eliminacion_Medicos)this.receiver);			
			
			wizardWindow0.setSigButtonText("Finalizar");
									
			loadMedicos();		
			
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
		JOptionPane contingencia = new JOptionPane();
		int res = contingencia.showConfirmDialog(null, "Esta seguro que desea eliminar al medico " + apellido);
		if (res == JOptionPane.YES_OPTION)
		{
			String SQLQueryEliminarMedico =
				"DELETE FROM Medico WHERE " +
				"	Doc_Tipo = '" + tipoDoc + "' AND" +
				"	Doc_Numero = " + numeroDoc + ";";
			try {
				appCore.sendCommand(SQLQueryEliminarMedico);
				
			} catch (SQLException e) {
				contingencia.showMessageDialog(	
						null, e.getMessage(), 
						"ERROR: " + e.getErrorCode(), 
						JOptionPane.ERROR_MESSAGE
				);
			}
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
