package main;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Meteor extends JLabel {
	public final int SIZE = 30;
	private int x, y;
	private int x_right;
	private int speed;
	private int direction;
	private ImageIcon meteor;
	private ImageIcon meteor_fall=new ImageIcon("meteor_fall1.png");
	public boolean yosicrashstate, crongcrashstate, dooleycrashstate; 
	public Meteor() {
		inimg();
		set();
	}
	
	public void set() {
		x = (int) (1000 * Math.random());
		x_right = x + SIZE;
		y = 40;
		direction = (int) (3 * Math.random());
		speed = (int) (5 * Math.random()) + 5;
		yosicrashstate = false;
		crongcrashstate=false;
		dooleycrashstate=false;
		
		setIcon(meteor);
		setSize(30, 30);
		setLocation(x, y);
	}

	private void inimg() {
		meteor = new ImageIcon("meteor.png");
		Image img=meteor.getImage();
	}


	public void movement() {
		// 0:down-left, 1:down, 2:down-right
		if (direction == 0) {
			x -= speed;
			x_right = x + SIZE;
			y += speed;
		} else if (direction == 1) {
			y += speed;
		} else if (direction == 2) {
			x += speed;
			x_right = x + SIZE;
			y += speed;
		}
		if(y>=440) {
			setIcon(meteor_fall);
		}
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
	public boolean getyosiCrashstate() {
		return yosicrashstate;
	}
	public void setyosiCrashstate(boolean b) {
		this.yosicrashstate = b;
	}

	public boolean isCrong_crashstate() {
		return crongcrashstate;
	}

	public void setCrong_crashstate(boolean b) {
		crongcrashstate=b;		
	}

	public void setDooley_crashstate(boolean b) {
		dooleycrashstate=b;
	}
	
}
