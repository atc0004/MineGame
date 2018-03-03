package framework;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import objects.Player;

public class Animation {

	private ArrayList frames;
	private int currentFrame;
	private long animTime;
	private long totalDuration;

	public Animation() {
		frames = new ArrayList();
		totalDuration = 0;
		synchronized (this) {
			animTime = 0;
			currentFrame = 0;
		}
	}

	public synchronized void addFrame(BufferedImage image, long duration) {
		totalDuration += duration;
		frames.add(new AnimFrame(image, totalDuration));
	}

	public synchronized void tick(long elapsedTime) {
		if (frames.size() > 1) {
			animTime += elapsedTime;
			if (animTime >= totalDuration) {
				animTime = animTime % totalDuration;
				currentFrame = 0;
			}
			while (animTime > getFrame(currentFrame).endTime) {
				currentFrame++;
			}
		}
	}

	public synchronized BufferedImage getImage() {
		if (frames.size() == 0) {
			return null;
		} else {
			return getFrame(currentFrame).image;
		}
	}

	private AnimFrame getFrame(int i) {
		return (AnimFrame) frames.get(i);
	}

	private class AnimFrame {
		BufferedImage image;
		long endTime;

		public AnimFrame(BufferedImage image, long endTime) {
			this.image = image;
			this.endTime = endTime;
		}
	}
}
