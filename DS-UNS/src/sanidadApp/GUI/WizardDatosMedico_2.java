package sanidadApp.GUI;
import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

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
public class WizardDatosMedico_2 extends JDialog
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

	public WizardDatosMedico_2(){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setModal(true);
		this.getContentPane().setLayout(null);
		initGUI();
		this.pack();
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);	
	}

	protected void initGUI() 
	{
		this.setTitle("Especialidades");
		{
			jLabelEspecialidadesPoseidas = new JLabel();
			getContentPane().add(jLabelEspecialidadesPoseidas);
			jLabelEspecialidadesPoseidas.setText("Especialidades Poseidas");
			jLabelEspecialidadesPoseidas.setBounds(212, 21, 153, 14);
		}
		{
			jLabelEspecialidadesExistentes = new JLabel();
			getContentPane().add(jLabelEspecialidadesExistentes);
			jLabelEspecialidadesExistentes.setText("Especialidades Existentes");
			jLabelEspecialidadesExistentes.setBounds(24, 21, 122, 14);
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
			jButtonAceptar.setBounds(297, 241, 68, 21);
		}
		{
			jButtonCancelar = new JButton();
			getContentPane().add(jButtonCancelar);
			jButtonCancelar.setText("Cancelar");
			jButtonCancelar.setBounds(221, 241, 65, 21);
		}
		{
			jButtonAnterior = new JButton();
			getContentPane().add(jButtonAnterior);
			jButtonAnterior.setText("Anterior");
			jButtonAnterior.setBounds(147, 241, 63, 21);
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
		}
		{
			jButtonremEsp = new JButton();
			getContentPane().add(jButtonremEsp);
			jButtonremEsp.setText("<--");
			jButtonremEsp.setBounds(263, 187, 57, 21);
		}
		
	}

}
