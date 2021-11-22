package membership;

import javax.swing.JFrame;

public class JoinFrame extends JFrame{

	public JoinFrame() {
		super("JOIN US!");
		setLayout(null);
		setBounds(100, 100, 400, 400);
		add(new JoinPanel());
		setVisible(true);
		revalidate();
	}
	
}
