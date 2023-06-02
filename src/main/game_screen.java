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

	private int item_interval = 0;
	private ArrayList<HP_item> hpList = new ArrayList<HP_item>();
	private HP_item hpItem;
	
	private ArrayList<Star> starList = new ArrayList<Star>();
	private Star star;

	private JLabel laLifeCount, laLifeCount2, laLifeCount3;
	private JLabel laLifeCount4, laLifeCount5, laLifeCount6;
	private JLabel laLifeCount7, laLifeCount8;
	private ImageIcon lifeCounticon;

	private JLabel small_yosi, small_dooley, small_crong;
	private ImageIcon small, small1, small2;

	private JLabel gameScreen;
	private Yosi yosi;
	private Crong crong;
	private Dooley dooley;

	boolean yosi_alive = true;
	boolean dooley_alive = true;
	boolean crong_alive = true;

	// public Plyaer player;

	public game_screen(String dinosaur) {
		initObject();
		initSetting();

		lifeLaInit();

		if (dinosaur.equals(Chosen_dinosaur.DINOSAUR1)) {

			yosi = new Yosi();
			add(yosi);
			crong = new Crong();
			add(crong);

			dooley = new Dooley();
			dooley.setLife(0);

			this.add(small_yosi);
			small_yosi.setBounds(150, 0, 50, 50);
			this.add(small_crong);
			small_crong.setBounds(700, 0, 50, 50);

			laLifeCount7.setBounds(800, 0, 50, 50);
			laLifeCount8.setBounds(750, 0, 50, 50);

			yosiprocess();
			crongprocess();

			new Thread() {
				public void run() {
					while (yosi_alive && crong_alive) {
						try {

							lifeCounting_yosi_crong();
//							Game_Over();
							repaint();
							Thread.sleep(3);
						} catch (Exception e) {
						}
					}
				}
			}.start();

		} else if (dinosaur.equals(Chosen_dinosaur.DINOSAUR2)) {
			crong = new Crong();
			add(crong);
			dooley = new Dooley();
			add(dooley);

			yosi = new Yosi();
			yosi.setLife(0);

			this.add(small_crong);
			small_crong.setBounds(250, 0, 50, 50);
			this.add(small_dooley);
			small_dooley.setBounds(800, 0, 50, 50);

			laLifeCount7.setBounds(150, 0, 50, 50);
			laLifeCount8.setBounds(200, 0, 50, 50);

			dooleyprocess();
			crongprocess();

			new Thread() {
				public void run() {
					while (dooley_alive && crong_alive) {
						try {
							lifeCounting_crong_dooley();
//							Game_Over();
							repaint();
							Thread.sleep(3);
						} catch (Exception e) {
						}
					}
				}
			}.start();
		} else if (dinosaur.equals(Chosen_dinosaur.DINOSAUR3)) {
			dooley = new Dooley();
			add(dooley);
			yosi = new Yosi();
			add(yosi);

			crong = new Crong();
			crong.setLife(0);

			this.add(small_dooley);
			small_dooley.setBounds(150, 0, 50, 50);
			this.add(small_yosi);
			small_yosi.setBounds(800, 0, 50, 50);

			dooleyprocess();
			yosiprocess();

			new Thread() {
				public void run() {
					while (yosi_alive && dooley_alive) {
						try {
							lifeCounting_dooley_yosi();
//							Game_Over();
							repaint();
							Thread.sleep(3);
						} catch (Exception e) {
						}
					}
				}
			}.start();
		}

//		lifeLaInit();

		initListener();

		meteorAppear();
		meteorMove();

		hpMove();

//		yosiprocess();
//		dooleyprocess();
		
		starApper();
		starMove();

		setVisible(true);

////		lifeLaInit();
		this.add(laLifeCount);
		this.add(laLifeCount2);
		this.add(laLifeCount3);
		laLifeCount.setBounds(0, 0, 50, 50);
		laLifeCount2.setBounds(50, 0, 50, 50);
		laLifeCount3.setBounds(100, 0, 50, 50);

		this.add(laLifeCount4);
		this.add(laLifeCount5);
		this.add(laLifeCount6);
		laLifeCount4.setBounds(850, 0, 50, 50);
		laLifeCount5.setBounds(900, 0, 50, 50);
		laLifeCount6.setBounds(950, 0, 50, 50);

//		laLifeCount.setVisible(true);
//		laLifeCount2.setVisible(true);
//		laLifeCount3.setVisible(true);
//		laLifeCount4.setVisible(true);
//		laLifeCount5.setVisible(true);
//		laLifeCount6.setVisible(true);
		this.add(laLifeCount7);
		this.add(laLifeCount8);
	}

