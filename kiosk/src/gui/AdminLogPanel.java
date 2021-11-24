package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AdminLogPanel extends MyUtil{

	private final String ID = "admin";
	private final String PW = "1111";
	
	private AdminPanel ap = new AdminPanel();
	private AdminFrame f;
	
	private JLabel logo;
	private JLabel title = new JLabel("관리자 로그인");
	private JLabel idLabel = new JLabel("ID:");
	private JLabel pwLabel = new JLabel("PW:");
	private JTextField idField = new JTextField();
	private JTextField pwField = new JTextField();
	private JButton logIn;
	
	public AdminLogPanel(AdminFrame f) {
		setLayout(null);
		setBounds(0, 0, 500, 500);
		setBackground(Color.white);
		this.f = f;
		
		setLabel();
		setTextField();
		setButton();
	}
	
	private void setButton() {
		this.logIn = new JButton(new ImageIcon("images/login.png"));
		this.logIn.setBounds(250, 320, 100, 50);
		this.logIn.addActionListener(this);
		add(this.logIn);
	}

	private void setLabel() {
		this.logo = new JLabel(new ImageIcon("images/logoBanner.png"));
		this.logo.setBounds(0, 0, 500, 70);
		add(this.logo);
		
		this.title.setBounds(0, 120, 500, 50);
		this.title.setFont(new Font("", Font.BOLD, 25));
		this.title.setHorizontalAlignment(JLabel.CENTER);
		add(this.title);
		
		this.idLabel.setBounds(150,200,50,40);
		add(this.idLabel);
		this.pwLabel.setBounds(150,250,50,40);
		add(this.pwLabel);
	}

	private void setTextField() {
		this.idField.setBounds(200,200,150,40);
		add(this.idField);
		this.pwField.setBounds(200,250,150,40);
		add(this.pwField);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.logIn) {
			String idInput = this.idField.getText();
			String pwInput = this.pwField.getText();
			
			if(idInput.equals("") || pwInput.equals("")) {
				JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 모두 입력해주세요");
			}
			else if(idInput.equals(ID) && pwInput.equals(PW)){
				this.f.changePanel();
			}
			else {
				JOptionPane.showMessageDialog(null, "회원정보가 일치하지 않습니다");
			}
			
		}
	}
}
