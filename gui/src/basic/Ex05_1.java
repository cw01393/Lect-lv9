//package basic;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
//class ResultOmok extends JFrame{
//	private JLabel textLabel = new JLabel();
//	
//	public ResultOmok(String str) {
//		super("GAME OVER");
//		setBounds(OmokFrame.W/2-150,OmokFrame.H/2-100,300,200);
//		this.textLabel.setText(str);
//		this.textLabel.setBounds(0, 0, 300, 200);
//		this.textLabel.setHorizontalAlignment(JLabel.CENTER);
//		add(textLabel);
//		setVisible(true);
//		revalidate();
//	}
//}
//
//class OmokPanel2 extends JPanel implements ActionListener{
//	
//	private JLabel title = new JLabel("Omok Game");
//	private JButton reset = new JButton("Reset");
//	
//	private final int SIZE = 10;
//	private JButton[][] map = new JButton[SIZE][SIZE];
//	private int[][] mark = new int[SIZE][SIZE];
//	
//	private int turn = 1;
//	private int win;
//	
//	public OmokPanel2() {
//		setLayout(null);
//		setBounds(0,0,OmokFrame.SIZE,OmokFrame.SIZE);
//		setBackground(Color.white);
//		setTitle();
//		setMap();
//		setReset();
//	}
//	
//	private void setReset() {
//		this.reset.setBounds(OmokFrame.SIZE/2-150,OmokFrame.SIZE/2-150,300,300);
//		this.reset.setFont(new Font("",Font.PLAIN,50));
//		this.reset.setHorizontalAlignment(JLabel.CENTER);
//		this.reset.setVerticalAlignment(JLabel.CENTER);
//		this.reset.setBackground(Color.pink);
//		this.reset.addActionListener(this);
//		add(this.reset,0); // 0으로 하면 0순위가 됨(숫자가 커질수록 우선순위가 밀림) // -1은 가장 뒤로 밀림
//		this.reset.setVisible(false);
//	}
//	
//	private void resetGame() {
//		this.mark = new int[SIZE][SIZE];
//		this.turn = 1;
//		this.win = 0;
//		for(int i=0; i<SIZE; i++) {
//			for(int j=0; j<SIZE; j++) {
//				this.map[i][j].setBackground(Color.gray);
//			}
//		}
////		this.reset.remove(this);
//		this.reset.setVisible(false);
//	}
//	
//	private void setTitle() {
//		this.title.setBounds(0,0,OmokFrame.SIZE,70);
//		this.title.setHorizontalAlignment(JLabel.CENTER);
//		this.title.setFont(new Font("",Font.BOLD,30));
//		add(this.title);
//	}
//	private void setMap() {
//		int x = OmokFrame.SIZE/2-50*10/2;
//		int y = OmokFrame.SIZE/2-50*10/2;
//		
//		for(int i=0; i<SIZE; i++) {
//			for(int j=0; j<SIZE; j++) {
//				// 버튼 생성
//				JButton bt = new JButton();
//				bt.setBounds(x,y,50,50);
//				bt.setBackground(Color.gray);
//				bt.addActionListener(this);
//				this.map[i][j] = bt;
//				add(this.map[i][j]);
//				
//				x += 50 + 2;
//			}
//			x = OmokFrame.SIZE/2-50*10/2;
//			y += 50 + 2;
//		}
//	}
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if(e.getSource() instanceof JButton) {
//			JButton target = (JButton) e.getSource();
//			
//			if(target == this.reset) {
//				resetGame();
//				return;
//			}
//			
//			for(int i=0; i<SIZE; i++) {
//				for(int j=0; j<SIZE; j++) {
//					if(this.win == 0 && target == this.map[i][j] && this.mark[i][j] == 0) {
//						if(this.turn == 1) {
//							target.setBackground(Color.red);
//						}
//						else {
//							target.setBackground(Color.black);
//						}
//						this.mark[i][j] = this.turn;
//						checkWin();
//						this.turn = this.turn == 1 ? 2 : 1;
//					}
//				}
//			}
//		}
//	}
//	private void checkWin() {
//		this.win = this.win == 0 ? checkHori() : this.win;
//		this.win = this.win == 0 ? checkVerti() : this.win;
//		this.win = this.win == 0 ? checkDia() : this.win;
//		this.win = this.win == 0 ? checkReverse() : this.win;
//		
//		if(this.win != 0) {
////			add(this.reset,0);
//			new ResultOmok(String.format("p%d win!", this.win));
//			this.reset.setVisible(true);
//		}
//	}
//	private int checkReverse() {
//		for(int i=0; i<SIZE-4; i++) {
//			for(int j=4; j<SIZE; j++) {
//				int cnt = 0;
//				if(this.mark[i][j] == this.turn) {
//					for(int k=0; k<5; k++) {
//						if(this.mark[i+k][j-k] == this.turn) {
//							cnt ++;
//						}
//					}
//					if(cnt >= 5) return this.turn;
//				}
//			}
//		}
//		return 0;
//	}
//	private int checkDia() {
//		for(int i=0; i<SIZE-4; i++) {
//			for(int j=0; j<SIZE-4; j++) {
//				int cnt = 0;
//				if(this.mark[i][j] == this.turn) {
//					for(int k=0; k<5; k++) {
//						if(this.mark[i+k][j+k] == this.turn) {
//							cnt ++;
//						}
//					}
//					if(cnt >= 5) return this.turn;
//				}
//			}
//		}
//		return 0;
//	}
//	private int checkVerti() {
//		for(int i=0; i<SIZE-4; i++) {
//			for(int j=0; j<SIZE; j++) {
//				int cnt = 0;
//				if(this.mark[i][j] == this.turn) {
//					for(int k=0; k<5; k++) {
//						if(this.mark[i+k][j] == this.turn) {
//							cnt ++;
//						}
//					}
//					if(cnt >= 5) return this.turn;
//				}
//			}
//		}
//		return 0;
//	}
//	private int checkHori() {
//		for(int i=0; i<SIZE; i++) {
//			for(int j=0; j<SIZE-4; j++) {
//				int cnt = 0;
//				if(this.mark[i][j] == this.turn) {
//					for(int k=0; k<5; k++) {
//						if(this.mark[i][j+k] == this.turn) {
//							cnt ++;
//						}
//					}
//					if(cnt >= 5) return this.turn;
//				}
//			}
//		}
//		return 0;
//	}
//}
//
//class OmokFrame extends JFrame{
//	
//	private static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
//	public static final int W = dm.width;
//	public static final int H = dm.height;
//	public static final int SIZE = 700;
//	private OmokPanel2 panel = new OmokPanel2();
//	
//	public OmokFrame() {
//		
//		setLayout(null);
//		setBounds(W/2-SIZE/2, H/2-SIZE/2, SIZE, SIZE); // setLocation(x,y) , setSize(int width, int height)를 합한것
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		add(panel);
//		setVisible(true);
//		revalidate();
//	}
//}
//
//public class Ex05_1 {
//
//	public static void main(String[] args) {
//		OmokFrame game = new OmokFrame();
//	}
//
//}
