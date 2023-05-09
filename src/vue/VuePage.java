package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controleur.CtrlPage;
import controleur.CtrlSucc;
import modele.Livre;
import modele.Page;
/*
 * Cette classe affiche un JPanel qui permet de créer et d'éditer des pages
 */
public class VuePage extends JPanel implements ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JButton ajouterSuccesseur;
	private JButton suppSuccesseur;
	private JButton btn_AddImage;
	private JButton btn_Valid;
	private JComboBox<Page> choix;
	private CtrlPage ctrlPage;
	private CtrlSucc ctrlSucc;
	private ImageIcon icon;
	private JLabel labelImg;
	private JLabel labelNomPage;
	private Livre livre;
	private Page page;
	private ArrayList<Page> pages;
	private JScrollPane panelCenter;
	private JPanel panelEast;
	private JPanel panelImage;
	private JPanel panelNorth;
	private JPanel panelSouth;
	private JPanel panelSuccesseur;
	private JPanel panelSuccesseur2;
	private JPanel panelWest;
	private JTextArea textArea;
	/*
	 * Constructeur
	 */
	public VuePage(Page page, Livre livre) {
		ctrlSucc = new CtrlSucc();
		ctrlPage = new CtrlPage();
		labelNomPage = new JLabel();
		labelNomPage.setText(page.getNomPage());
		this.setLayout(new BorderLayout());
		this.page = page;
		this.livre = livre;
		this.pages = livre.pages;
		choix = new JComboBox<Page>(this.pages.toArray(new Page[0]));
		/*
		 * PANEL SUCCESSEURS
		 */
		panelSuccesseur = new JPanel();
		panelSuccesseur.setLayout(new GridLayout(8, 1));
		ajouterSuccesseur = new JButton("Ajouter successeur");
		ajouterSuccesseur.addActionListener(this);
		suppSuccesseur = new JButton("Supprimer successeur");
		suppSuccesseur.addActionListener(this);
		panelSuccesseur.setBorder(getCoolBorder("Successeurs"));
		panelSuccesseur.add(choix);
		panelSuccesseur.add(ajouterSuccesseur);
		panelSuccesseur.add(suppSuccesseur);
		/*
		 * ON AJOUTE LES SUCCESSEURS DE LA PAGE AU PANEL
		 */
		panelSuccesseur2 = new JPanel();

		for (int i = 0; i < page.getPageSuccesseurs().size(); i++) {
			JLabel label = new JLabel();
			label.setText(page.getPageSuccesseurs().get(i).getNomPage());
			panelSuccesseur2.add(label);
		}
		panelSuccesseur.add(panelSuccesseur2);
		/*
		 * PANEL IMAGE
		 */
		panelImage = new JPanel();
		icon = new ImageIcon(page.getImgFond());
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(100, 100,
				Image.SCALE_SMOOTH);
		ImageIcon icon2 = new ImageIcon(resizedImage);
		labelImg = new JLabel(icon2);
		panelImage.setBorder(getCoolBorder("Image"));
		btn_AddImage = new JButton();
		btn_AddImage.setText("Ajouter une image");
		btn_AddImage.addActionListener(this);
		/*
		 * PANEL TEXTE
		 */
		textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(200, 200));
		textArea.setText(page.getContenu());
		textArea.setFont(new Font("", Font.PLAIN, 16));
		// --------------------------------------------->
		panelCenter = new JScrollPane(textArea);
		panelEast = new JPanel();
		panelNorth = new JPanel();
		panelSouth = new JPanel();
		panelWest = new JPanel();
		panelWest.setLayout(new GridLayout(2, 1));
		panelCenter.setBorder(getCoolBorder("Texte"));
		// Bouton valider
		btn_Valid = new JButton("Valider");
		btn_Valid.addActionListener(this);
		btn_Valid.setFont(new Font("", Font.PLAIN, 18));

		panelImage.add(btn_AddImage);
		panelImage.add(labelImg);
		panelNorth.add(labelNomPage);
		panelWest.add(panelSuccesseur);
		panelWest.add(panelImage);
		panelSouth.add(btn_Valid);

		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelEast, BorderLayout.EAST);
		this.add(panelNorth, BorderLayout.NORTH);
		this.add(panelSouth, BorderLayout.SOUTH);
		this.add(panelWest, BorderLayout.WEST);
	}
	/*
	 * Action listeners
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// AJOUTER
		if (e.getSource() == ajouterSuccesseur) {
			Page pageS = (Page) choix.getSelectedItem();
			ctrlSucc.addSucc(this.page, pageS, this.livre);
			this.page.setContenu(textArea.getText());

			// SUPPRIMER
		} else if (e.getSource() == suppSuccesseur) {
			Page pageS = (Page) choix.getSelectedItem();
			ctrlSucc.suppSucc(this.page, pageS, this.livre);// Le contrôleur
															// successeur ajoute
															// ou supprime
															// directement dans
															// la base de
															// donnnées
			this.page.setContenu(textArea.getText());

			// VALIDER
		} else if (e.getSource() == btn_Valid) {
			this.page.setContenu(textArea.getText());
			ctrlPage.updateContent(this.page, this.livre);

			// AJOUTER IMAGE
		} else if (e.getSource() == btn_AddImage) {
			/*
			 * Ouvre le file chooser à l'emplacement spécifié
			 */
			JFileChooser fileChooser = new JFileChooser(
					"C:\\Users\\greta\\eclipse-workspace\\PPE_V2");
			fileChooser.setDialogTitle("Selectionnez une image");
			fileChooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"Images", "jpg", "png");
			fileChooser.addChoosableFileFilter(filter);
			int resultat = fileChooser.showOpenDialog(panelImage);

			if (resultat == JFileChooser.APPROVE_OPTION) {
				this.page.setImgFond(fileChooser.getSelectedFile().getName());
				this.page.setContenu(textArea.getText());
				ctrlPage.updateContent(page, livre);// Controleur qui met à jour
													// le contenu d'une page
			}
		}
	}
	/*
	 * Méthode pour ajouter une bordure avec un titre
	 */
	private Border getCoolBorder(String titre) {
		Border lowBorder = BorderFactory.createBevelBorder(75);
		Border titleBorder = BorderFactory.createTitledBorder(lowBorder, titre,
				TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Arial", Font.PLAIN, 18), Color.black);
		return titleBorder;
	}
}
