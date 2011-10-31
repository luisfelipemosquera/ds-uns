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
import sanidadApp.GUI.VentanaGestionMedicos;

public class ABM_Medicos extends FeatureTemplate
{	
	protected Date today;
	
	protected VentanaGestionMedicos ventana;
	
	public ABM_Medicos(sanidadAppCore app)
	{
		super(app);
		app.registerButtonForTools(new GestionMedicos(this), "/images/Admin_Medicos_64.png", "Administrar Personal Medico");
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
			ventana = new VentanaGestionMedicos(this.receiver);
			return null;
		}		
	}
}