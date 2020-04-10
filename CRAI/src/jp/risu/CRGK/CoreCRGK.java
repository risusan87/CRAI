package jp.risu.CRGK;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Toolkit;
import jp.risu.CRGK.util.FileIOUtils;
import jp.risu.CRGK.util.ThreadProxy;
import jp.risu.CRGK.util.color.Colour;
import jp.risu.CRGK.util.color.PureColour;

/**
 * find player stats @ https://statsroyale.com/profile/*player tag*
 * <p>Date modified: 2020/03/24
 * @author Risusan
 */
public class CoreCRGK {
	static { FileIOUtils.initIO(CoreCRGK.class); };
	
	public static final String VERSION = "dev.0.7.8";
	public static final String PROJNAM = "CrGkun_" + VERSION; 
	
	public static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	public static final int R_WIDTH = getRatio().width;
	public static final int R_HEIGHT = getRatio().height;
	
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
		ThreadProxy.init();
		System.out.println(FileIOUtils.getResourceFromHomeFolder("test.test"));
		ThreadProxy.activateAI();
	}
}
