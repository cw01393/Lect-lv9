package membership;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LogInPanel extends MyUtil{
	
	private ChoicePanel cp = ChoicePanel.getInstance();
	
	private JTextField id = new JTextField();
	private JTextField pw = new JTextField();
	
	private JLabel title = new JLabel("로그인");
	private JLabel idT = new JLabel("ID ");
	private JLabel pwT = new JLabel("PW ");
	private JButton logIn = new JButton("LogIn");
	
	public LogInPanel() {
		setLayout(null);
		setBounds(0, 0, 400, 400);
		
		setTextLabel();
		setTextField();
		setButton();
	}

	private void setTextLabel() {
		this.title.setBounds(150, 0, 100, 100);
		this.title.setHorizontalAlignment(JLabel.CENTER);
		this.title.setFont(new Font("",Font.BOLD,30));
		add(this.title);
		this.idT.setBounds(100, 100, 40, 30);
		this.idT.setHorizontalAlignment(JLabel.RIGHT);
		add(this.idT);
		this.pwT.setBounds(100, 150, 40, 30);
		this.pwT.setHorizontalAlignment(JLabel.RIGHT);
		add(this.pwT);
	}
	
	private void setTextField() {
		this.id.setBounds(150, 100, 100, 30);
		this.id.setFocusable(true);
		add(this.id);
		this.pw.setBounds(150, 150, 100, 30);
		this.pw.setFocusable(true);
		add(this.pw);
	}
	
	private void setButton() {
		this.logIn.setBounds(150, 200, 100, 50);
		this.logIn.setBackground(Color.LIGHT_GRAY);
		this.logIn.addActionListener(this);
		add(this.logIn);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			
			if(target == this.logIn) {
				String id = this.id.getText();
				String pw = this.pw.getText();
				
				String name = check(id,pw);
				if(name.equals("")) {
					new PopUp("회원정보를 다시 확인하세요");
					System.out.println(this.cp.getUserSize());
				}
				else {
					new PopUp(String.format("%s님 로그인되었습니다", name));
				}
			}
		}
	}
	
	private String check(String id, String pw) {
		for(int i=0; i<this.cp.getUserSize(); i++) {
			Vector<String> u = this.cp.getUser(i);
			
			if(id.equals(u.get(0)) && pw.equals(u.get(1))) {
				return u.get(2);
			}
		}
		return "";
	}
}
