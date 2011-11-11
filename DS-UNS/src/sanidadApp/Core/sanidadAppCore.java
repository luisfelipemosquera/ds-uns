package sanidadApp.Core;

import javax.swing.JPanel;

import sMySQLappTemplate.Core.AppCoreTemplate;
import sMySQLappTemplate.Core.Command;
import sMySQLappTemplate.Core.FeatureTemplate;
import sMySQLappTemplate.Exceptions.InvalidButtonLocation;
import sanidadApp.Features.Alta_Medicos;
import sanidadApp.Features.Alta_Turnos;
import sanidadApp.Features.Eliminacion_Medicos;
import sanidadApp.Features.Modificacion_Medicos;
import sanidadApp.GUI.MainWindowSanidad;

public class sanidadAppCore extends AppCoreTemplate 
{
	protected MainWindowSanidad masterWindow;
	
	protected FeatureTemplate altaMedicos;
	protected FeatureTemplate modificacionMedicos;
	protected FeatureTemplate eliminacionMedicos;
	
	protected FeatureTemplate altaTurnos;
	
	
	protected TablaTurnos turnsData;
	
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
		
		altaMedicos = new Alta_Medicos(this);
		modificacionMedicos = new Modificacion_Medicos(this);
		eliminacionMedicos = new Eliminacion_Medicos(this);
		
		altaTurnos = new Alta_Turnos(this);		
	}

	@Override
	protected void initGUI() {
		// TODO Auto-generated method stub
		masterWindow = new MainWindowSanidad(this);
		turnsData = new TablaTurnos();
		actualizarTurnData();
		masterWindow.setTablaPrincipal(turnsData);
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

	private void actualizarTurnData() {
		// TODO Auto-generated method stub
		
	}
}
