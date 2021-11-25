package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class OmokGameResult extends JFrame{
	private JLabel resultText = new JLabel();
	
	public OmokGameResult(String str) {
		super("GAME CLEAR!");
		setBounds(100, 100, 300, 200);
		resultText.setBounds(0, 0, 300, 200);
		resultText.setHorizontalAlignment(JLabel.CENTER);
		resultText.setText(str);
		add(resultText);
		setVisible(true);
	}
}

class Rect{
	private int x, y, w, h, owner;
	private Color c;
	
	public Rect(int x, int y, int w, int h, Color c) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.c = c;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getW() {
		return w;
	}
	public int getH() {
		return h;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
}

class Omok2Panel extends JPanel implements MouseListener{
	
	private final int SIZE = 10;
	private Rect[][] map = new Rect[SIZE][SIZE];
	
	private int turn = 1;
	private int win;
	private Color p1 = Color.black;
	private Color p2 = Color.white;
	
	public Omok2Panel() {
		setLayout(null);
		setBounds(0, 0, OmokFrame.SIZE, OmokFrame.SIZE);
		setMap();
		addMouseListener(this);
	}
	
	private void setMap() {
		
		int x = OmokFrame.SIZE/2-50*10/2;
		int y = OmokFrame.SIZE/2-50*10/2;
		
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				this.map[i][j] = new Rect(x,y,50,50,Color.green);
				x += 50;
			}
			x = OmokFrame.SIZE/2-50*10/2;
			y += 50;
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// map 그리기
		for(int i=0; i<SIZE-1; i++) {
			for(int j=0; j<SIZE-1; j++) {
				Rect r= this.map[i][j];
				g.drawRect(r.getX()+25, r.getY()+25, r.getW(), r.getH());
			}
		}
		
		// stone 그리기
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				Rect r= this.map[i][j];
				if(r.getOwner() != 0) {
					g.setColor(Color.black);
					g.drawRoundRect(r.getX()+5, r.getY()+5, r.getW()-10, r.getH()-10, r.getW()-10, r.getH()-10);
					g.setColor(r.getC());
					g.fillRoundRect(r.getX()+5, r.getY()+5, r.getW()-10, r.getH()-10, r.getW()-10, r.getH()-10);
				}
			}
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				Rect r = this.map[i][j];
				if(x>= r.getX() && x < r.getX()+r.getW() 
				&& y >= r.getY() && y < r.getY()+r.getH()) {
					if(r.getOwner() == 0) {
						r.setOwner(this.turn);
						r.setC(this.turn == 1 ? this.p1 : this.p2);
						
						checkWin();
						this.turn = this.turn == 1 ? 2 :1;
					}
				}
			}
		}
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	private void checkWin() {
		this.win = this.win == 0 ? checkHori() : this.win;
		this.win = this.win == 0 ? checkVerti() : this.win;
		this.win = this.win == 0 ? checkDia() : this.win;
		this.win = this.win == 0 ? checkReverse() : this.win;
		
		if(this.win != 0) {
			new OmokGameResult(String.format("p%d win!", this.win));
		}
	}

	private int checkReverse() {
		for(int i=4; i<SIZE; i++) {
			for(int j=0; j<SIZE-4; j++) {
				Rect r = this.map[i][j];
				if(r.getOwner() == this.turn) {
					int cnt = 0;
					for(int k=0; k<5; k++) {
						if(this.map[i-k][j+k].getOwner() == this.turn) cnt ++;
					}
					if(cnt == 5) {
						return this.turn;
					}
				}
			}
		}
		return 0;
	}

	private int checkDia() {
		for(int i=0; i<SIZE-4; i++) {
			for(int j=0; j<SIZE-4; j++) {
				Rect r = this.map[i][j];
				if(r.getOwner() == this.turn) {
					int cnt = 0;
					for(int k=0; k<5; k++) {
						if(this.map[i+k][j+k].getOwner() == this.turn) cnt ++;
					}
					if(cnt == 5) {
						return this.turn;
					}
				}
			}
		}
		return 0;
	}

	private int checkVerti() {
		for(int i=0; i<SIZE-4; i++) {
			for(int j=0; j<SIZE; j++) {
				Rect r = this.map[i][j];
				if(r.getOwner() == this.turn) {
					int cnt = 0;
					for(int k=0; k<5; k++) {
						if(this.map[i+k][j].getOwner() == this.turn) cnt ++;
					}
					if(cnt == 5) {
						return this.turn;
					}
				}
			}
		}
		return 0;
	}

	private int checkHori() {
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE-4; j++) {
				Rect r = this.map[i][j];
				if(r.getOwner() == this.turn) {
					int cnt = 0;
					for(int k=0; k<5; k++) {
						if(this.map[i][j+k].getOwner() == this.turn) cnt ++;
					}
					if(cnt == 5) {
						return this.turn;
					}
				}
			}
		}
		return 0;
	}
}

class OmokFrame extends JFrame{
	
	public static Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	public static int width = dm.width;
	public static int height = dm.height;
	public static final int SIZE = 700;
	private Omok2Panel panel = new Omok2Panel();
	
	public OmokFrame() {
		super("OMOK GAME");
		setLayout(null);
		setBounds(width/2-SIZE/2, height/2-SIZE/2, SIZE, SIZE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(panel);
		setVisible(true);
		revalidate();
	}
}

public class Ex08_omok {

	public static void main(String[] args) {

		OmokFrame game = new OmokFrame();
	}

}
