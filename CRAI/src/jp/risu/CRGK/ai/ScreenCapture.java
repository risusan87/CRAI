package jp.risu.CRGK.ai;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;

import org.opencv.core.Point;

import javafx.scene.control.Slider;
import jp.risu.CRGK.CoreCRGK;
import jp.risu.CRGK.GUI.scene.main.MainLabel;
import jp.risu.CRGK.GUI.scene.main.SceneMain;
import jp.risu.CRGK.util.ThreadProxy;
import net.coobird.thumbnailator.Thumbnails;

public class ScreenCapture {
	private Robot capture;
	private BufferedImage capturedImage;
	public BufferedImage shrinkedImage;
	public BufferedImage processedImage;
	public volatile Dimension game_size = new Dimension(490, 870);//490 x 870
	public volatile Dimension origin = new Dimension(100, 100);
	
	public ScreenCapture() {
		try {
			this.capture = new Robot();
			this.capturedImage = ImageIO.read(new File(CoreCRGK.IMG_PATH_ + "Back.png"));
		} catch (AWTException e) {
			e.printStackTrace();
			this.capture = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Captures the screen. width is x, height is y, origin at the top left corner.
	 * @param par1rec - {@code Rectangle} object
	 */
	public BufferedImage captureScreen(Rectangle par1rec) {
		return this.capture.createScreenCapture(par1rec);
	}
	
	public BufferedImage captureSelectedScreen(Dimension par1dimension, Dimension par2dimension) {
		par1dimension = ThreadProxy.AI.coordGUItoScreen(par1dimension);
		par2dimension = ThreadProxy.AI.coordGUItoScreen(par2dimension);

		int x1 = (int)(par1dimension.width);
		int y1 = (int)(par1dimension.height);
		int x2 = (int)(par2dimension.width);
		int y2 = (int)(par2dimension.height);
		
		int x = x1 >= x2 ? x2 : x1; x += origin.width;
		int y = y1 >= y2 ? y2 : y1; y += origin.height;
		int width = x2 >= x1 ? x2 - x1 : x1 - x2; width = width <= 0 ? 1 : width;
		int height = y2 >= y1 ? y2 - y1 : y1 - y2; height = height <= 0 ? 1 : height;
		
		return this.captureScreen(new Rectangle(x, y, width, height));
	}
	
	public BufferedImage getScreenCapturedImage() {
		return this.capturedImage;
	}
	
	public void onThreadStart() {
		//do something when thread starts
	}
	
	public void onStateUpdate(int par1int) {
		this.capturedImage = this.captureScreen(new Rectangle(origin.width, origin.height, game_size.width, game_size.height));
		try {
			this.shrinkedImage = Thumbnails.of(this.capturedImage).forceSize(320, 550).asBufferedImage();
			if (!ImageProcessor.isJobClear()) {
				CompletableFuture<BufferedImage> cf = CompletableFuture.supplyAsync(() -> {
					return ImageProcessor.executeProcesses(this.shrinkedImage);
				}, ThreadProxy.poolAI());
				try {
					this.processedImage = cf.get();
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			} else
				this.processedImage = this.shrinkedImage;
			ThreadProxy.GUI.setProcessedImage(this.processedImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onThreadTarminate() {
		
	}
} 
