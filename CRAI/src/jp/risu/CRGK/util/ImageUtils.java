package jp.risu.CRGK.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * 
 * <p>Date created: 2020/03/22
 * @author Risusan
 */
public class ImageUtils {
	
	@Deprecated
	public synchronized static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  

	
	public synchronized static BufferedImage imgConvert(BufferedImage image) {
        BufferedImage rt = 
        		new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        rt.getGraphics().drawImage(image, 0, 0, null);
        return rt;
    }
}
