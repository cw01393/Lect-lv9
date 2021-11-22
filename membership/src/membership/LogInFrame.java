package membership;

import javax.swing.JFrame;

public class LogInFrame extends JFrame{
	public LogInFrame() {
		super("LON IN");
		setLayout(null);
		setBounds(100, 100, 400, 400);
		add(new LogInPanel());
		setVisible(true);
		revalidate();
	}
}
