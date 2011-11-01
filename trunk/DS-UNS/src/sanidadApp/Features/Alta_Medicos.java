package sanidadApp.Features;

import java.sql.Date;

import sMySQLappTemplate.Core.Command;
import sMySQLappTemplate.Core.FeatureTemplate;
import sanidadApp.Core.sanidadAppCore;
import sanidadApp.GUI.WizardDatosMedico_1;
import sanidadApp.GUI.WizardDatosMedico_2;

public class Alta_Medicos extends FeatureTemplate
{	
	protected Date today;
	
	protected WizardDatosMedico_1 wizardWindow1;
	protected WizardDatosMedico_2 wizardWindow2;
	
	protected String TipoDNI;
	protected long NumeroDNI;
	protected String Apellido;
	protected String Nombre;
	
	public Alta_Medicos(sanidadAppCore app)
	{
		super(app);
		app.registerButtonForTools(new GestionMedicos(this), "/images/add_medic_64.png", "Administrar Personal Medico");
	}	
	
	@SuppressWarnings("unchecked")
	private class GestionMedicos extends Command
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
			wizardWindow1 = new WizardDatosMedico_1((Alta_Medicos)this.receiver);
			wizardWindow2 = new WizardDatosMedico_2((Alta_Medicos)this.receiver);
			wizardWindow1.setVisible(true);
			return null;
		}		
	}
	
	public void siguiente()
	{
		wizardWindow1.setVisible(false);
		wizardWindow2.setVisible(true);
	}

	public void anterior()
	{
		wizardWindow2.setVisible(false);
		wizardWindow1.setVisible(true);
	}
	
	public void guardar()
	{
		
	}
	
	public void cancelar()
	{
		wizardWindow1.dispose();
		wizardWindow2.dispose();
	}
	
	
	public void addEsp(String esp)
	{
		
	}
	
	public void remEsp(String esp)
	{
		
	}
}













