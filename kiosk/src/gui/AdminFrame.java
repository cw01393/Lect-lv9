package gui;

import java.awt.CardLayout;

import javax.swing.JFrame;

public class AdminFrame extends JFrame{
	
	private AdminLogPanel logPanel = new AdminLogPanel(this);
	private AdminPanel settingPanel = new AdminPanel();
	
	public AdminFrame() {
		super("Admin");
		setLayout(null);
		setBounds(200, 0, 500, 500);
		
		add(this.logPanel);
		
		setVisible(true);
		revalidate();
	}
	
	public void changePanel() {
		this.getContentPane().removeAll();
		this.getContentPane().add(this.settingPanel);
		revalidate();
		repaint();
		
	}
	
}
