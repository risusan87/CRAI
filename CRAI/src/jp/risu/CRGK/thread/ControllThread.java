package jp.risu.CRGK.thread;

import java.awt.AWTException;
import java.awt.Robot;

/**
 * replaced with ExecutorService.
 * <p>Date created: 2020/03/16
 * @author Risusan
 */
@Deprecated
public class ControllThread implements Runnable {
	private final Thread main;
	@SuppressWarnings("unused")
	private final Robot robot;
	private boolean cond;
	
	public ControllThread() throws AWTException {
		this.main = new Thread(this, "Controll Thread");
		this.robot = new Robot();
		this.cond = true;
	}
	
	public void start() {
		this.main.start();
	}
	
	public void stop() {
		this.cond = false;
	}
	
	@Override
	public void run() {
		while (this.cond);
	}
}
