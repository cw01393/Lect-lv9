package race;

import javax.swing.JFrame;

public class Race extends JFrame{

	private Content panel = new Content();
	
	public Race() {
		super("HORSE RACING");
		setLayout(null);
		setBounds(50, 50, 1000, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(this.panel);
		setVisible(true);
		revalidate();
		
		this.panel.run();
	}
}
