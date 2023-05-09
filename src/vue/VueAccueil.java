package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import controleur.CtrlUtilisateur;
/*
 * C'est la première vue affichée quand on lance l'application
 */
public class VueAccueil extends JPanel implements ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private CtrlUtilisateur ctrlUtilisateur;
	private JPanel panelConnect;
	private JPanel panelName;
	private JLabel labelName;
	private JLabel labelPseudo;
	private JLabel labelPassword;
	private JLabel labelPoint;
	private JTextField fieldPseudo;
	private JPasswordField passwordField;
	private JButton btnValid;
	private JButton btnRegister;
	/*
	 *
	 */
	public VueAccueil() {
		ctrlUtilisateur = new CtrlUtilisateur();
		this.setLayout(null);
		panelConnect = new JPanel();
		panelConnect.setBackground(Color.white);
		panelConnect.setBounds(800, 200, 400, 300);
		panelConnect.setLayout(new GridLayout(6, 1));
		panelConnect.setBorder(getCoolBorder("Connexion"));
		panelName = new JPanel();
		panelName.setLayout(new FlowLayout());
		panelName.setBounds(100, 250, 600, 400);
		labelName = new JLabel();
		labelPoint = new JLabel();
		labelPoint.setText(".");
		labelPoint.setFont(new Font("", Font.BOLD, 90));
		labelPoint.setForeground(new Color(70, 130, 180));
		labelName.setText("JDVELH");
		labelName.setForeground(new Color(138, 43, 226));
		labelName.setFont(new Font("", Font.BOLD, 90));
		panelName.add(labelName);
		panelName.add(labelPoint);
		labelPseudo = new JLabel();
		labelPseudo.setText("Pseudo :");
		labelPseudo.setFont(getFunnyFont());
		labelPassword = new JLabel("Mot de passe :");
		labelPassword.setFont(getFunnyFont());
		fieldPseudo = new JTextField(10);
		fieldPseudo.setFont(getFunnyFont());
		passwordField = new JPasswordField(10);
		passwordField.setFont(getFunnyFont());
		btnRegister = new JButton("Créer un compte");
		btnRegister.setPreferredSize(new Dimension(100, 25));
		btnRegister.setBackground(new Color(70, 130, 180));
		btnRegister.setForeground(Color.white);
		btnRegister.setFont(getFunnyFont());
		btnRegister.addActionListener(this);
		btnValid = new JButton("Se connecter");
		btnValid.setBackground(new Color(138, 43, 226));
		btnValid.setForeground(Color.white);
		btnValid.setFont(getFunnyFont());
		btnValid.setPreferredSize(new Dimension(300, 25));
		btnValid.addActionListener(this);
		panelConnect.add(labelPseudo);
		panelConnect.add(fieldPseudo);
		panelConnect.add(labelPassword);
		panelConnect.add(passwordField);
		panelConnect.add(btnValid);
		panelConnect.add(btnRegister);
		this.add(panelConnect);
		this.add(panelName);
	}

	/*
	 * METHODE POUR AJOUTER UNE BORDURE COOL
	 */
	private Border getCoolBorder(String titre) {
		Border lowBorder = BorderFactory.createBevelBorder(35);
		Border titleBorder = BorderFactory.createTitledBorder(lowBorder, titre,
				TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Arial", Font.BOLD, 18), new Color(138, 43, 226));
		return titleBorder;
	}

	/*
	 * METHODE POUR AJOUTER UNE FONT FUNNY
	 */
	public Font getFunnyFont() {
		Font font = new Font("", Font.BOLD, 16);
		return font;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String password = new String(passwordField.getPassword());
		/*
		 * APPEL DE LA METHODE CONNECT DE CTRLCONNEXION
		 */
		if (e.getSource() == btnValid && !fieldPseudo.getText().isEmpty()
				&& !password.isEmpty()) {
			ctrlUtilisateur.connect(fieldPseudo.getText(), password);
		}
		/*
		 * APPEL DE LA METHODE REGISTER DE CTRLCONNEXION
		 */
		else if (e.getSource() == btnRegister) {
			ctrlUtilisateur.registerView();
			AppFrame.appFrame.setEnabled(false);
		} else {
			JOptionPane.showMessageDialog(AppFrame.appFrame,
					"Les champs sont vides", "Identifiants",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
