package main;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Star extends JLabel{
	public final int SIZE = 30;
	private int x, y;
	private int x_right;
	private int speed;
	private int direction;
	private ImageIcon meteor;
	
	public boolean yosicrashstate; 
	public boolean crong_crashstate; 
	public boolean dooley_crashstate; 
	
	public boolean crong_star_crashstate;
	public boolean dooley_star_crashstate;
	public boolean yosi_star_crashstate;
	
	
	
	
	public boolean isCrong_star_crashstate() {
		return crong_star_crashstate;
	}

	public void setCrong_star_crashstate(boolean crong_star_crashstate) {
		this.crong_star_crashstate = crong_star_crashstate;
	}

	public boolean isDooley_star_crashstate() {
		return dooley_star_crashstate;
	}

	public void setDooley_star_crashstate(boolean dooley_star_crashstate) {
		this.dooley_star_crashstate = dooley_star_crashstate;
	}

	public boolean isYosi_star_crashstate() {
		return yosi_star_crashstate;
	}

	public void setYosi_star_crashstate(boolean yosi_star_crashstate) {
		this.yosi_star_crashstate = yosi_star_crashstate;
	}

	public Star() {
		inimg();
		set();
	}
	
	public void set() {
		x = (int) (1000 * Math.random());
		x_right = x + SIZE;
		y = 40;
		direction = (int) (2 * Math.random());
		speed = (int) (5 * Math.random()) + 5;
		
		yosicrashstate = false;
		crong_crashstate = false;
		dooley_crashstate = false;
		
		setIcon(meteor);
		setSize(50, 50);
		setLocation(x, y);
	}

	private void inimg() {
		meteor = new ImageIcon("star.png");
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
		return crong_crashstate;
	}

	public void setCrong_crashstate(boolean crong_crashstate) {
		this.crong_crashstate = crong_crashstate;
	}

	public boolean isDooley_crashstate() {
		return dooley_crashstate;
	}

	public void setDooley_crashstate(boolean dooley_crashstate) {
		this.dooley_crashstate = dooley_crashstate;
	}
}
