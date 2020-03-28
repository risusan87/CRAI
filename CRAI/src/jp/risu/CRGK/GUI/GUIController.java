package jp.risu.CRGK.GUI;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import jp.risu.CRGK.GUI.scene.Scene;
import jp.risu.CRGK.GUI.scene.main.MainLabel;
import jp.risu.CRGK.GUI.scene.main.SceneMain;
import jp.risu.CRGK.util.FileIOUtils;

/**
 * Class {@code GUIController} is the main GUI of the program.
 * Anything that is on the gui screen is called "Scenes" that are pre-programmed
 * and using {@code Scene.enableScene(boolean)} method to controll the content on the screen.
 * 
 * <p>Date created: 2020/03/17
 * @author Risusan
 */
@SuppressWarnings("serial")
public class GUIController extends JFrame {
	private final List<Scene> Scenes;
	
	public GUIController() {
		this.Scenes = new ArrayList<Scene>();
		this.setTitle("ÉNÉâÉçÉèÉKÉ`ÇËåN dev0.7.6");
		this.setSize(new Dimension(700, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			this.setIconImage(new ImageIcon(ImageIO.read(FileIOUtils.getResource("resources/img/ic.png"))).getImage());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		SceneMain s = new SceneMain();
		s.enableScene(true);
		this.Scenes.add(s);
		this.getContentPane().add(s);
		
		try {
			this.setProcessedImage(ImageIO.read(FileIOUtils.getResource("resources/img/Back.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setVisible(true);
	}

	public Scene getScene(String par1str) {
		for (int i = 0; i < this.Scenes.size(); i++)
			if (this.Scenes.get(i).getSceneName().equals(par1str)) {
				return (this.Scenes.get(i));
			}
		System.out.println("Scene can't be found: " + par1str);
		return null;
	}
	
	public synchronized void setProcessedImage(BufferedImage par1buffimg) {
		SceneMain s = (SceneMain)this.getScene("SceneMain");
		MainLabel ml = (MainLabel)s.main;
		ml.setImage(par1buffimg);
	}
}
