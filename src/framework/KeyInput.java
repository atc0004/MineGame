package framework;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyInput implements KeyListener{
	Handler handler;
	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			if (temp.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_W)
					handler.setUp(true);
				if (key == KeyEvent.VK_S)
					handler.setDown(true);
				if (key == KeyEvent.VK_A)
					handler.setLeft(true);
				if (key == KeyEvent.VK_D)
					handler.setRight(true);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W)
			handler.setUp(false);
		if (key == KeyEvent.VK_S)
			handler.setDown(false);
		if (key == KeyEvent.VK_A)
			handler.setLeft(false);
		if (key == KeyEvent.VK_D)
			handler.setRight(false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
