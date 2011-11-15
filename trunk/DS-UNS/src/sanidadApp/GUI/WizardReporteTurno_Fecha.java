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
public class WizardReporteTurno_Fecha extends JDialog
{	
	private Reporte_Turnos_Dia control;
	private JButton jButtonAnterior;
	private JScrollPane jScrollPaneFecha;
	private JButton jButtoncancelar;
	private JButton jButtonAceptar;
	private JLabel jLabelFechaInicio;
	
	CalendarPane datePicker;

	public Reporte_Turnos_Dia getControl() {
		return control;
	}

	public WizardReporteTurno_Fecha(Reporte_Turnos_Dia control)
	{;
		this.control = control;
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent we) {
			    	(((WizardReporteTurno_Fecha) we.getWindow()).getControl()).cancelar();
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
			jLabelFechaInicio.setText("Fecha del Turno");
			jLabelFechaInicio.setBounds(26, 18, 96, 16);
		}
		{
			jButtonAceptar = new JButton();
			getContentPane().add(jButtonAceptar);
			jButtonAceptar.setText("Aceptar");
			jButtonAceptar.setBounds(464, 386, 81, 23);
			jButtonAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) 
				{					
					control.aceptar(datePicker.getDate());
				}
			});
		}
		{
			jButtoncancelar = new JButton();
			getContentPane().add(jButtoncancelar);
			jButtoncancelar.setText("Cancelar");
			jButtoncancelar.setBounds(367, 386, 81, 23);
			jButtoncancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					control.cancelar();
				}
			});
		}
		{
			jScrollPaneFecha = new JScrollPane();
			getContentPane().add(jScrollPaneFecha);
			jScrollPaneFecha.setBounds(26, 40, 519, 273);
			jScrollPaneFecha.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPaneFecha.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		}
		{
			datePicker = new CalendarPane();
			datePicker.setEnabled(true);
			datePicker.setVisible(true);
			jScrollPaneFecha.setViewportView(datePicker);
			datePicker.setPreferredSize(new java.awt.Dimension(299, 267));
		}
	}
}
