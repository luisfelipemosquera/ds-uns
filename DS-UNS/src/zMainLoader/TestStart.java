package zMainLoader;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import sanidadApp.Core.sanidadAppCore;

import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;

// sopadmop2901dj209jaiopfja0j1390¡'hq89hciawvhweweholadjfaweaweffawew2effe12325¡'3ffwewefwewffewefefw

public class TestStart 
{	
	public static void main(String[] args)
	{	
		/*
	    try {
		    // Set System L&F
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
	    */
		

	    try { // Set Synthetica Skin
	    	UIManager.setLookAndFeel(new SyntheticaSimple2DLookAndFeel());
			SwingUtilities.invokeLater(new Runnable() 
			{
		         public void run() 
		         {
		        	 sanidadAppCore AppCore = new sanidadAppCore();
		         }
		    });
	    } 
	    catch (Exception e) {
	    	// TODO cartelito de imposible cargar skin
	    	e.printStackTrace();
	    }
	}
}
