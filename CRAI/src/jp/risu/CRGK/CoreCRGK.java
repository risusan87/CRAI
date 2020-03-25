package jp.risu.CRGK;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Toolkit;

import org.opencv.core.Core;

import jp.risu.CRGK.util.ThreadProxy;

/**
 * find player stats @ https://statsroyale.com/profile/*player tag*
 * <p>Date modified: 2020/03/24
 * @author Risusan
 */
public class CoreCRGK {
	public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	public static final int R_WIDTH = getRatio().width;
	public static final int R_HEIGHT = getRatio().height;
	
	public static final String IMG_PATH_ = "./resources/img/";
	
	private static Dimension getRatio() {
		int h = HEIGHT, w = WIDTH;
		for (int i = 2;; i++)
			if (h % i == 0 && w % i == 0) {
				h /= i; w /= i;
				i = 1;
			} else if (h <= i || w <= i)
				break;
		return new Dimension(w, h);
	}
	
	public static void main(String main[]) throws AWTException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		System.out.println("Starting CRGK(ƒNƒ‰ƒƒƒKƒ`‚èŒN)_Ver.dev1.0");
		ThreadProxy.activateCP();
		ThreadProxy.activateAI();
	}
}
