package basic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import basic.MyUtil;

class ExPanel extends MyUtil{
	
	// 줄바꿈 불가능
	JTextField jf = new JTextField();
	
	// 줄바꿈 가능 -> JTextArea활용
	JTextArea ja = new JTextArea();
	
	// 로그인 & 회원가입
	// ㄴ 메인프레임에 버튼 두개
	// ㄴ 로그인과 회원가입
	// ㄴ 버튼을 누르면 팝업(새로운 프레임) -> 텍스트 입력
	// ㄴ 회원가입 정보는 Vector에 저장
	
	Vector<Vector<String>> users = new Vector<>();
	// User: Vector<String>;
	// ㄴ add(id) : 중복예외처리
	// ㄴ add(pw)
	// ㄴ add(name)
	
	// 옵션: 파일처리
	
	public ExPanel() {
		setLayout(null);
		setBounds(0, 0, 400, 500);
		
		setTextField();
		setTextArea();
	}

	private void setTextArea() {
		this.ja.setBounds(100, 180, 200, 200);
		add(this.ja);
	}

	private void setTextField() {
		this.jf.setBounds(100,100,100,30);
		this.jf.setFocusable(true);
		this.jf.addKeyListener(this);
		add(this.jf);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			System.out.println(jf.getText());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}

public class Ex extends JFrame{

	public Ex() {
		setLayout(null);
		setBounds(100, 100, 400, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(new ExPanel());
		
		setVisible(true);
		revalidate();
	}
	
	public static void main(String[] args) {

		new Ex();
	}

}
