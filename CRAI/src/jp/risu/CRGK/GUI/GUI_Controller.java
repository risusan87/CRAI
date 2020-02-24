package jp.risu.CRGK.GUI;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;

import jp.risu.CRGK.GUI.component.ComponentManager;
import jp.risu.CRGK.GUI.component.frame.*;

public class GUI_Controller {
	public GUI_Controller() {
		init();
	}
	
	private void init() {
		new Main_GUI();
		
		getComponent("Main_GUI").setVisible(true);
		((JFrame)getComponent("Main_GUI")).getContentPane().add(getComponent("Panel_Start"), BorderLayout.CENTER);
		this.current = screen.MainMenu;
	}
	
	private Component getComponent(String n) {
		return ComponentManager.getComponentByname(n);
	}
	
	private screen current;
	private enum screen {
		MainMenu;
	}
}
