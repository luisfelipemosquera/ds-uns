package sanidadApp.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;

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
public class WizardDatosTurno_Fecha extends JDialog
{	
	private Alta_Turnos control;
	private JScrollPane jScrollPaneClock;
	private JButton jButtonAnterior;
	private JScrollPane jScrollPaneFecha;
	private JButton jButtoncancelar;
	private JButton jButtonAceptar;
	private JLabel jLabelDuracionElegida;
	private JSlider jSliderDuracion;
	private JLabel jLabelDuracion;
	private JLabel jLabelFechaInicio;
	private JLabel jLabelHoraInicio;
	CalendarPane datePicker;	
	TimePanel timePiker;

	public Alta_Turnos getControl() {
		return control;
	}

	public WizardDatosTurno_Fecha(Alta_Turnos control)
	{;
		this.control = control;
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent we) {
			    	(((WizardDatosTurno_Fecha) we.getWindow()).getControl()).cancelar();
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
			jLabelHoraInicio = new JLabel();
			getContentPane().add(jLabelHoraInicio);
			jLabelHoraInicio.setText("Hora del Turno");
			jLabelHoraInicio.setBounds(372, 18, 124, 16);
		}
		{
			jLabelDuracion = new JLabel();
			getContentPane().add(jLabelDuracion);
			jLabelDuracion.setText("Duracion");
			jLabelDuracion.setBounds(362, 266, 110, 16);
		}
		{
			jSliderDuracion = new JSlider();
			getContentPane().add(jSliderDuracion);
			jSliderDuracion.setBounds(361, 288, 184, 22);
			jSliderDuracion.setMaximum(16);
			jSliderDuracion.setMinimum(1);
			jSliderDuracion.setMinorTickSpacing(1);
			jSliderDuracion.setSnapToTicks(true);
			jSliderDuracion.setValue(1);
			jSliderDuracion.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e){
					jLabelDuracionElegida.setText(jSliderDuracion.getValue()*15 + " minutos");					
				}
			});
		}
		{
			jLabelDuracionElegida = new JLabel();
			getContentPane().add(jLabelDuracionElegida);
			jLabelDuracionElegida.setText("15 minutos");
			jLabelDuracionElegida.setBounds(407, 266, 66, 16);
			jLabelDuracionElegida.setHorizontalTextPosition(JLabel.RIGHT);
		}
		{
			jButtonAceptar = new JButton();
			getContentPane().add(jButtonAceptar);
			jButtonAceptar.setText("Aceptar");
			jButtonAceptar.setBounds(464, 386, 81, 23);
			jButtonAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					control.guardar();
				}
			});
		}
		{
			jButtoncancelar = new JButton();
			getContentPane().add(jButtoncancelar);
			jButtoncancelar.setText("Cancelar");
			jButtoncancelar.setBounds(280, 386, 81, 23);
			jButtoncancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					control.cancelar();
				}
			});
		}
		{
			jScrollPaneFecha = new JScrollPane();
			getContentPane().add(jScrollPaneFecha);
			jScrollPaneFecha.setBounds(26, 40, 316, 270);
			jScrollPaneFecha.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPaneFecha.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
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
			jScrollPaneClock = new JScrollPane();
			getContentPane().add(jScrollPaneClock);
			jScrollPaneClock.setBounds(361, 40, 184, 214);
			jScrollPaneClock.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPaneClock.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		}
		{
			datePicker = new CalendarPane();
			datePicker.setEnabled(true);
			datePicker.setVisible(true);
			jScrollPaneFecha.setViewportView(datePicker);
			datePicker.setPreferredSize(new java.awt.Dimension(299, 267));
		}
		{
			timePiker = new TimePanel();
			timePiker.setEnabled(true);
			timePiker.setVisible(true);
			jScrollPaneClock.setViewportView(timePiker);
		}
		
	}
}
