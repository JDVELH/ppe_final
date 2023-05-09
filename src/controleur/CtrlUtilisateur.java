package controleur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import modele.Utilisateur;
import tool.DAOacces;
import vue.AppFrame;
import vue.ViewAcc;
import vue.VueInscription;
/*
 * Cette classe controle la connexion et l'enregistrement de l'utilisateur
 */
public class CtrlUtilisateur {
	private CtrlPage ctrlPage;
	/*
	 * Controleur
	 */
	public CtrlUtilisateur() {
		ctrlPage = new CtrlPage();
	}

	/*
	 * METHODE POUR VERIFIER SI LES IDENTIFIANTS SONT CORRECTS
	 */
	public void connect(String pseudo, String mdp) {
		DAOacces access = new DAOacces();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		String sql = "SELECT * FROM utilisateur WHERE pseudo = ?;";

		try {
			connection = access.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, pseudo);
			resultat = preparedStatement.executeQuery();
			boolean flag = false;

			while (resultat.next()) {

				if (resultat.getString("password").equals(mdp)) {
					/*
					 * AFFECTATION DU USER STATIC DE APPFRAME
					 */
					AppFrame.appFrame.user = new Utilisateur(pseudo, mdp,
							resultat.getString("email"));
					flag = true;
					AppFrame.appFrame.controlHost.removeAll();
					AppFrame.appFrame.add(new ViewAcc());
					AppFrame.appFrame.revalidate();
					AppFrame.appFrame.repaint();
				}
			}
			/*
			 * OUVERTURE D UNE FENETRE DE DIALOGUE SI LES IDENTIFIANTS SONT
			 * INCORRECTS
			 */
			if (flag == false) {
				JOptionPane.showMessageDialog(AppFrame.appFrame,
						"Vos identifiants sont incorrects",
						"Identifiants incorrects", JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		access.close();
	}
	/*
	 * METHODE POUR ENREGISTRER DANS LA BDD UN NOUVEL UTILISATEUR
	 */
	public void register(String pseudo, String email, String password) {
		DAOacces access = new DAOacces();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		String sql1 = "SELECT * FROM utilisateur;";
		String sql2 = "INSERT INTO utilisateur (pseudo, email, password)"
				+ " VALUES (?, ?, ?);";
		try {
			connection = access.getConnection();
			preparedStatement = connection.prepareStatement(sql1);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				/*
				 * SI LE PSEUDO EXISTE DEJA UNE FENETRE DE DIALOGUE S OUVRE
				 */
				if (resultat.getString("pseudo").equals(pseudo)) {
					JOptionPane.showMessageDialog(AppFrame.appFrame,
							"Ce pseudo existe déjà.", "Pseudo existant",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setString(1, pseudo);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, password);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		access.close();
	}
	/*
	 * Méthode
	 */
	public void registerView() {
		new VueInscription();
	}
	/*
	 * METHODE POUR SUPPRIMER UN UTILISATEUR
	 */
	public void supprimerUtilisateur(String pseudo) {
		int dialogResult = JOptionPane.showConfirmDialog(AppFrame.appFrame,
				"Etes vous sûr de vouloir supprimer votre compte ?",
				"Confirmation de suppression du compte",
				JOptionPane.YES_NO_OPTION);

		if (dialogResult == JOptionPane.YES_OPTION) {
			DAOacces access = new DAOacces();
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String sql = "DELETE FROM utilisateur WHERE pseudo = ?;";
			ctrlPage.homePage();

			try {
				connection = access.getConnection();
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, pseudo);
				preparedStatement.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			access.close();
		}
	}
}