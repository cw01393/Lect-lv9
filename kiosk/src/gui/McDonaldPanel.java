package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

public class McDonaldPanel extends MyUtil {
	
	private AdminPanel ap = new AdminPanel();
	private AdminFrame af;

	private ArrayList<Menu> burger = new ArrayList<Menu>();
	private ArrayList<Menu> side = new ArrayList<Menu>();
	private ArrayList<Menu> drink = new ArrayList<Menu>();
	private ArrayList<Menu> orderlist = new ArrayList<Menu>();
	
	private final int BURGERPAGE = 0;
	private final int SIDEPAGE = 1;
	private final int DRINKPAGE = 2;
	private int nowPage;
	private int pageCnt = 1;
	private int totalCnt;
	private int totalPrice;
	private int x;
	private int y;
	private boolean pay;
	
	private JTable table = null;
	private Vector<String> colName = new Vector<String>();
	private Vector<Vector<String>> order = new Vector<>();
	
	private JButton plus = new JButton("+");
	private JButton minus = new JButton("-");
	private JButton pre = new JButton("◀");
	private JButton next = new JButton("▶");
	private JButton[] menuButton = new JButton[3];
	private JButton payment;
	private JButton logo;
	
	private PaymentFrame pf = null;
	
	private static McDonaldPanel instance = new McDonaldPanel();
	private McDonaldPanel() {
		setLayout(null);
		setBounds(0, 70, 500, 700);
		setBackground(Color.white);
		addMouseListener(this);
		
		setMenu();
		setTable();
		setButton();
	}
	
	public static McDonaldPanel getInstance() {
		return instance;
	}
	
	public int burgerSize() {
		return this.burger.size();
	}
	public int sideSize() {
		return this.side.size();
	}
	public int drinkSize() {
		return this.drink.size();
	}
	
	public Menu getBurger(int index) {
		return this.burger.get(index);
	}
	public Menu getSide(int index) {
		return this.side.get(index);
	}
	public Menu getDrink(int index) {
		return this.drink.get(index);
	}
	
	public boolean getPay() {
		return this.pay;
	}
	
	public void setPay(boolean pay) {
		this.pay = pay;
	}
	
	public int getTotalPrice() {
		return this.totalPrice;
	}
	
	private void setButton() {
		this.plus.setBounds(200, 425, 45, 30);
		this.plus.setBackground(Color.LIGHT_GRAY);
		this.plus.addActionListener(this);
		add(this.plus);
		this.minus.setBounds(255, 425, 45, 30);
		this.minus.setBackground(Color.LIGHT_GRAY);
		this.minus.addActionListener(this);
		add(this.minus);
		
		this.pre.setBounds(25, 425, 50, 30);
		this.pre.setBackground(Color.LIGHT_GRAY);
		this.pre.addActionListener(this);
		add(this.pre);
		this.next.setBounds(425, 425, 50, 30);
		this.next.setBackground(Color.LIGHT_GRAY);
		this.next.addActionListener(this);
		add(this.next);
		
		String[] menu = {"BURGER","SIDE","DRINK"};
		int x = 100;
		int y = 80;
		for(int i=0; i<this.menuButton.length; i++) {
			JButton b = new JButton();
			b.setBounds(x, y, 100, 30);
			b.setText(menu[i]);
			b.setBackground(Color.white);
			b.addActionListener(this);
			
			this.menuButton[i] = b;
			add(this.menuButton[i]);
			x += 100;
		}
		
		this.payment = new JButton(new ImageIcon("images/pay.png"));
		this.payment.setBounds(270, 590, 200, 55);
		this.payment.addActionListener(this);
		add(this.payment);
		
		this.logo = new JButton(new ImageIcon("images/logoBanner.png"));
		this.logo.setBounds(0, 0, 500, 70);
		this.logo.setBorderPainted(false);
//		this.logo.addActionListener(this);
		this.logo.addMouseListener(this);
		add(this.logo);
	}
	
	private void setTable() {
		this.colName.add("Menu");
		this.colName.add("Price");
		this.colName.add("Count");
		
		this.table = new JTable(this.order, this.colName);
		this.table.setBounds(25, 460, 450, 120);
		this.table.setBorder(new LineBorder(Color.black));
		this.table.setGridColor(Color.black);
		
		JScrollPane js = new JScrollPane(this.table);
		js.setBounds(25, 460, 450, 120);
		add(js);
		
	}
	
