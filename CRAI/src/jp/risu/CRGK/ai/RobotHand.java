package jp.risu.CRGK.ai;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Robot;
import java.util.Vector;

import net.coobird.thumbnailator.geometry.Coordinate;

/**
 * Class {@code RobotHand} is the "hand" of the ai, controlling all nessessary movement on the screen.
 * input from keyboard and mouse will be ignored until the movement is completed.
 * <p>Date modified: 2020/03/24
 * @author Risusan
 */

/*
 * tile: 32x18
 */
public class RobotHand {
	@SuppressWarnings("unused")
	private final Robot hand;
	@SuppressWarnings("unused")
	private static volatile Point origin = new Point(0, 0);
	
	public RobotHand() throws AWTException {
		this.hand = new Robot();
	}
	
	/**
	 * Origin will be the coordinate of a pixel, specifically the very top left corner of the game screen. 
	 * @param par1int
	 * @param par2int
	 */
	public synchronized static void setOrigin(int par1int, int par2int) {
		origin = new Point(par1int, par2int);
	}
	
	public void setButton() {
		
	}
}
