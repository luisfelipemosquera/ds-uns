package sMySQLappTemplate.GUI;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

// implentacion para botoncitos con imagenes y eso

@SuppressWarnings("serial")
public class MyButton extends JButton 
{	
	protected Border defaultBorder;
	protected Border rolloverBorder;
	protected Border presedBorder;
	
	public MyButton (String imagePath, String toolTip)
	{
		super();
		
		this.setToolTipText(toolTip);
		this.setContentAreaFilled(false);
		this.setIcon(createImageIcon(imagePath, toolTip));
		
		Dimension bSize = new Dimension(this.getIcon().getIconHeight()+2,
										this.getIcon().getIconWidth()+2);
		this.setSize(bSize);
		this.setMinimumSize(bSize);
		this.setMaximumSize(bSize);
		this.setPreferredSize(bSize);
		
		this.defaultBorder = BorderFactory.createEmptyBorder();
		this.rolloverBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
		this.presedBorder = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
		
		this.setBorder(defaultBorder);
		
		this.setFocusable(false);
				
		this.addMouseListener(new MouseAdapter() 
		{
	         public void mouseEntered(MouseEvent e) {
	        	 if (e.getComponent().isEnabled())
	        		((MyButton)e.getComponent()).setBorder(rolloverBorder);
	         }

	         public void mouseExited(MouseEvent e) {
	        	 ((MyButton)e.getComponent()).setBorder(defaultBorder);
	         }
	         
	         public void mousePressed(MouseEvent e) {
	        	 if (e.getComponent().isEnabled())
	        		((MyButton)e.getComponent()).setBorder(presedBorder);	        	 
	         }	         
	         
	         public void mouseReleased(MouseEvent e) {
	        	 if (e.getComponent().isEnabled())
	        		((MyButton)e.getComponent()).setBorder(rolloverBorder);	        	 
	         }
	    });
	}
	
	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path, String description) 
	{
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL, description);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}
	
	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);
		this.setBorder(defaultBorder);		
	}
}
