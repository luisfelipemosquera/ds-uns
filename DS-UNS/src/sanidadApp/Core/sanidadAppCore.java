package sanidadApp.Core;

import sMySQLappTemplate.Core.AppCoreTemplate;
import sanidadApp.GUI.MainWindowSanidad;

public class sanidadAppCore extends AppCoreTemplate 
{
	MainWindowSanidad rootWindow;
	
	public sanidadAppCore()
	{
		super();
		
		rootWindow = new MainWindowSanidad(this);
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

}
