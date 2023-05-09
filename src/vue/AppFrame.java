package vue;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import modele.Livre;
import modele.Utilisateur;

public class AppFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public static ArrayList<Livre> livres = new ArrayList<Livre>();
	public static AppFrame appFrame;
	static public Dimension dimension;
	public Container controlHost;
	public Utilisateur user;

	public AppFrame() {
		super("");
		controlHost = getContentPane();
		this.setTitle("JDVELH");
		dimension = getToolkit().getScreenSize();
		this.setSize(dimension);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(false);
		this.setFocusable(true);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		// try {
		// UIManager.setLookAndFeel(new NimbusLookAndFeel());
		// } catch (UnsupportedLookAndFeelException e) {
		// e.printStackTrace();
		// }
		appFrame = new AppFrame();
		appFrame.controlHost.add(new VueAccueil());
		appFrame.setVisible(true);
	}
}
