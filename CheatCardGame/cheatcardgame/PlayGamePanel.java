package cheatcardgame;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.SwingConstants;

public class PlayGamePanel extends JPanel {
	private JLabel lblInstructions;
	private JLabel playerCount;
	private JLabel opponentCount;
	private JLabel currentCard;
	
	public PlayGamePanel(PlayGameControl pgc) {
		setBackground(new Color(0, 100, 0));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(105, 105, 105));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel lblTitle = new JLabel("Cheat!");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		panel.add(lblTitle);
		
		lblInstructions = new JLabel("Instructions:");
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblInstructions);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(105, 105, 105));
		panel.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel lblPlayerCards = new JLabel("Player Cards: ");
		lblPlayerCards.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(lblPlayerCards);
		
		playerCount = new JLabel("0");
		panel_4.add(playerCount);
		
		JLabel lblOpponentCards = new JLabel("Opponent Cards: ");
		lblOpponentCards.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(lblOpponentCards);
		
		opponentCount = new JLabel("0");
		panel_4.add(opponentCount);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 100, 0));
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(139, 0, 0));
		panel_1.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton button = new JButton("<--");
		panel_2.add(button);
		button.setActionCommand("Back");
		button.addActionListener(pgc);
		
		JButton btnSelect = new JButton("Select");
		panel_2.add(btnSelect);
		btnSelect.setActionCommand("Select");
		btnSelect.addActionListener(pgc);
		
		JButton button_1 = new JButton("-->");
		panel_2.add(button_1);
		button_1.setActionCommand("Forward");
		button_1.addActionListener(pgc);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(139, 0, 0));
		panel_1.add(panel_3);
		
		JButton btnCheat = new JButton("Cheat!");
		btnCheat.setActionCommand("Cheat");
		btnCheat.addActionListener(pgc);
		panel_3.add(btnCheat);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 100, 0));
		add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		//scaling image down to workable sizing 
		ImageIcon imageIcon = new ImageIcon("/CheatCardGame/cards/blue_back.png");
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(0, 100, 0));
		panel_5.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));
		currentCard = new JLabel("", imageIcon, SwingConstants.CENTER);
		panel_6.add(currentCard);
		
	}
	
	public void setCurrentCard(String card) {
		ImageIcon imageIcon = new ImageIcon("CheatCardGame/cards/" + card);
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		imageIcon.setImage(newimg);
	}
	public void setCardCounts(int player, int opponent) {
		playerCount.setText(Integer.toString(player));
		opponentCount.setText(Integer.toString(opponent));
	}
	public void setInstructions(String instructions) {
		lblInstructions.setText(instructions);
	}
	
	
	

}
