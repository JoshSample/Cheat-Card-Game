package cheatcardgame;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class PlayGamePanel extends JPanel {
	public PlayGamePanel() {
		setBackground(new Color(0, 100, 0));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(105, 105, 105));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lblTitle = new JLabel("Cheat!");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
		panel.add(lblTitle);
		
		JLabel lblInstructions = new JLabel("Instructions:");
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblInstructions);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 100, 0));
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(139, 0, 0));
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(139, 0, 0));
		panel_2.add(panel_4);
		
		JButton button = new JButton("<--");
		panel_4.add(button);
		button.setActionCommand("Back");
		
		JButton btnSelect = new JButton("Select");
		panel_4.add(btnSelect);
		btnSelect.setActionCommand("Select");
		
		JButton button_1 = new JButton("-->");
		panel_4.add(button_1);
		button_1.setActionCommand("Forward");
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setActionCommand("Play");
		panel_2.add(btnPlay);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(139, 0, 0));
		panel_1.add(panel_3);
		
		JButton btnDeselectAll = new JButton("Deselect All");
		btnDeselectAll.setActionCommand("Deselect");
		panel_3.add(btnDeselectAll);
		
		JButton btnCheat = new JButton("Cheat!");
		btnCheat.setActionCommand("Cheat");
		panel_3.add(btnCheat);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 100, 0));
		add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}

}
