package jp.risu.CRGK.GUI.scene.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import jp.risu.CRGK.CoreCRGK;
import jp.risu.CRGK.util.ThreadProxy;

/**
 * 
 * <p>Date created: 2020/03/24
 * @author Risusan
 */
@SuppressWarnings("serial")
public class MainLabel extends JLabel implements MouseListener, MouseMotionListener {
	public final Dimension SIZE = new Dimension(320, 550);
	public volatile boolean isDragging = false;
	public volatile boolean isInLabel = false;
	public Dimension start;
	public Dimension end;
	
	public MainLabel() {
		super();
		try {
			this.setIcon(new ImageIcon(ImageIO.read(new File(CoreCRGK.IMG_PATH_ + "back.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setPreferredSize(SIZE);
		this.setBorder(new LineBorder(Color.GRAY, 2, true));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public synchronized void setImage(BufferedImage par1buffimg) {
		this.setIcon(new ImageIcon(par1buffimg));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if (this.isInLabel) {
			isDragging = true;
			start = new Dimension(e.getX(), e.getY());
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (this.isInLabel) 
			end = new Dimension(e.getX(), e.getY());
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.isInLabel && start != null && end != null) {
			end = new Dimension(e.getX(), e.getY());
			SceneMain sm = (SceneMain)ThreadProxy.GUI.getScene("SceneMain");
			McLabel mcl = (McLabel)sm.mcl;
			mcl.setImage(ThreadProxy.CAP.captureSelectedScreen(start, end));
			isDragging = false;
			start = null;
			end = null;
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		this.isInLabel = false;
		this.start = null;
		this.end = null;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		this.isInLabel = true;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {}
}
