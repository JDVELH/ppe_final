package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import controleur.CtrlPage;
import modele.Livre;
import modele.Page;
/*
 * Cette classe affiche une JFrame qui permet d'ajouter une page
 */
public class VueAjouterPage extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Page page;
	private JPanel panel;
	private JLabel labelNom;
	private JTextField fieldNom;
	private JButton boutonValider;
	private CtrlPage ctrlPage;
	/*
	 *
	 */
	public VueAjouterPage(Livre livre) {
		page = new Page("", "", "", livre.getTitreLivre(),
				AppFrame.appFrame.user);
		ctrlPage = new CtrlPage();
		panel = new JPanel();
		panel.setBorder(getCoolBorder(livre.getTitreLivre()));
		panel.setLayout(new GridLayout(3, 1));
		labelNom = new JLabel("Nom de la page :");
		fieldNom = new JTextField(20);
		boutonValider = new JButton("Valider");
		/*
		 * Méthode qui permet de valider en tapant Entrée
		 */
		fieldNom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				page.setNomPage(fieldNom.getText());
				if (e.getKeyCode() == KeyEvent.VK_ENTER
						&& !(fieldNom.getText().isEmpty())) {
					ctrlPage.ajouterPage(livre, page);// Le controleur ajoute
														// une page
					dispose();
				}
			}
		});
		/*
		 * Action listener
		 */
		boutonValider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				page.setNomPage(fieldNom.getText());

				if (e.getSource() == boutonValider
						&& !(fieldNom.getText().isEmpty())) {
					ctrlPage.ajouterPage(livre, page);// Le controleur ajoute
														// une page
					dispose();
				}
			}
		});
		panel.add(labelNom);
		panel.add(fieldNom);
		panel.add(boutonValider);
		this.add(panel);
		this.setAlwaysOnTop(true);// Toujours au top!
		this.pack();
		this.setVisible(true);
	}
	/*
	 * Méthode pour ajouter une bordure avec un titre
	 */
	private Border getCoolBorder(String titre) {
		Border lowBorder = BorderFactory.createBevelBorder(10);
		Border titleBorder = BorderFactory.createTitledBorder(lowBorder, titre,
				TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Arial", Font.BOLD, 18), Color.black);
		return titleBorder;
	}
}
