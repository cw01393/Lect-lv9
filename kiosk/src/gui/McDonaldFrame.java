package gui;

import javax.swing.JFrame;

public class McDonaldFrame extends JFrame{
	private boolean admin;
	
	private McDonaldPanel mp = McDonaldPanel.getInstance();
	
	public McDonaldFrame() {
		super("Welcome to McDonald");
		setLayout(null);
		setBounds(100, 0, 500, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setContentPane(this.mp);
		
		setVisible(true);
		revalidate();
	}
	
}
