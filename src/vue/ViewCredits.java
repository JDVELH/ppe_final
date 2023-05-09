package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import controleur.CtrlAffTexte;

public class ViewCredits extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	public ViewCredits() {

		Color couleur = new Color(125, 0, 255, 255);
		Color couleur2 = new Color(125, 0, 125, 255);
		Border bord = BorderFactory.createLineBorder(couleur, 2);// BORDURES
		Border bord2 = BorderFactory.createLineBorder(Color.WHITE, 1);

		/*
		 * ------------------------ Bouton FERMER
		 * -------------------------------- sera affiché dans le JPanel CENTER,
		 * taille de l'image : 143 x 62
		 */
		String imgUrl = "C:/Users/greta/eclipse-workspace/PPE_V2/src/Images/";
		String imgBtn7 = "bt_fermer.png";
		ImageIcon icon7 = new ImageIcon((imgUrl + imgBtn7));
		JLabel fermer = new JLabel();
		fermer.setIcon(icon7);
		fermer.setVerticalAlignment(JLabel.TOP); // positionne texte+image
													// verticalement DANS le
													// JLabel
		fermer.setHorizontalAlignment(JLabel.RIGHT); // idem horizontalement
		fermer.setBorder(bord2);

		// gère le click sur le bouton Fermer, pour renvoyer à l'accueil
		fermer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent mevt) {
				new CtrlAffTexte(7);
			}
		});
		/*
		 * ------------------------ FIN Bouton FERMER
		 * --------------------------------
		 */

		/* Layout qui détermine l'affichage dans le Panel */
		this.setLayout(new BorderLayout());

		/* ---------------- définition des JPanel ---------------- */
		JPanel panelN = new JPanel();
		JPanel panelS = new JPanel();
		JPanel panelE = new JPanel();
		JPanel panelW = new JPanel();
		JPanel panelC = new JPanel();
		panelN.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelS.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelC.setLayout(new FlowLayout(FlowLayout.LEFT));

		panelN.setBackground(couleur2);
		panelS.setBackground(couleur2);
		panelE.setBackground(couleur2);
		panelW.setBackground(couleur2);
		panelC.setBackground(couleur2);

		panelN.setPreferredSize(new Dimension(800, 40));
		panelS.setPreferredSize(new Dimension(800, 70));
		panelE.setPreferredSize(new Dimension(10, 690));
		panelW.setPreferredSize(new Dimension(10, 690));
		panelC.setPreferredSize(new Dimension(780, 690));
		/* ---------------- FIN de la définition des JPanel ---------------- */

		// JLabel de titre : sera affiché dans le JPanel NORTH
		JLabel txtTitre = new JLabel("CREDITS"); // initialise la zone de titre
													// et la positionne à Gauche
		txtTitre.setBackground(couleur2);
		txtTitre.setForeground(Color.white);
		txtTitre.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		txtTitre.setHorizontalAlignment(JLabel.LEFT);

		// JLabel de texte : sera affiché dans le JPanel CENTER
		JTextPane txtPanneau = new JTextPane(); // initialise la zone de texte
		txtPanneau.setLayout(new FlowLayout(FlowLayout.LEADING));
		txtPanneau.setBackground(couleur2);

		// définition des styles
		Style defaut = txtPanneau.getStyle("default");
		Style style1 = txtPanneau.addStyle("style1", defaut);
		StyleConstants.setFontFamily(style1, "Comic sans MS");
		StyleConstants.setBold(style1, true);
		StyleConstants.setBackground(style1, couleur2);
		StyleConstants.setForeground(style1, Color.WHITE);

		// Construction des textes à afficher
		String s1 = "Credits\n\n";
		String s2 = "Ici s'afficheront les informations de credits. Elles comprennent : \n"
				+ ". les noms et prénoms des développeurs,\n"
				+ ". les noms et raisons sociales des sociétés ayant collaboré au logiciel,\n"
				+ ". etc.";

		StyledDocument sDoc = (StyledDocument) txtPanneau.getDocument();
		try {
			int pos = 0;
			sDoc.insertString(pos, s1, style1);
			pos += s1.length();
			sDoc.insertString(pos, s2, style1);
			pos += s2.length();
		} catch (BadLocationException e) {
		}

		panelN.add(txtTitre);
		panelS.add(fermer);
		panelC.add(txtPanneau);

		this.setBounds(20, 20, 800, 800); // définit le setBounds pour le JPanel
		this.setBackground(couleur2); // définit la couleur de fond du JPanel
		this.setBorder(bord); // affecte le border au JLabel (ici, la fenêtre)
								// => NE FONCTIONNE PAS ???

		this.add(panelN, BorderLayout.NORTH);
		this.add(panelS, BorderLayout.SOUTH);
		this.add(panelE, BorderLayout.EAST);
		this.add(panelW, BorderLayout.WEST);
		this.add(panelC, BorderLayout.CENTER);

		this.setVisible(true);

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
}