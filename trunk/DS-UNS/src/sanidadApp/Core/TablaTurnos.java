package sanidadApp.Core;

@SuppressWarnings("serial")
public class TablaTurnos extends javax.swing.table.DefaultTableModel 
{
	public TablaTurnos()
	{
		super
		(
			new String[][] {},
	        new String[] {"Estado", "Dia", "Hora", "Apellido Doctor", "Nombre Doctor", "ID Turno"}
		);
	}
	
	public boolean isCellEditable (int row, int column)
	{
		return false;
	}
}
