package sanidadApp.GUI;
import java.awt.BorderLayout;
import java.sql.Date;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.michaelbaranov.microba.calendar.CalendarPane;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
@SuppressWarnings("serial")
public class CalendarioTest extends JFrame
{	
	static JFrame My;
	private JButton jButton1;
	private JScrollPane jScrollPane1;

	public CalendarioTest()
	{
		{
			jButton1 = new JButton();
			getContentPane().add(jButton1);
			jButton1.setText("Hola");
			jButton1.setBounds(48, 35, 128, 79);
		}
		{
			jScrollPane1 = new JScrollPane();
			getContentPane().add(jScrollPane1);
			jScrollPane1.setBounds(218, 35, 296, 248);
		}
		{
			this.setLayout(null);
			this.setSize(541, 335);
		}
		{
			CalendarPane cp = new CalendarPane();
			cp.setEnabled(true);
			cp.setVisible(true);
			jScrollPane1.setViewportView(cp);
		}
	}
	
	public static void main (String[] args)
	{
		/*
	    try { // Set Synthetica Skin
	    	UIManager.setLookAndFeel(new SyntheticaSimple2DLookAndFeel());
			SwingUtilities.invokeLater(new Runnable() 
			{
		         public void run() 
		         {
		        	 new CalendarioTest();
		         }
		    });
	    } 
	    catch (Exception e) {
	    	// TODO cartelito de imposible cargar skin
	    	e.printStackTrace();
	    }
	    */
		My = new CalendarioTest();
		My.setVisible(true);
	}
}
