package main;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Dooley extends JLabel{
	public final int SIZE = 100;
	//위치상태
	private int x;
	private int y;
	private int x_right;
	private int y_bottom;
	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	private boolean shooting;
	
	private boolean moving=false;
	
	private ImageIcon playerR1, playerR2, playerL1, playerL2;//,playerRdie, playerLdie;
	private final int SPEED = 4;
	private final int JUMPSPEED = 2;
	
	private int state = 0; // 0 : live , 1 : die
	private int life=3;

	
	public int getX_right() {
		return x_right;
	}
	public int getY_bottom() {
		return y_bottom;
	}
	public void setX_right(int x_right) {
		this.x_right = x_right;
	}
	public void setHeight(int y_bottom) {
		this.y_bottom = y_bottom;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}
	public boolean getShooting() {
		return shooting;
	}
	
	public Dooley() {
		initObject();
		initSetting();
	}
	
	
	private void initObject() {
		
		playerR1 = new ImageIcon("dooleyright.png");
		playerR2 = new ImageIcon("dooleyright2.png");
		playerL1 = new ImageIcon("dooleyleft.png");
		playerL2 = new ImageIcon("dooleyleft2.png");
		Image imager1 = playerR1.getImage();
		Image imager2 = playerR2.getImage();
		Image imagel1 = playerL1.getImage();
		Image imagel2 = playerL2.getImage();
		Image newing1 = imager1.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		Image newing2 = imager2.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		Image newing3 = imagel1.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		Image newing4 = imagel2.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
		playerR1 = new ImageIcon(newing1);
		playerR2 = new ImageIcon(newing2);
		playerL1 = new ImageIcon(newing3);
		playerL2 = new ImageIcon(newing4);
		imager1 = playerR1.getImage();
		imager2 = playerR2.getImage();
		imagel1 = playerL1.getImage();
		imagel2 = playerL2.getImage();
	}

	
	private void initSetting() {
		x = 80;
		y = 380;
		x_right = x + SIZE;
		y_bottom = y + SIZE;
		left = false;
		right = false;
		up = false;
		down = false;
//		life = 5;
		shooting = false;
		setIcon(playerR1);
		setSize(SIZE, SIZE);
		setLocation(x, y);
	}
	
	public void loselife() {
		life -= 1;
	}
	
	public void left() {
		left = true;
		new Thread(()-> {
			while(left && getState() == 0) {
				if(moving==true) {
					setIcon(playerL1);
					moving=false;
				}
				else {
					setIcon(playerL2);
					moving=true;
				}
//				setIcon(playerL1);
				//sleep(100);
				//setIcon(playerL2);
				if(x <= 0) {
					x = 0;
				}
				x = x - SPEED;
				x_right = x + SIZE;
				setLocation(x, y);
				try {
					Thread.sleep(10); // 0.01초
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 

			}
		}).start();
	}

	public void right() {
		right = true;
		new Thread(()-> {
			while(right && getState() == 0) {
				if(moving==true) {
					setIcon(playerR1);
					moving=false;
				}
				else {
					setIcon(playerR2);
					moving=true;
				}
//				setIcon(playerR1);
				//setIcon(playerR2);
				if(x >= 900) {
					x = 900;
				}
				x = x + SPEED;
				x_right = x + SIZE;
				setLocation(x, y);
				try {
					Thread.sleep(10); // 0.01초
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
		}).start();
	}

	public void up() {
		//System.out.println("up");
		up = true;
		new Thread(()->{
			for(int i=0; i<130/JUMPSPEED; i++) {
				y = y - JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			up = false;
			down();
			
		}).start();
	}

	public void down() {
		//System.out.println("down");
		down = true;
		new Thread(()->{
			while(down) {
				y = y + JUMPSPEED;
				setLocation(x, y);
				
				if(y>=380) {
					break;
				}
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			down=false;
		}).start();
	}
	
	public int getLife() {
		// TODO Auto-generated method stub
		return life;
	}
	public void setLife(int i) {
		life=i;		
	}
	public void pluslife() {
		if(getLife()<3)
			life+=1;
	}
}
