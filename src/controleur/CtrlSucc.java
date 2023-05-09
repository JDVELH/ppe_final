package controleur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import modele.Livre;
import modele.Page;
import tool.DAOacces;
import vue.AppFrame;

public class CtrlSucc {
	ArrayList<Page> pages;
	CtrlPage ctrlPage;
	CtrlLivre ctrlBook;

	public CtrlSucc() {
		pages = new ArrayList<Page>();
		ctrlPage = new CtrlPage();
		ctrlBook = new CtrlLivre();
		suppDoublon();
	}

	/*
	 * AJOUT DES SUCCESSEURS DANS LA BDD
	 */
	public void addSucc(Page page, Page pageSucc, Livre livre) {
		page.getPageSuccesseurs().clear();
		DAOacces access = new DAOacces();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		String sql1 = "INSERT INTO successeur (nomPage, nomPageSuccesseur, titreLivre, auteur)"
				+ " VALUES (?, ?, ?, ?);";
		String sql2 = "SELECT nomPageSuccesseur FROM successeur WHERE nomPage = ? AND auteur = ?;";

		try {
			connection = access.getConnection();
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.setString(1, page.getNomPage());
			preparedStatement.setString(2, pageSucc.getNomPage());
			preparedStatement.setString(3, livre.getTitreLivre());
			preparedStatement.setString(4, AppFrame.appFrame.user.getPseudo());
			preparedStatement.executeUpdate();

			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setString(1, page.getNomPage());
			preparedStatement.setString(2, AppFrame.appFrame.user.getPseudo());
			resultat = preparedStatement.executeQuery();
			while (resultat.next()) {
				page.getPageSuccesseurs()
						.add(new Page(resultat.getString("nomPageSuccesseur"),
								"", "", page.getTitreLivre(),
								page.getUtilisateur()));
			}
		} catch (Exception e1) {
			System.out.println(e1);
		}
		ctrlPage.editPage(page, livre);
	}

	/*
	 * SUPPRESSION DES SUCCESSEURS DANS LA BDD
	 */
	public void suppSucc(Page page, Page pageSucc, Livre livre) {
		page.getPageSuccesseurs().clear();
		DAOacces access = new DAOacces();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		String sql = " DELETE FROM successeur WHERE nomPage = ? "
				+ "AND nomPageSuccesseur = ?" + " AND titreLivre = ?"
				+ " AND auteur = ?;";
		String sql2 = "SELECT nomPageSuccesseur FROM successeur WHERE nomPage = ?"
				+ " AND titreLivre = ? AND auteur = ?;";

		try {
			connection = access.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, page.getNomPage());
			preparedStatement.setString(2, pageSucc.getNomPage());
			preparedStatement.setString(3, livre.getTitreLivre());
			preparedStatement.setString(4, AppFrame.appFrame.user.getPseudo());
			preparedStatement.executeUpdate();

			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setString(1, page.getNomPage());
			preparedStatement.setString(2, livre.getTitreLivre());
			preparedStatement.setString(3, AppFrame.appFrame.user.getPseudo());
			resultat = preparedStatement.executeQuery();
			while (resultat.next()) {
				page.getPageSuccesseurs()
						.add(new Page(resultat.getString("nomPageSuccesseur"),
								"", "", page.getTitreLivre(),
								page.getUtilisateur()));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		ctrlPage.editPage(page, livre);
	}

	/*
	 * METHODE POUR SUPPRIMER LES DOUBLONS DANS LA BDD
	 */
	public void suppDoublon() {
		DAOacces access = new DAOacces();
		Connection connection = null;
		Statement statement = null;
		String sqlSuppDoublon = "DELETE s1"
				+ " FROM successeur s1, successeur s2"
				+ " WHERE s1.idPage > s2.idPage"
				+ " AND s1.nomPageSuccesseur = s2.nomPageSuccesseur"
				+ " AND s1.nomPage = s2.nomPage;";

		try {
			connection = access.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(sqlSuppDoublon);

		} catch (Exception e2) {
			System.out.println(e2);
		}
		access.close();
	}
}
