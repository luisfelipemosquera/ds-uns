package sanidadApp.Features;


import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;

import sMySQLappTemplate.Core.Command;
import sMySQLappTemplate.Core.FeatureTemplate;
import sanidadApp.Core.sanidadAppCore;
import sanidadApp.GUI.WizardDatosMedico_Seleccionar;

public class Baja_Medicos extends Modificacion_Medicos 
{
	public Baja_Medicos(sanidadAppCore app) 
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
			
			wizardWindow0 = new WizardDatosMedico_Seleccionar((Baja_Medicos)this.receiver);			
			
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
		
		String afectados = calcularAfectados();
		String msg = null;
		if (afectados.isEmpty())
			msg = "Esta seguro que desea dar de baja al medico " + apellido + "\n" + 
			      "Actualmente ningun paciente se vera afectado";
		else
			msg = "Esta seguro que desea dar de baja al medico " + apellido + "\n" +
				  "Los siguentes pacientes tienen turno con el: \n" +
				  afectados;
		
		System.out.println(afectados);
		
		int res = contingencia.showConfirmDialog(null,  msg);
		if (res == JOptionPane.YES_OPTION)
		{			
			String SQLQuerySeleccionarConsultasAfectadas = 
				"CREATE TEMPORARY TABLE ConsultasAfectadas (" +
				"SELECT DISTINCT Consulta.ID " +
				"	FROM  " +
				"		Consulta NATURAL JOIN  " +
				"		IntraConsulta JOIN Turno " +
				"	WHERE  " +
				"		Consulta_ID = Consulta.ID AND " +
				"		IntraConsulta.Turno_ID = Turno.ID AND " +
				"		Medico_Doc_Tipo = '" + tipoDoc + "' AND    " +
				"		Medico_Doc_Numero = " + numeroDoc + " " +
				"); ";
			
			String SQLQueryEliminarConsultas =
				"DELETE FROM Consulta " +
				"WHERE EXISTS ( " +
				"	SELECT ConsultasAfectadas.ID FROM ConsultasAfectadas " +
				"	WHERE ConsultasAfectadas.ID = Consulta.ID); ";
			
			String SQLQueryEliminarMedico =
				"DELETE FROM Medico WHERE " +
				"	Doc_Tipo = '" + tipoDoc + "' AND" +
				"	Doc_Numero = " + numeroDoc + ";";
			
			try {
				
				appCore.sendCommand("START TRANSACTION;");
					appCore.sendCommand(SQLQuerySeleccionarConsultasAfectadas);
					appCore.sendCommand(SQLQueryEliminarConsultas);
				appCore.sendCommand("COMMIT;");
				
				appCore.sendCommand(SQLQueryEliminarMedico);
				
				((sanidadAppCore)appCore).actualizarTablaTurnos();
				
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
	
	private String calcularAfectados() 
	{
		String SQLqueryAfectados = 
			"SELECT DISTINCT Apellido, Nombre " +
			"   FROM "+
			"	  Consulta NATURAL JOIN Persona NATURAL JOIN  " +
			"	  IntraConsulta JOIN Turno " +
			"   WHERE  " +
			"	  Paciente_Doc_Tipo = Persona.Doc_Tipo AND    " +
			"	  Paciente_Doc_Numero = Persona.Doc_Numero AND " +
			"	  Consulta_ID = Consulta.ID AND " +
			"	  IntraConsulta.Turno_ID = Turno.ID AND " +
			"	  Medico_Doc_Tipo = '" + tipoDoc + "' AND    " +
			"	  Medico_Doc_Numero = " + numeroDoc + "; ";
		
		System.out.println(SQLqueryAfectados);
		
		StringBuffer afectados = new StringBuffer();
		
		try {
			CachedRowSet rs = appCore.sendConsult(SQLqueryAfectados);
			while(rs.next())
				afectados.append("  - " + rs.getString(1) + " " + rs.getString(2) + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new String(afectados);
	}

	public void cancelar()
	{
		wizardWindow0.dispose();
	}

}
