package snake;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.KeyEvent;

import utilities.GDV5;

public class Grid extends Rectangle{

	private int direction;
	private Color col;
	private int dimension = 20;
	
	
	public Grid(int x, int y) {
		super(x, y, 0, 0);
		this.setSize(dimension, dimension);
		col = Color.BLACK;
	}
	
	public Grid(int x, int y, int direction) {
		super(x, y, 0, 0);
		this.setSize(dimension, dimension);
		this.direction = direction;
		if (Snake.level == 1) {
			col = Color.YELLOW;
		} else if (Snake.level == 2) {
			col = Color.WHITE;
		} else if (Snake.level == 3) {
			col = Color.BLACK;
		}
	}
	
	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public Color getCol() {
		return col;
	}

	public void setCol(Color col) {
		this.col = col;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int size) {
		this.dimension = size;
	}

	public void draw(Graphics2D pb) {
		pb.setColor(col);
		pb.draw(this);
	}
	
	public void fill(Graphics2D pb) {
		pb.setColor(col);
		pb.fill(this);
	}
	
	public void setHeadDirection() {
		if (GDV5.KeysPressed[KeyEvent.VK_W]) direction = 2;
		if (GDV5.KeysPressed[KeyEvent.VK_A]) direction = 1;
		if (GDV5.KeysPressed[KeyEvent.VK_S]) direction = 4;
		if (GDV5.KeysPressed[KeyEvent.VK_D]) direction = 3;
	}
	public void move() {
		if (direction == 1) this.x -= this.dimension;
		if (direction == 2) this.y -= this.dimension;
		if (direction == 3) this.x += this.dimension;
		if (direction == 4) this.y += this.dimension;
	}
	
	public void updateDirection(Grid[] board) {
		for (int i = 0; i < board.length; i++) {
			if (this.getX() == board[i].getX() && this.getY() == board[i].getY()) {
				board[i].setDirection(this.getDirection());
			}
		}
	}
	
	
}