	private void setMenu() {
		this.nowPage = BURGERPAGE;
		
		String[] bN = {"스파이시 맥앤치즈 버거","슈니언 버거","트리플 치즈버거","베이컨 토마토 디럭스","빅맥","맥스파이시 상하이버거","슈슈버거","슈비버거","에그 불고기버거","불고기버거","치즈버거","햄버거"};
		int[] bP = {6900, 5800, 6300, 6200, 5300, 5300,5200,6200,3900,2900,3000,2700};
		String[] sN = {"케이준 비프 스낵랩","츄러스","상하이 치킨스낵","맥너겟 10조각","맥너겟 6조각","맥너겟 4조각","치킨텐더 4조각","치킨텐더 2조각","후렌치 후라이","애플파이","치즈스틱 4조각"};
		int[] sP = {2900, 2200, 2900, 5200, 3700, 2500,5600,3200,2400,1900,4700};
		String[] dN = {"자두 칠리","바닐라라떼","카페라떼","카푸치노","아메리카노","에스프레소","코카콜라","코카콜라 제로","스프라이트","환타"};
		int[] dP = {3400, 3900, 3400, 3400, 2900, 2200, 2100,2100,2100,2100};
		
		int x = 25;
		int y = 120;
		for(int i=0; i<bN.length; i++) {
			Menu temp = new Menu(x, y, "b",i,bN[i],bP[i]);
			this.burger.add(temp);
			
			x += 150;
			if((i+1)%3 == 0) {
				x = 25;
				y += 150;
			}
			if((i+1)%6 == 0) {
				y = 120;
			}
		}
		x = 25;
		y = 120;
		for(int i=0; i<sN.length; i++) {
			Menu temp = new Menu(x, y, "s",i,sN[i],sP[i]);
			this.side.add(temp);
			
			x += 150;
			if((i+1)%3 == 0) {
				x = 25;
				y += 150;
			}
			if((i+1)%6 == 0) {
				y = 120;
			}
		}
		x = 25;
		y = 120;
		for(int i=0; i<dN.length; i++) {
			Menu temp = new Menu(x, y, "d",i,dN[i],dP[i]);
			this.drink.add(temp);
			
			x += 150;
			if((i+1)%3 == 0) {
				x = 25;
				y += 150;
			}
			if((i+1)%6 == 0) {
				y = 120;
			}
		}
	}
	
	private void calTotal() {
		int calCnt = 0;
		int calPrice = 0;
		for(int i=0; i<this.order.size(); i++) {
			int cnt = Integer.parseInt(this.order.get(i).get(2));
			int price = Integer.parseInt(this.order.get(i).get(1));
			this.orderlist.get(i).setCnt(cnt);
			calCnt += cnt;
			calPrice += cnt*price;
		}
		this.totalCnt = calCnt;
		this.totalPrice = calPrice;
	}
	
	private int checkOrder(String name) {
		for(int i=0; i<this.order.size(); i++) {
			if(this.order.get(i).get(0).equals(name)) return i;
		}
		return -1;
	}
	
	private int checkIndex(ArrayList<Menu> temp) {
		for(int i=6*(this.pageCnt-1); i<temp.size(); i++) {
			Menu m = temp.get(i);
			if(this.x > m.getX() && this.x < m.getX()+150 
					&& this.y > m.getY() && this.y < m.getY()+120) {
				return i;
			}
		}
		return -1;
	}
	
	private void reset() {
		for(int i=0; i<this.orderlist.size(); i++) {
			this.orderlist.get(i).setCnt(0);
		}
		this.nowPage = BURGERPAGE;
		this.pageCnt = 1;
		this.order.clear();
		this.orderlist.clear();
		this.table.clearSelection();
		this.totalCnt = 0;
		this.totalPrice = 0;
		this.pf = null;
		this.pay = false;
	}
	
