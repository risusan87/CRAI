package jp.risu.CRGK.ai;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

public class ScreenCapture {
	private Robot capture;
	public ScreenCapture() {
		try {
			this.capture = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
			this.capture = null;
		}
	}
	
	/**
	 * Captures the screen. width is x, height is y, origin at the top left corner.
	 * @param par1rec - {@code Rectangle} object
	 */
	public BufferedImage captureScreen(Rectangle par1rec) {
		return this.capture.createScreenCapture(par1rec);
	}
}
