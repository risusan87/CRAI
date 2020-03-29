package jp.risu.CRGK.ai.algorithm;

import java.awt.image.BufferedImage;
import java.util.concurrent.CompletableFuture;

import jp.risu.CRGK.util.ImageUtils;

/**
 * All images will be converted into {@code Model} objects in this application befour processing.
 * Class {@code Model} will manage anything to do with models, including IO.
 * <p>Date created: 2020/03/28
 * @author Risusan
 */
public final class Model {
	/**
	 * Loads {@code BufferedImage} as {@code Model} object.
	 * !!IT DOES NOT NEED TO BE PROCESSED WITH {@code CompletableFuture}!!
	 * <p>NOTE::This method is meant to be used for captured image, and so, the model loaded would only last
	 * for one single process cycle. (very short time)
	 * @param par1buffimg - Screen image
	 */
	public static void loadAsModel0(BufferedImage par1buffimg) {
		int height = par1buffimg.getHeight();
		int width = par1buffimg.getWidth();
	}
}
