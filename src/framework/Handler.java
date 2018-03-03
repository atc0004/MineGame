package framework;

import java.awt.Graphics;
import java.util.LinkedList;

import SquirrelsGame.Game;
import objects.Block;

public class Handler {
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	private boolean up = false, down = false, left = false, right = false;
	private final int imageSize = 32;

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick(object);
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void createLevel() {
		for (int xx = 0; xx < Game.WIDTH + imageSize; xx += imageSize) {
			addObject(new Block(xx, Game.HEIGHT - imageSize, ObjectId.Block, null));
		}
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
}
