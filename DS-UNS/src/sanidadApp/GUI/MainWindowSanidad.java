package sanidadApp.GUI;

import sMySQLappTemplate.GUI.MyBorder;
import sMySQLappTemplate.GUI.MyButtonPanel;
import sMySQLappTemplate.GUI.MyMenuBar;
import sMySQLappTemplate.GUI.MyTable;
import sanidadApp.Core.sanidadAppCore;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class MainWindowSanidad extends sMySQLappTemplate.GUI.MainWindowTemplate
{		
	public MyButtonPanel tools;
	
	public JMenuBar mainMenuBar;
	
	public sanidadAppCore appCore;
	
	public MainWindowSanidad(sanidadAppCore AppCore)
	{
		super();
		
		this.appCore = AppCore;
		
		this.setTitle("Sanidad de la UNS");
		
		// INIT COMPONENTS :P
		
		JSplitPane basePanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		basePanel.setDividerSize(3);
		this.setContentPane(basePanel);
		
		// Barra de Menues
		mainMenuBar = new MyMenuBar();		
		this.setJMenuBar(mainMenuBar);
		
		// Panel de Seleccion de Reservas	
		JPanel applicationsPane = new JPanel(new BorderLayout());
		applicationsPane.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		applicationsPane.setBorder(new MyBorder(4,"Reservas del Día"));
		
		this.getContentPane().add(applicationsPane);
		
		// Panel de Herramientos de Reservas
		tools = new MyButtonPanel();
		
		applicationsPane.add(tools, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}

	public int getTablaReservasSelectedRow(){
		return 0;
	}
}
