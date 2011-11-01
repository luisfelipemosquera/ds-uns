package sanidadApp.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import sMySQLappTemplate.GUI.MyTable;
import sanidadApp.Features.Alta_Medicos;
import sanidadApp.Features.TablaEspecialidades;

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
public class WizardDatosMedico_Especialidades extends JDialog
{
	private JLabel jLabelEspecialidadesPoseidas;
	private JLabel jLabelEspecialidadesExistentes;
	private JScrollPane jScrollPaneEspPoseidas;
	private JButton jButtonremEsp;
	private JButton jButtonAddEsp;
	private JButton jButtonAnterior;
	private JButton jButtonCancelar;
	private JButton jButtonAceptar;
	private JScrollPane jScrollPaneEspExistentes;
	
	private Alta_Medicos control;
	
	private MyTable tablaEspExistentes;
	private MyTable tablaEspPoseidas;

	public Alta_Medicos getControl() {
		return control;
	}

	public WizardDatosMedico_Especialidades(Alta_Medicos control){
		this.control = control;
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent we) {
			    	(((WizardDatosMedico_Especialidades) we.getWindow()).getControl()).cancelar();
			    }
			});
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setModal(true);
		this.getContentPane().setLayout(null);
		initGUI();
		this.pack();
		this.setSize(400, 320);
		this.setLocationRelativeTo(null);	
	}

	protected void initGUI() 
	{
		this.setTitle("Especialidades");
		{
			jLabelEspecialidadesPoseidas = new JLabel();
			getContentPane().add(jLabelEspecialidadesPoseidas);
			jLabelEspecialidadesPoseidas.setText("Poseidas");
			jLabelEspecialidadesPoseidas.setBounds(212, 21, 153, 14);
		}
		{
			jLabelEspecialidadesExistentes = new JLabel();
			getContentPane().add(jLabelEspecialidadesExistentes);
			jLabelEspecialidadesExistentes.setText("Existentes");
			jLabelEspecialidadesExistentes.setBounds(24, 21, 151, 14);
		}
		{
			jScrollPaneEspExistentes = new JScrollPane();
			getContentPane().add(jScrollPaneEspExistentes);
			jScrollPaneEspExistentes.setBounds(24, 47, 151, 128);
		}
		{
			jButtonAceptar = new JButton();
			getContentPane().add(jButtonAceptar);
			jButtonAceptar.setText("Guardar");
			jButtonAceptar.setBounds(287, 241, 75, 21);
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
			jButtonCancelar.setBounds(199, 241, 75, 21);
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
			jButtonAnterior.setBounds(113, 241, 75, 21);
			jButtonAnterior.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					control.anterior();
				}
			});
		}
		{
			jScrollPaneEspPoseidas = new JScrollPane();
			getContentPane().add(jScrollPaneEspPoseidas);
			jScrollPaneEspPoseidas.setBounds(212, 47, 153, 128);
		}
		{
			jButtonAddEsp = new JButton();
			getContentPane().add(jButtonAddEsp);
			jButtonAddEsp.setText("-->");
			jButtonAddEsp.setBounds(70, 187, 59, 21);
			jButtonAddEsp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					control.addEsp(tablaEspExistentes.getSelectedRow());
				}
			});
		}
		{
			jButtonremEsp = new JButton();
			getContentPane().add(jButtonremEsp);
			jButtonremEsp.setText("<--");
			jButtonremEsp.setBounds(263, 187, 57, 21);
			jButtonremEsp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					control.remEsp(tablaEspPoseidas.getSelectedRow());
				}
			});
		}		
	}
	
	public void viewEspExistentes(TablaEspecialidades te)
	{
		tablaEspExistentes = new MyTable(te);
		jScrollPaneEspExistentes.setViewportView(tablaEspExistentes);
	}
	
	public void viewEspPoseidas(TablaEspecialidades te)
	{
		tablaEspPoseidas = new MyTable(te);
		jScrollPaneEspPoseidas.setViewportView(tablaEspPoseidas);
	}
}
