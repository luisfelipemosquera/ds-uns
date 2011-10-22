package sMySQLappTemplate.GUI;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.border.Border;

public class MyRoundBorder implements Border 
{
	  protected int dist;	

	  public MyRoundBorder(int dist)
	  {
	    this.dist = dist;
	  }

	  public Insets getBorderInsets(Component component) 
	  {
	      return new Insets(dist, dist, dist, dist);
	  }

	  public boolean isBorderOpaque() {
	      return false;
	  }

	  public void paintBorder(Component component, Graphics g, int x, int y, int w, int h) 
	  {
	      g.setColor(SystemColor.controlShadow);
	      g.drawRoundRect(x + dist / 2, y + dist / 2, w - dist, h - dist, 8, 8);     
	  }
}
