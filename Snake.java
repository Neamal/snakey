package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Set;

import java.util.Random;

import java.awt.Font;

import utilities.GDV5;
import utilities.SoundDriverHo;

public class Snake extends GDV5 {
	
	private static Grid[] board;
	
	Grid g1 = new Grid(300,300);
	
	private Apple a = new Apple(20,20);
	private Serpent s1 = new Serpent(1);
	private Grid h1;
	public static Poison p = new Poison(20,20);
	private int count = 0;
	public static int P1Score = 0;
	boolean start = false;
	public static int level = 1;
	Images image = new Images();
	static Random random = new Random();
	public static SoundDriverHo sound1;
	public static SoundDriverHo sound2;
	private SoundDriverHo sound3;
	public static SoundDriverHo sound4;
	private static int initialFramerate;
	private String[] filenames = new String[1];
	
	public Snake() {
		super();
		filenames[0] = "monch.wav";
		sound1 = new SoundDriverHo(filenames, this);
		filenames[0] = "gameover.wav";
		sound2 = new SoundDriverHo(filenames, this);
		filenames[0] = "natsunosora.wav";
		sound3 = new SoundDriverHo(filenames, this);
		filenames[0] = "lofiintro.wav";
		sound4 = new SoundDriverHo(filenames, this);
		h1 = s1.body.get(0);
		createGrid();
	}

	
	public static void main(String[] args) {
		Snake snake = new Snake();
		snake.start();
	}
	
	public void update() {
		if (level < 0) {
			sound4.play(0);
		}
		if (sound4.isPlaying(0) == false) {
			sound4.play(0);
		}
	}
	
	static void createGrid() {
		int x = 0, y = 0;
		board = new Grid[1200];
		for (int i = 0; i < board.length; i++) {
			board[i] = new Grid(x, y);
			x += board[i].getDimension();
			if (i != 0 && i % 40 == 0) {
				x = 0;
				y += board[i].getDimension();
			}
		}
	}

	void drawGrid(Graphics2D pb) {
		for (Grid t: board) {
			t.draw(pb);
		}
	}

