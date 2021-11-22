package gui;

import javax.swing.JFrame;

public class McDonaldFrame extends JFrame{
	public McDonaldFrame() {
		super("Welcome to McDonald");
		setLayout(null);
		setBounds(50, 0, 500, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(McDonaldPanel.getInstance());
		
		setVisible(true);
		revalidate();
	}
}
