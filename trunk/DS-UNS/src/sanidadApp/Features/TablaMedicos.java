package sanidadApp.Features;


@SuppressWarnings("serial")
public class TablaMedicos extends javax.swing.table.DefaultTableModel 
{
	public TablaMedicos()
	{
		super
		(
			new String[][] {},
	        new String[] {"Apellido", "Nombre", "Tipo Doc.", "Numero Doc."}
		);
	}
	
	public boolean isCellEditable (int row, int column)
	{
		return false;
	}
}

