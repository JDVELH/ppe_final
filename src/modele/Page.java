package modele;

import java.util.ArrayList;

public class Page {
    private String nomPage;
    private String descriptionJoueur;
    private String descriptionCreateur;
    private ArrayList<Page> pages;
    private ArrayList<Page> pagePredecesseurs;
    private ArrayList<Page> pageSuccesseurs;
    private String imgFond;
    private int numeroPage;
    private String contenu = "";
    private String titreLivre;
    private Utilisateur utilisateur;

    public Page(String nom, String img, String contenu, String titreLivre, Utilisateur utilisateur) {
	this.imgFond = img;
	this.pages = new ArrayList<Page>();
	this.pagePredecesseurs = new ArrayList<Page>();
	this.pageSuccesseurs = new ArrayList<Page>();
	this.nomPage = nom;
	this.contenu = contenu;
	this.titreLivre = titreLivre;
	this.utilisateur = utilisateur;
    }

    /**
     * @return the nomPage
     */
    public String getNomPage() {
	return nomPage;
    }

    /**
     * @param nomPage the nomPage to set
     */
    public void setNomPage(String nomPage) {
	this.nomPage = nomPage;
    }

    /**
     * @return the descriptionJoueur
     */
    public String getDescriptionJoueur() {
	return descriptionJoueur;
    }

    /**
     * @param descriptionJoueur the descriptionJoueur to set
     */
    public void setDescriptionJoueur(String descriptionJoueur) {
	this.descriptionJoueur = descriptionJoueur;
    }

    /**
     * @return the descriptionCreateur
     */
    public String getDescriptionCreateur() {
	return descriptionCreateur;
    }

    /**
     * @param descriptionCreateur the descriptionCreateur to set
     */
    public void setDescriptionCreateur(String descriptionCreateur) {
	this.descriptionCreateur = descriptionCreateur;
    }

    /**
     * @return the pagePredecesseurs
     */
    public ArrayList<Page> getPagePredecesseurs() {
	return pagePredecesseurs;
    }

    /**
     * @return the pageSuccesseurs
     */
    public ArrayList<Page> getPageSuccesseurs() {
	return pageSuccesseurs;
    }

    /**
     * @param pageSuccesseurs the pageSuccesseurs to set
     */
    public void setPageSuccesseurs(ArrayList<Page> pageSuccesseurs) {
	this.pageSuccesseurs = pageSuccesseurs;
    }

    /**
     * @return the imgFond
     */
    public String getImgFond() {
	return imgFond;
    }

    /**
     * @param imgFond the imgFond to set
     */
    public void setImgFond(String imgFond) {
	this.imgFond = imgFond;
    }

    /**
     * @return the numeroPage
     */
    public int getNumeroPage() {
	return numeroPage;
    }

    /**
     * @param numeroPage the numeroPage to set
     */
    public void setNumeroPage(int numeroPage) {
	this.numeroPage = numeroPage;
    }

    /**
     * @return the contenu
     */
    public String getContenu() {
	return contenu;
    }

    /**
     * @param contenu the contenu to set
     */
    public void setContenu(String contenu) {
	this.contenu = contenu;
    }

    /**
     * @return the titreLivre
     */
    public String getTitreLivre() {
	return titreLivre;
    }

    /**
     * @param titreLivre the titreLivre to set
     */
    public void setTitreLivre(String titreLivre) {
	this.titreLivre = titreLivre;
    }

    /**
     * @return the pages
     */
    public ArrayList<Page> getPages() {
	return pages;
    }

    /**
     * @param pages the pages to set
     */
    public void setPages(ArrayList<Page> pages) {
	this.pages = pages;
    }

    /**
     * @return the utilisateur
     */
    public Utilisateur getUtilisateur() {
	return utilisateur;
    }

    /**
     * @param utilisateur the utilisateur to set
     */
    public void setUtilisateur(Utilisateur utilisateur) {
	this.utilisateur = utilisateur;
    }

    public void ajoutPredecesseurs(Page p) {
	this.getPagePredecesseurs().add(p);
	p.getPageSuccesseurs().add(this);
    }

    public void ajoutSuccesseurs(Page p) {

	if (!pageSuccesseurs.contains(p)) {
	    this.getPageSuccesseurs().add(p);
	    p.getPagePredecesseurs().add(this);
	}
    }

    public void supprimerSuccesseurs(Page p) {
	this.getPageSuccesseurs().remove(p);
	p.getPagePredecesseurs().remove(this);
    }

    @Override
    public String toString() {
	return nomPage;
    }
}
