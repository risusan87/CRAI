package jp.risu.CRGK.GUI.scene.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 
 * <p>Date created: 2020/03/24
 * @author Risusan
 */
@SuppressWarnings("serial")
public class McPanel extends JPanel {
	private final JLabel label;
	private static final int HEIGHT = 300;
	private static final int WIDTH = 220;
	public McPanel() {
		super();
		this.label = new JLabel();
		this.label.setBorder(new LineBorder(Color.black, 1, true));
		this.setBounds(35, 100, WIDTH, HEIGHT);
		this.add(label);
		this.setBorder(BorderFactory.createTitledBorder("Model creator"));
		this.setToolTipText("Drag to select model image from the screen on the left");
	}
	
	public void setImage(BufferedImage par1buffimg) {
		int width = par1buffimg.getWidth();
		int height = par1buffimg.getHeight();
		int maxW = WIDTH - 6;
		int maxH = (int)(HEIGHT * BigDecimal.valueOf(2).divide(BigDecimal.valueOf(3), 5, BigDecimal.ROUND_HALF_UP).floatValue()) - 3;
		float ratioX, ratioY;
		ratioX = width > maxW ? (float)maxW / (float)width : 1.0f;
		ratioY = height > maxH ? (float)maxH / (float)height : 1.0f;
		try {
			float ratio = ratioX > ratioY ? ratioY : ratioX;
			par1buffimg = Thumbnails.of(par1buffimg).forceSize((int)(width * ratio), (int)(height * ratio)).asBufferedImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = par1buffimg.getWidth();
		height = par1buffimg.getHeight();
		this.label.setBounds((WIDTH - width) / 2, (HEIGHT * (1 / 3) - (height / 2 - 3)), par1buffimg.getWidth(), par1buffimg.getHeight());
		this.label.setIcon(new ImageIcon(par1buffimg));
	}
}
