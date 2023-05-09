package controleur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modele.Livre;
import modele.Page;
import modele.Utilisateur;
import tool.DAOacces;
import vue.AppFrame;
import vue.VueChoixJeu;
import vue.VueLivre;
import vue.VueMenuBar;

public class CtrlChoix {

	public CtrlChoix() {
	}
	/*
	 * METHODE POUR INSTANCIER LA VUE DE CREATION / IMPORT DE TOUS LES LIVRES DE
	 * L UTILISATEUR
	 */
	public void creerLivre() {
		System.out.println("yoy");
		DAOacces access = new DAOacces();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		ArrayList<Livre> livres = new ArrayList<Livre>();
		ArrayList<Page> pages = new ArrayList<Page>();
		String sql = "SELECT * FROM livre WHERE auteur = ?;";
		try {
			connection = access.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, AppFrame.appFrame.user.getPseudo());
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				livres.add(new Livre(resultat.getString("titreLivre"), pages,
						AppFrame.appFrame.user));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		access.close();
		AppFrame.appFrame.controlHost.removeAll();
		/*
		 * VUE D EDITION DES LIVRES
		 */
		AppFrame.appFrame.setJMenuBar(new VueMenuBar());
		AppFrame.appFrame.controlHost.add(new VueLivre(livres));
		AppFrame.appFrame.controlHost.revalidate();
		AppFrame.appFrame.controlHost.repaint();
	}
	/*
	 * METHODE POUR INSTANCIER LA VUE DE JEU
	 */
	public void jouerLivre() {
		DAOacces access = new DAOacces();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		ArrayList<Livre> livres = new ArrayList<Livre>();
		ArrayList<Page> pages = new ArrayList<Page>();
		String sql = "SELECT * FROM livre";
		try {
			connection = access.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				livres.add(new Livre(resultat.getString("titreLivre"), pages,
						new Utilisateur(resultat.getString("auteur"), "", "")));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		access.close();
		AppFrame.appFrame.setJMenuBar(new VueMenuBar());
		AppFrame.appFrame.controlHost.removeAll();
		AppFrame.appFrame.controlHost.add(new VueChoixJeu(livres));
		AppFrame.appFrame.controlHost.revalidate();
		AppFrame.appFrame.controlHost.repaint();
	}
}
