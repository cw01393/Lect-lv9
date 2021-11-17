package game;

import javax.swing.JFrame;

public class MapFrame extends JFrame{
	
	private Content panel = new Content();

	public MapFrame() {
		super("SOKOBAN");
		setLayout(null);
		setBounds(50, 50, 600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(this.panel);
		setVisible(true);
		revalidate();
	}
}
