package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.Handler;
import framework.ObjectId;

public class Player extends GameObject {

	Handler handler;

	public Player(float x, float y, ObjectId id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
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
					x += ((velX)* -1);
					y += ((velY)* -1);
				}

			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int) x, (int) y, 32, 48);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 48);
	}

}
