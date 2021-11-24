package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

public class AdminPanel extends MyUtil {
	
	private McDonaldPanel mp = McDonaldPanel.getInstance();

	private static int totalSales;
	private final int BURGER = 0;
	private final int SIDE = 1;
	private final int DRINK = 2;
	
	private int menu = 0;
	
	private JLabel logo;
	private JTable table;
	private JButton[] menuButton = new JButton[3];
	
	private Vector<Vector<String>> stock = new Vector<>();;
	private Vector<String> colName = new Vector<>();
	
//	private static AdminPanel instance = new AdminPanel();
//	public static AdminPanel getInstance() {
//		return instance;
//	}
	public AdminPanel() {
		setLayout(null);
		setBounds(0, 0, 500, 500);
		setBackground(Color.LIGHT_GRAY);
		
		setLabel();
		setButton();
		setTable();
	}
	public int getTotalSales() {
		return totalSales;
	}
	public void plusTotalSales(int sales) {
		totalSales += sales;
	}
	
	private void setLabel() {
		this.logo = new JLabel(new ImageIcon("images/logoBanner.png"));
		this.logo.setBounds(0, 0, 500, 70);
		add(this.logo);
	}
	private void setButton() {
		String[] menu = {"BURGER","SIDE","DRINK"};
		int x = 100;
		int y = 140;
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
	}
	
	private void setTable() {
		this.colName.add("Menu");
		this.colName.add("Price");
		this.colName.add("Stock");
		
		this.table = new JTable(this.stock, this.colName);
		this.table.setBounds(25, 190, 450, 220);
		this.table.setBorder(new LineBorder(Color.black));
		this.table.setGridColor(Color.black);
		
		JScrollPane js = new JScrollPane(this.table);
		js.setBounds(25, 190, 450, 220);
		add(js);
	}
	
	private void selectMenu() {
		this.stock.clear();
		
		if(this.menu == BURGER) {
			for(int i=0; i<this.mp.burgerSize(); i++) {
				Menu m = this.mp.getBurger(i);
				
				Vector<String> temp = new Vector<>();
				temp.add(m.getName());
				temp.add(m.getPrice()+"");
				temp.add(m.getMaxCnt()+"");
				
				this.stock.add(temp);
			}
		}
		else if(this.menu == SIDE) {
			for(int i=0; i<this.mp.sideSize(); i++) {
				Menu m = this.mp.getSide(i);
				
				Vector<String> temp = new Vector<>();
				temp.add(m.getName());
				temp.add(m.getPrice()+"");
				temp.add(m.getMaxCnt()+"");
				
				this.stock.add(temp);
			}
		}
		else if(this.menu == DRINK) {
			for(int i=0; i<this.mp.drinkSize(); i++) {
				Menu m = this.mp.getDrink(i);
				
				Vector<String> temp = new Vector<>();
				temp.add(m.getName());
				temp.add(m.getPrice()+"");
				temp.add(m.getMaxCnt()+"");
				
				this.stock.add(temp);
			}
		}
		this.table.revalidate();
	}
	
//	private void stockSetting() {
//		if(this.menu == BURGER) {
//			for(int i=0; i<this.stock.size(); i++) {
//				Menu m = this.mp.getBurger(i);
//				
//				int cnt = Integer.parseInt(this.stock.get(i).get(2));
//				if(m.getMaxCnt() != cnt) {
//					m.setMaxCnt(cnt);
//				}
//			}
//		}
//		else if(this.menu == SIDE) {
//			for(int i=0; i<this.stock.size(); i++) {
//				Menu m = this.mp.getSide(i);
//				
//				int cnt = Integer.parseInt(this.stock.get(i).get(2));
//				if(m.getMaxCnt() != cnt) {
//					m.setMaxCnt(cnt);
//				}
//			}
//		}
//		else if(this.menu == DRINK) {
//			for(int i=0; i<this.stock.size(); i++) {
//				Menu m = this.mp.getDrink(i);
//				
//				int cnt = Integer.parseInt(this.stock.get(i).get(2));
//				if(m.getMaxCnt() != cnt) {
//					m.setMaxCnt(cnt);
//				}
//			}
//		}
//	}
	
	private void stockSetting() {
		int index = this.table.getSelectedRow();
		int column = this.table.getSelectedColumn();
		if(index != -1 &&  column == 2) {
			Menu m = null;
			if(this.menu == BURGER) m = this.mp.getBurger(index);
			else if(this.menu == SIDE) m = this.mp.getSide(index);
			else if(this.menu == DRINK) m = this.mp.getDrink(index);
			
			int modCnt = Integer.parseInt((String) this.table.getValueAt(index, column));
			if(m.getMaxCnt() != modCnt) {
				m.setMaxCnt(modCnt);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton target = (JButton) e.getSource();
			
			for(int i=0; i<this.menuButton.length; i++) {
				if(target == this.menuButton[i]) {
					this.menu = i;
					selectMenu();
					return;
				}
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		stockSetting();
		
		g.setFont(new Font("", Font.BOLD, 20));
		g.drawString(String.format("TOTAL SALES: %d,%03d원", totalSales/1000, totalSales%1000), 20, 110);
		
		repaint();
	}
	
}
