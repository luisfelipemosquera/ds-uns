package sMySQLappTemplate.GUI;

import java.awt.*;

@SuppressWarnings("serial")
public abstract class MainWindowTemplate extends javax.swing.JFrame
{		
	public MainWindowTemplate()
	{
		super();
		
		// Inicializacion de la Ventana Principal
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		float scale = 1.2f;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();		
		this.setSize((int)(scrnsize.width/scale), (int)(scrnsize.height/scale));
		this.setLocationRelativeTo(getRootPane()); 
	}
}
