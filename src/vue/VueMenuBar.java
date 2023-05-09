package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controleur.CtrlChoix;
/*
 * Cette classe affiche une JMenuBar
 */
public class VueMenuBar extends JMenuBar implements ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private CtrlChoix ctrlChoix;
	private JMenu action;
	private JMenu aide;
	private JMenuItem itemConnexion;
	private JMenuItem itemCreer;
	private JMenuItem itemJouer;

	public VueMenuBar() {
		ctrlChoix = new CtrlChoix();

		action = new JMenu("Action");
		aide = new JMenu("Aide");
		itemConnexion = new JMenuItem("Déconnexion");
		itemConnexion.addActionListener(this);
		itemCreer = new JMenuItem("Créer");
		itemCreer.addActionListener(this);
		itemJouer = new JMenuItem("Jouer");
		itemJouer.addActionListener(this);

		action.add(itemConnexion);
		action.add(itemCreer);
		action.add(itemJouer);

		this.add(action);
		this.add(aide);
	}
	/*
	 * Actions de la barre de menu
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Connexion
		if (e.getSource() == itemConnexion) {
			AppFrame.appFrame.controlHost.removeAll();
			AppFrame.appFrame.setJMenuBar(null);
			AppFrame.appFrame.controlHost.add(new VueAccueil());
			AppFrame.appFrame.controlHost.revalidate();
			AppFrame.appFrame.controlHost.repaint();
		}
		// CREER
		if (e.getSource() == itemCreer) {
			ctrlChoix.creerLivre();
		}
		// JOUER
		if (e.getSource() == itemJouer) {
			ctrlChoix.jouerLivre();
		}
	}
}