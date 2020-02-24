package jp.risu.CRGK.GUI.component.button;

import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
abstract class ButtonBase extends JButton implements ActionListener {
	public ButtonBase() {
		super();
		this.addActionListener(this);
	}
	
	public ButtonBase(String title) {
		super(title);
		this.addActionListener(this);
	}
}
