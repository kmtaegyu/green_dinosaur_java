package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class game_screen extends JFrame {
	private int cnt;
	private Image bufferImage;
	private Graphics screenGraphic;

	private ArrayList<Meteor> meteorList = new ArrayList<Meteor>();
	private Meteor meteor;
	private ArrayList<Yosimeteor> yosimeteorList = new ArrayList<Yosimeteor>();
	private Yosimeteor yosimeteor;

	private ArrayList<DooleyAttack> dooleyAttackList = new ArrayList<DooleyAttack>();
	private DooleyAttack dooleyAttack;
	// private Image gameScreen = new ImageIcon("dinosaur_age.png").getImage();
	
	private JLabel laLifeCount,laLifeCount2,laLifeCount3;
	private ImageIcon lifeCounticon;
	
	private JLabel gameScreen;
	private yosi yosi;
	private Crong crong;
	private Dooley dooley;
	
	//public Plyaer player;
	
	public game_screen(String dinosaur) {
		initObject();
		initSetting();
		
		if (dinosaur.equals(Chosen_dinosaur.DINOSAUR1)) {
			yosi = new yosi();
			add(yosi);
			crong = new Crong();
			add(crong);
			
			yosiprocess();
		} else if (dinosaur.equals(Chosen_dinosaur.DINOSAUR2)) {
			crong = new Crong();
			add(crong);
			dooley = new Dooley();
			add(dooley);
			
			dooleyprocess();
		} else if (dinosaur.equals(Chosen_dinosaur.DINOSAUR3)) {
			dooley = new Dooley();
			add(dooley);
			yosi = new yosi();
			add(yosi);
			
			dooleyprocess();
			yosiprocess();
		}
		
		initListener();
		meteorAppear();
		meteorMove();
//		yosiprocess();
//		dooleyprocess();
		setVisible(true);
		
		lifeLaInit();
		this.add(laLifeCount);
		this.add(laLifeCount2);
		this.add(laLifeCount3);
		laLifeCount.setBounds(0, 0, 50, 50);
		laLifeCount2.setBounds(50, 0, 50, 50);
		laLifeCount3.setBounds(100, 0, 50, 50);
		//lifeCounting();
	}

	private void initObject() {
		gameScreen = new JLabel(new ImageIcon("dinosaur_age.png"));
		setContentPane(gameScreen);
	}

	private void initSetting() { 
		setTitle("Shooting Game");
		setUndecorated(true);
		setLayout(null);
		setSize(Test.SCREEN_WIDTH, Test.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		cnt = 0;
	}
	
	private void lifeLaInit() { // 당장 안 써요. 괜히 만들어달라 했나..
		lifeCounticon = new ImageIcon("LifeCount.png");
		laLifeCount = new JLabel(lifeCounticon);
		laLifeCount2 = new JLabel(lifeCounticon);
		laLifeCount3 = new JLabel(lifeCounticon);
	}
	public void lifeCounting() {
		if (yosi.getLife() == 3) {
			laLifeCount.setVisible(true);
			laLifeCount2.setVisible(true);
			laLifeCount3.setVisible(true);
			repaint();
		} else if (yosi.getLife() == 2) {
			laLifeCount.setVisible(true);
			laLifeCount2.setVisible(true);
			laLifeCount3.setVisible(false);
			repaint();
		} else if (yosi.getLife() == 1) {
			laLifeCount.setVisible(true);
			laLifeCount2.setVisible(false);
			laLifeCount3.setVisible(false);
			repaint();
		} else {
			laLifeCount.setVisible(false);
			laLifeCount2.setVisible(false);
			laLifeCount3.setVisible(false);
			repaint();
		}
	}

//	yosi 	-> left(<) right(>)
//	dooley  -> left(a) right(d)
//	crong   -> left(1) right(3)
	private void initListener() {
		addKeyListener(new KeyAdapter() {

			// 키보드 클릭 이벤트 핸들러
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if (!yosi.isLeft() && yosi.getState() == 0)
						yosi.left("yosileft1");
					repaint();
					break;
				case KeyEvent.VK_RIGHT:
					if (!yosi.isRight() && yosi.getState() == 0)
						yosi.right();
					repaint();
					break;
				case KeyEvent.VK_UP:
					yosi.setCrash(true);

						repaint();
					break;
				case KeyEvent.VK_SPACE:
					dooley.setShooting(true);
					break;
				case KeyEvent.VK_A:
					if (!dooley.isLeft() && dooley.getState() == 0)
						dooley.left();
					repaint();
					break;
				case KeyEvent.VK_D:
					if (!dooley.isRight() && dooley.getState() == 0)
						dooley.right();
					repaint();
					break;
				case KeyEvent.VK_W:
					if (!dooley.isUp() && !dooley.isDown() && dooley.getState() == 0)

						repaint();
					break;
				case KeyEvent.VK_1:
					if (!crong.isLeft() && crong.getState() == 0)
						crong.left();
					repaint();
					break;
				case KeyEvent.VK_3:
					if (!crong.isRight() && crong.getState() == 0)
						crong.right();
					repaint();
					break;
				case KeyEvent.VK_5:
					if (!crong.isUp() && !crong.isDown() && crong.getState() == 0)

						repaint();
					break;
				}
			}

			// 키보드 해제 이벤트 핸들러
			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					yosi.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					yosi.setRight(false);
					break;
				 case KeyEvent.VK_SPACE:
	                dooley.setShooting(false);
	                break;
				case KeyEvent.VK_UP:
					 yosi.setCrash(false);
					 repaint();
					 break;
				 case KeyEvent.VK_A:
					 dooley.setLeft(false);
					 break;
				 case KeyEvent.VK_D:
					 dooley.setRight(false);
						break;
				 case KeyEvent.VK_1:
					 crong.setLeft(false);
					 break;
				 case KeyEvent.VK_3:
					 crong.setRight(false);
					 break;		
				}
				
			}
			
		});
	}
