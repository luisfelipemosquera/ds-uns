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
			tablaEspExistentes = new TablaEspecialidades();
					
			
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













