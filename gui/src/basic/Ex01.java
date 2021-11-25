package basic;

import javax.swing.JFrame;

class MyFrame1 extends JFrame{
	
	public MyFrame1() {
		setLayout(null);
		setTitle("title");
		setBounds(50,50,500,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		revalidate();
	}
	
}

public class Ex01 {

	public static void main(String[] args) {

		MyFrame1 frame = new MyFrame1();
	}

}
