package snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

import utilities.GDV5;

public class Serpent extends Rectangle{
	
	ArrayList<Grid> body;
	
	public Serpent(int size) {
		body = new ArrayList<Grid>();
		body.add(new Grid(GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY()/2));
		body.get(0).setDirection(1);
		addBlock(size - 1, body.get(0).getDirection());
	}
	
	public void addBlock () {
		int x = (int)body.get(body.size()-1).getX();
		int y = (int)body.get(body.size()-1).getY();
		int boxSize = (int)body.get(body.size()-1).getDimension();
		int direct = (int)body.get(body.size()-1).getDirection();
		if (direct == 1) x += boxSize;
		if (direct == 2) y += boxSize;
		if (direct == 3) x -= boxSize;
		if (direct == 4) y -= boxSize;
		body.add(new Grid(x, y, direct));
	}
	
	public void addBlock (int size) {
		int x = (int)body.get(0).getX();
		int y = (int)body.get(0).getY();
		int boxSize = body.get(0).getDimension();
		int direct = body.get(0).getDirection();
		for (int i = 0; i < size; i++) {
			x += boxSize;
			body.add(new Grid(x, y, direct));
		}
	}
	
	public void addBlock (int size, int direction) {
		int x = (int)body.get(0).getX();
		int y = (int)body.get(0).getY();
		for (int i = 0; i < size; i++) {
			body.add(new Grid(x + body.get(0).getDimension(), y, direction));
			x += body.get(0).getDimension();
		}
	}
//	updates the direct of the body to the head and the body parts before it.
	public void updateBodyDirection(Grid[] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 1; j < body.size(); j++) {
			if (body.get(j).getX() == board[i].getX() && body.get(j).getY() == board[i].getY()) {
				body.get(j).setDirection(board[i].getDirection());
				}
			}
		}
	}
	
	public void draw(Graphics2D pb) {
		for(Grid t: body) {
			t.fill(pb);
		}
		
	}
	
	public void move() {
		for (Grid t: body) {
			t.move();
		}
	}
	
//	if the snake hits the walls, this function goes through a for loop which removes every part of the snake except for the head.
	public void stop() {
		if (body.get(0).x < 0 || body.get(0).x > GDV5.getMaxWindowX() || body.get(0).y > GDV5.getMaxWindowY() || body.get(0).y < 0) {
			body.get(0).setLocation(GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY()/2);
			int currentLength = body.size();
			for (int i = currentLength - 1; i > 0; i--) {
				body.remove(i);
			}
			Snake.P1Score = 0;
			Snake.sound2.play(0);
			Snake.level = 0;
		} 
	}
//	in order to check if the snake hits itself, this function runs two for loops where the first one goes through every body piece and checks if the head intersects with it.  If this is true, then it removes the body.
	public void hitItself() {
		for (int i = 1; i < body.size(); i++) {
			if (body.get(0).intersects(body.get(i))) {
				body.get(0).setLocation(GDV5.getMaxWindowX()/2, GDV5.getMaxWindowY()/2);
				int currentLength = body.size();
				for (int j = currentLength - 1; j > 0; j--) {
					body.remove(j);
				}
				Snake.P1Score = 0;
				Snake.sound2.play(0);
				Snake.level = 0;
			}
		}
	}
	
	public void eatsPoison() {
		if (body.get(0).intersects(Snake.p)) {
			int currentLength = body.size();
			for (int i = currentLength - 1; i > 0; i--) {
				body.remove(i);
			}
			Snake.P1Score = 0;
			Snake.level = 0;
			Snake.sound4.play(0);
		}
	}
}
