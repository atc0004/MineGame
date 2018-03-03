package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;
import framework.SpriteSheet;

public class Block extends GameObject {
	private BufferedImage spikes = null;

	public Block(float x, float y, ObjectId id, SpriteSheet ss) {
		super(x, y, id, ss);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {

	}

	@Override
	public void render(Graphics g) {
		spikes = ss.grabImage(3, 1, 32, 32);
		g.drawImage(spikes, (int) x, (int) y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x + 6, (int) y, 24, 32);
	}

}