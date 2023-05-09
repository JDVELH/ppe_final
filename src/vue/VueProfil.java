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
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import controleur.CtrlUtilisateur;
/*
 * Cette classe affiche une JFrame qui affiche les informations de l'utilisateur
 */
public class VueProfil extends JFrame implements ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel labelPseudo;
	private JLabel labelPseudoUtitlisateur;
	private JLabel labelEmail;
	private JLabel labelEmailUtitlisateur;
	private JLabel labelMdp;
	private JLabel labelMdpUtilisateur;
	private JButton btnSupp;
	private CtrlUtilisateur ctrlUtilisateur;

	public VueProfil() {
		ctrlUtilisateur = new CtrlUtilisateur();
		// -------------------------------->
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(getCoolBorder("Profil"));
		panel.setPreferredSize(new Dimension(350, 200));
		panel.setLayout(new GridLayout(4, 1));
		// -------------------------------->
		labelPseudo = new JLabel();
		labelPseudo.setText("Pseudo : ");
		labelPseudo.setFont(getFunnyFont());
		// -------------------------------->
		labelPseudoUtitlisateur = new JLabel();
		labelPseudoUtitlisateur.setText(AppFrame.appFrame.user.getPseudo());
		labelPseudoUtitlisateur.setFont(getFunnyFont());
		// -------------------------------->
		labelEmail = new JLabel();
		labelEmail.setText("Email : ");
		labelEmail.setFont(getFunnyFont());
		// -------------------------------->
		labelEmailUtitlisateur = new JLabel();
		labelEmailUtitlisateur.setText(AppFrame.appFrame.user.getEmail());
		labelEmailUtitlisateur.setFont(getFunnyFont());
		// -------------------------------->
		labelMdp = new JLabel();
		labelMdp.setText("Mot de passe : ");
		labelMdp.setFont(getFunnyFont());
		// -------------------------------->
		labelMdpUtilisateur = new JLabel();
		labelMdpUtilisateur.setText(AppFrame.appFrame.user.getMdp());
		labelMdpUtilisateur.setFont(getFunnyFont());

		btnSupp = new JButton();
		btnSupp.setText("Supprimer le compte");
		btnSupp.setForeground(Color.white);
		btnSupp.setBackground(new Color(70, 130, 180));
		btnSupp.addActionListener(this);
		// -------------------------------->
		panel.add(labelPseudo);
		panel.add(labelPseudoUtitlisateur);
		panel.add(labelEmail);
		panel.add(labelEmailUtitlisateur);
		panel.add(labelMdp);
		panel.add(labelMdpUtilisateur);
		panel.add(btnSupp);
		// -------------------------------->
		this.add(panel);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
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
			@Override
			public void windowClosing(WindowEvent e) {
				AppFrame.appFrame.setEnabled(true);

			}
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}
	/*
	 * Méthode qui ajoute une bordure avec un titre
	 */
	private Border getCoolBorder(String titre) {
		Border lowBorder = BorderFactory.createBevelBorder(35);
		Border titleBorder = BorderFactory.createTitledBorder(lowBorder, titre,
				TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Arial", Font.BOLD, 18), new Color(138, 43, 226));
		return titleBorder;
	}
	/*
	 * Méthode qui ajoute une font
	 */
	public Font getFunnyFont() {
		Font font = new Font("", Font.BOLD, 16);
		return font;
	}
	/*
	 * Supprime le compte utitlisateur
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnSupp) {
			AppFrame.appFrame.setEnabled(true);
			ctrlUtilisateur
					.supprimerUtilisateur(AppFrame.appFrame.user.getPseudo());
			dispose();
		}
	}
}
