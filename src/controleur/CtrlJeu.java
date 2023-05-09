package controleur;

import java.awt.BorderLayout;
import java.util.ArrayList;

import modele.Page;
import vue.AppFrame;
import vue.VueJeu;
import vue.VueJeuTest;

public class CtrlJeu {

    public CtrlJeu() {
    }

    public void jouerLivreTest(ArrayList<Page> pages) {
	AppFrame.appFrame.controlHost.remove(1);
	AppFrame.appFrame.controlHost.add(new VueJeuTest(pages, pages.get(0)), BorderLayout.CENTER);
	AppFrame.appFrame.controlHost.revalidate();
	AppFrame.appFrame.controlHost.repaint();
    }

    public void jouerLivre(ArrayList<Page> pages) {
	AppFrame.appFrame.controlHost.removeAll();
	AppFrame.appFrame.controlHost.add(new VueJeu(pages, pages.get(0)));
	AppFrame.appFrame.controlHost.revalidate();
	AppFrame.appFrame.controlHost.repaint();
    }
}
