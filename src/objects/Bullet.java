package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.Handler;
import framework.ObjectId;

public class Bullet extends GameObject {
	private Handler handler;
	private final int SPEED = 16;

	public Bullet(float x, float y, ObjectId id, Handler handler, int mx, int my) {
		super(x, y, id);
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
		g.setColor(Color.green);
		g.fillOval((int) x, (int) y, 8, 8);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 4, 4);
	}

}