//-------------------------------------------------------------------------------------여기까지 생성자
	private void initObject() {
		gameScreen = new JLabel(new ImageIcon("sundown.png"));
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

	private void lifeLaInit() {
		lifeCounticon = new ImageIcon("LifeCount.png");
		laLifeCount = new JLabel(lifeCounticon);
		laLifeCount2 = new JLabel(lifeCounticon);
		laLifeCount3 = new JLabel(lifeCounticon);

		laLifeCount4 = new JLabel(lifeCounticon);
		laLifeCount5 = new JLabel(lifeCounticon);
		laLifeCount6 = new JLabel(lifeCounticon);

		laLifeCount7 = new JLabel(lifeCounticon);
		laLifeCount8 = new JLabel(lifeCounticon);

		small = new ImageIcon("Yosi_Select_small.png");
		small_yosi = new JLabel(small);

		small1 = new ImageIcon("Crong_Select_small.png");
		small_crong = new JLabel(small1);

		small2 = new ImageIcon("Dooley_Select_small.png");
		small_dooley = new JLabel(small2);
	}

	public void lifeCounting_yosi_crong() {
		if (yosi.getLife() == 3) {
			laLifeCount.setVisible(true);
			laLifeCount2.setVisible(true);
			laLifeCount3.setVisible(true);
			System.out.println("요시 생명 3");
			repaint();
		} else if (yosi.getLife() == 2) {
			laLifeCount.setVisible(true);
			laLifeCount2.setVisible(true);
			laLifeCount3.setVisible(false);
			System.out.println("요시 생명 2");
			repaint();
		} else if (yosi.getLife() == 1) {
			laLifeCount.setVisible(true);
			laLifeCount2.setVisible(false);
			laLifeCount3.setVisible(false);
			System.out.println("요시 생명 1");
			repaint();
		} else {
			laLifeCount.setVisible(false);
			laLifeCount2.setVisible(false);
			laLifeCount3.setVisible(false);
			System.out.println("요시 생명 0");
			Game_Over();
			repaint();
		}
		if (crong.getLife() == 5) {
			laLifeCount4.setVisible(true);
			laLifeCount5.setVisible(true);
			laLifeCount6.setVisible(true);
			laLifeCount7.setVisible(true);
			laLifeCount8.setVisible(true);
			System.out.println("크롱 생명 5");
			repaint();
		} else if (crong.getLife() == 4) {
			laLifeCount4.setVisible(true);
			laLifeCount5.setVisible(true);
			laLifeCount6.setVisible(true);
			laLifeCount7.setVisible(true);
			laLifeCount8.setVisible(false);
			System.out.println("크롱 생명 4");
			repaint();
		} else if (crong.getLife() == 3) {
			laLifeCount4.setVisible(true);
			laLifeCount5.setVisible(true);
			laLifeCount6.setVisible(true);
			laLifeCount7.setVisible(false);
			laLifeCount8.setVisible(false);
			System.out.println("크롱 생명 3");
			repaint();
		} else if (crong.getLife() == 2) {
			laLifeCount4.setVisible(false);
			laLifeCount5.setVisible(true);
			laLifeCount6.setVisible(true);
			laLifeCount7.setVisible(false);
			laLifeCount8.setVisible(false);
			System.out.println("크롱 생명 2");
			repaint();
		} else if (crong.getLife() == 1) {
			laLifeCount4.setVisible(false);
			laLifeCount5.setVisible(false);
			laLifeCount6.setVisible(true);
			laLifeCount7.setVisible(false);
			laLifeCount8.setVisible(false);
			System.out.println("크롱 생명 1");
			repaint();
		} else {
			laLifeCount4.setVisible(false);
			laLifeCount5.setVisible(false);
			laLifeCount6.setVisible(false);
			laLifeCount7.setVisible(false);
			laLifeCount8.setVisible(false);
			System.out.println("크롱 생명 0");
			Game_Over();
			repaint();
		}
	}

	public void lifeCounting_crong_dooley() {
		if (crong.getLife() == 5) {
			laLifeCount.setVisible(true);
			laLifeCount2.setVisible(true);
			laLifeCount3.setVisible(true);
			laLifeCount7.setVisible(true);
			laLifeCount8.setVisible(true);
			System.out.println("크롱 생명 5");
			repaint();
		} else if (crong.getLife() == 4) {
			laLifeCount.setVisible(true);
			laLifeCount2.setVisible(true);
			laLifeCount3.setVisible(true);
			laLifeCount7.setVisible(true);
			laLifeCount8.setVisible(false);
			System.out.println("크롱 생명 4");
			repaint();
		} else if (crong.getLife() == 3) {
			laLifeCount.setVisible(true);
			laLifeCount2.setVisible(true);
			laLifeCount3.setVisible(true);
			laLifeCount7.setVisible(false);
			laLifeCount8.setVisible(false);
			System.out.println("크롱 생명 3");
			repaint();
		} else if (crong.getLife() == 2) {
			laLifeCount.setVisible(true);
			laLifeCount2.setVisible(true);
			laLifeCount3.setVisible(false);
			laLifeCount7.setVisible(false);
			laLifeCount8.setVisible(false);
			System.out.println("크롱 생명 2");
			repaint();
		} else if (crong.getLife() == 1) {
			laLifeCount.setVisible(true);
			laLifeCount2.setVisible(false);
			laLifeCount3.setVisible(false);
			laLifeCount7.setVisible(false);
			laLifeCount8.setVisible(false);
			System.out.println("크롱 생명 1");
			repaint();
		} else {
			laLifeCount.setVisible(false);
			laLifeCount2.setVisible(false);
			laLifeCount3.setVisible(false);
			laLifeCount7.setVisible(false);
			laLifeCount8.setVisible(false);
			System.out.println("크롱 생명 0");
			Game_Over();
			repaint();
		}
		if (dooley.getLife() == 3) {
			laLifeCount4.setVisible(true);
			laLifeCount5.setVisible(true);
			laLifeCount6.setVisible(true);
			System.out.println("둘리 생명 3");
		} else if (dooley.getLife() == 2) {
			laLifeCount4.setVisible(true);
			laLifeCount5.setVisible(true);
			laLifeCount6.setVisible(false);
			System.out.println("둘리 생명 2");
		} else if (dooley.getLife() == 1) {
			laLifeCount4.setVisible(true);
			laLifeCount5.setVisible(false);
			laLifeCount6.setVisible(false);
			System.out.println("둘리 생명 1");
		} else {
			laLifeCount4.setVisible(false);
			laLifeCount5.setVisible(false);
			laLifeCount6.setVisible(false);
			System.out.println("둘리 생명 0");
			Game_Over();
		}
	}

	public void lifeCounting_dooley_yosi() {
		if (dooley.getLife() == 3) {
			laLifeCount.setVisible(true);
			laLifeCount2.setVisible(true);
			laLifeCount3.setVisible(true);
			System.out.println("둘리 생명 3");
			repaint();
		} else if (dooley.getLife() == 2) {
			laLifeCount.setVisible(true);
			laLifeCount2.setVisible(true);
			laLifeCount3.setVisible(false);
			System.out.println("둘리 생명 2");
			repaint();
		} else if (dooley.getLife() == 1) {
			laLifeCount.setVisible(true);
			laLifeCount2.setVisible(false);
			laLifeCount3.setVisible(false);
			System.out.println("둘리 생명 1");
			repaint();
		} else {
			laLifeCount.setVisible(false);
			laLifeCount2.setVisible(false);
			laLifeCount3.setVisible(false);
			System.out.println("둘리 생명 0");
			Game_Over();
			repaint();
		}
		if (yosi.getLife() == 3) {
			laLifeCount4.setVisible(true);
			laLifeCount5.setVisible(true);
			laLifeCount6.setVisible(true);
			System.out.println("요시 생명 3");
		} else if (yosi.getLife() == 2) {
			laLifeCount4.setVisible(true);
			laLifeCount5.setVisible(true);
			laLifeCount6.setVisible(false);
			System.out.println("요시 생명 2");
		} else if (yosi.getLife() == 1) {
			laLifeCount4.setVisible(true);
			laLifeCount5.setVisible(false);
			laLifeCount6.setVisible(false);
			System.out.println("요시 생명 1");
		} else {
			laLifeCount4.setVisible(false);
			laLifeCount5.setVisible(false);
			laLifeCount6.setVisible(false);
			System.out.println("요시 생명 0");
			Game_Over();
		}
	}

//	dooley 	-> left(<) right(>)
//	yosi  -> left(a) right(d)
//	crong   -> left(1) right(3)
	private void initListener() {
		addKeyListener(new KeyAdapter() {

			// 키보드 클릭 이벤트 핸들러
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				//yosi
				case KeyEvent.VK_A:
					if (!yosi.isLeft() && yosi.getState() == 0)
						yosi.left();
					repaint();
					break;
				case KeyEvent.VK_D:
					if (!yosi.isRight() && yosi.getState() == 0)
						yosi.right();
					repaint();
					break;
				case KeyEvent.VK_ESCAPE:
					yosi.setCrash(true);
					repaint();
					break;
				case KeyEvent.VK_W:
					if (!yosi.isUp() && !yosi.isDown() && yosi.getState() == 0)
						yosi.up();
						repaint();
					break;
				//dooley
				case KeyEvent.VK_SPACE:
					dooley.setShooting(true);
					break;
				case KeyEvent.VK_LEFT:
					if (!dooley.isLeft() && dooley.getState() == 0)
						dooley.left();
					repaint();
					break;
				case KeyEvent.VK_RIGHT:
					if (!dooley.isRight() && dooley.getState() == 0)
						dooley.right();
					repaint();
					break;
				case KeyEvent.VK_UP:
					if (!dooley.isUp() && !dooley.isDown() && dooley.getState() == 0)
						dooley.up();
					repaint();
					break;
				//crong
				case KeyEvent.VK_NUMPAD4:
					if (!crong.isLeft() && crong.getState() == 0)
						crong.left();
					repaint();
					break;
				case KeyEvent.VK_NUMPAD6:
					if (!crong.isRight() && crong.getState() == 0)
						crong.right();
					repaint();
					break;
				case KeyEvent.VK_NUMPAD8:
					if (!crong.isUp() && !crong.isDown() && crong.getState() == 0)
						crong.up();
					repaint();
					break;
				}
			}

			// 키보드 해제 이벤트 핸들러
			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				//yosi
				case KeyEvent.VK_A:
					yosi.setLeft(false);
					break;
				case KeyEvent.VK_D:
					yosi.setRight(false);
					break;
				case KeyEvent.VK_ESCAPE:
					yosi.setCrash(false);
					repaint();
					break;
				//dooley
				case KeyEvent.VK_SPACE:
					dooley.setShooting(false);
					break;
				case KeyEvent.VK_LEFT:
					dooley.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					dooley.setRight(false);
					break;
				//crong
				case KeyEvent.VK_NUMPAD4:
					crong.setLeft(false);
					break;
				case KeyEvent.VK_NUMPAD6:
					crong.setRight(false);
					break;
				}

			}

		});
	}

