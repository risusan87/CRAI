package jp.risu.CRGK.ai;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import jp.risu.CRGK.GUI.scene.main.MainLabel;
import jp.risu.CRGK.GUI.scene.main.SceneMain;
import jp.risu.CRGK.ai.algorithm.Model;
import jp.risu.CRGK.util.ImageUtils;
import jp.risu.CRGK.util.ThreadProxy;
import jp.risu.CRGK.util.color.Colour;
import jp.risu.CRGK.util.color.PureColour;

/**
 * <p>Date modified: 2020/03/20
 * @author Risusan
 *
 */
public class CentralCommander {
	@SuppressWarnings("unused")
	private static final boolean CHECK_ONLINE = true;
	
	public CentralCommander() {
	}
	
	public Dimension coordGUItoScreen(Dimension par1dimension) {
		SceneMain sm = (SceneMain)ThreadProxy.GUI.getScene("SceneMain");
		MainLabel ml = (MainLabel)sm.main;
		Dimension gui = ml.SIZE;
		
		float x_ratio = (float)ThreadProxy.CAP.game_size.width / (float)gui.width;
		float y_ratio = (float)ThreadProxy.CAP.game_size.height / (float)gui.height;

		int x1 = (int)(par1dimension.width * x_ratio);
		int y1 = (int)(par1dimension.height * y_ratio);
		
		return new Dimension(x1, y1);
	}
	
	/**
	 * Called once when ai thread is started.
	 */
	public void onThreadStart() {
		//file input onto stream
		ThreadProxy.activateCP();
	}
	
	/**
	 * Called for every single loop when ai thread is active.
	 */
	public void updateStatus(int par1int) {
		BufferedImage mainIM = ThreadProxy.CAP.shrinkedImage;
		if (mainIM != null)
			Model.loadAsModel0(mainIM);
			if (!ProcessPromiser.isJobClear())
				ProcessPromiser.clearJobs();
			SceneMain sm = (SceneMain)ThreadProxy.GUI.getScene("SceneMain");
			MainLabel ml = (MainLabel)sm.main;
			if (ml.isDragging && ml.start != null && ml.end != null) {
				ProcessPromiser.addProcess(img -> {
					//Something to do with coord converting -> gui to screen
					Point strt = new Point(ml.start.width, ml.start.height);
					Point dst = new Point(ml.end.width, ml.end.height);
					int x, y, width, height;
					if (dst.x > strt.x) {
						x = strt.x;
						width = dst.x - strt.x;
					} else if (strt.x > dst.x) {
						x = dst.x;
						width = strt.x - dst.x;
					} else {
						x = strt.x;
						width = 1;
					}
					if (dst.y > strt.y) {
						y = strt.y;
						height = dst.y - strt.y;
					} else if (strt.y > dst.y) {
						y = dst.y;
						height = strt.y - dst.y;
					} else {
						y = strt.y;
						height = 1;
					}
					
					Graphics2D g = (Graphics2D)img.getGraphics();
					g.setStroke(new BasicStroke(1.0f));
					g.setColor(new PureColour(0, 255, 0).asColor());
					g.drawRect(x, y, width, height);
					g.dispose();
					return img;
				});
			}
			if (!ProcessPromiser.isJobClear())
				ThreadProxy.GUI.setProcessedImage(ProcessPromiser.executeProcesses(mainIM));
			else if (ThreadProxy.CAP.shrinkedImage != null)
				ThreadProxy.GUI.setProcessedImage(mainIM);

		sm.setPPS(ThreadProxy.currentPPS);
		sm.setFPS(ThreadProxy.currentFPS);
	}
	
	/**
	 * Called once when ai thread is tarminated.
	 */
	public void onThreadClose() {
		ThreadProxy.tarminateCP();
	}
}
