package membership;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JoinPanel extends MyUtil{
	
	private ChoicePanel cp = ChoicePanel.getInstance();
	
	private Vector<String> user;
	private JTextField id = new JTextField();
	private JTextField pw = new JTextField();
	private JTextField name = new JTextField();
	
	private JLabel title = new JLabel("회원가입");
	private JLabel idT = new JLabel("ID ");
	private JLabel pwT = new JLabel("PW ");
	private JLabel nameT = new JLabel("NAME ");
	private JButton join = new JButton("JOIN!");
	
	public JoinPanel() {
		setLayout(null);
		setBounds(0, 0, 400, 400);
		
		setTextLabel();
		setTextField();
		setButton();
	}
	
	private void setTextLabel() {
		this.title.setBounds(100, 0, 200, 100);
		this.title.setHorizontalAlignment(JLabel.CENTER);
		this.title.setFont(new Font("",Font.BOLD,30));
		add(this.title);
		this.idT.setBounds(100, 100, 40, 30);
		this.idT.setHorizontalAlignment(JLabel.RIGHT);
		add(this.idT);
		this.pwT.setBounds(100, 150, 40, 30);
		this.pwT.setHorizontalAlignment(JLabel.RIGHT);
		add(this.pwT);
		this.nameT.setBounds(100, 200, 40, 30);
		this.nameT.setHorizontalAlignment(JLabel.RIGHT);
		add(this.nameT);
	}

	private void setTextField() {
		this.id.setBounds(150, 100, 100, 30);
		this.id.setFocusable(true);
		add(this.id);
		this.pw.setBounds(150, 150, 100, 30);
		this.pw.setFocusable(true);
		add(this.pw);
		this.name.setBounds(150, 200, 100, 30);
		this.name.setFocusable(true);
		add(this.name);
	}
	
	private void setButton() {
		this.join.setBounds(150, 250, 100, 50);
		this.join.setBackground(Color.LIGHT_GRAY);
		this.join.addActionListener(this);
		add(this.join);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			if(target == this.join) {
				String id = this.id.getText();
				String pw = this.pw.getText();
				String name = this.name.getText();
				if(!id.equals("") && !pw.equals("") && !name.equals("")) {
					if(idCheck(id)) {
						this.user = new Vector<>();
						this.user.add(id);
						this.user.add(pw);
						this.user.add(name);
						
						this.cp.addUser(this.user);
						new PopUp("회원가입 완료!");
					}
					else {
						new PopUp("회원가입 불가: 아이디 중복");
					}
				}
				else {
					new PopUp("정보를 모두 입력해주세요");
				}
			}
		}
	}
	
	private boolean idCheck(String id) {
		
		for(int i=0; i<this.cp.getUserSize(); i++) {
			Vector<String> u = this.cp.getUser(i);
			if(id.equals(u.get(0))) return false;
		}
		return true;
	}
}
