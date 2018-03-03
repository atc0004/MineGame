package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.GameObject;
import framework.Handler;
import framework.ObjectId;
import framework.SpriteSheet;

public class Bullet extends GameObject {
	private Handler handler;
	private final int SPEED = 8;
	private BufferedImage nut = null;

	public Bullet(float x, float y, ObjectId id, Handler handler, int mx, int my, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;

		int dist = (int) Math.hypot((mx - x), (my - y));
		dist /= SPEED; // speed is a constant I set
		velX = (mx - x) / dist;
		velY = (my - y) / dist;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Block) {
				if (getBounds().intersects(tempObject.getBounds()))
					handler.removeObject(this);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		nut = ss.grabImage(2, 1, 32, 32);
		g.drawImage(nut, (int)x-16, (int) y-16, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 10, 12);
	}

}
