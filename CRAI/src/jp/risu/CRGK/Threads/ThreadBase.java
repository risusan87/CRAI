package jp.risu.CRGK.Threads;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import jp.risu.CRGK.GUI.component.ComponentManager;

abstract class ThreadBase extends Thread {
	private boolean isActive = true;
	public void stopThread() {
		this.isActive = false;
	}
	@Override
	public void run() {
		while(isActive) {
			plan();
		}
	}
	
	abstract protected void plan();
}
