package jp.risu.CRGK.GUI.scene.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import jp.risu.CRGK.CoreCRGK;
import jp.risu.CRGK.util.Colour;
import jp.risu.CRGK.util.FileIOUtils;

/**
 * 
 * <p>Date created: 2020/03/24
 * @author Risusan
 */
@SuppressWarnings("serial")
public class McLabel extends JLabel {
	private BufferedImage back;
	public McLabel() {
		super();
		try {
			this.back = ImageIO.read(FileIOUtils.getResource("resources/img/mcl_back.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.setIcon(new ImageIcon(this.back));
		this.setBounds(80, 350, 150, 150);
		this.setBorder(new LineBorder(Colour.getColorFromDegrees(270), 1, true));
		this.setToolTipText("Drag to select model image from the screen on the left");
	}
	
	public void setImage(BufferedImage par1buffimg) {
		this.setBounds(80, 350, par1buffimg.getWidth(), par1buffimg.getHeight());
		this.setIcon(new ImageIcon(par1buffimg));
	}
}
