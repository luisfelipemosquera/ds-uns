package sanidadApp.Features;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
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

		private String apellido;
		private String nombre;

		public GestionTurnos(FeatureTemplate feature) {
			super(feature);
			// TODO Auto-generated constructor stub
		}

		@SuppressWarnings("static-access")
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
				
				String msg = null;
				String SQLqueryReservadoPor =
				   "SELECT Apellido, Nombre " +
				   "  FROM Persona NATURAL JOIN IntraConsulta NATURAL JOIN Consulta " +
				   "  WHERE " +
				   "     Consulta_ID = Consulta.ID AND " +
				   "	 Paciente_Doc_Tipo = Persona.Doc_Tipo AND " +
				   "	 Paciente_Doc_Numero = Persona.Doc_Numero AND " +
				   "	 IntraConsulta.Turno_ID = " + turnoSeleccionado + ";" ;
				
				try {
					CachedRowSet rs = appCore.sendConsult(SQLqueryReservadoPor);
					if (rs.next())
					{
						apellido = rs.getString(1);
						nombre = rs.getString(2);						
						msg = "Esta seguro de eliminar este turno? \n" +
							  apellido + " " + nombre + " lo tiene reservado";
					}
					else
					{
						msg = "Esta seguro de eliminar este turno? \n" +
						      "el turno no esta reservado";
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				int res = confirm.showConfirmDialog(null, msg);
				if (res == JOptionPane.YES_OPTION)
				{
					String SQLqueryDeleteConsulta = 
						"DELETE FROM Consulta " +
						" WHERE ID = " +
						" (SELECT Consulta_ID FROM IntraConsulta " +
						"    WHERE Turno_ID = " + turnoSeleccionado + "); ";
					String SQLqueryDeleteTurno =
						"DELETE FROM Turno WHERE Turno.ID = " + turnoSeleccionado + ";";
					try {
						appCore.sendCommand(SQLqueryDeleteConsulta);
						appCore.sendCommand(SQLqueryDeleteTurno);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					((sanidadAppCore)appCore).actualizarTablaTurnos();
				}
			}
			return null;
		}		
	}
}
