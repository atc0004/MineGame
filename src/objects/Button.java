package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;

public class Button extends GameObject{
	private int x, y, w, h, velX, velY;
	//private String text;
	//private boolean clicked = false;
	//private Image pressed, unpressed;
	private Color color;

	public Button(int x, int y, int w, int h, Color color) {
		super(x, y, ObjectId.Button);
		this.w = w;
		this.h = h;
		this.color = color;
	}

	public Rectangle createButton() {
		return new Rectangle(x, y, w, h);
	}

	public void setClicked() {
		//clicked = true;
	}


	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, w, h);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		this.x += velX;
		this.y += velY;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}
}
