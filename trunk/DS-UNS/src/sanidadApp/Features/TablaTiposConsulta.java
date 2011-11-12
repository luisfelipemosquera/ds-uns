package sanidadApp.Features;


@SuppressWarnings("serial")
public class TablaTiposConsulta extends javax.swing.table.DefaultTableModel 
{
	public TablaTiposConsulta()
	{
		super
		(
			new String[][] {},
	        new String[] {"Tipo Consulta"}
		);
	}
	
	public boolean isCellEditable (int row, int column)
	{
		return false;
	}
}

