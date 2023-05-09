package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import controleur.CtrlAffTexte;
import controleur.CtrlChoix;

/**
 * La classe ViewAccueil est un JPanel qui permet : - de choisir son mode
 * (créateur ou joueur), - de choisir le livre à jouer, - de gérer les
 * paramètres - d'accèder aux informations légales, crédits, etc.
 */
public class ViewAcc extends JPanel implements MouseListener {
	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewAcc() {
		this.setLayout(new BorderLayout(0, 0));
		Color couleur = new Color(125, 0, 255, 255);
		String imgUrl = "C:/Users/greta/eclipse-workspace/PPE_V2/src/Images/";
		String imgBtn1 = "bt_infos_leg.png";
		String imgBtn2 = "bt_copyright.png";
		String imgBtn3 = "bt_credits.png";
		String imgBtn4 = "bt_partenariat.png";
		String imgBtn5 = "bt_evaluez.png";
		String imgBtn6 = "bt_tutoriel.png";
		String imgJouer = "bt_jouer.png";
		String imgCreer = "bt_creer.png";
		/* --------------------- mes panels --------------------- */
		JPanel pTitre = new JPanel(); // contiendra le titre du Jeu, le livre,
										// le nom de la page
		JPanel pGauche = new JPanel(); // indéterminé
		JPanel pDroite = new JPanel(new FlowLayout()); // contiend les boutons
														// Compte et Tutoriels
		JPanel pBas = new JPanel(new FlowLayout()); // contiendra les boutons
													// pour les textes divers
		JPanel pCentre = new JPanel(new FlowLayout()); // affichage général

		pTitre.setBackground(Color.black); // déclare les couleurs des JPanel
		pGauche.setBackground(Color.yellow);
		pDroite.setBackground(Color.yellow);
		pBas.setBackground(Color.white);
		pCentre.setBackground(couleur);

		pTitre.setPreferredSize(new Dimension(1700, 80)); // déclare les
															// dimensions des
															// JPanel
		pGauche.setPreferredSize(new Dimension(50, 600));
		pDroite.setPreferredSize(new Dimension(200, 600));
		pBas.setPreferredSize(new Dimension(1700, 120));
		pCentre.setPreferredSize(new Dimension(1700, 600));

		// Texte de bienvenue
		JLabel okCnx = new JLabel(
				"Bienvenue sur Le jeu dont vous êtes le héros", JLabel.LEFT);
		okCnx.setForeground(Color.white);
		okCnx.setFont(new Font("Comic Sans Ms", Font.BOLD, 15));
		/*
		 * ----------------- affiche l'image bouton Infos légales
		 * -----------------
		 */
		ImageIcon icon1 = new ImageIcon((imgUrl + imgBtn1));
		JLabel mentionsLeg = new JLabel();
		mentionsLeg.setIcon(icon1);

		mentionsLeg.addMouseListener(new MouseAdapter() {
			// @Override
			@Override
			public void mouseClicked(final MouseEvent mevt) {
				new CtrlAffTexte(1);
			}
		});
		/*
		 * ----------------- affiche l'image bouton Copyright -----------------
		 */
		ImageIcon icon2 = new ImageIcon((imgUrl + imgBtn2));
		JLabel copyright = new JLabel();
		copyright.setIcon(icon2);

		copyright.addMouseListener(new MouseAdapter() {
			// @Override
			@Override
			public void mouseClicked(final MouseEvent mevt) {
				new CtrlAffTexte(2);
			}
		});
		/* ----------------- affiche l'image bouton Crédits ----------------- */
		ImageIcon icon3 = new ImageIcon((imgUrl + imgBtn3));
		JLabel credits = new JLabel();
		credits.setIcon(icon3);

		credits.addMouseListener(new MouseAdapter() {
			// @Override
			@Override
			public void mouseClicked(final MouseEvent mevt) {
				new CtrlAffTexte(3);
			}
		});
		/*
		 * ----------------- affiche l'image bouton Partenariats
		 * -----------------
		 */
		ImageIcon icon4 = new ImageIcon((imgUrl + imgBtn4));
		JLabel partenariats = new JLabel();
		partenariats.setIcon(icon4);

		partenariats.addMouseListener(new MouseAdapter() {
			// @Override
			@Override
			public void mouseClicked(final MouseEvent mevt) {
				new CtrlAffTexte(4);
			}
		});
		/*
		 * ----------------- affiche l'image bouton Partenariats
		 * ----------------------
		 */
		ImageIcon icon5 = new ImageIcon((imgUrl + imgBtn5));
		JLabel evaluez = new JLabel();
		evaluez.setIcon(icon5);

		evaluez.addMouseListener(new MouseAdapter() {
			// @Override
			@Override
			public void mouseClicked(final MouseEvent mevt) {
				new CtrlAffTexte(5);
			}
		});
		/* ----------------- Bouton Tutoriel ------------------- */
		ImageIcon icon6 = new ImageIcon((imgUrl + imgBtn6));
		JLabel tutoriel = new JLabel();
		tutoriel.setIcon(icon6);

		tutoriel.addMouseListener(new MouseAdapter() {
			// @Override
			@Override
			public void mouseClicked(final MouseEvent mevt) {
				new CtrlAffTexte(6);
			}
		});
		/* ----------------- Bouton Connexion Compte ------------------- */
		ImageIcon icon = new ImageIcon("icon-happy.png");
		JButton cnx = new JButton();
		cnx.setBorder(getCoolBorder(AppFrame.appFrame.user.getPseudo()));
		cnx.setIcon(icon);
		cnx.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AppFrame.appFrame.setEnabled(false);
				new VueProfil();
			}
		});

		/* ----------------- CHOIX JOUER ------------------- */
		ImageIcon iJouer = new ImageIcon((imgUrl + imgJouer));
		JLabel jouer = new JLabel();
		jouer.setIcon(iJouer);

		jouer.addMouseListener(new MouseAdapter() {
			// @Override
			@Override
			public void mouseClicked(final MouseEvent mevt) {
				CtrlChoix ctrlChoix = new CtrlChoix();
				ctrlChoix.jouerLivre();
			}
		});

		/* ----------------- CHOIX CREER ------------------- */
		ImageIcon iCreer = new ImageIcon((imgUrl + imgCreer));
		JLabel creer = new JLabel();
		creer.setIcon(iCreer);

		creer.addMouseListener(new MouseAdapter() {
			// @Override
			@Override
			public void mouseClicked(final MouseEvent mevt) {
				CtrlChoix ctrlChoix = new CtrlChoix();
				ctrlChoix.creerLivre();
			}
		});

		/*
		 * ----------------- Add des boutons dans les panels ------------------
		 */
		pTitre.add(okCnx);
		pBas.add(mentionsLeg);

		pBas.add(copyright);
		pBas.add(credits);
		pBas.add(partenariats);
		pBas.add(evaluez);

		pDroite.add(cnx);
		pDroite.add(tutoriel);

		// pCentre.add(listPanelLivre);
		// pCentre.add(parcLivre);
		pCentre.add(creer);
		pCentre.add(jouer);

		this.add(pTitre, BorderLayout.NORTH);
		this.add(pGauche, BorderLayout.WEST);
		this.add(pDroite, BorderLayout.EAST);
		this.add(pBas, BorderLayout.SOUTH);
		this.add(pCentre, BorderLayout.CENTER);

		/*
		 * ------------------ Gestion des actions de la souris
		 * --------------------
		 */
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	private Border getCoolBorder(String titre) {
		Border lowBorder = BorderFactory.createBevelBorder(35);
		Border titleBorder = BorderFactory.createTitledBorder(lowBorder, titre,
				TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Arial", Font.BOLD, 18), Color.black);
		return titleBorder;
	}
}
