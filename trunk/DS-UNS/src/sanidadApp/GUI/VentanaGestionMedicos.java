package sanidadApp.GUI;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import sMySQLappTemplate.Core.FeatureTemplate;


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
public class VentanaGestionMedicos extends javax.swing.JDialog {
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JButton jButton6;
	private JButton jButton5;
	private JButton jButton4;
	private JButton jButton3;
	private JTextField jTextField4;
	private JButton jButton1;
	private JLabel jLabel5;
	private JTextField jTextField3;
	private JTextField jTextField2;
	private JTextField jTextField1;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JButton jButton2;
	private JScrollPane jScrollPane3;

	public VentanaGestionMedicos(FeatureTemplate receiver) {
		// TODO Auto-generated constructor stub
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setModal(true);
		getContentPane().setLayout(null);
		{
			jScrollPane1 = new JScrollPane();
			getContentPane().add(jScrollPane1);
			jScrollPane1.setBounds(12, 56, 270, 203);
		}
		{
			jScrollPane2 = new JScrollPane();
			getContentPane().add(jScrollPane2);
			jScrollPane2.setBounds(302, 56, 106, 203);
		}
		{
			jScrollPane3 = new JScrollPane();
			getContentPane().add(jScrollPane3);
			jScrollPane3.setBounds(420, 56, 108, 203);
		}
		{
			jButton1 = new JButton();
			getContentPane().add(jButton1);
			jButton1.setText("-->");
			jButton1.setBounds(335, 271, 53, 21);
		}
		{
			jButton2 = new JButton();
			getContentPane().add(jButton2);
			jButton2.setText("<--");
			jButton2.setBounds(443, 271, 53, 21);
		}
		{
			jLabel1 = new JLabel();
			getContentPane().add(jLabel1);
			jLabel1.setText("Personal Medico");
			jLabel1.setBounds(12, 30, 104, 14);
		}
		{
			jLabel2 = new JLabel();
			getContentPane().add(jLabel2);
			jLabel2.setText("Especialidades");
			jLabel2.setBounds(302, 12, 86, 14);
		}
		{
			jLabel3 = new JLabel();
			getContentPane().add(jLabel3);
			jLabel3.setText("Especialidades");
			jLabel3.setBounds(420, 12, 93, 14);
		}
		{
			jLabel4 = new JLabel();
			getContentPane().add(jLabel4);
			jLabel4.setText("Poseidas");
			jLabel4.setBounds(302, 30, 64, 14);
		}
		{
			jLabel5 = new JLabel();
			getContentPane().add(jLabel5);
			jLabel5.setText("Existentes");
			jLabel5.setBounds(420, 30, 69, 14);
		}
		{
			jTextField1 = new JTextField();
			getContentPane().add(jTextField1);
			jTextField1.setText("DNI");
			jTextField1.setBounds(12, 314, 39, 21);
		}
		{
			jTextField2 = new JTextField();
			getContentPane().add(jTextField2);
			jTextField2.setText("Numero");
			jTextField2.setBounds(57, 314, 129, 21);
		}
		{
			jTextField3 = new JTextField();
			getContentPane().add(jTextField3);
			jTextField3.setText("Apellido");
			jTextField3.setBounds(208, 314, 98, 21);
		}
		{
			jTextField4 = new JTextField();
			getContentPane().add(jTextField4);
			jTextField4.setText("Nombres");
			jTextField4.setBounds(312, 314, 217, 21);
		}
		{
			jButton3 = new JButton();
			getContentPane().add(jButton3);
			jButton3.setText("Agregar");
			jButton3.setBounds(12, 271, 83, 21);
		}
		{
			jButton4 = new JButton();
			getContentPane().add(jButton4);
			jButton4.setText("Eliminar");
			jButton4.setBounds(200, 271, 80, 21);
		}
		{
			jButton5 = new JButton();
			getContentPane().add(jButton5);
			jButton5.setText("Actualizar");
			jButton5.setBounds(106, 271, 83, 21);
		}
		{
			jButton6 = new JButton();
			getContentPane().add(jButton6);
			jButton6.setText("Regresar");
			jButton6.setBounds(443, 351, 86, 21);
		}
		this.pack();
		this.setSize(548, 428);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
