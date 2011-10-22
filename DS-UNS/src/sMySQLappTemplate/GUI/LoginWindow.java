package sMySQLappTemplate.GUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import sMySQLappTemplate.Core.AppCoreTemplate;
import sMySQLappTemplate.Core.ConnectionCfg;
import sMySQLappTemplate.Core.UserAccount;

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
public class LoginWindow extends JDialog 
{
	private JTextField jTextFieldUser;
	private JLabel jLabelUser;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JTextField jTextFieldPort;
	private JTextField jTextFielddbName;
	private JTextField jTextFieldHost;
	private JLabel jLabelPass;
	private JPasswordField jTextFieldPass;
	
	private JPanel jPanelConnectionCfg;
	private JPanel jPanelUserPass;
	
	private MyButton myButtonTryLogin;
	private MyButton myButtonShowConnectionCfg;
	
	private MyButton myButtonHideConnectionCfg;
	private MyButton myButtonRestoreDefaultCfg;
	
	private AppCoreTemplate appCore;
	
	private ConnectionCfg defaultConnectionCfg;
	
	public LoginWindow(AppCoreTemplate AppCore, ConnectionCfg defaultConnetion)
	{
		super();
		
		this.appCore = AppCore;
		this.defaultConnectionCfg = defaultConnetion;
		
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setModal(true);
		this.setTitle("");	
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
		this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		Dimension LoginPanelDimension = new Dimension(296, 256);
		Insets LoginTextFieldMargins = new Insets(3, 6, 3, 4);
		
		// Panel de Autenticacion de Usuario //
		
		jPanelUserPass = new JPanel();
		jPanelUserPass.setLayout(null);
		jPanelUserPass.setPreferredSize(LoginPanelDimension);
		jPanelUserPass.setBorder(new MyBorder(6,"Identificacion de Usuario"));

		{
			jTextFieldUser = new JTextField();
			jPanelUserPass.add(jTextFieldUser, BorderLayout.CENTER);
			jTextFieldUser.setBounds(36, 66, 224, 24);
			jTextFieldUser.setMargin(LoginTextFieldMargins);
		}	
		{
			jLabelUser = new JLabel();
			jPanelUserPass.add(jLabelUser);
			jLabelUser.setText("Usuario");
			jLabelUser.setBounds(38, 46, 44, 14);
		}
		{
			jTextFieldPass = new JPasswordField();
			jPanelUserPass.add(jTextFieldPass);
			jTextFieldPass.setBounds(36, 128, 224, 24);
			jTextFieldPass.setMargin(LoginTextFieldMargins);
		}
		{
			jLabelPass = new JLabel();
			jPanelUserPass.add(jLabelPass);
			jLabelPass.setText("Contraseña");
			jLabelPass.setBounds(38, 108, 77, 14);
		}
		
		KeyAdapter EnterPressed = new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					tryLogin();
			}
		};
		
		jTextFieldUser.addKeyListener(EnterPressed);		
		jTextFieldPass.addKeyListener(EnterPressed);

		myButtonTryLogin = new MyButton("/images/login64.png", "Login");
		jPanelUserPass.add(myButtonTryLogin);	
		myButtonTryLogin.setLocation(64, 170);		
		myButtonTryLogin.addMouseListener(new MouseAdapter() 
		{
	         public void mouseClicked(MouseEvent e) 
	         {
	        	 tryLogin();
	         }
		});
		
		myButtonShowConnectionCfg = new MyButton("/images/Show Connection Manager.png", "Configurar Conexion");
		jPanelUserPass.add(myButtonShowConnectionCfg);	
		myButtonShowConnectionCfg.setLocation(168, 170);
		myButtonShowConnectionCfg.addMouseListener(new MouseAdapter() 
		{
	         public void mouseClicked(MouseEvent e) 
	         {
		         myButtonHideConnectionCfg.setEnabled(true); 
		         myButtonShowConnectionCfg.setEnabled(false);
	        	 getContentPane().add(jPanelConnectionCfg);
	        	 pack();
	        	 setLocationRelativeTo(null);
	        	 repaint();
	         }
		});	
		
		this.getContentPane().add(jPanelUserPass);
		
		
		// Panel de Configuracion de Conexion //
		
		jPanelConnectionCfg = new JPanel();
		jPanelConnectionCfg.setLayout(null);
		jPanelConnectionCfg.setPreferredSize(LoginPanelDimension);
		jPanelConnectionCfg.setBorder(new MyBorder(6,"Configurar Conexion"));
		
		{
			jTextFieldHost = new JTextField();
			jPanelConnectionCfg.add(jTextFieldHost);
			jTextFieldHost.setBounds(36, 66, 164, 24);
			jTextFieldHost.setText(defaultConnetion.getHost());
			jTextFieldHost.setMargin(LoginTextFieldMargins);
		}
		{
			jTextFielddbName = new JTextField();
			jPanelConnectionCfg.add(jTextFielddbName);
			jTextFielddbName.setBounds(36, 128, 224, 24);
			jTextFielddbName.setText(defaultConnetion.getDataBase());
			jTextFielddbName.setMargin(LoginTextFieldMargins);
		}
		{
			jTextFieldPort = new JTextField();
			jPanelConnectionCfg.add(jTextFieldPort);
			jTextFieldPort.setText(String.valueOf(defaultConnectionCfg.getPort()));
			jTextFieldPort.setBounds(212, 66, 48, 24);
			jTextFieldPort.setMargin(LoginTextFieldMargins);
		}
		{
			jLabel1 = new JLabel();
			jPanelConnectionCfg.add(jLabel1);
			jLabel1.setText("Host");
			jLabel1.setBounds(38, 46, 34, 14);
		}
		{
			jLabel2 = new JLabel();
			jPanelConnectionCfg.add(jLabel2);
			jLabel2.setText("Port");
			jLabel2.setBounds(214, 46, 31, 14);
		}
		{
			jLabel3 = new JLabel();
			jPanelConnectionCfg.add(jLabel3);
			jLabel3.setText("Data Base");
			jLabel3.setBounds(38, 108, 66, 14);
		}
		
		
		myButtonHideConnectionCfg = new MyButton("/images/Hide Connection Manager.png", "Ocultar Configuracion");
		jPanelConnectionCfg.add(myButtonHideConnectionCfg);	
		myButtonHideConnectionCfg.setLocation(64, 170);		
		myButtonHideConnectionCfg.addMouseListener(new MouseAdapter() 
		{
	         public void mouseClicked(MouseEvent e) 
	         {
		        myButtonHideConnectionCfg.setEnabled(false); 
		        myButtonShowConnectionCfg.setEnabled(true);
		        jTextFieldUser.grabFocus();
	        	remove(jPanelConnectionCfg);
	        	pack();
	        	setLocationRelativeTo(null);
	        	repaint();
	         }
		});
		
		myButtonRestoreDefaultCfg = new MyButton("/images/Restore Default Cfg.png", "Restaurar Valores por Defecto");
		jPanelConnectionCfg.add(myButtonRestoreDefaultCfg);	
		myButtonRestoreDefaultCfg.setLocation(168, 170);		
		myButtonRestoreDefaultCfg.addMouseListener(new MouseAdapter() 
		{
	         public void mouseClicked(MouseEvent e) 
	         {
		        jTextFieldHost.setText(defaultConnectionCfg.getHost());
		        jTextFieldPort.setText(defaultConnectionCfg.getPort());
		        jTextFielddbName.setText(defaultConnectionCfg.getDataBase());
	         }
		});
		
		
		// Rendering de Ventana //
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void clearPass() {
		this.jTextFieldPass.setText("");
		this.jTextFieldPass.grabFocus();		
	}
	
	@SuppressWarnings("deprecation")
	private void tryLogin()
	{
   	 	try {
   	 		UserAccount   who   = new UserAccount(jTextFieldUser.getText(), jTextFieldPass.getText());
   	 		ConnectionCfg where = new ConnectionCfg(jTextFieldHost.getText(), jTextFieldPort.getText(), jTextFielddbName.getText());
   	 		appCore.TryLogin(who, where);
   	 		this.dispose();
   	 	}
   	 	catch(Exception x) {
   	 		setTitle("ERROR [" + x.getMessage() + "] ");
   	 	}
	}
	
	public void setTitle(String title)
	{
		super.setTitle(" - LOGIN " + title + "-");
	}
}
