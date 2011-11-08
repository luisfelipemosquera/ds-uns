package sanidadApp.Features;

import sMySQLappTemplate.Core.Command;
import sMySQLappTemplate.Core.FeatureTemplate;
import sanidadApp.Core.sanidadAppCore;
import sanidadApp.GUI.WizardDatosTurno;

public class Alta_Turnos extends FeatureTemplate
{
	protected WizardDatosTurno wizardWindow;
	
	protected TablaMedicos TablaMedicosExistentes;
	protected TablaEspecialidades tablaEspExistentes;
	
	public Alta_Turnos(sanidadAppCore app)
	{
		this.appCore = app;
		app.registerButtonForTools(new GestionTurnos(this), "/images/MedicTurn64.png", "Nuevo Turno");
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
			tablaEspExistentes = new TablaEspecialidades();
			
			// Datos de Prueba
			tablaEspExistentes.setRowCount(3);
			tablaEspExistentes.setValueAt("Especialidad X", 0, 0);
			tablaEspExistentes.setValueAt("Especialidad Y", 1, 0);
			tablaEspExistentes.setValueAt("Especialidad Z", 2, 0);
			
			TablaMedicosExistentes.setRowCount(1);
			TablaMedicosExistentes.setValueAt("Gonzalez", 0, 0);
			TablaMedicosExistentes.setValueAt("Juan Domingo", 0, 1);
			TablaMedicosExistentes.setValueAt("LE", 0, 2);
			TablaMedicosExistentes.setValueAt("31298030", 0, 3);
			// Fin Datos de Prueba			
			
			wizardWindow = new WizardDatosTurno((Alta_Turnos)this.receiver);
			wizardWindow.setTitle("Nuevo Turno");
			
			wizardWindow.viewMedicos(TablaMedicosExistentes);
			wizardWindow.viewEspExistentes(tablaEspExistentes);
			
			wizardWindow.setVisible(true);
			return null;
		}		
	}
	
	public void guardar()
	{
		System.out.println("Guardando...");
		wizardWindow.dispose(); 
	}
	
	public void cancelar()
	{
		wizardWindow.dispose();
	}	
}













