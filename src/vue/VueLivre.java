package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import controleur.CtrlLivre;
import modele.Livre;
/*
 * Cette classe affiche un JPanel qui permet de créer des livres et de les éditer
 */
public class VueLivre extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnIcon;
	private JButton buttonAdd;
	private JButton buttonEdit;
	private JButton buttonSupp;
	private CtrlLivre ctrlLivre;
	private JList<Livre> listBook;
	private DefaultListModel<Livre> listModel;
	private ArrayList<Livre> livres;
	private String pseudo;
	private JScrollPane scrollPane;
	private JToolBar toolBar;

	public VueLivre(ArrayList<Livre> livres) {
		ctrlLivre = new CtrlLivre();

		ImageIcon icon = new ImageIcon("icon-happy.png");
		ImageIcon iconAdd = new ImageIcon("icons1.png");
		ImageIcon iconEdit = new ImageIcon("icon-pencil.png");
		ImageIcon iconSupp = new ImageIcon("iconTrash.png");

		this.setLayout(new BorderLayout());
		this.livres = livres;

		JPanel panelUser = new JPanel();
		JPanel panelWest = new JPanel();
		JPanel panelNorth = new JPanel();
		JPanel panelSouth = new JPanel();

		panelNorth.setLayout(new BorderLayout());
		listModel = new DefaultListModel<Livre>();
		listBook = new JList<Livre>(listModel);
		scrollPane = new JScrollPane(listBook);
		scrollPane.setLayout(new ScrollPaneLayout());
		scrollPane.setPreferredSize(new Dimension(100, 100));
		panelWest.add(scrollPane);
		panelWest.setBorder(getCoolBorder("Livres"));
		btnIcon = new JButton();
		btnIcon.setIcon(icon);
		pseudo = AppFrame.appFrame.user.getPseudo();
		btnIcon.setText(pseudo);
		btnIcon.addActionListener(this);
		panelUser.add(btnIcon);

		buttonAdd = new JButton(iconAdd);
		buttonAdd.addActionListener(this);
		buttonEdit = new JButton(iconEdit);
		buttonEdit.addActionListener(this);
		buttonSupp = new JButton(iconSupp);
		buttonSupp.addActionListener(this);
		loadBook();
		// toolbar
		toolBar = new JToolBar();
		toolBar.add(buttonAdd);
		toolBar.add(buttonEdit);
		toolBar.add(buttonSupp);

		panelNorth.add(btnIcon, BorderLayout.EAST);
		panelNorth.add(toolBar, BorderLayout.WEST);
		this.add(panelNorth, BorderLayout.NORTH);
		this.add(panelSouth, BorderLayout.SOUTH);
		this.add(panelWest, BorderLayout.WEST);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * NOUVEAU LIVRE
		 */
		if (e.getSource() == buttonAdd) {
			ctrlLivre.nouveauLivre();
			AppFrame.appFrame.setEnabled(false);// Action sur la frame
												// principale

		}
		if (e.getSource() == buttonEdit) {
			/*
			 * EDITION DU LIVRE
			 */
			if (listBook.getSelectedValue() != null) {
				ctrlLivre.editerLivre(listBook.getSelectedValue());
			}
		}
		if (e.getSource() == buttonSupp) {
			/*
			 * SUPPRIMER LIVRE
			 */
			Livre selectedItem = listBook.getSelectedValue();
			int selectedIndex = listBook.getSelectedIndex();
			ctrlLivre.supprimerLivre(selectedItem);
			listModel.remove(selectedIndex);
			/*
			 * RETOUR A LA VUE CHOIX
			 */
		}
		/*
		 * VUE DU PROFIL
		 */
		if (e.getSource() == btnIcon) {
			AppFrame.appFrame.setEnabled(false);
			new VueProfil();
		}
	};
	/*
	 * Méthode pour ajouter une bordure avec un titre
	 */
	private Border getCoolBorder(String titre) {
		Border lowBorder = BorderFactory.createBevelBorder(35);
		Border titleBorder = BorderFactory.createTitledBorder(lowBorder, titre,
				TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Arial", Font.BOLD, 18), Color.black);
		return titleBorder;
	}

	/*
	 * METHODE QUI CHARGE LES LIVRES DANS UNE LISTE
	 */
	private void loadBook() {

		if (livres.size() > 0)

			for (Livre livre : this.livres) {
				listModel.addElement(livre);
			}
	}
}
