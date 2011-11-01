package sanidadApp.GUI;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import javax.swing.JLabel;
import javax.swing.JTextField;

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
public class WizardDatosMedico_1 extends JDialog
{
	private JComboBox jComboBoxTipoDNI;
	private JLabel jLabelTipoDNI;
	private JTextField jTextFieldNumeroDNI;
	private JLabel jLabelNumeroDNI;
	private JLabel jLabelApellido;
	private JButton jButtonSiguiente;
	private JButton jButtonCancelar;
	private JTextField jTextFieldNombre;
	private JLabel jLabelNombre;
	private JTextField jTextFieldApellido;
	
	public WizardDatosMedico_1(){
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setModal(true);
		this.getContentPane().setLayout(null);
		initGUI();
		this.pack();
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
	}

	protected void initGUI() {
		try {
			{
				this.setTitle("Datos Personales");
				{
					ComboBoxModel jComboBoxTipoDNIModel = 
						new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
					jComboBoxTipoDNI = new JComboBox();
					getContentPane().add(jComboBoxTipoDNI);
					jComboBoxTipoDNI.setModel(jComboBoxTipoDNIModel);
					jComboBoxTipoDNI.setBounds(25, 43, 71, 21);
				}
				{
					jLabelTipoDNI = new JLabel();
					getContentPane().add(jLabelTipoDNI);
					jLabelTipoDNI.setText("Tipo DNI");
					jLabelTipoDNI.setBounds(25, 23, 71, 14);
				}
				{
					jTextFieldNumeroDNI = new JTextField();
					getContentPane().add(jTextFieldNumeroDNI);
					jTextFieldNumeroDNI.setBounds(108, 43, 260, 21);
				}
				{
					jLabelNumeroDNI = new JLabel();
					getContentPane().add(jLabelNumeroDNI);
					jLabelNumeroDNI.setText("Numero DNI");
					jLabelNumeroDNI.setBounds(108, 23, 89, 14);
				}
				{
					jLabelApellido = new JLabel();
					getContentPane().add(jLabelApellido);
					jLabelApellido.setText("Apellido");
					jLabelApellido.setBounds(25, 81, 71, 14);
				}
				{
					jTextFieldApellido = new JTextField();
					getContentPane().add(jTextFieldApellido);
					jTextFieldApellido.setBounds(25, 101, 343, 21);
				}
				{
					jLabelNombre = new JLabel();
					getContentPane().add(jLabelNombre);
					jLabelNombre.setText("Nombres");
					jLabelNombre.setBounds(25, 139, 83, 14);
				}
				{
					jTextFieldNombre = new JTextField();
					getContentPane().add(jTextFieldNombre);
					jTextFieldNombre.setBounds(25, 159, 343, 21);
				}
				{
					jButtonSiguiente = new JButton();
					getContentPane().add(jButtonSiguiente);
					jButtonSiguiente.setText("Siguiente");
					jButtonSiguiente.setBounds(291, 241, 77, 21);
				}
				{
					jButtonCancelar = new JButton();
					getContentPane().add(jButtonCancelar);
					jButtonCancelar.setText("Cancelar");
					jButtonCancelar.setBounds(204, 241, 76, 21);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
