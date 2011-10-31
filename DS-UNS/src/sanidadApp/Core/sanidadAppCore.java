package sanidadApp.Core;

import javax.swing.JPanel;

import sMySQLappTemplate.Core.AppCoreTemplate;
import sMySQLappTemplate.Core.Command;
import sMySQLappTemplate.Exceptions.InvalidButtonLocation;
import sanidadApp.GUI.MainWindowSanidad;

public class sanidadAppCore extends AppCoreTemplate 
{
	MainWindowSanidad masterWindow;
	
	public sanidadAppCore()
	{
		super();
		
		masterWindow = new MainWindowSanidad(this);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initFeatures() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initGUI() {
		// TODO Auto-generated method stub
		
	}
	
	// METODOS PARA REGISTRA BOTONES
	
	@SuppressWarnings("unchecked")
	public void registerButtonForReservasTools(Command featureComm, String iconPath, String buttonLabel)
	{
		JPanel appTools = this.masterWindow.reservasTools;
		try {
			this.registerNewButtonFor(featureComm, appTools, iconPath, buttonLabel);
		} catch (InvalidButtonLocation e) {
			// TODO cartelito de erro interno
			e.printStackTrace();
		}
	}

}
