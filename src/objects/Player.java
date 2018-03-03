package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.GameObject;
import framework.Handler;
import framework.ObjectId;
import framework.SpriteSheet;

public class Player extends GameObject {
	private BufferedImage squirrel;
	Handler handler;

	public Player(float x, float y, ObjectId id, Handler handler, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		squirrel = ss.grabImage(1, 2, 32, 32);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;

		collision();
		// Movement Handling
		if (handler.isUp())
			velY = -4;
		else if (!handler.isDown())
			velY = 0;

		if (handler.isDown())
			velY = 4;
		else if (!handler.isUp())
			velY = 0;

		if (handler.isRight())
			velX = 4;
		else if (!handler.isLeft())
			velX = 0;

		if (handler.isLeft())
			velX = -4;
		else if (!handler.isRight())
			velX = 0;
	}

	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Block) {
				if (getBounds().intersects(tempObject.getBounds())) {
					x += ((velX) * -1);
					y += ((velY) * -1);
				}

			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(squirrel, (int) x, (int) y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(((int) x) + 7, ((int) y) + 4, 22, 27);
	}

}
