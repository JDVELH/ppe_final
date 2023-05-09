package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

import controleur.CtrlLivre;
import modele.Livre;
/*
 * Classe qui affiche la vue de choix du jeu
 */
public class VueChoixJeu extends JPanel implements ActionListener {
	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CtrlLivre ctrlLivre;
	private ArrayList<Livre> livres;
	private JScrollPane listBook;
	private JList<Livre> listLivre;
	private JButton btnJouer;
	private DefaultListModel<Livre> listModel;
	private JPanel panelCenter;
	private ImageIcon avatar = new ImageIcon("icon-happy.png");
	private JButton btnAvatar;
	private String pseudo = AppFrame.appFrame.user.getPseudo();
	private JPanel panelNorth;
	private JPanel panelSouth;

	public VueChoixJeu(ArrayList<Livre> livres) {
		ctrlLivre = new CtrlLivre();
		this.setLayout(new BorderLayout());
		this.livres = new ArrayList<Livre>();

		for (int i = 0; i < livres.size(); i++) {
			this.livres.add(livres.get(i));
		}
		panelCenter = new JPanel();
		panelCenter.setPreferredSize(new Dimension(300, 200));
		panelNorth = new JPanel();
		panelNorth.setLayout(new FlowLayout());
		panelSouth = new JPanel();
		btnAvatar = new JButton(avatar);
		btnAvatar.setText(pseudo);
		btnAvatar.addActionListener(this);
		listModel = new DefaultListModel<Livre>();
		listLivre = new JList<Livre>(listModel);
		listBook = new JScrollPane(listLivre);
		listBook.setLayout(new ScrollPaneLayout());
		listBook.setPreferredSize(new Dimension(100, 100));
		panelCenter.add(listBook);
		btnJouer = new JButton();
		btnJouer.setText("JOUER!");
		btnJouer.setPreferredSize(new Dimension(150, 50));
		btnJouer.addActionListener(this);
		panelSouth.add(btnJouer);
		panelNorth.add(btnAvatar);
		loadBook();
		this.add(panelNorth, BorderLayout.NORTH);
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelSouth, BorderLayout.SOUTH);
	}
	/*
	 * Méthode qui charge les livre dans une liste
	 */
	private void loadBook() {
		for (Livre livre : this.livres) {
			listModel.addElement(livre);
		}
	}
	/*
	 * Action listener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAvatar) {
			AppFrame.appFrame.setEnabled(false);
			new VueProfil();
		}
		if (e.getSource() == btnJouer) {
			Livre selectedItem = listLivre.getSelectedValue();
			ctrlLivre.jouerLivre(selectedItem); // Le controleur lance le jeu
		}
	}
}
