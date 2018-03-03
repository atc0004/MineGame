package framework;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import objects.Bullet;

public class MouseInput implements MouseListener {
	private Handler handler;
	private Camera camera;
	private SpriteSheet ss;
	

	public MouseInput(Handler handler, Camera camera, SpriteSheet ss) {
		this.handler = handler;
		this.camera = camera;
		this.ss = ss;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = (int) (e.getX() + camera.getX());
		int my = (int) (e.getY() + camera.getY());

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Player) {
				handler.addObject(new Bullet(tempObject.getX() + 16, tempObject.getY() + 24,
						ObjectId.Bullet, handler, mx, my, ss));
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
}
