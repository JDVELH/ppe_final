package controleur;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modele.Livre;
import modele.Page;
import tool.DAOacces;
import vue.AppFrame;
import vue.PanelImg;
import vue.VueAjouterLivre;
import vue.VueEditeur;
import vue.VueJeu;
import vue.VueLivre;

public class CtrlLivre {
	CtrlChoix ctrlChoice;
	Livre livre;
	ArrayList<Page> pages;

	public CtrlLivre() {
		ctrlChoice = new CtrlChoix();
	}

	/*
	 * METHODE POUR AJOUTER UN LIVRE
	 */
	public void ajouterLivreBdd(String titreLivre) {
		DAOacces access = new DAOacces();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		AppFrame.livres.clear();
		String sql1 = "INSERT INTO livre (titreLivre, auteur) VALUES (?, ?);";
		String sql2 = "SELECT * from livre WHERE auteur = ?;";

		try {
			connection = access.getConnection();
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.setString(1, titreLivre);
			preparedStatement.setString(2, AppFrame.appFrame.user.getPseudo());
			preparedStatement.executeUpdate();

			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setString(1, AppFrame.appFrame.user.getPseudo());
			resultat = preparedStatement.executeQuery();
			while (resultat.next()) {
				AppFrame.livres.add(new Livre(resultat.getString("titreLivre"),
						pages, AppFrame.appFrame.user));
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		AppFrame.appFrame.controlHost.removeAll();
		AppFrame.appFrame.controlHost.add(new VueLivre(AppFrame.livres));
		AppFrame.appFrame.controlHost.revalidate();
		AppFrame.appFrame.controlHost.repaint();
	}

	/*
	 * METHODE POUR EDITER UN LIVRE
	 */
	public void editerLivre(Livre livre) {
		DAOacces access = new DAOacces();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Statement statement = null;
		ResultSet resultat = null;
		livre.pages = new ArrayList<Page>();
		String sql = "SELECT * FROM page"
				+ " WHERE titreLivre = ? AND auteur = ?;";

		try {
			connection = access.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, livre.getTitreLivre());
			preparedStatement.setString(2, AppFrame.appFrame.user.getPseudo());
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				livre.pages.add(new Page(resultat.getString("nomPage"),
						resultat.getString("imgFond"),
						resultat.getString("contenu"), livre.getTitreLivre(),
						AppFrame.appFrame.user));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		for (int i = 0; i < livre.pages.size(); i++) {

			String sql2 = "SELECT nomPageSuccesseur" + " FROM successeur"
					+ " WHERE nomPage = '" + livre.pages.get(i).getNomPage()
					+ "' AND titreLivre = '" + livre.getTitreLivre()
					+ "' AND auteur = '" + AppFrame.appFrame.user.getPseudo()
					+ "';";
			try {
				statement = connection.createStatement();
				ResultSet resultat2 = statement.executeQuery(sql2);

				while (resultat2.next()) {
					livre.pages.get(i).getPageSuccesseurs().add(new Page(
							resultat2.getString("nomPageSuccesseur"), "", "",
							livre.getTitreLivre(), AppFrame.appFrame.user));
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			access.close();
		}
		AppFrame.appFrame.controlHost.removeAll();
		/*
		 * INSTANCE DE LA VUE EDITEUR
		 */
		AppFrame.appFrame.controlHost.add(new VueEditeur(livre),
				BorderLayout.WEST);
		AppFrame.appFrame.controlHost.add(new PanelImg(), BorderLayout.CENTER);
		AppFrame.appFrame.controlHost.revalidate();
		AppFrame.appFrame.controlHost.repaint();
	}

	public void nouveauLivre() {
		new VueAjouterLivre();
	}

	/*
	 * METHODE POUR SUPPRIMER UN LIVRE
	 */
	public void supprimerLivre(Livre livre) {
		DAOacces access = new DAOacces();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql1 = "DELETE FROM livre WHERE titreLivre = ?;";
		String sql2 = "DELETE FROM page WHERE titreLivre = ?;";
		String sql3 = "DELETE FROM successeur WHERE titreLivre = ?;";

		try {
			connection = access.getConnection();
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.setString(1, livre.getTitreLivre());
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setString(1, livre.getTitreLivre());
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(sql3);
			preparedStatement.setString(1, livre.getTitreLivre());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		access.close();
	}

	public void jouerLivre(Livre livre) {
		DAOacces access = new DAOacces();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Statement statement = null;
		ResultSet resultat = null;
		livre.pages = new ArrayList<Page>();
		String sql = "SELECT * FROM page WHERE titreLivre = ?;";

		try {
			connection = access.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, livre.getTitreLivre());
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				livre.pages.add(new Page(resultat.getString("nomPage"),
						resultat.getString("imgFond"),
						resultat.getString("contenu"), livre.getTitreLivre(),
						AppFrame.appFrame.user));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		for (int i = 0; i < livre.pages.size(); i++) {
			String sqlSuccesseur = "SELECT nomPageSuccesseur"
					+ " FROM successeur" + " WHERE nomPage = '"
					+ livre.pages.get(i).getNomPage() + "' AND titreLivre = '"
					+ livre.getTitreLivre() + "';";
			try {
				statement = connection.createStatement();
				ResultSet resultat2 = statement.executeQuery(sqlSuccesseur);

				while (resultat2.next()) {
					livre.pages.get(i).getPageSuccesseurs().add(new Page(
							resultat2.getString("nomPageSuccesseur"), "", "",
							livre.getTitreLivre(), AppFrame.appFrame.user));
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			access.close();
		}

		if (livre.pages.size() > 0) {
			new VueJeu(livre.pages, livre.pages.get(0));
		}
	}
}