	public void draw(Graphics2D win) {
//		splash screen
		
		if (!start) {
			win.drawImage(image.snake, Snake.getMaxWindowX()/2 - 100, Snake.getMaxWindowY()/2 - 250, 200, 200, null);
			win.setColor(Color.BLACK);
			win.setFont(new Font("Arial", Font.BOLD, 50));
			win.drawString("Snake", Snake.getMaxWindowX()/2 - 60, Snake.getMaxWindowY()/2);
			win.setFont(new Font("Arial", Font.ITALIC, 20));
			win.drawString("Use your keys to move your snake towards the apples", Snake.getMaxWindowX()/2 - 220, Snake.getMaxWindowY()/2 + 40);
			win.drawString("Try to eat as many apples until the snake can't fit the screen", Snake.getMaxWindowX()/2 - 240, Snake.getMaxWindowY()/2 + 60);
			win.setColor(Color.RED);
			win.drawString("DO NOT EAT THE POISON!", Snake.getMaxWindowX()/2 - 140, Snake.getMaxWindowY()/2 + 80);
			win.setColor(Color.BLACK);
			win.drawString("Use 'w' to move up     Use 's' to move down", Snake.getMaxWindowX()/2 - 180, Snake.getMaxWindowY()/2 + 100);
			win.drawString("Use 'a' to move left     Use 'd' to move right", Snake.getMaxWindowX()/2 - 180, Snake.getMaxWindowY()/2 + 120);
			win.setColor(Color.BLACK);
			win.setFont(new Font("Arial", Font.BOLD, 30));
			win.drawString("Made by: Ethan Ongkeko", Snake.getMaxWindowX()/2 - 180, Snake.getMaxWindowY() - 120);
			win.setFont(new Font("Arial", Font.ITALIC, 20));
			win.setColor(Color.BLACK);
			win.drawString("Press '1' to play level 1 | Press '2' to play level 2 | Press '3' to play level 3", 70, GDV5.getMaxWindowY() - 70);
			
		}
//		starts the game
		if (GDV5.KeysTyped[KeyEvent.VK_ENTER]) {
			start = true;
			s1.draw(win);
			win.drawImage(image.snakehead, h1.x, h1.y, 20, 20, null);
			start = true;
			win.setColor(Color.RED);
			win.drawImage(image.apple, a.x, a.y, 20, 20, null);
			win.drawImage(image.powerup, p.x, p.y, 20, 20, null);
			win.setColor(Color.BLACK);
			Font f1 = new Font("Arial", Font.PLAIN, 30);
			win.setFont(f1);
			win.drawString("Score:" + P1Score + " ", GDV5.getMaxWindowX() - 140, 50);
			win.setFont(f1);
			win.drawString("Level: " + (level - 1), 20, 50);
		}
//		level 1
		if (GDV5.KeysTyped[KeyEvent.VK_1]) {
			initialFramerate = 240;
			count++;
			level = 2;
			h1.setHeadDirection();
			if (count % initialFramerate == 0) {
				System.out.println(s1.body.size());
				h1.updateDirection(board);	
				s1.updateBodyDirection(board);
				s1.move();
				s1.stop();
				s1.hitItself();
				s1.eatsPoison();
			}
			
			if (h1.intersects(a)) {
				sound1.play(0);
				a.setLocation((random.nextInt(20) + 1)*20, (random.nextInt(20) + 1) * 20);
				s1.addBlock();
				P1Score += 10;
			}
		}
//		level 2
		if (GDV5.KeysTyped[KeyEvent.VK_2]) {
			initialFramerate = 200;
			count++;
			level = 3;
			h1.setHeadDirection();
			if (count % initialFramerate == 0) {
				System.out.println(s1.body.size());
				h1.updateDirection(board);	
				s1.updateBodyDirection(board);
				s1.move();
				s1.stop();
				s1.hitItself();
				s1.eatsPoison();
			}
			
			if (h1.intersects(a)) {
				sound1.play(0);
				a.setLocation((random.nextInt(20) + 1)*20, (random.nextInt(20) + 1) * 20);
				s1.addBlock();
				P1Score += 10;
			}
			
			if (h1.intersects(p)) {
				sound4.play(0);
				level = 0;
			}
		}
//		level 3
		if (GDV5.KeysTyped[KeyEvent.VK_3]) {
			initialFramerate = 150;
			count++;
			level = 4;
			h1.setHeadDirection();
			if (count % initialFramerate == 0) {
				System.out.println(s1.body.size());
				h1.updateDirection(board);	
				s1.updateBodyDirection(board);
				s1.move();
				s1.stop();
				s1.hitItself();
				s1.eatsPoison();
			}
			
			if (h1.intersects(a)) {
				sound1.play(0);
				a.setLocation((random.nextInt(20) + 1)*20, (random.nextInt(20) + 1) * 20);
				s1.addBlock();
				P1Score += 10;
			}
			if (h1.intersects(p)) {
				sound4.play(0);
				level = 0;
			}
		}
//		restart
		if (GDV5.KeysPressed[KeyEvent.VK_ESCAPE]) { 
			start = false;
			level = 1;
			h1.setDirection(1);
		}
//		game over
		if (level == 0) {
			GDV5.KeysTyped[KeyEvent.VK_ENTER] = false;
			GDV5.KeysTyped[KeyEvent.VK_1] = false;
			GDV5.KeysTyped[KeyEvent.VK_2] = false;
			GDV5.KeysTyped[KeyEvent.VK_3] = false;
			win.setColor(Color.RED);
			win.setFont(new Font("Arial", Font.BOLD, 50));
			win.drawString("Gameover", Snake.getMaxWindowX()/2 - 120, 100);
			win.setFont(new Font("Arial", Font.ITALIC, 40));
			win.drawString("Press ESCAPE to go back to menu", Snake.getMaxWindowX()/2 - 300, 200);
			h1.setLocation(Snake.getMaxWindowX()/2, Snake.getMaxWindowY()/2);
		}
	}
}

