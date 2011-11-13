package sanidadApp.Features;

@SuppressWarnings("serial")
public class TablaTurnosDisponibles extends javax.swing.table.DefaultTableModel 
{
	public TablaTurnosDisponibles()
	{
		super
		(
			new String[][] {},
	        new String[] {"Dia", "Hora", "Apellido Doctor", "Nombre Doctor", "ID Turno"}
		);
	}
	
	public boolean isCellEditable (int row, int column)
	{
		return false;
	}
}
