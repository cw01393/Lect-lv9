package membership;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JButton;

public class ChoicePanel extends MyUtil{

	private JButton logIn = new JButton("LogIn");
	private JButton join = new JButton("Join");
	private Vector<Vector<String>> users = new Vector<>();
	// User: Vector<String>;
	// い add(id) : 吝汗抗寇贸府
	// い add(pw)
	// い add(name)
	
	// 可记: 颇老贸府
	
	private static ChoicePanel instance = new ChoicePanel();
	
	private ChoicePanel() {
		setLayout(null);
		setBounds(0, 0, 400, 500);
		
		setButton();
	}
	private void setButton() {
		this.logIn.setBounds(100, 100, 200, 80);
		this.logIn.setBackground(Color.white);
		this.logIn.setFont(new Font("",Font.BOLD,30));
		this.logIn.addActionListener(this);
		add(this.logIn);
		this.join.setBounds(100, 200, 200, 80);
		this.join.setBackground(Color.white);
		this.join.setFont(new Font("",Font.BOLD,30));
		this.join.addActionListener(this);
		add(this.join);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			
			if(target == this.logIn) {
				new LogInFrame();
			}
			else if(target == this.join) {
				new JoinFrame();
			}
		}
	}
	
	public Vector<String> getUser(int index){
		return this.users.get(index);
	}
	
	public void addUser(Vector<String> user) {
		this.users.add(user);
	}
	public int getUserSize() {
		return this.users.size();
	}
	public static ChoicePanel getInstance() {
		return instance;
	}
}
