package sMySQLappTemplate.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class MyNotification extends JPanel
{	
	protected static ImageIcon warningIcon;
	protected Dimension dimension;	
	
	protected Border defaultBorder;
	protected Border rolloverBorder;
	protected Border presedBorder;
	
	protected int id;
	protected String type;
	protected String msg;
	
	public MyNotification(int id, String type, String msg)
	{
		this.id = id;
		this.type = type;
		this.msg = msg;
		
		if (warningIcon == null)
			warningIcon = createImageIcon("/images/Warning-icon.png");
		
		defaultBorder = new MyRoundBorder(4);
		rolloverBorder = new MyRoundBorder(4, Color.GREEN);
		presedBorder =new MyRoundBorder(4, Color.YELLOW);
		
		this.setBorder(defaultBorder);
		this.setLayout(null);
		JLabel jLabelWarning = new JLabel();
		jLabelWarning.setIcon(warningIcon);
		jLabelWarning.setBounds(10, 10, 32, 32);
		this.add(jLabelWarning);
		
		JTextPane jTextPaneMsg = new JTextPane();
		jTextPaneMsg.setText(msg);		
		jTextPaneMsg.setBounds(54, 10, 116, 50);
		jTextPaneMsg.setEnabled(false);
		jTextPaneMsg.setDisabledTextColor(Color.darkGray);
		
		this.add(jTextPaneMsg);
		
		dimension = new Dimension(180,90);
		this.setSize(dimension);
		this.setPreferredSize(dimension);
		this.setMinimumSize(dimension);
		this.setMaximumSize(dimension);
		
		this.addMouseListener(new MouseAdapter() 
		{
	         public void mouseEntered(MouseEvent e) {
	        	 if (e.getComponent().isEnabled())
	        		((MyNotification)e.getComponent()).setBorder(rolloverBorder);
	         }

	         public void mouseExited(MouseEvent e) {
	        	 ((MyNotification)e.getComponent()).setBorder(defaultBorder);
	         }
	         
	         public void mousePressed(MouseEvent e) {
	        	 if (e.getComponent().isEnabled())
	        		((MyNotification)e.getComponent()).setBorder(presedBorder);	        	 
	         }	         
	         
	         public void mouseReleased(MouseEvent e) {
	        	 if (e.getComponent().isEnabled())
	        		((MyNotification)e.getComponent()).setBorder(rolloverBorder);	        	 
	         }
	    });		
	}
	
	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path) 
	{
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL);
	    } else {
	        System.out.println("Couldn't find file: " + path);
	        return null;
	    }
	}

}
