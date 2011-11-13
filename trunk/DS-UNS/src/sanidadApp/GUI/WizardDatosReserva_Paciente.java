package sanidadApp.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import sanidadApp.Features.Alta_Medicos;
import sanidadApp.Features.Otorgar_Turno;

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
public class WizardDatosReserva_Paciente extends JDialog
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
	
	private Otorgar_Turno control;
	private JComboBox jComboBoxRelacionUNS;
	private JLabel jLabelRelacionUNS;
	
	private boolean click;

	public Otorgar_Turno getControl() {
		return control;
	}

	public WizardDatosReserva_Paciente(Otorgar_Turno receiver){
		click = true;
		this.control = receiver;
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent we) {
			    	(((WizardDatosReserva_Paciente) we.getWindow()).getControl()).cancelar();
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
					jComboBoxTipoDNI.addItemListener(new ItemListener(){
						@Override
						public void itemStateChanged(ItemEvent arg0) 
						{
							if (click)
							{
								control.encontrarPersona(jComboBoxTipoDNI.getSelectedItem().toString(),
														 jTextFieldNumeroDNI.getText());
								click = false;
							}
							else
							{
								click = true;
							}
					}});
				}
				{
					jLabelTipoDNI = new JLabel();
					getContentPane().add(jLabelTipoDNI);
					jLabelTipoDNI.setText("Tipo Doc.");
					jLabelTipoDNI.setBounds(25, 23, 71, 14);
				}
				{
					jTextFieldNumeroDNI = new JTextField();
					getContentPane().add(jTextFieldNumeroDNI);
					jTextFieldNumeroDNI.setBounds(108, 43, 260, 21);
					jTextFieldNumeroDNI.addKeyListener(new KeyListener()
					{	
						@Override
						public void keyPressed(KeyEvent arg0) {}
						@Override
						public void keyReleased(KeyEvent arg0) {
							if (jTextFieldNumeroDNI.getText().length() > 6)
							  control.encontrarPersona(jComboBoxTipoDNI.getSelectedItem().toString(),
									 jTextFieldNumeroDNI.getText());
						}

						@Override
						public void keyTyped(KeyEvent e) {
											
					}});
				}
				{
					jLabelNumeroDNI = new JLabel();
					getContentPane().add(jLabelNumeroDNI);
					jLabelNumeroDNI.setText("Numero Doc.");
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
					jTextFieldApellido.setBounds(25, 101, 187, 21);
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
					jTextFieldNombre.setBounds(25, 159, 187, 21);
				}
				{
					jButtonSiguiente = new JButton();
					getContentPane().add(jButtonSiguiente);
					jButtonSiguiente.setText("Siguiente");
					jButtonSiguiente.setBounds(291, 241, 77, 21);
					jButtonSiguiente.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							control.siguiente(
								jComboBoxTipoDNI.getSelectedItem().toString(),
								jTextFieldNumeroDNI.getText(),
								jTextFieldApellido.getText(),
								jTextFieldNombre.getText(),
								jComboBoxRelacionUNS.getSelectedItem().toString()
							);
						}
					});
				}
				{
					jButtonCancelar = new JButton();
					getContentPane().add(jButtonCancelar);
					jButtonCancelar.setText("Cancelar");
					jButtonCancelar.setBounds(204, 241, 76, 21);
					jButtonCancelar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							control.cancelar();
						}
					});
				}
				{
					jComboBoxRelacionUNS = new JComboBox();
					getContentPane().add(jComboBoxRelacionUNS);
					jComboBoxRelacionUNS.setBounds(238, 101, 130, 21);
				}
				{
					jLabelRelacionUNS = new JLabel();
					getContentPane().add(jLabelRelacionUNS);
					jLabelRelacionUNS.setText("Relacion con la UNS");
					jLabelRelacionUNS.setBounds(238, 81, 130, 14);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void setDocTypeData(DefaultComboBoxModel docTypes) {
		this.jComboBoxTipoDNI.setModel(docTypes);		
	}
	
	public void setUNSrelData(DefaultComboBoxModel unsRel) {
		this.jComboBoxRelacionUNS.setModel(unsRel);		
	}

	public void setDocType(String tipoDoc) 
	{
		jComboBoxTipoDNI.setSelectedItem(tipoDoc);		
	}

	public void setDocNumber(String numeroDoc) {
		jTextFieldNumeroDNI.setText(String.valueOf(numeroDoc));		
	}

	public void setApellido(String apellido) {
		jTextFieldApellido.setText(apellido);		
	}

	public void setNombre(String nombre) {
		jTextFieldNombre.setText(nombre);		
	}

	public void disableKey() {
		jComboBoxTipoDNI.setEnabled(false);
		jTextFieldNumeroDNI.setEnabled(false);
	}

	public void setUNSrel(String string) {
		jComboBoxRelacionUNS.setSelectedItem(string);		
	}

	public void disableNonKey() {
		jComboBoxRelacionUNS.setEnabled(false);
		jTextFieldApellido.setEnabled(false);
		jTextFieldNombre.setEnabled(false);		
	}
	
	public void enableNonKey() {
		jComboBoxRelacionUNS.setEnabled(true);
		jTextFieldApellido.setEnabled(true);
		jTextFieldNombre.setEnabled(true);	
	}
}
