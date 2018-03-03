package framework;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage image;
	private final int SPRITESIZE = 32;

	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage grabImage(int col, int row, int width, int height) {
		return image.getSubimage((col * SPRITESIZE) - SPRITESIZE, (row * SPRITESIZE) - SPRITESIZE, width, height);
	}
}
