package gui;

import javax.swing.JFrame;

public class PaymentFrame extends JFrame{
	private PaymentPanel panel = new PaymentPanel();
	
	public PaymentFrame() {
		super("PAYMENT");
		setLayout(null);
		setBounds(200, 100, 500, 400);
		add(this.panel);
		setVisible(true);
		revalidate();
	}

}
