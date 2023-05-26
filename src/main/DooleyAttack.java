package main;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DooleyAttack extends JLabel {
	public final int SIZE = 30;
	private int x, y;
	private int x_right;
	private int speed;
	private ImageIcon dooleymagic;
	private Dooley dooley;
	public DooleyAttack(Dooley d) {
		inimg();
		set(d);
	}
	
	public void set(Dooley dooley) {
		x = dooley.getX() + 50;
		x_right = x + SIZE;
		y = dooley.getY() - 100;
		speed = (int) (5 * Math.random()) + 5;
		setIcon(dooleymagic);
		setSize(30, 30);
		setLocation(x, y);
	}

	private void inimg() {
		dooleymagic = new ImageIcon("dooley_magic.png");
		Image img = dooleymagic.getImage();
	}

	public void movement() {
		y -= speed;
		setLocation(x, y);
		
	}
	public int getX_right() {
		return x_right;
	}
	public int getY() {
		return y;
	}
	public void setY(int yy) {
		y=yy;
		setLocation(x, y);
	}
}
