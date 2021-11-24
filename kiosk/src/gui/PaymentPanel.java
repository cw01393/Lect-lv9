package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PaymentPanel extends MyUtil{
	
	private McDonaldPanel mp = McDonaldPanel.getInstance();

	private JLabel logo;
	private JLabel text;
	private JButton cash;
	private JButton card;
	
	public PaymentPanel() {
		setLayout(null);
		setBounds(0, 0, 500, 400);
		setBackground(Color.white);

		setLabel();
		setButton();
	}
	
	private void setLabel() {
		this.logo = new JLabel();
		this.logo.setIcon(new ImageIcon("images/logoBanner.png"));
		this.logo.setBounds(0, 0, 500, 70);
		add(this.logo);
		
		this.text = new JLabel();
		this.text.setBounds(0, 80, 500, 100);
		this.text.setText(String.format("결제할 금액: %d,%03d원", this.mp.getTotalPrice()/1000,this.mp.getTotalPrice()%1000));
		this.text.setFont(new Font("",Font.BOLD,28));
		this.text.setHorizontalAlignment(JLabel.CENTER);
		add(this.text);
	}
	
	private void setButton() {
		this.cash = new JButton(new ImageIcon("images/cash.png"));
		this.cash.setBounds(80, 200, 150, 100);
		this.cash.addActionListener(this);
		add(this.cash);
		
		this.card = new JButton(new ImageIcon("images/card.png"));
		this.card.setBounds(270, 200, 150, 100);
		this.card.addActionListener(this);
		add(this.card);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			if(target == this.cash) {
				JOptionPane.showMessageDialog(null, "현금 결제완료", "PAYMENT COMPLETED", JOptionPane.PLAIN_MESSAGE);
				this.mp.setPay(true);
			}
			else if(target == this.card) {
				JOptionPane.showMessageDialog(null, "카드 결제완료", "PAYMENT COMPLETED", JOptionPane.PLAIN_MESSAGE);
				this.mp.setPay(true);
			}
		}
	}
	
	
}
