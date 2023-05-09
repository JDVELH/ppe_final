package vue;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Page;

public class FenetreAfficherLivre extends JPanel {

	private static final long serialVersionUID = 1L;
	JLabel l1;
	public String s = "HGHGHG";
	String lignes;
	String[] colonnes;
	String[][] tab;
	String[] tabLignes;
	TextField textField;
	JButton button;
	ArrayList<Page> page;
	Container container;

	public FenetreAfficherLivre() {

		this.setLayout(new FlowLayout());
		this.setSize(300, 200);
		this.page = new ArrayList<Page>();
		// Livre livre = new Livre("Mon livre", page, );
		// lignes = livre.afficherLivre(livre.getPages().get(0), "", page);
		tabLignes = lignes.split("/");

		for (String ligne : tabLignes) {
			JButton button = new JButton(ligne);
			this.add(button);

		}
	}
}
