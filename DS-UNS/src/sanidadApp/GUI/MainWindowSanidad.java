package sanidadApp.GUI;

import sMySQLappTemplate.GUI.MyBorder;
import sMySQLappTemplate.GUI.MyButtonPanel;
import sMySQLappTemplate.GUI.MyMenuBar;
import sMySQLappTemplate.GUI.MyNotification;
import sMySQLappTemplate.GUI.MyNotificationPane;
import sMySQLappTemplate.GUI.MyTable;
import sanidadApp.Core.sanidadAppCore;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class MainWindowSanidad extends sMySQLappTemplate.GUI.MainWindowTemplate
{		
	public MyButtonPanel tools;
	
	public JMenuBar mainMenuBar;
	
	public sanidadAppCore appCore;
	
	protected JScrollPane scrollPrincipal;
	protected MyTable tablaPrincipal;

	private MyNotificationPane rightPrincipalPane;
	
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
		
		// Panel de Seleccion de turnos	
		JPanel leftPrincipalPane = new JPanel(new BorderLayout());
		leftPrincipalPane.setPreferredSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		leftPrincipalPane.setBorder(new MyBorder(4,"Turnos"));
		
		this.getContentPane().add(leftPrincipalPane, JSplitPane.LEFT);
		
		scrollPrincipal = new JScrollPane();
		leftPrincipalPane.add(scrollPrincipal);
		
		// Panel de Notificaciones
		
		rightPrincipalPane = new MyNotificationPane();
		BoxLayout layout = new BoxLayout(rightPrincipalPane, BoxLayout.Y_AXIS);
		rightPrincipalPane.setLayout(layout);
		rightPrincipalPane.setBorder(new MyBorder(4,"Notificaciones"));
		
		this.getContentPane().add(rightPrincipalPane, JSplitPane.RIGHT);
			
		// Panel de Herramientos de Reservas
		tools = new MyButtonPanel();
		
		leftPrincipalPane.add(tools, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}

	public int getTablaPrincipalSelectedRow(){
		return tablaPrincipal.getSelectedRow();
	}
	
	public void setTablaPrincipal (DefaultTableModel table)
	{
		tablaPrincipal = new MyTable(table);
		scrollPrincipal.setViewportView(tablaPrincipal);
	}
	
	public MyNotificationPane getNotificationPane()
	{
		return rightPrincipalPane;
	}
}
