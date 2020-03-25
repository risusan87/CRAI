package jp.risu.CRGK.ai;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import jp.risu.CRGK.CoreCRGK;
import jp.risu.CRGK.GUI.scene.main.SceneMain;
import jp.risu.CRGK.thread.ThreadProxy;
import net.coobird.thumbnailator.Thumbnails;

/**
 * <p>Date modified: 2020/03/20
 * @author ypmxx
 *
 */
public class CentralCommander {
	private final ScreenCapture capturer;
	private static final boolean CHECK_ONLINE = true;
	
	public CentralCommander() {
		this.capturer = new ScreenCapture();
	}
	
	private CompletableFuture<BufferedImage> searchGameScreen() {
		BufferedImage im = this.capturer.captureScreen(new Rectangle(0, 0, CoreCRGK.WIDTH, CoreCRGK.HEIGHT));
		return null;
	}
	
	
	/**
	 * Called once when ai thread is started.
	 */
	public void onThreadStart() {
		//file input onto stream
	}
	
	/**
	 * Called for every single loop when ai thread is active.
	 */
	public void updateStatus(int par1int) {
		long rec = System.nanoTime();
		CompletableFuture<BufferedImage> rawImg = CompletableFuture.supplyAsync(() -> {
			BufferedImage i = this.capturer.captureScreen(new Rectangle(100, 100, 490, 870));
			try {
				return Thumbnails.of(i).forceSize(350, 550).asBufferedImage();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}, ThreadProxy.poolAI());
		try {
			CoreCRGK.gui.setProcessedImage(rawImg.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		if (ThreadProxy.currentPPS != -1) {
			SceneMain m = (SceneMain)CoreCRGK.gui.getScene("SceneMain");
			m.setFPS(ThreadProxy.currentPPS);
		}
		SceneMain sm = (SceneMain)CoreCRGK.gui.getScene("SceneMain");
		sm.setPPS(new Float(TimeUnit.MILLISECONDS.convert(System.nanoTime() - rec, TimeUnit.NANOSECONDS)));
	}
	
	/**
	 * Called once when ai thread is tarminated.
	 */
	public void onThreadClose() {
		System.out.println("Closing");
	}
}
