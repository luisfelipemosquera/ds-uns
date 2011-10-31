package sanidadApp.Features;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import sMySQLappTemplate.Core.AppCoreTemplate;
import sMySQLappTemplate.Core.Command;
import sMySQLappTemplate.Core.FeatureTemplate;
import sMySQLappTemplate.Core.Fechas;
import sanidadApp.Core.sanidadAppCore;
import sanidadApp.GUI.FormularioAltaMedicos;

public class AltaMedicos extends FeatureTemplate
{	
	protected Date today;
	
	protected FormularioAltaMedicos Formulario;
	
	public AltaMedicos(sanidadAppCore app)
	{
		super(app);
	}	
	
	protected void registarBoton(sanidadAppCore app)
	{
		app.registerButtonForReservasTools(new AltaPersonalMedico(this), "/images/Whack Notepad ++ modificar.png", "Modificar Solicitud (Agregar Pedido)");
	}	
	
	@SuppressWarnings("unchecked")
	private class AltaPersonalMedico extends Command
	{

		public AltaPersonalMedico(FeatureTemplate feature) {
			super(feature);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Object ExecCommand(Object... args)
		{
			try {
				today = (Date)appCore.getValue("SELECT CURDATE();");
			} catch (SQLException e) {
				// TODO	JOptionPane.showMessageDialog(null, "Se produjo un Error al intentar consultar la fecha actual", "- ERROR MOLESTO -", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return null;
			}
			Formulario = new FormularioAltaMedicos(this.receiver);
			return null;
		}		
	}
}