//----------------------------------------------------
	private void meteorAppear() { //운석 생성 
		new Thread(() -> {
			try {
				while (true) {
					meteor = new Meteor();
					meteorList.add(meteor);
					add(meteor);
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void meteorMove() { //운석 움직임 
		new Thread(() -> {
			try {
				while (true) {
					for (int i = 0; i < meteorList.size(); i++) {
						meteor = meteorList.get(i);

						if (meteor.getY() >= 500) {
							meteor.setY(40);
							meteor.setyosiCrashstate(false); // 운석 재활용 0으로 초기화
							meteor.setVisible(true); // 운석 보이게 재설정
						}
						meteor.movement();
						yosimeteorCrash(meteor); // 요시먹뱉운석과 운석 충돌 확인
						dooleyAttack(meteor); //둘리가 마법쏘는 코드 
					}
					Thread.sleep(200);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void yosiprocess() { 
 		new Thread(() -> {
			try {
				while (true) {
					yosimeteorAppear();
					yosimeteorMove();
					Thread.sleep(300);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private boolean isYosiCrash(Meteor m) { //요시가 운석과 충돌 
		boolean horizontalcrash = false;
		boolean verticalcrash = false;
		int cnt = 3;
		if (yosi.getX() < m.getX_right() && yosi.getX_right() > m.getX()) { // 수평충돌
			horizontalcrash = true;
			yosi.setLife(--cnt);
		}
		if (yosi.getY() <= m.getY() + 30 && yosi.getY_bottom() > m.getY()) { // 수직충돌
			verticalcrash = true;
			yosi.setLife(--cnt);
		}
		if (verticalcrash && horizontalcrash) {
			yosi.setLife(--cnt);
			return true;
		}
		return false;
	}

	private void yosimeteorAppear() { //요시 먹뱉 운석 생성
		for (int i = 0; i < meteorList.size(); i++) {
			meteor = meteorList.get(i);
			if (yosi.isCrash() && isYosiCrash(meteor) && !meteor.getyosiCrashstate()) {
				meteor.setVisible(false); // 운석 안보이게 처리
				System.out.println("충돌");
				yosi.loselife();
				yosimeteor = new Yosimeteor(yosi);
				yosimeteorList.add(yosimeteor);
				add(yosimeteor);
				meteor.setyosiCrashstate(true); // 요시와 운석 충돌상태
			}
		}
	}

	private void yosimeteorMove() { //요시 먹뱉 운석 움직임 
		for (int i = 0; i < yosimeteorList.size(); i++) {
			yosimeteor = yosimeteorList.get(i);
			if (yosimeteor != null) {
				if (yosimeteor.getY() <= 0) {
					yosimeteor.setVisible(false);
				}
				yosimeteor.movement();
			}
		}
	}

	private boolean isyosimeteorCrash(Yosimeteor ym, Meteor m) { // 요시 먹뱉 운석과 운석 충돌 확인
		boolean horizontalcrash = false;
		boolean verticalcrash = false;
		if (ym.getX() < m.getX_right() && ym.getX_right() > m.getX()) // 수평충돌
			horizontalcrash = true;
		if (ym.getY() <= m.getY() + 30 && ym.getY() + 30 > m.getY()) // 수직충돌
			verticalcrash = true;
		if (verticalcrash && horizontalcrash) {
			return true;
		}
		return false;
	}

	private void yosimeteorCrash(Meteor meteor) { //요시 먹뱉 운석과 운석 충돌 
		for (int i = 0; i < yosimeteorList.size(); i++) {
			yosimeteor = yosimeteorList.get(i);
			if (isyosimeteorCrash(yosimeteor, meteor)) {
				meteor.invalidate();
				meteorList.remove(meteor);
				meteor.setVisible(false);
			}
		}
	}

	private void dooleyprocess() {
		new Thread(() -> {
			try {
				while (true) {
					cnt++;
					DooleyCrash();
					dooleyAttackAppear();
					
					Thread.sleep(10);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	
	private void DooleyCrash() { //둘리와 운석 충돌 확인 
		for (int i = 0; i < meteorList.size(); i++) {
			meteor = meteorList.get(i);
			boolean horizontalcrash = false;
			boolean verticalcrash = false;
			if (dooley.getX() < meteor.getX_right() && dooley.getX_right() > meteor.getX()) // 수평충돌
				horizontalcrash = true;
			if (dooley.getY() <= meteor.getY() + 30 && dooley.getY_bottom() > meteor.getY()) // 수직충돌
				verticalcrash = true;
			if (verticalcrash && horizontalcrash) {
				dooley.loselife();
			}
		}
	}
	

	private void dooleyAttackAppear() {//둘리 공격마법 생성 
		if(dooley.getShooting() && cnt % 50 == 0) {
			dooleyAttack = new DooleyAttack(dooley);
			dooleyAttackList.add(dooleyAttack);
			add(dooleyAttack);
		}
	}
	
	private void dooleyAttack(Meteor meteor) { //둘리 공격마법 움직임 (meteor 스레드에서 실행)
		for (int i = 0; i < dooleyAttackList.size(); i++) {
			dooleyAttack = dooleyAttackList.get(i);
			dooleyAttack.movement();

			if(isdooleymeteorCrash(dooleyAttack, meteor)) {//공격마법과 운석 충돌시 운석 삭제
				meteor.invalidate();
				meteorList.remove(meteor);
				meteor.setVisible(false);
			}
		}
	}
	private boolean isdooleymeteorCrash(DooleyAttack d, Meteor m) { // 둘리 공격마법과 운석 충돌 확인
		boolean horizontalcrash = false;
		boolean verticalcrash = false;
		if (d.getX() < m.getX_right() && d.getX_right() > m.getX()) // 수평충돌
			horizontalcrash = true;
		if (d.getY() <= m.getY() + 30 && d.getY() + 30 > m.getY()) // 수직충돌
			verticalcrash = true;
		if (verticalcrash && horizontalcrash) {
			return true;
		}
		return false;
	}
	
	private void Game_Over() {
		if(yosi.getLife() == 0) {
			new Init();
		}
	}
}
