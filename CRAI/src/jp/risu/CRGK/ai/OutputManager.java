package jp.risu.CRGK.ai;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;

/**
 * 
 * <p>Date modified: 2020/03/24
 * @author Risusan
 */
/*
 * tile: 32x18
 */
public class OutputManager {
	private final Robot hand;
	private static volatile Dimension origin = new Dimension(0, 0);
	public OutputManager() throws AWTException {
		this.hand = new Robot();
	}
	
	public synchronized static void setOrigin(int par1int, int par2int) {
		origin = new Dimension(par1int, par2int);
	}
	
	/**
	 * 
	 */
	public void setButton() {
		
	}
}
