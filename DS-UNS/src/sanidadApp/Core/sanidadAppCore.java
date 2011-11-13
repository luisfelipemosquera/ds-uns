package sanidadApp.Core;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JPanel;
import javax.swing.JTable;

import sMySQLappTemplate.Core.AppCoreTemplate;
import sMySQLappTemplate.Core.Command;
import sMySQLappTemplate.Core.FeatureTemplate;
import sMySQLappTemplate.Exceptions.InvalidButtonLocation;
import sanidadApp.Features.Alta_Medicos;
import sanidadApp.Features.Alta_Turnos;
import sanidadApp.Features.Cancelacion_Turno;
import sanidadApp.Features.Eliminacion_Medicos;
import sanidadApp.Features.Modificacion_Medicos;
import sanidadApp.Features.Otorgar_Turno;
import sanidadApp.GUI.MainWindowSanidad;

public class sanidadAppCore extends AppCoreTemplate 
{
	protected MainWindowSanidad masterWindow;
	
	protected FeatureTemplate otorgarTurno;
	
	protected FeatureTemplate altaMedicos;
	protected FeatureTemplate modificacionMedicos;
	protected FeatureTemplate eliminacionMedicos;
	
	protected FeatureTemplate altaTurnos;
	protected FeatureTemplate cancelacionTurnos;
	
	
	protected TablaTurnos tablaTurnos;
	
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
		
		otorgarTurno = new Otorgar_Turno(this);
		
		altaMedicos = new Alta_Medicos(this);
		modificacionMedicos = new Modificacion_Medicos(this);
		eliminacionMedicos = new Eliminacion_Medicos(this);
		
		altaTurnos = new Alta_Turnos(this);	
		cancelacionTurnos = new Cancelacion_Turno(this);
	}

	@Override
	protected void initGUI() {
		// TODO Auto-generated method stub
		masterWindow = new MainWindowSanidad(this);
		tablaTurnos = new TablaTurnos();
		actualizarTablaTurnos();
		masterWindow.setTablaPrincipal(tablaTurnos);
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

	public void actualizarTablaTurnos() {
		String SQLqueryTurnos = 
		"	SELECT IF(EXISTS(SELECT * FROM IntraConsulta WHERE Turno_ID = Turno.ID) , " +
		"			'Reservado', 'Disponible') AS Estado,							  " +
		"			 DATE_FORMAT(FechaHoraInicio, '%Y-%m-%d') AS Dia, 				  " +
		"	         DATE_FORMAT(FechaHoraInicio, '%T') AS Hora, 					  " +
		"			 Apellido, Nombre, Turno.ID 									  " +
		"	  FROM Turno NATURAL JOIN Persona										  " +
		"	  WHERE Medico_Doc_Tipo = Doc_Tipo AND									  " +
		"	        Medico_Doc_Numero = Doc_Numero									  " +
		"	  ORDER BY FechaHoraInicio												  " ;
		
		try {
			populateTable(tablaTurnos, SQLqueryTurnos);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int getSelectedTurno() 
	{
		int selectedRow = masterWindow.getTablaPrincipalSelectedRow();
		int idTurno = -1;
		
		try
		{
			idTurno = Integer.parseInt(tablaTurnos.getValueAt(selectedRow, 5).toString());
		}
		catch (Exception e)	{} // Ignore
		
		System.out.println(idTurno);

		
		return idTurno;
	}
}
