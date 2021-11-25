package basic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// GUI
// ㄴ awt & swing

// UI 엘리먼트를 담는 컨테이너

// 최상위 컨테이너 : JFrame
// 컨테이너 : JPanel
// 컴포넌트 : JButton, JLabel, JTextField, ...

class MyPanel extends JPanel{
	
	public MyPanel(int x, int y, int width, int height, Color color){
		setBounds(x,y,width,height);
		setBackground(color);
	}
}

class Contents extends JPanel implements ActionListener{
	
	// 버튼만들기
	// JButton 클래스를 import하고 객체생성
	private JButton bt = new JButton();
	private boolean click;
	
	public Contents() {
		
		setLayout(null);
		setBounds(0,0,500,400);
		
		// 버튼 속성 설정
		this.bt.setBounds(100,100,100,100); // 크기와 위치 설정
		this.bt.setText("PUSH"); // 버튼의 텍스트 설정
		System.out.println(this.bt);
		
//		on mac
//		bt.setOpaque(true); // 투명도
//		bt.setBorderPainted(false); // 테두리삭제
		this.bt.setBackground(Color.gray); // 버튼 컬러
		this.bt.addActionListener(this); // 버튼에 리스너를 달아줌
		
		// 패널에 버튼을 달아줌
		add(this.bt);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("아야");
		
		if(e.getSource() == this.bt) {
//			Color color = this.bt.getBackground() == Color.gray ? Color.red : Color.gray;
			this.click = this.click ? false : true;
			if(this.click) {
				this.bt.setBackground(Color.red);
			}
			else {
				this.bt.setBackground(Color.gray);
			}
		}
	}
}


// JFrame 만들기
class MyFrame extends JFrame{
	public MyFrame() {
		// JFrame 설정
		
		// setLayout
		// 기본 레이아웃 구성의 설정 -> 순서대로 나열식
		setLayout(null);
		
		// 타이틀 설정
		// super("title");
		// setTitle("title");
		setTitle("MyFrame");
		
		// 크기
		// setBounts(x,y,width,height);
		setBounds(50,50,500,400);
		
		// 종료 조건
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// add
//		add(new MyPanel(200,160,100,80,Color.white));
//		add(new MyPanel(0,0,250,200,Color.pink));
//		add(new MyPanel(250,0,250,200,Color.orange));
//		add(new MyPanel(0,200,250,200,Color.lightGray));
//		add(new MyPanel(250,200,250,200,Color.black));
		add(new Contents());
		
		// 보이기
		// setVisible(true);
		setVisible(true);
		
		// 갱신
		revalidate();
		
	}
}

public class Ex {

	public static void main(String[] args) {

		MyFrame frame = new MyFrame();
	}

}
