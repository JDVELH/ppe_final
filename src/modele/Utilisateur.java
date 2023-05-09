package modele;

import java.util.ArrayList;

public class Utilisateur {
    ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
    private String pseudo;
    private String mdp;
    private String email;

    public Utilisateur(String pseudo, String mdp, String email) {
	this.pseudo = pseudo;
	this.mdp = mdp;
	this.email = email;
    }

    public String getPseudo() {
	return pseudo;
    }

    public void setPseudo(String id) {
	this.pseudo = id;
    }

    public String getMdp() {
	return mdp;
    }

    public void setMdp(String mdp) {
	this.mdp = mdp;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

}