//-------------------------------------------------------
	private void starApper() {
		new Thread(() -> {
			try {
				while (true) {
					star = new Star();
					starList.add(star);
					add(star);
					Thread.sleep(6000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
	private void starMove() { // 운석 움직임
		new Thread(() -> {
			try {
				while (true) {
					for (int i = 0; i < starList.size(); i++) {
						star = starList.get(i);

						if (star.getY() >= 440) {
							star.setY(40);
							star.setyosiCrashstate(false); // 운석 재활용 0으로 초기화
							
							//delete check
							star.setCrong_crashstate(false);
							star.setDooley_crashstate(false);
							
							star.setVisible(true); // 운석 보이게 재설정
						}
						star.movement();
//						yosimeteorCrash(star); // 요시먹뱉운석과 운석 충돌 확인
//						dooleyAttack(star); // 둘리가 마법쏘는 코드
					}
					Thread.sleep(200);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

//----------------------------------------------------
	private void meteorAppear() { // 운석 생성
		new Thread(() -> {
			try {
				while (true) {
					item_interval++;
					hpAppear();

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

	private void meteorMove() { // 운석 움직임
		new Thread(() -> {
			try {
				while (true) {
					for (int i = 0; i < meteorList.size(); i++) {
						meteor = meteorList.get(i);

						if (meteor.getY() >= 440) {
							meteorList.remove(meteor);
							meteor.setVisible(false);
//							meteor.setY(40);
//							meteor.setyosiCrashstate(false); // 운석 재활용 0으로 초기화
//
//							// delete check
//							meteor.setCrong_crashstate(false);
//							meteor.setDooley_crashstate(false);
//
//							meteor.setVisible(true); // 운석 보이게 재설정
						}
						meteor.movement();
						yosimeteorCrash(meteor); // 요시먹뱉운석과 운석 충돌 확인
						dooleyAttack(meteor); // 둘리가 마법쏘는 코드
					}

					Thread.sleep(200);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

//------------------------------crong-----------------------------------------
	private void crongprocess() {
		new Thread(() -> {
			try {
				while (true) {
					// isCrongCrash();
					Crong_Crash();
					Crong_Star_Crash();
					Thread.sleep(300);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private boolean isCrongCrash(Meteor m) { // 요시가 운석과 충돌
		boolean horizontalcrash = false;
		boolean verticalcrash = false;
		if (crong.getX()+50 < m.getX_right() && crong.getX_right()-50 > m.getX()) { // 수평충돌
			horizontalcrash = true;
		}
		if (crong.getY()+50 <= m.getY() + 30 && crong.getY_bottom()-50 > m.getY()) { // 수직충돌
			verticalcrash = true;
		}
		if (verticalcrash && horizontalcrash) {
			// crong.loselife();
			return true;
		}
		return false;
	}

	private void Crong_Crash() {
		for (int i = 0; i < meteorList.size(); i++) {
			meteor = meteorList.get(i);
			if (isCrongCrash(meteor) && !meteor.isCrong_crashstate()) {
				meteorList.remove(i);
				crong.loselife();
				meteor.setVisible(false); // 운석 안보이게 처리

				// System.out.println(crong.getLife());
				// yosi.loselife();
				// meteor.setyosiCrashstate(true); // 요시와 운석 충돌상태
				meteor.setCrong_crashstate(true);
			}
		}
	}
	
	private boolean isCrong_Star_Crash(Star s) { // 요시가 운석과 충돌
		boolean horizontalcrash = false;
		boolean verticalcrash = false;
		if (crong.getX() < s.getX_right() && crong.getX_right() > s.getX()) { // 수평충돌
			horizontalcrash = true;
		}
		if (crong.getY() <= s.getY() + 30 && crong.getY_bottom() > s.getY()) { // 수직충돌
			verticalcrash = true;
		}
		if (verticalcrash && horizontalcrash) {
			//crong.loselife();
			return true;
		}
		return false;
	}
	private void Crong_Star_Crash() {
		for (int i = 0; i < starList.size(); i++) {
			star = starList.get(i);
			if (isCrong_Star_Crash(star) && !star.isCrong_crashstate()) {
				starList.remove(i);
				crong.pluslife();
				star.setVisible(false); // 운석 안보이게 처리
				
				//System.out.println(crong.getLife());
				// yosi.loselife();
				//meteor.setyosiCrashstate(true); // 요시와 운석 충돌상태
				star.setCrong_crashstate(true);
			}
		}
	}

//------------------------------yosi-----------------------------------------
	private void yosiprocess() {
		new Thread(() -> {
			try {
				while (true) {
					yosimeteorAppear();
					yosimeteorMove();
					Yosi_Star_Crash();
					Thread.sleep(300);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private boolean isYosiCrash(Meteor m) { // 요시가 운석과 충돌
		boolean horizontalcrash = false;
		boolean verticalcrash = false;
		int cnt = 3;
		if (yosi.getX() < m.getX_right() && yosi.getX_right() > m.getX()) { // 수평충돌
			horizontalcrash = true;
			// yosi.setLife(yosi.getLife() - 1);
//			yosi.loselife();
		}
		if (yosi.getY() <= m.getY() + 30 && yosi.getY_bottom() > m.getY()) { // 수직충돌
			verticalcrash = true;
//			yosi.setLife(yosi.getLife() - 1);
//			yosi.loselife();
		}
		if (verticalcrash && horizontalcrash) {
//			yosi.setLife(yosi.getLife() - 1);
//			yosi.loselife();
			return true;
		}
		return false;
	}

	private void yosimeteorAppear() { // 요시 먹뱉 운석 생성
		for (int i = 0; i < meteorList.size(); i++) {
			meteor = meteorList.get(i);
			if (!yosi.isCrash() && isYosiCrash(meteor) && !meteor.getyosiCrashstate()) {
				yosi.loselife();
				meteor.setVisible(false); // 운석 안보이게 처리
				System.out.println(yosi.getLife());
				// yosi.loselife();
				meteor.setyosiCrashstate(true); // 요시와 운석 충돌상태
			} else if (yosi.isCrash() && isYosiCrash(meteor) && !meteor.getyosiCrashstate()) {
				meteor.setVisible(false); // 운석 안보이게 처리
				// System.out.println("충돌");
				// yosi.loselife();
				yosimeteor = new Yosimeteor(yosi);
				yosimeteorList.add(yosimeteor);
				add(yosimeteor);
				meteor.setyosiCrashstate(true); // 요시와 운석 충돌상태
			}
		}
	}

	private void yosimeteorMove() { // 요시 먹뱉 운석 움직임
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
		boolean horizontalcrash = false; // 수평
		boolean verticalcrash = false; // 수직
		if (ym.getX() < m.getX_right() && ym.getX_right() > m.getX()) // 수평충돌
			horizontalcrash = true;
		if (ym.getY() <= m.getY() + 30 && ym.getY() + 30 > m.getY()) // 수직충돌
			verticalcrash = true;
		if (verticalcrash && horizontalcrash) {
			return true;
		}
		return false;
	}

	private void yosimeteorCrash(Meteor meteor) { // 요시 먹뱉 운석과 운석 충돌
		for (int i = 0; i < yosimeteorList.size(); i++) {
			yosimeteor = yosimeteorList.get(i);
			if (isyosimeteorCrash(yosimeteor, meteor)) {
				meteor.invalidate();
				meteorList.remove(meteor);
				meteor.setVisible(false);
			}
		}
	}
	
	private boolean isyosi_Star_Crash(Star s) { // 요시가 운석과 충돌
		boolean horizontalcrash = false;
		boolean verticalcrash = false;
		if (yosi.getX() < s.getX_right() && yosi.getX_right() > s.getX()) { // 수평충돌
			horizontalcrash = true;
		}
		if (yosi.getY() <= s.getY() + 30 && yosi.getY_bottom() > s.getY()) { // 수직충돌
			verticalcrash = true;
		}
		if (verticalcrash && horizontalcrash) {
			//crong.loselife();
			return true;
		}
		return false;
	}
	private void Yosi_Star_Crash() {
		for (int i = 0; i < starList.size(); i++) {
			star = starList.get(i);
			if (isyosi_Star_Crash(star) && !star.getyosiCrashstate()) {
				starList.remove(i);
				yosi.pluslife();
				star.setVisible(false); // 운석 안보이게 처리
				
				//System.out.println(crong.getLife());
				// yosi.loselife();
				//meteor.setyosiCrashstate(true); // 요시와 운석 충돌상태
				star.setyosiCrashstate(true);
			}
		}
	}

//---------------------------------------dooley---------------------------------------------------------

	private void dooleyprocess() {
		new Thread(() -> {
			try {
				while (true) {
					cnt++;
					DooleyCrash();
					dooleyAttackAppear();
					Dooley_Star_Crash();

					Thread.sleep(10);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void DooleyCrash() { // 둘리와 운석 충돌 확인
		for (int i = 0; i < meteorList.size(); i++) {
			meteor = meteorList.get(i);
			boolean horizontalcrash = false;
			boolean verticalcrash = false;
			if (dooley.getX() < meteor.getX_right() && dooley.getX_right() > meteor.getX()) // 수평충돌
				horizontalcrash = true;
//				dooley.loselife();
			if (dooley.getY() <= meteor.getY() + 30 && dooley.getY_bottom() > meteor.getY()) // 수직충돌
				verticalcrash = true;
//				dooley.loselife();
			if (verticalcrash && horizontalcrash) {
				System.out.println("둘리 충돌, life : " + dooley.getLife());
				dooley.loselife();

				meteor.setVisible(false);
				meteorList.remove(meteor);
			}
		}
	}

	private void dooleyAttackAppear() {// 둘리 공격마법 생성
		if (dooley.getShooting() && cnt % 50 == 0) {
			dooleyAttack = new DooleyAttack(dooley);
			dooleyAttackList.add(dooleyAttack);
			add(dooleyAttack);
		}
	}

	private void dooleyAttack(Meteor meteor) { // 둘리 공격마법 움직임 (meteor 스레드에서 실행)
		for (int i = 0; i < dooleyAttackList.size(); i++) {
			dooleyAttack = dooleyAttackList.get(i);
			dooleyAttack.movement();

			if (isdooleymeteorCrash(dooleyAttack, meteor)) {// 공격마법과 운석 충돌시 운석 삭제
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
	
	private boolean isdooley_Star_Crash(Star s) { // 요시가 운석과 충돌
		boolean horizontalcrash = false;
		boolean verticalcrash = false;
		if (dooley.getX() < s.getX_right() && dooley.getX_right() > s.getX()) { // 수평충돌
			horizontalcrash = true;
		}
		if (dooley.getY() <= s.getY() + 30 && dooley.getY_bottom() > s.getY()) { // 수직충돌
			verticalcrash = true;
		}
		if (verticalcrash && horizontalcrash) {
			//crong.loselife();
			return true;
		}
		return false;
	}
	private void Dooley_Star_Crash() {
		for (int i = 0; i < starList.size(); i++) {
			star = starList.get(i);
			if (isdooley_Star_Crash(star) && !star.isDooley_crashstate()) {
				starList.remove(i);
				dooley.pluslife();
				star.setVisible(false); // 운석 안보이게 처리
				
				//System.out.println(crong.getLife());
				// yosi.loselife();
				//meteor.setyosiCrashstate(true); // 요시와 운석 충돌상태
				star.setDooley_crashstate(true);
			}
		}
	}

	// ----------------------------------------------------------------item

	private void hpAppear() {
		if (item_interval == 5) {
			hpItem = new HP_item();
			hpList.add(hpItem);
			add(hpItem);

			item_interval = 0;
		}
	}

	private void hpMove() {
		new Thread(() -> {
			try {
				while (true) {
					for (int i = 0; i < hpList.size(); i++) {
						hpItem = hpList.get(i);
						hpItem.movement();

						if (hpItem.getY() >= 440) {
							hpList.remove(i);
							hpItem.setVisible(false);
						}

						DooleyHP(hpItem);
						YosiHP(hpItem);
						CrongHP(hpItem);

						hpitem(hpItem);

						yosiattackHP(hpItem);
						dooleyattackHP(hpItem);
					}

					Thread.sleep(200);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private boolean DooleyHP(HP_item hpItem) { // 둘리와 hp아이템 충돌 확인
		boolean horizontalcrash = false;
		boolean verticalcrash = false;
		if (dooley.getX() < hpItem.getX_right() && dooley.getX_right() > hpItem.getX()) // 수평충돌
			horizontalcrash = true;
		if (dooley.getY() <= hpItem.getY_bottom() && dooley.getY_bottom() > hpItem.getY()) // 수직충돌
			verticalcrash = true;

		if (verticalcrash && horizontalcrash) {
			hpList.remove(hpItem);
			hpItem.setVisible(false);
			return true;
		} else
			return false;
	}

	private boolean YosiHP(HP_item hpItem) { // 요시와 hp아이템 충돌 확인
		boolean horizontalcrash = false;
		boolean verticalcrash = false;
		if (yosi.getX() < hpItem.getX_right() && yosi.getX_right() > hpItem.getX()) // 수평충돌
			horizontalcrash = true;
		if (yosi.getY() <= hpItem.getY_bottom() && yosi.getY_bottom() > hpItem.getY()) // 수직충돌
			verticalcrash = true;

		if (verticalcrash && horizontalcrash) {
			hpList.remove(hpItem);
			hpItem.setVisible(false);
			return true;
		} else
			return false;
	}

	private boolean CrongHP(HP_item hpItem) { // 크롱과 hp아이템 충돌 확인
		boolean horizontalcrash = false;
		boolean verticalcrash = false;
		if (crong.getX()+50 < hpItem.getX_right() && crong.getX_right()-50 > hpItem.getX()) // 수평충돌
			horizontalcrash = true;
		if (crong.getY()+50 <= hpItem.getY_bottom() && crong.getY_bottom()-50 > hpItem.getY()) // 수직충돌
			verticalcrash = true;

		if (verticalcrash && horizontalcrash) {
			hpList.remove(hpItem);
			hpItem.setVisible(false);
			return true;
		} else
			return false;
	}

	private void hpitem(HP_item i) {
		if (i.getMode() == 0) { // mode=0 : minus
			if (YosiHP(i)) {
				if (crong.getLife() > 0) {
					crong.loselife();
				} else if (dooley.getLife() > 0) {
					dooley.loselife();
				}
			} else if (DooleyHP(i)) {
				if (yosi.getLife() > 0) {
					yosi.loselife();
				} else if (crong.getLife() > 0) {
					crong.loselife();
				}
			} else if (CrongHP(i)) {
				if (yosi.getLife() > 0) {
					yosi.loselife();
				} else if (dooley.getLife() > 0) {
					dooley.loselife();
				}
			}
		} else { // mode=1 : plus
			if (YosiHP(i)) {
				if (crong.getLife() > 0) {
					crong.pluslife();
				} else if (dooley.getLife() > 0) {
					dooley.pluslife();
				}
			} else if (DooleyHP(i)) {
				if (yosi.getLife() > 0) {
					yosi.pluslife();
				} else if (crong.getLife() > 0) {
					crong.pluslife();
				}
			} else if (CrongHP(i)) {
				if (yosi.getLife() > 0) {
					yosi.pluslife();
				} else if (dooley.getLife() > 0) {
					dooley.pluslife();
				}
			}
		}
	}

	private void yosiattackHP(HP_item it) { // 요시 먹뱉 운석과 아이템 충돌 확인
		for (int i = 0; i < yosimeteorList.size(); i++) {
			yosimeteor = yosimeteorList.get(i);
			boolean horizontalcrash = false; // 수평
			boolean verticalcrash = false; // 수직
			if (yosimeteor.getX() < it.getX_right() && yosimeteor.getX_right() > it.getX()) // 수평충돌
				horizontalcrash = true;
			if (yosimeteor.getY() <= it.getY() + 30 && yosimeteor.getY() + 30 > it.getY()) // 수직충돌
				verticalcrash = true;
			if (verticalcrash && horizontalcrash) {
				yosimeteorList.remove(i);
				yosimeteor.setVisible(false);
				hpList.remove(it);
				it.setVisible(false);
			}
		}
	}

	private void dooleyattackHP(HP_item it) { // 둘리 공격마법과 아이템 충돌 확인
		for (int i = 0; i < dooleyAttackList.size(); i++) {
			dooleyAttack = dooleyAttackList.get(i);
			boolean horizontalcrash = false;
			boolean verticalcrash = false;
			if (dooleyAttack.getX() < it.getX_right() && dooleyAttack.getX_right() > it.getX()) // 수평충돌
				horizontalcrash = true;
			if (dooleyAttack.getY() <= it.getY() + 30 && dooleyAttack.getY() + 30 > it.getY()) // 수직충돌
				verticalcrash = true;
			if (verticalcrash && horizontalcrash) {
				dooleyAttackList.remove(i);
				dooleyAttack.setVisible(false);
				hpList.remove(it);
				it.setVisible(false);
			}
		}
	}

	// ----------------------------------------------------------------

	private void Game_Over() {
		if (yosi.getLife() <= 0) {
			yosi_alive = false;
			System.out.println("yosi die");
		}

		if (dooley.getLife() <= 0) {
			dooley_alive = false;
			System.out.println("dooley die");
		}

		if (crong.getLife() <= 0) {
			crong_alive = false;
			System.out.println("crong die");
		}
		if (yosi.getLife() <= 0 || dooley.getLife() <= 0 || crong.getLife() <= 0) {
			System.out.println("game_over");

//			new Init();
			if (yosi.getLife() == 0 && dooley.getLife() == 0) {
				System.out.println("crong 생성");
				new Winner_Panel("crong");
			} else if (crong.getLife() == 0 && yosi.getLife() == 0) {
				System.out.println("둘리 생성");
				new Winner_Panel("dooley");
			} else if (dooley.getLife() == 0 && crong.getLife() == 0) {
				System.out.println("요시 생성");
				new Winner_Panel("yosi");
			}
//			getContentPane().removeAll();
//			dispose();
//			System.exit(0);
		}

	}
}
