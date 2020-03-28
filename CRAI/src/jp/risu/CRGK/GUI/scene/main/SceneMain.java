package jp.risu.CRGK.GUI.scene.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jp.risu.CRGK.GUI.scene.Scene;

/**
 * Set up all conponent when initialized.
 * Date modified: 2020/03/24
 * @author Risusan
 */
@SuppressWarnings("serial")
public class SceneMain extends Scene {
	public  final JLabel main;
	
	//STEP1: Declaring components
	public final McPanel mcl;
	public FPPSSetter fpps;
	public final AIModeSetter aimode;
	
	public SceneMain() {
		super("SceneMain");
		this.setLayout(new BorderLayout());
		
		JPanel leftP = new JPanel(new FlowLayout());
		JPanel rightP = new JPanel();
		
		rightP.setLayout(null);
		rightP.setPreferredSize(new Dimension(320, -1));
		//remove thisÅ´ from commenting if needed as debugging for right side of the window.
		//rightP.setBorder(new LineBorder(Colour.getColorFromDegrees(180), 2, true));
		
		leftP.setPreferredSize(new Dimension(370, -1));
		
		this.main = new MainLabel();
		
		//STEP2: Initiating components on the right side.
		this.mcl = new McPanel();
		this.fpps = new FPPSSetter();
		this.aimode = new AIModeSetter();
		
		leftP.add(this.main);
		
		//STEP3: Setting up components and they are ready to go.
		rightP.add(this.mcl);
		rightP.add(this.fpps);
		rightP.add(this.aimode);
		
		//Final setup
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
