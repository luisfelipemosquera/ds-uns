package sanidadApp.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import sMySQLappTemplate.GUI.MyTable;
import sanidadApp.Features.Alta_Medicos;
import sanidadApp.Features.Otorgar_Turno;
import sanidadApp.Features.TablaEspecialidades;
import sanidadApp.Features.TablaMedicos;
import sanidadApp.Features.TablaTurnosDisponibles;

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
public class WizardDatosReserva_Turno extends JDialog
{
	private JButton jButtonAnterior;
	private JButton jButtonCancelar;
	private JButton jButtonAceptar;
	
	private Otorgar_Turno control;
	private JComboBox jComboBoxTiposConsulta;
	private JLabel jLabelTiposConsulta;
	private JScrollPane jScrollPaneTurnosDisponibles;
	private JLabel jLabelTurnosDisponibles;
	private MyTable tablaTurnosDisponibles;
	
	private boolean click;

	public Otorgar_Turno getControl() {
		return control;
	}

	public WizardDatosReserva_Turno(Otorgar_Turno control){
		click = true;
		this.control = control;
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent we) {
			    	(((WizardDatosReserva_Turno) we.getWindow()).getControl()).cancelar();
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
		this.setTitle("Turnos Disponibles");
		{
			jButtonAceptar = new JButton();
			getContentPane().add(jButtonAceptar);
			jButtonAceptar.setText("Guardar");
			jButtonAceptar.setBounds(476, 383, 75, 21);
			jButtonAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					control.guardar();
				}
			});
		}
		{
			jButtonCancelar = new JButton();
			getContentPane().add(jButtonCancelar);
			jButtonCancelar.setText("Cancelar");
			jButtonCancelar.setBounds(382, 383, 75, 21);
			jButtonCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					control.cancelar();
				}
			});
		}
		{
			jButtonAnterior = new JButton();
			getContentPane().add(jButtonAnterior);
			jButtonAnterior.setText("Anterior");
			jButtonAnterior.setBounds(286, 383, 75, 21);
			jButtonAnterior.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					control.anterior();
				}
			});
		}		
		{
			jComboBoxTiposConsulta = new JComboBox();
			getContentPane().add(jComboBoxTiposConsulta);
			jComboBoxTiposConsulta.setBounds(27, 299, 126, 21);
			jComboBoxTiposConsulta.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent arg0) 
				{
					if (click)
					{
						control.loadTurnosDisponibles(jComboBoxTiposConsulta.getSelectedItem().toString());
						click = false;
					}
					else
					{
						click = true;
					}
				}});
		}
		{
			jLabelTurnosDisponibles = new JLabel();
			getContentPane().add(jLabelTurnosDisponibles);
			jLabelTurnosDisponibles.setText("Turnos Disponibles");
			jLabelTurnosDisponibles.setBounds(27, 12, 211, 14);
		}
		{
			jScrollPaneTurnosDisponibles = new JScrollPane();
			getContentPane().add(jScrollPaneTurnosDisponibles);
			jScrollPaneTurnosDisponibles.setBounds(27, 32, 524, 227);
		}
		{
			jLabelTiposConsulta = new JLabel();
			getContentPane().add(jLabelTiposConsulta);
			jLabelTiposConsulta.setText("Tipo de Consulta");
			jLabelTiposConsulta.setBounds(27, 279, 106, 14);
		}
	}
	
	public void setVisible(boolean b)
	{
		if (b)
		{ 
		   control.loadTurnosDisponibles(jComboBoxTiposConsulta.getSelectedItem().toString());
		}
		super.setVisible(b);
	}
	
	public void setTiposConsultaData(DefaultComboBoxModel tiposConsulta) {
		this.jComboBoxTiposConsulta.setModel(tiposConsulta);		
	}
	
	public void setTurnosDisponiblesData(TablaTurnosDisponibles te)
	{
		tablaTurnosDisponibles = new MyTable(te);
		jScrollPaneTurnosDisponibles.setViewportView(tablaTurnosDisponibles);
		tablaTurnosDisponibles.setPreferredSize(new java.awt.Dimension(520, 217));
	}
}
