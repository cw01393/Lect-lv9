package game;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Result extends JFrame{
	JLabel text = new JLabel("GAME CLEAR!");
	
	public Result() {
		setLayout(null);
		setBounds(200, 200, 300, 200);
		this.text.setBounds(0, 0, 300, 200);
		this.text.setHorizontalAlignment(JLabel.CENTER);
		this.text.setFont(new Font("",Font.BOLD,20));
		add(this.text);
		setVisible(true);
		revalidate();
	}
}
