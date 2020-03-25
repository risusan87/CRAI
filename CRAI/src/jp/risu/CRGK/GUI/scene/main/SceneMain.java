package jp.risu.CRGK.GUI.scene.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import jp.risu.CRGK.GUI.scene.Scene;
import jp.risu.CRGK.thread.ThreadProxy;
import jp.risu.CRGK.util.Colour;

/**
 * Set up all conponent when initialized.
 * Date modified: 2020/03/24
 * @author Risusan
 */
@SuppressWarnings("serial")
public class SceneMain extends Scene {
	private final JLabel label;
	private final JLabel fps = new JLabel();
	private final JLabel pps = new JLabel();
	public SceneMain() {
		super("SceneMain");
		this.setLayout(new BorderLayout());
		
		JPanel leftP = new JPanel(new FlowLayout());
		JPanel rightP = new JPanel();
		
		rightP.setLayout(null);
		rightP.setPreferredSize(new Dimension(293, -1));
		leftP.setPreferredSize(new Dimension(400, -1));
		
		rightP.setBorder(new LineBorder(Colour.getColorFromDegrees(180), 2, true));
		
		this.label = new JLabel();
		this.label.setPreferredSize(new Dimension(350, 550));
		this.label.setBorder(new LineBorder(Color.black, 2, true));
		
		this.fps.setBounds(5, 0, 60, 30);
		this.pps.setBounds(5, 20, 60, 30);
		
		leftP.add(this.label);
		rightP.add(this.fps);
		rightP.add(this.pps);
		rightP.add(new FpsSetter());
		
		this.add(leftP, BorderLayout.WEST);
		this.add(rightP, BorderLayout.EAST);
		this.addComponent(leftP, "leftP");
		this.addComponent(rightP, "rightP");
	}
	
	public synchronized void setImage(BufferedImage par1buffimg) {
		this.label.setIcon(new ImageIcon(par1buffimg));
	}
	
	public synchronized void setFPS(Float par1float) {
		this.fps.setText("FPS: " + Float.toString(par1float));
	}
	
	public synchronized void setPPS(Float par1float) {
		this.pps.setText("PPS: " + Float.toString(par1float));
	}
}
