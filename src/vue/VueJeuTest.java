package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modele.Page;

public class VueJeuTest extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelNom;
	private JLabel labelImage;
	private JTextArea textArea;
	private JScrollPane scrollPane;;
	private JPanel panelNorth;
	private JPanel panelCenter;
	private JPanel panelSouth;
	private JPanel panelWest;
	private JPanel panelEast;
	private Page pageClicked;
	private ImageIcon icon;

	public VueJeuTest(ArrayList<Page> pages, Page page) {
		icon = new ImageIcon(page.getImgFond());
		this.setLayout(new BorderLayout());
		panelCenter = new JPanel();
		panelCenter.setLayout(new GridLayout(2, 1));
		panelNorth = new JPanel();
		panelSouth = new JPanel();
		panelWest = new JPanel();
		panelEast = new JPanel();
		labelNom = new JLabel();
		labelImage = new JLabel(icon);
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText(page.getContenu());
		scrollPane = new JScrollPane(textArea);
		textArea.setForeground(Color.GREEN);
		textArea.setBackground(Color.BLACK);
		labelNom.setText(page.getNomPage());
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelNorth, BorderLayout.NORTH);
		this.add(panelEast, BorderLayout.EAST);
		this.add(panelWest, BorderLayout.WEST);
		panelNorth.add(labelNom);
		panelCenter.add(labelImage);
		panelCenter.add(scrollPane);
		/*
		 * Code assez pénible qui permet de connaître
		 */
		for (int i = 0; i < page.getPageSuccesseurs().size(); i++) {
			JButton button = new JButton(
					page.getPageSuccesseurs().get(i).getNomPage());
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
					AppFrame.appFrame.controlHost.remove(1);
					AppFrame.appFrame.controlHost.add(
							new VueJeuTest(pages, pageClicked),
							BorderLayout.CENTER);
					AppFrame.appFrame.controlHost.revalidate();
					AppFrame.appFrame.controlHost.repaint();
				}
			});
			panelSouth.add(button);
			this.add(panelSouth, BorderLayout.SOUTH);
		}
	}
}