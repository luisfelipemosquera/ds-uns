package sMySQLappTemplate.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Date;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class MyTable extends JTable
{
	private int rollOverRowIndex = -1;
	
	public MyTable(DefaultTableModel data)
	{
		super(data);
		initGUI();
	}
	
	public MyTable ()
	{ 		
		super();
		initGUI();
	}
	
	private void initGUI()
	{
		this.setColumnSelectionAllowed(false);
		this.setRowSelectionAllowed(true);
		this.setCellSelectionEnabled(false);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setShowGrid(false);
		
		TableCellRenderer tcr = new noBorderRenderer();
		this.setDefaultRenderer(String.class, tcr);
		this.setDefaultRenderer(Integer.class, tcr);
		this.setDefaultRenderer(Boolean.class, tcr);
		this.setDefaultRenderer(Date.class, tcr);
		
		RollOverListener lst = new RollOverListener();
		addMouseMotionListener(lst);
		addMouseListener(lst);
	}
	
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) 
	{
		Component c = super.prepareRenderer(renderer, row, column);
		
		if (isRowSelected(row))		
		{
			c.setForeground(getSelectionForeground());
			c.setBackground(getSelectionBackground());			
		}
		else if(row == rollOverRowIndex) 
		{
			c.setForeground(Color.DARK_GRAY);
			c.setBackground(Color.CYAN);
		}
		else
		{
			c.setForeground(getForeground());
			c.setBackground(getBackground());
		}

		return c;
	}
	
	public class noBorderRenderer extends JButton implements TableCellRenderer
	{
		public noBorderRenderer()
		{
			this.setBorderPainted(false);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) 
		{
	        if (isSelected) {
	        	// cell (and perhaps other cells) are selected
	        }

	        if (hasFocus) {
	            // this cell is the anchor and the table has the focus
	        }
			return this;
		}
		
		// The following methods override the defaults for performance reasons
	    public void validate() {}
	    public void revalidate() {}
	    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {}
	    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {}
		
	}
	
	private class RollOverListener extends MouseInputAdapter 
	{
		public void mouseExited(MouseEvent e) 
		{
			rollOverRowIndex = -1;
			repaint();
		}

		public void mouseMoved(MouseEvent e) 
		{
			int row = rowAtPoint(e.getPoint());
			if( row != rollOverRowIndex ) 
			{
				rollOverRowIndex = row;
				repaint();
			}
		}
	}
}