	private void purchase() {
		for(int i=0; i<this.orderlist.size(); i++) {
			this.orderlist.get(i).puchase();
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == this.logo) {
			if(e.getClickCount() == 1) reset();
			else if(e.getClickCount() > 4) {
				this.af = new AdminFrame();
			}
		}
		
		this.x = e.getX();
		this.y = e.getY();
		
		ArrayList<Menu> temp = null;
		if(this.nowPage == BURGERPAGE) temp = this.burger;
		else if(this.nowPage == SIDEPAGE) temp = this.side;
		else if(this.nowPage == DRINKPAGE) temp = this.drink;
		
		int index = checkIndex(temp);
		if(index != -1) {
			Menu m = temp.get(index);
			String name = m.getName();
			int price = m.getPrice();
			int cnt = m.getCnt();
			int maxCnt = m.getMaxCnt();
			
			if(maxCnt > cnt) {
				int orderIndex = checkOrder(name);
				if(orderIndex == -1) {
					Vector<String> choice = new Vector<String>();
					choice.add(name);
					choice.add(price+"");
					choice.add("1");
					m.setCnt(cnt+1);
					
					this.order.add(choice);
					this.orderlist.add(m);
					this.table.revalidate();
					this.table.repaint();
				}
				else {
					m.setCnt(cnt+1);
					int mCnt = Integer.parseInt(this.order.get(orderIndex).get(2));
					this.order.get(orderIndex).set(2, (mCnt+1)+"");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "재고가 부족합니다");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			
			if(target == this.pre) {
				if(this.pageCnt > 1) this.pageCnt --;
			}
			else if(target == this.next) {
				ArrayList<Menu> temp = null;
				if(this.nowPage == BURGERPAGE) temp = this.burger;
				else if(this.nowPage == SIDEPAGE) temp = this.side;
				else if(this.nowPage == DRINKPAGE) temp = this.drink;
				
				if(this.pageCnt < (temp.size()-1)/6 + 1) this.pageCnt ++;
			}
			else if(target == this.plus || target == this.minus) {
				int row = this.table.getSelectedRow();
				if(row != -1) {
					int cnt = Integer.parseInt(this.order.get(row).get(2));
					if(target == this.plus) {
						if(cnt < this.orderlist.get(row).getMaxCnt())
							cnt ++;
						
						else {
							JOptionPane.showMessageDialog(null, "재고가 부족합니다");
						}
					}
					else if(target == this.minus && cnt > 0) {
						cnt --;
					}
					this.order.get(row).set(2, cnt+"");
					this.table.revalidate();
				}
			}
//			else if(target == this.logo) {
//				reset();
//			}
			else if(target == this.payment) {
				if(this.order.size() > 0) {
					this.pf = new PaymentFrame();
				}
				else {
					JOptionPane.showMessageDialog(null, "주문내역이 없습니다");
				}
			}
			else {
				for(int i=0; i<this.menuButton.length; i++) {
					if(target == this.menuButton[i]) {
						this.nowPage = i;
						this.pageCnt = 1;
						return;
					}
				}
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(this.pf != null && this.pay) {
			this.pf.dispose();
			purchase();
			this.ap.plusTotalSales(this.totalPrice);
			reset();
		}
		
		calTotal();
		
		ArrayList<Menu> temp = null;
		int max = 0;
		if(this.nowPage == BURGERPAGE) {
			temp = this.burger;
			max = 6+6*(this.pageCnt-1) > this.burger.size() ? this.burger.size() : 6+6*(this.pageCnt-1);
		}
		else if(this.nowPage == SIDEPAGE) {
			temp = this.side;
			max = 6+6*(this.pageCnt-1) > this.side.size() ? this.side.size() : 6+6*(this.pageCnt-1);
		}
		else if(this.nowPage == DRINKPAGE) {
			temp = this.drink;
			max = 6+6*(this.pageCnt-1) > this.drink.size() ? this.drink.size() : 6+6*(this.pageCnt-1);
		}
		
		for(int i=6*(this.pageCnt-1); i<max; i++) {
			g.setColor(Color.black);
			Menu m = temp.get(i);
			g.drawImage(m.getImage().getImage(), m.getX(), m.getY(), null);
			if(m.getMaxCnt() == 0) {
				g.fillRect(m.getX()+10, m.getY()+55, 130, 30);
				FontMetrics metrics = g.getFontMetrics();
				int width = metrics.stringWidth("Sold out");
				g.setColor(Color.white);
				g.drawString("Sold out", m.getX() + 150/2 - width/2, m.getY()+75);
			}
			
			FontMetrics metrics = g.getFontMetrics();
			int width = metrics.stringWidth(m.getName());
			g.setColor(Color.black);
			g.drawString(m.getName(), m.getX() + 150/2 - width/2, m.getY()+130);
			g.setColor(Color.red);
			width = metrics.stringWidth(m.getPrice()+"");
			g.drawString(m.getPrice()+"", m.getX() + 150/2 - width/2, m.getY()+145);
		}
		
		g.setColor(Color.black);
		g.setFont(new Font("", Font.BOLD, 20));
		g.drawString(String.format("Total Count: %d개", this.totalCnt), 30, 610);
		String price = "";
		if(this.totalPrice > 0) {
			price = String.format("Total Price: %d,%03d원", this.totalPrice/1000, this.totalPrice%1000);
		}
		else {
			price = "Total Price: 0원";
		}
		g.drawString(price, 30, 640);
		
		repaint();
	}

}
