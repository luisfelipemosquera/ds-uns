package sanidadApp.Features;

import javax.swing.JOptionPane;

import sMySQLappTemplate.Core.Command;
import sMySQLappTemplate.Core.FeatureTemplate;
import sanidadApp.Core.sanidadAppCore;

public class Cancelacion_Turno extends FeatureTemplate
{
	protected int turnoSeleccionado;
	
	public Cancelacion_Turno(sanidadAppCore app)
	{
		this.appCore = app;
		app.registerButtonForTools(new GestionTurnos(this), "/images/MedicTurn64_rem.png", "Cancelar Turno");
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
				JOptionPane confirm = new JOptionPane();
				String msg = "Esta seguro de eliminar el turno? " +
							 "esto podria causar problemas si el mismo " +
							 "se encuentra reservado.";
				int res = confirm.showConfirmDialog(null, msg);
				if (res == JOptionPane.YES_OPTION)
				{
					// TODO codigo para eliminar turno
				}
				else if (res == JOptionPane.CANCEL_OPTION)
				{
					// TODO supongo que nada
				}
			}
			return null;
		}		
	}
}
