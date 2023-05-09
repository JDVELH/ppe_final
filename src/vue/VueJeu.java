package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modele.Page;
/*
 * Cette classe affiche une JFrame
 */
public class VueJeu extends JFrame implements ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JLabel labelNom;
	private JLabel labelImage;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JButton btnQuitter;
	private JPanel panelNorth;
	private JPanel panelCenter;
	private JPanel panelSouth;
	private JPanel panelWest;
	private JPanel panelEast;
	private Page pageClicked;
	private ImageIcon icon;

	public VueJeu(ArrayList<Page> pages, Page page) {
		this.setUndecorated(true);
		this.setLayout(new BorderLayout());
		this.setBackground(Color.black);
		panelCenter = new JPanel();
		panelCenter.setOpaque(false);
		panelCenter.setLayout(new GridLayout(2, 1));
		panelNorth = new JPanel();
		panelSouth = new JPanel();
		panelWest = new JPanel();
		panelEast = new JPanel();
		icon = new ImageIcon(page.getImgFond());
		labelNom = new JLabel();
		labelImage = new JLabel(icon);
		labelNom.setText(page.getNomPage());
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText(page.getContenu());
		scrollPane = new JScrollPane(textArea);
		textArea.setForeground(Color.GREEN);
		textArea.setBackground(Color.BLACK);
		btnQuitter = new JButton();
		btnQuitter.setText("Quitter");
		btnQuitter.addActionListener(this);
		this.getContentPane().add(panelCenter, BorderLayout.CENTER);
		this.getContentPane().add(panelNorth, BorderLayout.NORTH);
		this.getContentPane().add(panelEast, BorderLayout.EAST);
		this.getContentPane().add(panelWest, BorderLayout.WEST);
		panelNorth.add(labelNom);
		panelNorth.add(btnQuitter);
		panelCenter.add(labelImage);
		panelCenter.add(scrollPane);

		for (int i = 0; i < page.getPageSuccesseurs().size(); i++) {
			JButton button = new JButton(
					page.getPageSuccesseurs().get(i).getNomPage());
			panelSouth.add(button);
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					pageClicked = new Page("", "", "", "",
							AppFrame.appFrame.user);

					for (int i = 0; i < pages.size(); i++) {

						if (pages.get(i).getNomPage()
								.equals(button.getText())) {
							pageClicked = pages.get(i);
							break;
						}
					}
					new VueJeu(pages, pageClicked);
					dispose();
				}
			});
		}
		this.getContentPane().add(panelSouth, BorderLayout.SOUTH);
		this.setSize(AppFrame.dimension);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(false);
		this.setFocusable(true);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnQuitter) {
			dispose();
		}
	}
}
