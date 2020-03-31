package jp.risu.CRGK.ai.algorithm;

import java.awt.image.BufferedImage;

/**
 * All images will be converted into {@code Model} objects in this application befour processing.
 * Class {@code Model} will manage anything to do with models, including IO.
 * <p>Date created: 2020/03/28
 * @author Risusan
 */
public final class Model {
	public static Model currentModel;
	/**
	 * Used to make a list of Model in method {@code loadAsModel0()}
	 * @param par1int - width of the image
	 */
	private Model(int[] par1int) {
		
	}
	
	/**
	 * 
	 * @param par1int
	 * @param par2int - give 0 as nothing
	 */
	private Model(int[] par1int, int par2int) {
		
	}
	
	/**
	 * Loads {@code BufferedImage} as {@code Model} object.
	 * !!IT DOES NOT NEED TO BE PROCESSED WITH {@code CompletableFuture}!!
	 * <p>NOTE::This method is meant to be used for captured image, and so, the model loaded would only last
	 * for one single process cycle. (very short time)
	 * @param par1buffimg - Screen image
	 */
	public static void loadAsModel0(BufferedImage par1buffimg) {
		int height = par1buffimg.getHeight();
		int width  = par1buffimg.getWidth();
		
		int b[] = new int[height * width];
		for (int h = 0; h < height; h++)
			for (int w = 0; w < width; w++)
				b[h * width + w] = par1buffimg.getRGB(w, h);
		currentModel = new Model(b);
	}
	
	/**
	 * used to create model data. data will be exported to sys folder, and will not be loaded yet.
	 * model data can be used to detect it as object in model loaded from screen.
	 * @param par1buffimg
	 */
	public static void createModelData(BufferedImage par1buffimg) {
		int height = par1buffimg.getHeight();
		int width  = par1buffimg.getWidth();
	}
	
	public static void loadAsModel(Model par1model) {
		
	}
}
