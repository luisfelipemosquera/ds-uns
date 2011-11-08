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
import sanidadApp.Features.Modificacion_Medicos;
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
public class WizardDatosMedico_Seleccionar extends JDialog
{	
	private Modificacion_Medicos control;	
	private JButton jButtonCancelar;
	private JButton jButtonSiguiente;
	private JScrollPane jScrollPaneMedicos;
	
	private MyTable tablaMedicos;

	public Alta_Medicos getControl() {
		return control;
	}

	public WizardDatosMedico_Seleccionar(Modificacion_Medicos control){
		this.control = control;
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent we) {
			    	(((WizardDatosMedico_Seleccionar) we.getWindow()).getControl()).cancelar();
			    }
			});
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setModal(true);
		this.getContentPane().setLayout(null);
		initGUI();
		this.pack();
		this.setSize(400, 320);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Seleccion de Medico");
	}

	protected void initGUI() 
	{
		{
			jScrollPaneMedicos = new JScrollPane();
			getContentPane().add(jScrollPaneMedicos);
			jScrollPaneMedicos.setBounds(25, 28, 339, 172);
		}
		{
			jButtonSiguiente = new JButton();
			getContentPane().add(jButtonSiguiente);
			jButtonSiguiente.setText("Siguiente");
			jButtonSiguiente.setBounds(294, 241, 75, 21);
			jButtonSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					control.siguiente(tablaMedicos.getSelectedRow());
				}
			});
		}
		{
			jButtonCancelar = new JButton();
			getContentPane().add(jButtonCancelar);
			jButtonCancelar.setText("Cancelar");
			jButtonCancelar.setBounds(208, 241, 75, 21);
			jButtonCancelar.addActionListener(new ActionListener() {
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
	
	public void setSigButtonText(String text)
	{
		jButtonSiguiente.setText(text);
	}
}
