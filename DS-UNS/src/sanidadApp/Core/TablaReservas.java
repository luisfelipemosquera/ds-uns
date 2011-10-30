package sanidadApp.Core;

@SuppressWarnings("serial")
public class TablaReservas extends javax.swing.table.DefaultTableModel 
{
	public TablaReservas()
	{
		super
		(
			new String[][] {},
	        new String[] {"Paciente", "DNI", "Hora", "Doctor"}
		);
	}
	
	public boolean isCellEditable (int row, int column)
	{
		return false;
	}
}
