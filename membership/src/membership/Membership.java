package membership;

import javax.swing.JFrame;

public class Membership extends JFrame{

	public Membership() {
		super("MEMBERSHIP");
		setLayout(null);
		setBounds(100, 100, 400, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(ChoicePanel.getInstance());
		
		setVisible(true);
		revalidate();
	}
}
