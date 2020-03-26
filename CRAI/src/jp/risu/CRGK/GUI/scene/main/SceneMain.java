package jp.risu.CRGK.GUI.scene.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import jp.risu.CRGK.GUI.scene.Scene;
import jp.risu.CRGK.util.Colour;
import jp.risu.CRGK.util.ThreadProxy;

/**
 * Set up all conponent when initialized.
 * Date modified: 2020/03/24
 * @author Risusan
 */
@SuppressWarnings("serial")
public class SceneMain extends Scene {
	public  final JLabel main;
	public final McLabel mcl;
	public FPPSSetter fpps;
	
	public SceneMain() {
		super("SceneMain");
		this.setLayout(new BorderLayout());
		
		JPanel leftP = new JPanel(new FlowLayout());
		JPanel rightP = new JPanel();
		
		rightP.setLayout(null);
		rightP.setPreferredSize(new Dimension(320, -1));
		//rightP.setBorder(new LineBorder(Colour.getColorFromDegrees(180), 2, true));
		
		leftP.setPreferredSize(new Dimension(370, -1));
		
		this.main = new MainLabel();
		this.mcl = new McLabel();
		this.fpps = new FPPSSetter();
		
		leftP.add(this.main);
		
		rightP.add(this.mcl);
		rightP.add(this.fpps);
		
		this.add(leftP, BorderLayout.WEST);
		this.add(rightP, BorderLayout.EAST);
		this.addComponent(leftP, "leftP");
		this.addComponent(rightP, "rightP");
	}
	
	public synchronized void setFPS(int par1float) {
		this.fpps.fps.setText("FPS: " + Float.toString(par1float));
	}
	
	public synchronized void setPPS(int par1float) {
		this.fpps.pps.setText("PPS: " + Float.toString(par1float));
	}
}
