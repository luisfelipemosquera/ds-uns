package sanidadApp.Core;

import javax.swing.JPanel;

import sMySQLappTemplate.Core.AppCoreTemplate;
import sMySQLappTemplate.Core.Command;
import sMySQLappTemplate.Core.FeatureTemplate;
import sMySQLappTemplate.Exceptions.InvalidButtonLocation;
import sanidadApp.Features.Alta_Medicos;
import sanidadApp.GUI.MainWindowSanidad;

public class sanidadAppCore extends AppCoreTemplate 
{
	MainWindowSanidad masterWindow;
	
	FeatureTemplate gestionMedicos;
	
	public sanidadAppCore()
	{
		super();
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void initFeatures() {
		// TODO Auto-generated method stub
		gestionMedicos = new Alta_Medicos(this);
		
	}

	@Override
	protected void initGUI() {
		// TODO Auto-generated method stub
		masterWindow = new MainWindowSanidad(this);
	}
	
	// METODOS PARA REGISTRA BOTONES
	
	@SuppressWarnings("unchecked")
	public void registerButtonForTools(Command featureComm, String iconPath, String buttonLabel)
	{
		try {
			this.registerNewButtonFor(featureComm, this.masterWindow.tools, iconPath, buttonLabel);
		} catch (InvalidButtonLocation e) {
			// TODO cartelito de erro interno
			e.printStackTrace();
		}
	}

}
