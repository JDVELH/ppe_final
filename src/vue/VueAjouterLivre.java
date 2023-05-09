package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import controleur.CtrlLivre;
/*
 * Cette classe affiche une JFrame qui permet d'ajouter un livre
 */
public class VueAjouterLivre extends JFrame {
	/*
	 *
	 */
	private static final long serialVersionUID = 1L;

	public VueAjouterLivre() {
		CtrlLivre ctrlLivre = new CtrlLivre();
		JLabel label = new JLabel();
		JPanel panel = new JPanel();
		panel.setBorder(getCoolBorder("Titre du livre"));
		panel.setLayout(new GridLayout(3, 1));
		JButton button = new JButton();
		button.setText("Valider");
		JTextField textField = new JTextField(15);
		panel.add(label);
		panel.add(textField);
		panel.add(button);
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				AppFrame.appFrame.setEnabled(true);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
		/*
		 * Méthode qui permet de valider l'ajout du livre en appuyant sur entrée
		 */
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ctrlLivre.ajouterLivreBdd(textField.getText());
					AppFrame.appFrame.setEnabled(true);
					dispose();
				}
			}
		});
		this.add(panel);
		this.setAlwaysOnTop(true);
		this.pack();
		this.setVisible(true);
		/*
		 * Action listener pour valider l'ajout
		 */
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!(textField.getText().isEmpty())) {
					ctrlLivre.ajouterLivreBdd(textField.getText());
					AppFrame.appFrame.setEnabled(true);
					dispose();
				}
			}
		});
	}
	/*
	 * Méthode pour ajouter une bordure
	 */
	private Border getCoolBorder(String titre) {
		Border lowBorder = BorderFactory.createBevelBorder(35);
		Border titleBorder = BorderFactory.createTitledBorder(lowBorder, titre,
				TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Arial", Font.BOLD, 18), Color.black);
		return titleBorder;
	}
}
