package framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {
	protected float x, y;
	protected ObjectId id;
	protected float velX = 0, velY = 0;
	protected SpriteSheet ss;

	public GameObject(float x, float y, ObjectId id, SpriteSheet ss) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.ss = ss;
	}

	public abstract void tick(LinkedList<GameObject> object);

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();

	/*
	 * Getters and Setters
	 */
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getvelX() {
		return velX;
	}

	public float getvelY() {
		return velY;
	}

	public void setvelX(float velX) {
		this.velX = velX;
	}

	public void setvelY(float velY) {
		this.velY = velY;
	}

	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}
}
