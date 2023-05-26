package main;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Yosimeteor extends JLabel {
	public final int SIZE = 30;
	private int x, y;
	private int x_right;
	private int speed;
	private ImageIcon meteor;
	private yosi yosi;
	public Yosimeteor(yosi y) {
		inimg();
		set(y);
	}
	
	public void set(yosi yosi) {
		x = yosi.getX() + 50;
		x_right = x + SIZE;
		y = yosi.getY() - 100;
		speed = (int) (5 * Math.random()) + 5;
		setIcon(meteor);
		setSize(30, 30);
		setLocation(x, y);
	}

	private void inimg() {
		meteor = new ImageIcon("meteor.png");
		Image img = meteor.getImage();
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
