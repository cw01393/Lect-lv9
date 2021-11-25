package race2;

import javax.swing.JFrame;

import race.Content;

public class Race_1 extends JFrame{

	Content_1 con = new Content_1();
	public Race_1() {
		super("Racing");
		setLayout(null);
		setBounds(100, 100, 1000, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(con);
		
		setVisible(true);
		revalidate();
		
		con.run();
	}
}
