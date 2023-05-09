package controleur;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modele.Livre;
import modele.Page;
import modele.Utilisateur;
import tool.DAOacces;
import vue.AppFrame;
import vue.PanelImg;
import vue.VueAccueil;
import vue.VueAjouterPage;
import vue.VueEditeur;
import vue.VuePage;
/*
 * CONTROLEUR QUI GERE LES PAGES
 */
public class CtrlPage {
	/*
	 * Constructeur
	 */
	public CtrlPage() {
	}
	/*
	 * METHODE POR AJOUTER UN LIVRE DANS LA BASE DE DONNEES
	 */
	public void ajouterPage(Livre livre, Page page) {
		DAOacces access = new DAOacces();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Statement statement = null;
		ResultSet resultat = null;
		livre.pages = new ArrayList<Page>();
		String sql = "INSERT INTO page (nomPage, titreLivre, auteur) VALUES (?, ?, ?);";
		String sql2 = "SELECT * FROM page"
				+ " WHERE titreLivre = ?  AND auteur = ?;";

		try {
			connection = access.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, page.getNomPage());
			preparedStatement.setString(2, livre.getTitreLivre());
			preparedStatement.setString(3, AppFrame.appFrame.user.getPseudo());
			preparedStatement.executeUpdate();

			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setString(1, livre.getTitreLivre());
			preparedStatement.setString(2, AppFrame.appFrame.user.getPseudo());
			resultat = preparedStatement.executeQuery();
			while (resultat.next()) {
				livre.pages.add(new Page(resultat.getString("nomPage"),
						resultat.getString("imgFond"),
						resultat.getString("contenu"),
						resultat.getString("titreLivre"),
						new Utilisateur(resultat.getString("auteur"), "", "")));
			}
			for (int i = 0; i < livre.pages.size(); i++) {
				String sqlSuccesseur = "SELECT nomPageSuccesseur"
						+ " FROM successeur" + " WHERE nomPage = '"
						+ livre.pages.get(i).getNomPage()
						+ "' AND titreLivre = '" + livre.getTitreLivre()
						+ "' AND auteur ='" + AppFrame.appFrame.user.getPseudo()
						+ "';";
				try {
					statement = connection.createStatement();

					ResultSet resultat2 = statement.executeQuery(sqlSuccesseur);

					while (resultat2.next()) {
						livre.pages.get(i).getPageSuccesseurs()
								.add(new Page(
										resultat2
												.getString("nomPageSuccesseur"),
										"", "", "", AppFrame.appFrame.user));
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		access.close();
		AppFrame.appFrame.controlHost.removeAll();
		/*
		 * NOUVELLE INSTANCE DE LA VUE D EDITION DU LIVRE
		 */
		AppFrame.appFrame.controlHost.add(new VueEditeur(livre),
				BorderLayout.WEST);
		AppFrame.appFrame.controlHost.add(new PanelImg(), BorderLayout.CENTER);
		AppFrame.appFrame.controlHost.revalidate();
		AppFrame.appFrame.controlHost.repaint();
	}

	public void editPage(Page page, Livre livre) {
		AppFrame.appFrame.controlHost.remove(1);
		/*
		 * NOUVELLE INSTANCE DE LA VUE D EDITION DE LA PAGE
		 */
		AppFrame.appFrame.controlHost.add(new VuePage(page, livre),
				BorderLayout.CENTER);
		AppFrame.appFrame.controlHost.revalidate();
		AppFrame.appFrame.controlHost.repaint();
	}
	/*
	 * METHODE POUR SUPPRIMER UNE PAGE
	 */
	public void suppPage(Page page) {
		DAOacces access = new DAOacces();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "DELETE  FROM page WHERE nomPage = ?;";
		String sql2 = "DELETE  FROM successeur WHERE nomPage = ? OR nomPageSuccesseur = ?;";
		try {
			connection = access.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, page.getNomPage());
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setString(1, page.getNomPage());
			preparedStatement.setString(2, page.getNomPage());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		access.close();
	}
	/*
	 * METHODE POUR METTRE A JOUR UNE PAGE DANS LA BASE DE DONNEES
	 *
	 */
	public void updateContent(Page page, Livre livre) {
		DAOacces access = new DAOacces();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "UPDATE page SET nomPage = ?, imgFond = ?, contenu = ?"
				+ " WHERE nomPage = ?;";
		try {
			connection = access.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, page.getNomPage());
			preparedStatement.setString(2, page.getImgFond());
			preparedStatement.setString(3, page.getContenu());
			preparedStatement.setString(4, page.getNomPage());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		access.close();
		AppFrame.appFrame.controlHost.remove(1);
		AppFrame.appFrame.controlHost.add(new VuePage(page, livre),
				BorderLayout.CENTER);
		AppFrame.appFrame.controlHost.revalidate();
		AppFrame.appFrame.controlHost.repaint();
	}
	/*
	 * RETOUR PAGE ACCEUIL
	 */
	public void homePage() {
		AppFrame.appFrame.setJMenuBar(null);
		AppFrame.appFrame.controlHost.removeAll();
		AppFrame.appFrame.controlHost.add(new VueAccueil());
		AppFrame.appFrame.controlHost.revalidate();
		AppFrame.appFrame.controlHost.repaint();
	}
	/*
	 * INSTANCE DE LA FENETRE DE NOUVELLE PAGE
	 */
	public void viewAddPage(Livre livre) {
		new VueAjouterPage(livre);
	}
}
