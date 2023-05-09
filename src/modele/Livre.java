package modele;

import java.util.ArrayList;

public class Livre {
	private String titreLivre;
	public ArrayList<Page> pages;
	private Utilisateur utilisateur;
	public Livre(String titreLivre, ArrayList<Page> pages,
			Utilisateur utilisateur) {
		this.titreLivre = titreLivre;
		this.pages = pages;
		this.utilisateur = utilisateur;
	}

	public ArrayList<Page> getPages() {
		return pages;
	}

	public void setPages(ArrayList<Page> pages) {
		this.pages = pages;
	}

	public String getTitreLivre() {
		return titreLivre;
	}

	public void setTitreLivre(String titreLivre) {
		this.titreLivre = titreLivre;
	}

	/**
	 * @return the user
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUtilisateur(Utilisateur user) {
		this.utilisateur = user;
	}

	public void _afficherLivre(Page r) {
		System.out.print(r.getNomPage() + "->");
		int j = r.getPageSuccesseurs().size();

		if (r.getPageSuccesseurs().size() == 0) {

			System.out.print("Fin");
		}
		for (int i = 0; i < j; i++) {

			System.out.print(r.getPageSuccesseurs().get(i).getNomPage());
		}
		System.out.print("\n");
		for (int i = 0; i < j; i++) {

			_afficherLivre(r.getPageSuccesseurs().get(i));
		}
	}

	/*
	 * METHODE RECURSIVE POUR AFFICHER LE LIVRE
	 */
	public String afficherLivre(Page root, String s,
			ArrayList<Page> pagesParcourues) {

		int j = root.getPageSuccesseurs().size();
		pagesParcourues.add(root);
		s += root.getNomPage() + " : ";

		if (j == 0) {

			s += "FIN!";
		}
		for (int i = 0; i < j; i++) {
			s += root.getPageSuccesseurs().get(i).getNomPage();
		}
		s += "\n/";
		for (int k = 0; k < j; k++) {
			boolean flag = false;
			for (int l = 0; l < pagesParcourues.size(); l++) {

				if (root.getPageSuccesseurs().get(k)
						.equals(pagesParcourues.get(l))) {
					flag = true;;
				}
			}

			if (flag == false) {
				s = afficherLivre(root.getPageSuccesseurs().get(k), s,
						pagesParcourues);
			}
		}
		return s;
	}

	/**
	 *
	 * @param page
	 */
	public void ajouterPage(Page page) {
		pages.add(page);
	}

	/**
	 *
	 * @param page
	 */
	void supprimerPage(Page page) {
		pages.remove(page);
	}

	@Override
	public String toString() {
		return titreLivre;
	}

}
