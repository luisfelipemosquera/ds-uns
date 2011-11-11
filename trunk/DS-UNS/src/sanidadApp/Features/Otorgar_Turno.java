package sanidadApp.Features;

import javax.swing.JOptionPane;

import sMySQLappTemplate.Core.Command;
import sMySQLappTemplate.Core.FeatureTemplate;
import sanidadApp.Core.sanidadAppCore;

public class Otorgar_Turno extends FeatureTemplate
{
	protected int turnoSeleccionado;
	
	public Otorgar_Turno(sanidadAppCore app)
	{
		this.appCore = app;
		app.registerButtonForTools(new GestionTurnos(this), "/images/Otorgar_Turno.png", "Otorgar Turno");
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
			turnoSeleccionado = ((sanidadAppCore)appCore).getSelectedTurno();
			if (turnoSeleccionado < 0)
			{
				JOptionPane confirm = new JOptionPane();
				String msg = "Debe Seleccionar un turno para eliminar";
				confirm.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
			}
			return null;
		}		
	}
}
