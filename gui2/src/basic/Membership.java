package basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

class JoinFrame extends JFrame {
	JTextField id = new JTextField();
	JTextField pw = new JTextField();
	JTextField name = new JTextField();
	
	JLabel idLabel = new JLabel("id: ");
	JLabel pwLabel = new JLabel("pw: ");
	JLabel nameLabel = new JLabel("name: ");
	
	public JoinFrame() {
		setLayout(null);
		setBounds(100, 100, 300, 400);
		
		setTextField();
		
		setVisible(true);
		revalidate();
	}
	

	
	private void setTextField() {
		this.idLabel.setBounds(30, 50, 60, 50);
		add(this.idLabel);
		this.pwLabel.setBounds(30, 110, 60, 50);
		add(this.pwLabel);
		this.nameLabel.setBounds(30, 170, 60, 50);
		add(this.nameLabel);
		
		this.id.setBounds(100, 50, 150, 50);
		add(this.id);
		this.pw.setBounds(100, 110, 150, 50);
		add(this.pw);
		this.name.setBounds(100, 170, 150, 50);
		add(this.name);
	}

	
}

class ChoicePanel extends MyUtil{
	
	private JButton logIn = new JButton("LogIn");
	private JButton join = new JButton("Join");
	private JTable table = null;
	
	private JoinFrame joinFrame;
	
	private Vector<Vector<String>> users = new Vector<>();
	private Vector<String> colName = new Vector<>();
	// User: Vector<String>;
	// ㄴ add(id) : 중복예외처리
	// ㄴ add(pw)
	// ㄴ add(name)
	
	// 옵션: 파일처리
	
	public ChoicePanel() {
		setLayout(null);
		setBounds(0, 0, 400, 500);
		
		setTable();
		setButton();
		
		init();
	}
	
	private void init() {
		Random rn = new Random();
		
		String[] front = {"김","이","박","정","오"};
		String[] back1 = {"성","지","우","아","희"};
		String[] back2 = {"연","무","안","용","이"};
		
		for(int i=0; i<100; i++) {
			Vector<String> user = new Vector<>();
			String name = front[rn.nextInt(front.length)] + back1[rn.nextInt(back1.length)] + back2[rn.nextInt(back2.length)];
			user.add(i +"");
			user.add(i +"");
			user.add(name);
			
			this.users.add(user);
		}
	}
	
	private void setTable() {
		this.colName = new Vector();
		this.colName.add("id");
		this.colName.add("pw");
		this.colName.add("name");
		
		this.table = new JTable(users, colName);
		this.table.setBounds(50, 50, 300, 300);
		
		this.table.setBorder(new LineBorder(Color.black));
		this.table.setGridColor(Color.black);
		
		JScrollPane js = new JScrollPane(table);
		js.setBounds(50, 50, 300, 300);
//		js.setAutoscrolls(false); // true가 디폴트값
		
		add(js);
	}
	
	private void setButton() {
		this.logIn.setBounds(100, 380, 100, 50);
		this.logIn.setBackground(Color.white);
//		this.logIn.setFont(new Font("",Font.BOLD,30));
		this.logIn.addActionListener(this);
		add(this.logIn);
		this.join.setBounds(210, 380, 100, 50);
		this.join.setBackground(Color.white);
//		this.join.setFont(new Font("",Font.BOLD,30));
		this.join.addActionListener(this);
		add(this.join);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			
			if(target == this.logIn) {
				
			}
			else if(target == this.join) {
				this.joinFrame = new JoinFrame();
				
				this.joinFrame.id.setFocusable(true);
				this.joinFrame.id.addKeyListener(this);
				this.joinFrame.pw.setFocusable(true);
				this.joinFrame.pw.addKeyListener(this);
				this.joinFrame.name.setFocusable(true);
				this.joinFrame.name.addKeyListener(this);
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		Object target = e.getSource();
		if(target == this.joinFrame.id || target == this.joinFrame.pw || target == this.joinFrame.name
				&& e.getKeyCode() == KeyEvent.VK_ENTER) {
			String id = this.joinFrame.id.getText();
			String pw = this.joinFrame.pw.getText();
			String name = this.joinFrame.name.getText();
			
			if(!id.equals("") && !pw.equals("") && !name.equals("")) {
				//join
				joinUser(id, pw, name);
			}
		}
	}
	private void joinUser(String id, String pw, String name) {
		boolean check = checkId(id);
		
		if(!check) {
			Vector<String> user = new Vector<>();
			user.add(id);
			user.add(pw);
			user.add(name);
			
			this.users.add(user);
			System.out.println("회원가입 완료");
			this.joinFrame.dispose(); // 프레임에 대한 창 닫기
			
			this.table.revalidate();
			this.table.repaint();
		}
		else {
			// 단순팝업창을 띄울때에만 활용(권장X)
			JOptionPane.showMessageDialog(null,	"중복된 아이디입니다");
		}
	}
	
	private boolean checkId(String id) {
		for(int i=0; i<this.users.size(); i++) {
			if(this.users.get(i).get(0).equals(id)) return true;
		}
		return false;
	}
	

}

public class Membership extends JFrame{
	
	public Membership() {
		super("MEMBERSHIP");
		setLayout(null);
		setBounds(100, 100, 400, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
//		this.getContentPane(); -> 유리장 교체 this.setContentPane();
		add(new ChoicePanel()); // 앞에 this.getContentPane()이 생략
		
		setVisible(true);
		revalidate();
	}

	public static void main(String[] args) {
		new Membership();
		
	}

}
