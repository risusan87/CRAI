package jp.risu.CRGK.GUI;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import jp.risu.CRGK.GUI.scene.Scene;
import jp.risu.CRGK.GUI.scene.main.SceneMain;

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
	public static int windowStatement = 0;
	public GUIController() {
		this.Scenes = new ArrayList<Scene>();
		this.setTitle("�N�������K�`��N");
		this.setSize(new Dimension(700, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon("./resources/img/ic.png").getImage());
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		SceneMain s = new SceneMain();
		s.enableScene(true);
		this.Scenes.add(s);
		this.getContentPane().add(s);
		
		try {
			this.setProcessedImage(ImageIO.read(new File("./resources/img/Back.png")));
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
	
	public void setProcessedImage(BufferedImage par1buffimg) {
		SceneMain s = (SceneMain)this.getScene("SceneMain");
		s.setImage(par1buffimg);
	}
}