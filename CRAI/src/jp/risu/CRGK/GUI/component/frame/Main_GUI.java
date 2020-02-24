package jp.risu.CRGK.GUI.component.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import jp.risu.CRGK.GUI.component.ComponentManager;

@SuppressWarnings("serial")
public class Main_GUI extends JFrame {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;

	public Main_GUI() {
		super("ÉNÉâÉçÉèÉKÉ`ÇËåN");
		ComponentManager.addComponent(this, "Main_GUI");
		this.setSize(Main_GUI.WIDTH, Main_GUI.HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon("./resources/ic.png").getImage());
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
	}
}
