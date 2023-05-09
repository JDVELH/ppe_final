package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import controleur.CtrlJeu;
import controleur.CtrlLivre;
import controleur.CtrlPage;
import modele.Livre;
import modele.Page;
/*
 * Cette classe affiche la vue qui permet de créer et d'éditer les pages
 */
public class VueEditeur extends JPanel implements ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Page> pages;
	private Livre livre;
	private JToolBar toolBar2;
	private JButton addButton;
	private JButton trashButton;
	private JButton playButton;
	private JButton stopButton;
	private JButton editButton;
	private JTabbedPane panelTabbed;
	private JPanel panelNorth;
	private JPanel panelWest;
	private JScrollPane scrollPane;
	private JList<Page> listPage;
	private DefaultListModel<Page> listModel;
	private CtrlPage ctrlPage;
	private CtrlJeu ctrlGame;
	private CtrlLivre ctrlLivre;

	public VueEditeur(Livre livre) {
		ctrlLivre = new CtrlLivre();
		ctrlPage = new CtrlPage();
		ctrlGame = new CtrlJeu();
		// Déclaration des images pour les boutons
		ImageIcon iconAdd = new ImageIcon("icons1.png");
		ImageIcon iconTrash = new ImageIcon("iconTrash.png");
		ImageIcon iconPlay = new ImageIcon("icons-play.png");
		ImageIcon iconStop = new ImageIcon("icon-stop.png");
		ImageIcon iconEdit = new ImageIcon("icon-pencil.png");

		this.livre = livre;
		listModel = new DefaultListModel<Page>();
		listPage = new JList<Page>(listModel);
		this.pages = livre.pages;
		loadPages();
		this.setLayout(new BorderLayout());
		panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout());
		addButton = new JButton(iconAdd);
		addButton.addActionListener(this);
		trashButton = new JButton(iconTrash);
		trashButton.addActionListener(this);
		editButton = new JButton(iconEdit);
		editButton.addActionListener(this);
		playButton = new JButton(iconPlay);
		playButton.addActionListener(this);
		stopButton = new JButton(iconStop);
		stopButton.setEnabled(false);
		stopButton.addActionListener(this);
		this.add(panelNorth, BorderLayout.NORTH);
		panelWest = new JPanel();
		panelWest.setBorder(getCoolBorder(livre.getTitreLivre()));
		scrollPane = new JScrollPane(listPage);
		scrollPane.setPreferredSize(new Dimension(200, 300));
		panelTabbed = new JTabbedPane();
		panelTabbed.setBackground(Color.black);
		toolBar2 = new JToolBar();
		toolBar2.add(addButton);
		toolBar2.add(trashButton);
		toolBar2.add(editButton);
		toolBar2.add(playButton);
		toolBar2.add(stopButton);
		panelNorth.add(toolBar2, BorderLayout.NORTH);
		panelWest.add(scrollPane, BorderLayout.WEST);
		this.add(panelWest, BorderLayout.WEST);
	}
	/*
	 * Méthode qui permet de charger les pages dans une liste
	 */
	private void loadPages() {

		for (int i = 0; i < this.pages.size(); i++) {
			listModel.addElement(this.pages.get(i));
		}
	}
	/*
	 * Action listener des boutons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addButton) {
			ctrlPage.viewAddPage(livre);// Le controleur ajoute une page

		}
		if (e.getSource() == editButton) {

			if (listPage.getSelectedValue() != null) {
				for (int i = 0; i < listPage.getSelectedValue()
						.getPageSuccesseurs().size(); i++) {
					System.out.println(listPage.getSelectedValue()
							.getPageSuccesseurs().get(i).getNomPage());
				}
				ctrlPage.editPage(listPage.getSelectedValue(), livre);
			}
		}
		if (e.getSource() == trashButton) {

			if (listPage.getSelectedValue() != null) {
				Page selectedItem = listPage.getSelectedValue();
				int selectIndex = listPage.getSelectedIndex();
				ctrlPage.suppPage(selectedItem);// le controleur supprime une
												// page
				listModel.remove(selectIndex);
			}
		}
		/*
		 * Lance la pré-visualisation du livre
		 */
		if (e.getSource() == playButton) {
			editButton.setEnabled(false);
			addButton.setEnabled(false);
			trashButton.setEnabled(false);
			playButton.setEnabled(false);
			stopButton.setEnabled(true);
			ctrlGame.jouerLivreTest(this.pages);

		}
		/*
		 * Arrête la pré-visualisation du livre
		 */
		if (e.getSource() == stopButton) {
			editButton.setEnabled(true);
			addButton.setEnabled(true);
			trashButton.setEnabled(true);
			playButton.setEnabled(true);
			ctrlLivre.editerLivre(livre);
		}
	}
	/*
	 * Méthode qui ajoute une bordure avec un titre
	 */
	private Border getCoolBorder(String titre) {
		Border lowBorder = BorderFactory.createBevelBorder(10);
		Border titleBorder = BorderFactory.createTitledBorder(lowBorder, titre,
				TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Arial", Font.BOLD, 18), Color.black);
		return titleBorder;
	}
}
