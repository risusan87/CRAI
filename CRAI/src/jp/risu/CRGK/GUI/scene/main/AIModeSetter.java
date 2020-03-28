package jp.risu.CRGK.GUI.scene.main;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * 
 * <p>Date created: 2020/03/28
 * @author Risusan
 */
@SuppressWarnings("serial")
public class AIModeSetter extends JPanel {
	public static final int MODE_DEEP_LEANING = 0x72;
	public static final int MODE_ALGORITHMIC = 0x202;
	public static final int MODE_SLGORITHMIC_CUSTOM = 0x77F;
	public AIModeSetter() {
		super();
		this.setBounds(35, 420, 220, 100);
		this.setBorder(BorderFactory.createTitledBorder("AI mode"));
	}
}
