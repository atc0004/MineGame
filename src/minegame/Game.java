package minegame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import framework.BufferedImageLoader;
import framework.Camera;
import framework.Handler;
import framework.KeyInput;
import framework.MouseInput;
import framework.ObjectId;
import framework.Window;
import objects.Block;
import objects.Player;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private boolean running = false;
	private Thread thread;
	public static int WIDTH = 1000, HEIGHT = 563;

	private Handler handler;
	private Window window;
	private BufferedImage level = null;
	private Camera camera;

	
	public Game() {
		window = new Window(WIDTH, HEIGHT, "Testing", this);
		start();

		handler = new Handler();
		camera = new Camera(0,0, window);
		// Listeners for Input
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler, camera));

		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/GameLevel.png");
		loadLevel(level);
		// Objects
	}

	private void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	private void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		for(int i = 0; i < handler.object.size();i++) {
			if(handler.object.get(i).getId() == ObjectId.Player)
				camera.tick(handler.object.get(i));
		}
		handler.tick();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		g.fillRect(0, 0, 1000, 750);
		/// Draw here
		g2d.translate(-camera.getX(), -camera.getY());
		
		handler.render(g);
		g2d.translate(camera.getX(), camera.getY());

		// To here
		g.dispose();
		bs.show();
	}

	// Load level
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				//int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 255)
					handler.addObject(new Block(xx * 32, yy * 32, ObjectId.Block));
				if (blue == 255)
					handler.addObject(new Player(xx * 32, yy * 32, ObjectId.Player, handler));
			}
		}
	}

	public static void main(String args[]) {
		new Game();
	}

}
