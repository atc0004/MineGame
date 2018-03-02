package framework;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import objects.Bullet;

public class MouseInput implements MouseListener {
	private Handler handler;
	private Camera camera;

	public MouseInput(Handler handler, Camera camera) {
		this.handler = handler;
		this.camera = camera;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = (int) (e.getX() + camera.getX());
		int my = (int) (e.getY() + camera.getY());

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Player) {
				handler.addObject(new Bullet(tempObject.getX()+16, tempObject.getY()+24, ObjectId.Bullet, handler, mx, my));
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
