package sanidadApp.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.lavantech.gui.comp.TimePanel;
import com.michaelbaranov.microba.calendar.CalendarPane;

import sMySQLappTemplate.GUI.MyTable;
import sanidadApp.Features.Alta_Turnos;
import sanidadApp.Features.Reporte_Turnos_Dia;
import sanidadApp.Features.TablaEspecialidades;
import sanidadApp.Features.TablaMedicos;
import sanidadApp.Features.TablaTiposConsulta;


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
public class WizardReporteTurno_Reporte extends JDialog
{	
	private Reporte_Turnos_Dia control;
	private JScrollPane jScrollPaneTurnosReservados;
	private JButton jButtonAnterior;
	private JButton jButtonAceptar;
	private JLabel jLabelFechaInicio;

	public Reporte_Turnos_Dia getControl() {
		return control;
	}

	public WizardReporteTurno_Reporte(Reporte_Turnos_Dia receiver)
	{;
		this.control = receiver;
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent we) {
			    	(((WizardReporteTurno_Reporte) we.getWindow()).getControl()).cancelar();
			    }
			});
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setModal(true);
		this.getContentPane().setLayout(null);
		initGUI();
		this.pack();
		this.setSize(586, 470);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	protected void initGUI() 
	{
		{
			jLabelFechaInicio = new JLabel();
			getContentPane().add(jLabelFechaInicio);
			jLabelFechaInicio.setText("Turnos Reservados Para el Dia");
			jLabelFechaInicio.setBounds(26, 18, 519, 16);
		}
		{
			jButtonAceptar = new JButton();
			getContentPane().add(jButtonAceptar);
			jButtonAceptar.setText("Finalizar");
			jButtonAceptar.setBounds(464, 386, 81, 23);
			jButtonAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) 
				{					
					control.cancelar();
				}
			});
		}
		{
			jButtonAnterior = new JButton();
			getContentPane().add(jButtonAnterior);
			jButtonAnterior.setText("Anterior");
			jButtonAnterior.setBounds(372, 387, 81, 21);
			jButtonAnterior.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					control.anterior();
				}
			});
		}
		{
			jScrollPaneTurnosReservados = new JScrollPane();
			getContentPane().add(jScrollPaneTurnosReservados);
			jScrollPaneTurnosReservados.setBounds(26, 40, 519, 282);
		}
	}

	public void setReporte(JTextArea reporte) {
		jScrollPaneTurnosReservados.setViewportView(reporte);		
	}

	public void setDia(String fechaInicio) {
		jLabelFechaInicio.setText("Turnos Reservados Para el Dia " + fechaInicio);		
	}
}
