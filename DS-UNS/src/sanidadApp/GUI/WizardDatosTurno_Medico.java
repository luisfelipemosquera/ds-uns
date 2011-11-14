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
public class WizardDatosTurno_Medico extends JDialog
{	
	private Alta_Turnos control;
	private JButton jButtoncancelar;
	private JButton jButtonSiguiente;
	private JLabel jLabelTipoConsulta;
	private JLabel jLabelMedicos;
	private JScrollPane jScrollPaneTipoConsulta;
	private JScrollPane jScrollPaneMedicos;
	
	private MyTable tablaMedicos;
	
	private boolean click;

	public Alta_Turnos getControl() {
		return control;
	}

	public WizardDatosTurno_Medico(Alta_Turnos control)
	{
		this.click = true;
		this.control = control;
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent we) {
			    	(((WizardDatosTurno_Medico) we.getWindow()).getControl()).cancelar();
			    }
			});
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setModal(true);
		this.getContentPane().setLayout(null);
		initGUI();
		this.pack();
		this.setSize(600, 480);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	protected void initGUI() 
	{
		{
			jScrollPaneMedicos = new JScrollPane();
			getContentPane().add(jScrollPaneMedicos);
			jScrollPaneMedicos.setBounds(24, 33, 546, 310);
		}
		{
			jLabelMedicos = new JLabel();
			getContentPane().add(jLabelMedicos);
			jLabelMedicos.setText("Medicos");
			jLabelMedicos.setBounds(24, 12, 105, 16);
		}
		{
			jButtonSiguiente = new JButton();
			getContentPane().add(jButtonSiguiente);
			jButtonSiguiente.setText("Siguiente");
			jButtonSiguiente.setBounds(489, 395, 81, 23);
			jButtonSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					control.siguiente(tablaMedicos.getSelectedRow());
				}
			});
		}
		{
			jButtoncancelar = new JButton();
			getContentPane().add(jButtoncancelar);
			jButtoncancelar.setText("Cancelar");
			jButtoncancelar.setBounds(392, 395, 81, 23);
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
		tablaMedicos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e){
				if (click)
				{
					control.medico_seleccionado(tablaMedicos.getSelectedRow());
					click = false;
				}
				else click = true;
			}
		});
		jScrollPaneMedicos.setViewportView(tablaMedicos);
		tablaMedicos.setPreferredSize(new java.awt.Dimension(532, 307));
	}
}
