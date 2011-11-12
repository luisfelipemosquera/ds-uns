package sanidadApp.Features;

import java.sql.SQLException;

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
	private String tipoConsulta;
	
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
			wizardWindow1.setTitle("Nuevo Turno");
			
			wizardWindow1.viewMedicos(TablaMedicosExistentes);
			wizardWindow1.viewTiposConsulta(tablaTiposConsulta);
			
			wizardWindow2 = new WizardDatosTurno_Fecha((Alta_Turnos)this.receiver);
			
			wizardWindow1.setVisible(true);
			return null;
		}		
	}
	
	public void siguiente(int tipoConsultaSeleccionada) 
	{
		wizardWindow1.setTitle("Nuevo Turno");
		
		if(docType.isEmpty() || docNumber.isEmpty())
		{
			wizardWindow1.setTitle("ERROR: Debe seleccionar un medico");
			return;
		}
		if(tipoConsultaSeleccionada < 0)
		{
			wizardWindow1.setTitle("ERROR: Debe elegir un tipo de consulta para el turno");
			return;
		}
		tipoConsulta = tablaTiposConsulta.getValueAt(tipoConsultaSeleccionada, 0).toString();
		wizardWindow1.setVisible(false);
		wizardWindow2.setVisible(true);

	}
	
	public void anterior() {
		wizardWindow2.setVisible(false);
		wizardWindow1.setVisible(true);		
	}
	
	public void guardar()
	{
		System.out.println("Guardando...");
		wizardWindow1.dispose(); 
		wizardWindow2.dispose();
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
	
	public void medico_seleccionado(int medic)
	{
		docType = TablaMedicosExistentes.getValueAt(medic, 2).toString();
		docNumber = TablaMedicosExistentes.getValueAt(medic, 3).toString();
		
		loadTipoConsulta();
	}
}













