package framework;

import java.awt.Dimension;

import javax.swing.JFrame;

import minegame.Game;

public class Window {
	private final int WIDTH, HEIGHT;
	public Window(int w, int h, String title, Game game) {
		this.WIDTH = w;
		this.HEIGHT = h;
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(w, h));
		frame.setMaximumSize(new Dimension(w, h));
		frame.setMinimumSize(new Dimension(w, h));

		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	public int getWIDTH() {
		return WIDTH;
	}
	public int getHEIGHT() {
		return HEIGHT;
	}
}
