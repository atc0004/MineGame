package objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import framework.Animation;
import framework.GameObject;
import framework.Handler;
import framework.ObjectId;
import framework.SpriteSheet;

public class Player extends GameObject {
	Handler handler;
	private BufferedImage squirrelLeft = null, squirrelRight = null, squirrel = null, squirrelTurnLeft = null,
			squirrelTurnRight = null;
	private Animation sanim;
	private Image currentSprite = null;
	private boolean isAnimating = false;

	public Player(float x, float y, ObjectId id, Handler handler, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		squirrel = ss.grabImage(5, 2, 32, 32);
		squirrelLeft = ss.grabImage(2, 2, 32, 32);
		squirrelRight = ss.grabImage(3, 2, 32, 32);
		squirrelTurnLeft = ss.grabImage(4, 2, 32, 32);
		squirrelTurnRight = ss.grabImage(4, 1, 32, 32);
		sanim = new Animation();
		sanim.addFrame(squirrel, 50);
		sanim.addFrame(squirrelLeft, 150);
		sanim.addFrame(squirrelRight, 150);
		currentSprite = sanim.getImage();
	}

	public void animate() {

		isAnimating = true;
		sanim.tick(50);
		currentSprite = sanim.getImage();

	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;

		collision();
		// Movement Handling
		if (handler.isUp()) {
			velY = -4;
			animate();
		} else if (!handler.isDown()) {
			velY = 0;
		}

		if (handler.isDown()) {
			velY = 4;
			animate();
		} else if (!handler.isUp()) {
			velY = 0;
		}

		if (handler.isRight()) {
			velX = 4;
			// animate();
			currentSprite = squirrelTurnRight;
		} else if (!handler.isLeft()) {
			velX = 0;
		}

		if (handler.isLeft()) {
			velX = -4;
			// animate();
			currentSprite = squirrelTurnLeft;
		} else if (!handler.isRight()) {
			velX = 0;
		}
		if (!handler.isUp() && !handler.isDown() && !handler.isRight() && !handler.isLeft()) {
			currentSprite = squirrel;
			isAnimating = false;
		}
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
		//currentSprite = currentSprite.getScaledInstance(128, 128, 0);
		g.drawImage(currentSprite, (int) x, (int) y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(((int) x) + 7, ((int) y) + 4, 22, 27);
	}

}
