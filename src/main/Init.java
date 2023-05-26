package main;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Init extends JFrame{
	
	//private ImageIcon startIcon = new ImageIcon("SelectPlane.png");
	//private Image startimg = startIcon.getImage();
	
	Change_Screen cs = new Change_Screen();
	
	private JLabel gameScreen;
	private Init me = this;
	public game_screen Game_Screen;
	public Player player;
	
	private Dooley dooley;
	private yosi yosi;
	private Crong crong;
	
	private ImageIcon player1Icon, player2Icon, player3Icon; // 플레이어 기체 이미지
	private ImageIcon planeDetailIcon1, planeDetailIcon2, planeDetailIcon3; // 기체 상세설명 이미지
	private ImageIcon bigPlayer1icon, bigPlayer2icon, bigPlayer3icon; // 버튼 누를시 커지는 이미지
	private ImageIcon selectPlaneIcon = new ImageIcon("SelectPlane.png");
	private Image selectPlaneImg = selectPlaneIcon.getImage();
	
	JLabel jl = new JLabel("click your dinosaur");
	
	public Init() {
		initObject();
		setting();
		setVisible(true);
		
		player1Icon = new ImageIcon("Yosi_Select.png");
		player2Icon = new ImageIcon("Crong_Select.png");
		player3Icon = new ImageIcon("Dooley_Select.png");

//		bigPlayer1icon = new ImageIcon("yosi.png");
//		bigPlayer2icon = new ImageIcon("crong.png");
//		bigPlayer3icon = new ImageIcon("dooley.png");
		
		planeDetailIcon1 = new ImageIcon("Yosi_Select.png");
		planeDetailIcon2 = new ImageIcon("Crong_Select.png");
		planeDetailIcon3 = new ImageIcon("Dooley_Select.png");

		JButton btn1 = new JButton("", player1Icon);
		JButton btn2 = new JButton("", player2Icon);
		JButton btn3 = new JButton("", player3Icon);
		JLabel planeImg = new JLabel("");

		// 버튼 테두리 없음
		btn1.setBorderPainted(false);
		btn2.setBorderPainted(false);
		btn3.setBorderPainted(false);

		// 버튼 채우기 없음
		btn1.setContentAreaFilled(false);
		btn2.setContentAreaFilled(false);
		btn3.setContentAreaFilled(false);

		// 버튼 투명
		btn1.setOpaque(false);
		btn2.setOpaque(false);
		btn3.setOpaque(false);
		
		//yosi
		btn1.addMouseListener(new MouseAdapter() { // 버튼 클릭 리스너
			@Override
			public void mousePressed(MouseEvent e) {
				//batch(Chosen_dinosaur.DINOSAUR1);
				//Game_Screen.choose_dinosaur("yosi");
				new game_screen("yosi");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				planeImg.setIcon(planeDetailIcon1);
				btn1.setSize(100, 143);
				//btn1.setIcon(bigPlayer1icon);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				planeImg.setIcon(null);
				btn1.setSize(100, 143);
				btn1.setIcon(player1Icon);
			}
		});
		//crong
		btn2.addMouseListener(new MouseAdapter() { // 버튼 클릭 리스너
			@Override
			public void mousePressed(MouseEvent e) {
				//Game_Screen.choose_dinosaur("crong");
				new game_screen("crong");
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				planeImg.setIcon(planeDetailIcon2);
				btn2.setSize(100, 143);
				//btn2.setIcon(bigPlayer2icon);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				planeImg.setIcon(null);
				btn2.setSize(100, 143);
				btn2.setIcon(player2Icon);
			}
		});
		//dooley
		btn3.addMouseListener(new MouseAdapter() { // 버튼 클릭 리스너
			@Override
			public void mousePressed(MouseEvent e) {
				
				//Game_Screen.choose_dinosaur("dooley");
				new game_screen("dooley");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				planeImg.setIcon(planeDetailIcon3);
				btn3.setSize(100, 143);
				//btn3.setIcon(bigPlayer3icon);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				planeImg.setIcon(null);
				btn3.setSize(100, 143);
				btn3.setIcon(player3Icon);
			}
		});

		btn1.setBounds(200, 300, 100, 143);
		btn2.setBounds(500, 300, 100, 143);
		btn3.setBounds(800, 300, 100, 143);
		planeImg.setBounds(450, 100, 200, 200);

		//this.add(planeImg);
		this.add(btn1);
		this.add(btn2);
		this.add(btn3);
		
		//this.add(jl);
		this.getContentPane().setLayout(null);
		JLabel lbl = new JLabel();
		lbl.setBounds(375,580,200,30);
		lbl.setText("click your favorite dinosaur");
		this.getContentPane().add(lbl);
	}
	public void initObject() {
		gameScreen = new JLabel(new ImageIcon("dinosaur_age.png"));
		setContentPane(gameScreen);
	}
	public void setting() {
		setTitle("choice your dinosaur");
		setUndecorated(true);
		//setLayout(null);
		setSize(Test.SCREEN_WIDTH, Test.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		//JLabel label = new JLabel("click your character",JLabel.CENTER);
	}
	public static void main(String[] args) {
		new Init();
	}
}
