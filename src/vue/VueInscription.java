package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import controleur.CtrlUtilisateur;
/*
 * Cette classe affiche une JFrame qui permet de créer un compte
 */
public class VueInscription extends JFrame implements ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel labelPseudo;
	private JLabel labelEmail;
	private JLabel password;
	private JTextField fieldPseudo;
	private JTextField fieldEmail;
	private JPasswordField fieldPassword;
	private JButton buttonValid;
	private CtrlUtilisateur ctrlConnexion;

	public VueInscription() {
		ctrlConnexion = new CtrlUtilisateur();
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 300));
		panel.setBackground(Color.white);
		panel.setLayout(new GridLayout(7, 1));
		panel.setBorder(getCoolBorder("Inscription"));
		labelPseudo = new JLabel();
		labelPseudo.setText("Pseudo :");
		labelPseudo.setFont(getFunnyFont());
		labelEmail = new JLabel();
		labelEmail.setText("Email :");
		labelEmail.setFont(getFunnyFont());
		password = new JLabel();
		password.setText("Password :");
		password.setFont(getFunnyFont());
		fieldPseudo = new JTextField(20);
		fieldEmail = new JTextField(20);
		fieldPassword = new JPasswordField(20);
		buttonValid = new JButton();
		buttonValid.setText("S'inscrire");
		buttonValid.setBackground(new Color(70, 130, 180));
		buttonValid.setForeground(Color.WHITE);
		buttonValid.setFont(getFunnyFont());
		buttonValid.addActionListener(this);
		panel.add(labelPseudo);
		panel.add(fieldPseudo);
		panel.add(labelEmail);
		panel.add(fieldEmail);
		panel.add(password);
		panel.add(fieldPassword);
		panel.add(buttonValid);
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
			/*
			 * La frame principale est désactivée quand cette frame est
			 * instanciée, elle est réactivée par la méthode ci-dessous
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				AppFrame.appFrame.setEnabled(true);

			}

			@Override
			public void windowClosed(WindowEvent e) {

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
		this.getContentPane().add(panel);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
	/*
	 * Méthode pour ajouter une font
	 */
	public Font getFunnyFont() {
		Font font = new Font("", Font.BOLD, 16);
		return font;
	}
	/*
	 * Méthode pour ajouter une bordure avec un titre
	 */
	private Border getCoolBorder(String titre) {
		Border lowBorder = BorderFactory.createBevelBorder(35);
		Border titleBorder = BorderFactory.createTitledBorder(lowBorder, titre,
				TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Arial", Font.BOLD, 18), new Color(138, 43, 226));
		return titleBorder;
	}
	/*
	 * Action listener(s) qui valident les champs d'inscription
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String password = new String(fieldPassword.getPassword());
		if (e.getSource() == buttonValid && !fieldPseudo.getText().isEmpty()
				&& !fieldEmail.getText().isEmpty() && !password.isEmpty()) {
			ctrlConnexion.register(fieldPseudo.getText(), fieldEmail.getText(),
					password);
			/*
			 * La frame principale est réactivée
			 */
			AppFrame.appFrame.setEnabled(true);
			dispose();
			/*
			 * Fenêtre de dialogue confirmant l'inscription
			 */
			JOptionPane.showMessageDialog(AppFrame.appFrame,
					"Votre compte a été créé", "Inscription", 1);
		} else {
			JOptionPane.showMessageDialog(AppFrame.appFrame,
					"Les champs sont vides", "Identifiants",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
