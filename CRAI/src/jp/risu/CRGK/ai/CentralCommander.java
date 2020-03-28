package jp.risu.CRGK.ai;

import java.awt.Dimension;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import jp.risu.CRGK.GUI.scene.main.MainLabel;
import jp.risu.CRGK.GUI.scene.main.SceneMain;
import jp.risu.CRGK.util.ImageUtils;
import jp.risu.CRGK.util.ThreadProxy;

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
		CompletableFuture<Void> pros = CompletableFuture.runAsync(() -> {
			if (!ProcessPromiser.isJobClear())
				ProcessPromiser.clearJobs();
			SceneMain sm = (SceneMain)ThreadProxy.GUI.getScene("SceneMain");
			MainLabel ml = (MainLabel)sm.main;
			if (ml.isDragging && ml.start != null && ml.end != null) {
				ProcessPromiser.addProcess(img -> {
					//Something to do with coord converting -> gui to screen
					Point strt = new Point(ml.start.width, ml.start.height);
					Point dst = new Point(ml.end.width, ml.end.height);
					Mat m = ImageUtils.toMatrix(img);
					Imgproc.rectangle(m, strt, dst, new Scalar(0, 0, 255, 0), 2);
					return ImageUtils.toBufferedImage(m);
				});
			}
			if (!ProcessPromiser.isJobClear())
				ThreadProxy.GUI.setProcessedImage(ProcessPromiser.executeProcesses(ThreadProxy.CAP.shrinkedImage));
			else if (ThreadProxy.CAP.shrinkedImage != null)
				ThreadProxy.GUI.setProcessedImage(ThreadProxy.CAP.shrinkedImage);
		}, ThreadProxy.poolAI());
		
		try {
			pros.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		SceneMain sm = (SceneMain)ThreadProxy.GUI.getScene("SceneMain");
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
