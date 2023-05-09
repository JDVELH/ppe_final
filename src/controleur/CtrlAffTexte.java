package controleur;

import vue.AppFrame;
import vue.ViewAcc;
import vue.ViewCopyright;
import vue.ViewCredits;
import vue.ViewEvaluez;
import vue.ViewMentionsLeg;
import vue.ViewPartenariats;
import vue.ViewTutoriel;

/**
 * La classe CtrlAffTexte est utilisée pour récupérer l'indice du texte à
 * afficher et appeler le JPanel correspondant
 */

public class CtrlAffTexte { // Déclaration de la classe
	/*
	 * Constructeur
	 */
	public CtrlAffTexte(int idBt) {

		switch (idBt) {

			case 1 : // Le bouton Mentions légales a été cliqué
				// On appelle la classe contenant le JPanel correspondant
				ViewMentionsLeg mentionsLeg = new ViewMentionsLeg();
				AppFrame.appFrame.getContentPane().removeAll(); // Le
				AppFrame.appFrame.getContentPane().add(mentionsLeg);
				AppFrame.appFrame.getContentPane().repaint();
				AppFrame.appFrame.getContentPane().revalidate();
				break;

			case 2 : // Le bouton Copyright a été cliqué
				ViewCopyright copyright = new ViewCopyright();
				AppFrame.appFrame.getContentPane().removeAll(); // Le
				AppFrame.appFrame.getContentPane().add(copyright);
				AppFrame.appFrame.getContentPane().repaint();
				AppFrame.appFrame.getContentPane().revalidate();
				break;

			case 3 : // Le bouton Credits a été cliqué
				ViewCredits credits = new ViewCredits();
				AppFrame.appFrame.getContentPane().removeAll(); // Le
				AppFrame.appFrame.getContentPane().add(credits);
				AppFrame.appFrame.getContentPane().repaint();
				AppFrame.appFrame.getContentPane().revalidate();
				break;

			case 4 : // Le bouton Partenariats a été cliqué
				ViewPartenariats partenariats = new ViewPartenariats();
				AppFrame.appFrame.getContentPane().removeAll(); // Le
				AppFrame.appFrame.getContentPane().add(partenariats);
				AppFrame.appFrame.getContentPane().repaint();
				AppFrame.appFrame.getContentPane().revalidate();
				break;

			case 5 : // Le bouton Evaluez a été cliqué
				ViewEvaluez evaluez = new ViewEvaluez();
				AppFrame.appFrame.getContentPane().removeAll(); // Le
																// getContentPane
				// récupére le
				// conteneur sur
				// lequel va être
				// appliqué les
				// modifs
				AppFrame.appFrame.getContentPane().add(evaluez);
				AppFrame.appFrame.getContentPane().repaint();
				AppFrame.appFrame.getContentPane().revalidate();
				break;

			case 6 : // Le bouton Tutoriel a été cliqué
				ViewTutoriel tutoriel = new ViewTutoriel();
				AppFrame.appFrame.getContentPane().removeAll();
				AppFrame.appFrame.getContentPane().add(tutoriel);
				AppFrame.appFrame.getContentPane().repaint();
				AppFrame.appFrame.getContentPane().revalidate();
				break;

			case 7 :
				ViewAcc acc = new ViewAcc();
				AppFrame.appFrame.getContentPane().removeAll();
				AppFrame.appFrame.getContentPane().add(acc);
				AppFrame.appFrame.getContentPane().repaint();
				AppFrame.appFrame.getContentPane().revalidate();
				break;

		}
	}

}
