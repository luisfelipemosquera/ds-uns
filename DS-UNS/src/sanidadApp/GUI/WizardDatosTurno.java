package sanidadApp.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;

import sMySQLappTemplate.GUI.MyTable;
import sanidadApp.Features.Alta_Turnos;
import sanidadApp.Features.TablaEspecialidades;
import sanidadApp.Features.TablaMedicos;


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
public class WizardDatosTurno extends JDialog
{	
	private Alta_Turnos control;
	private JButton jButtoncancelar;
	private JButton jButtonAceptar;
	private JLabel jLabel1;
	private JSlider jSliderDuracion;
	private JSpinner jSpinnerHoraInicio;
	private JLabel jLabelDuracion;
	private JSpinner jSpinnerFechaInicio;
	private JLabel jLabelHoraInicio;
	private JLabel jLabelFechaInicio;
	private JLabel jLabelEspecialidades;
	private JLabel jLabelMedicos;
	private JScrollPane jScrollPaneEspecialidades;
	private JScrollPane jScrollPaneMedicos;
	
	private MyTable tablaMedicos;
	private MyTable tablaEspExistentes;

	public Alta_Turnos getControl() {
		return control;
	}

	public WizardDatosTurno(Alta_Turnos control)
	{
		this.control = control;
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent we) {
			    	(((WizardDatosTurno) we.getWindow()).getControl()).cancelar();
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
			jScrollPaneMedicos = new JScrollPane();
			getContentPane().add(jScrollPaneMedicos);
			jScrollPaneMedicos.setBounds(24, 33, 334, 240);
		}
		{
			jScrollPaneEspecialidades = new JScrollPane();
			getContentPane().add(jScrollPaneEspecialidades);
			jScrollPaneEspecialidades.setBounds(392, 33, 157, 240);
		}
		{
			jLabelMedicos = new JLabel();
			getContentPane().add(jLabelMedicos);
			jLabelMedicos.setText("Medicos");
			jLabelMedicos.setBounds(24, 12, 105, 16);
		}
		{
			jLabelEspecialidades = new JLabel();
			getContentPane().add(jLabelEspecialidades);
			jLabelEspecialidades.setText("Especialidades");
			jLabelEspecialidades.setBounds(392, 12, 126, 16);
		}
		{
			jLabelFechaInicio = new JLabel();
			getContentPane().add(jLabelFechaInicio);
			jLabelFechaInicio.setText("Fecha Inicio");
			jLabelFechaInicio.setBounds(24, 293, 86, 16);
		}
		{
			jLabelHoraInicio = new JLabel();
			getContentPane().add(jLabelHoraInicio);
			jLabelHoraInicio.setText("Hora Inicio");
			jLabelHoraInicio.setBounds(212, 293, 114, 16);
		}
		{
			SpinnerListModel jSpinnerFechaInicioModel = 
				new SpinnerListModel(
						new String[] { "Sun", "Mon" , "Tue" , "Wed" , "Thu" , "Fri" , "Sat" });
			jSpinnerFechaInicio = new JSpinner();
			getContentPane().add(jSpinnerFechaInicio);
			jSpinnerFechaInicio.setModel(jSpinnerFechaInicioModel);
			jSpinnerFechaInicio.setBounds(24, 315, 146, 23);
		}
		{
			jLabelDuracion = new JLabel();
			getContentPane().add(jLabelDuracion);
			jLabelDuracion.setText("Duracion");
			jLabelDuracion.setBounds(392, 293, 110, 16);
		}
		{
			SpinnerListModel jSpinnerHoraInicioModel = 
				new SpinnerListModel(
						new String[] { "Sun", "Mon" , "Tue" , "Wed" , "Thu" , "Fri" , "Sat" });
			jSpinnerHoraInicio = new JSpinner();
			getContentPane().add(jSpinnerHoraInicio);
			jSpinnerHoraInicio.setModel(jSpinnerHoraInicioModel);
			jSpinnerHoraInicio.setBounds(212, 315, 146, 23);
		}
		{
			jSliderDuracion = new JSlider();
			getContentPane().add(jSliderDuracion);
			jSliderDuracion.setBounds(387, 315, 162, 22);
		}
		{
			jLabel1 = new JLabel();
			getContentPane().add(jLabel1);
			jLabel1.setText("#Horas");
			jLabel1.setBounds(501, 293, 44, 16);
		}
		{
			jButtonAceptar = new JButton();
			getContentPane().add(jButtonAceptar);
			jButtonAceptar.setText("Aceptar");
			jButtonAceptar.setBounds(464, 388, 81, 23);
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
			jButtoncancelar.setBounds(372, 388, 81, 23);
			jButtoncancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					control.cancelar();
				}
			});
		}
	}
	
	public void viewMedicos(TablaMedicos te)
	{
		tablaMedicos = new MyTable(te);
		jScrollPaneMedicos.setViewportView(tablaMedicos);
	}
	
	public void viewEspExistentes(TablaEspecialidades te)
	{
		tablaEspExistentes = new MyTable(te);
		jScrollPaneEspecialidades.setViewportView(tablaEspExistentes);
	}
}
