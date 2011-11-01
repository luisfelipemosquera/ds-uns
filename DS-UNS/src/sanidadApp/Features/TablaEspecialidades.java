package sanidadApp.Features;


@SuppressWarnings("serial")
public class TablaEspecialidades extends javax.swing.table.DefaultTableModel 
{
	public TablaEspecialidades()
	{
		super
		(
			new String[][] {},
	        new String[] {"Especialidad"}
		);
	}
	
	public boolean isCellEditable (int row, int column)
	{
		return false;
	}
}

