package sMySQLappTemplate.GUI;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.SystemColor;

// Borde redondeado con barra de titulos improvisada

public class MyBorder extends MyRoundBorder 
{
	protected String title;
	
	public MyBorder(int dist, String title) 
	{
		super(dist);
		this.title = title;
	}
	
	public Insets getBorderInsets(Component component) 
	{
	    return new Insets(dist+20, dist, dist, dist);
	}

	public void paintBorder(Component component, Graphics g, int x, int y, int w, int h) 
	{
		super.paintBorder(component, g, x, y, w, h);
		g.setColor(SystemColor.controlShadow);
		g.drawRoundRect(x+6, y+6, 12, 12, 6, 6);
	    g.drawLine(x+dist/2, 21, x+w-dist/2, 21);
		g.setColor(SystemColor.controlText);
	    g.drawString(title, x+24, y+17);
	}
}
