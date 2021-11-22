package membership;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class PopUp extends JFrame{
	private JLabel label;
	
	public PopUp(String str) {
		setLayout(null);
		setBounds(200, 200, 300, 200);
		this.label = new JLabel(str);
		this.label.setBounds(0, 0, 300, 200);
		this.label.setHorizontalAlignment(JLabel.CENTER);
		add(this.label);
		setVisible(true);
		revalidate();
	}
}
