package basic;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class ImagePanel extends JPanel{
	ImageIcon icon = null;
	
	public ImagePanel(ImageIcon icon) {
		setLayout(null);
		setBounds(0, 0, 400, 500);
		this.icon = icon;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(icon.getImage(), 0, 0, null);
		repaint();
	}
	
}

public class Ex13 extends JFrame{
	
	JLabel image = null;
	Image im = new ImageIcon("images/뽀로로3-1.jfif").getImage().getScaledInstance(400, 500, Image.SCALE_SMOOTH);
	ImageIcon icon = new ImageIcon(im);

	public Ex13() {
		super("image");
		setLayout(null);
		setBounds(100,100,400,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
//		setImageLabel();
		add(new ImagePanel(this.icon));
		
		setVisible(true);
		revalidate();
	} 
	
	private void setImageLabel() {
		// ImageIcon
		Image im = new ImageIcon("images/뽀로로3-1.jfif").getImage().getScaledInstance(400, 500, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(im);
		this.image = new JLabel(icon);
		
		this.image.setBounds(0, 0, 400, 500);
		add(this.image);
	}
	
	public static void main(String[] args) {
		new Ex13();
	}

}
