package jp.risu.CRGK.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * 
 * <p>Date created: 2020/03/22
 * @author Risusan
 */
public class ImageUtils {
	public synchronized static BufferedImage toBufferedImage(Mat par1mat) {
		if (!par1mat.empty()) {
	        int type = BufferedImage.TYPE_BYTE_GRAY;
	        if (par1mat.channels() == 3) {
	            type = BufferedImage.TYPE_3BYTE_BGR;
	        }
	        else if (par1mat.channels() == 4)
	        	type = BufferedImage.TYPE_4BYTE_ABGR;
	        
	        int bufferSize = par1mat.channels() * par1mat.cols() * par1mat.rows();
	        byte[] b = new byte[bufferSize];
	        par1mat.get(0, 0, b); // get all the pixels
	        BufferedImage image = new BufferedImage(par1mat.cols(), par1mat.rows(), type);
	        byte[] targetPixels = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
	        System.arraycopy(b, 0, targetPixels, 0, b.length);
	        return image;
	    }
	    
	    return null;
	}
	
	@Deprecated
	public synchronized static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
	
	public synchronized static Mat toMatrix(BufferedImage par1buffimg) {
		par1buffimg = imgConvert(par1buffimg);
		Mat m = new Mat(par1buffimg.getHeight(), par1buffimg.getWidth(), CvType.CV_8SC3);
		byte[] data = ((DataBufferByte)par1buffimg.getRaster().getDataBuffer()).getData();
		m.put(0, 0, data);
		return m;
	}
	
	public synchronized static BufferedImage imgConvert(BufferedImage image) {
        BufferedImage rt = 
        		new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        rt.getGraphics().drawImage(image, 0, 0, null);
        return rt;
    }
}
