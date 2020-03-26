package jp.risu.CRGK.ai;

import java.awt.image.BufferedImage;
import java.util.function.Function;

/**
 * 
 * <p>Date created: 2020/03/25
 * @author Risusan
 */
public class ImageProcessor {
	private static Function<BufferedImage, BufferedImage> jobs;
	private static BufferedImage src;
	
	public static void addProcess(Function<BufferedImage, BufferedImage> par1function) {
		if (jobs == null)
			jobs = par1function;
		else
			jobs.andThen(par1function);
	}
	
	public static BufferedImage executeProcesses(BufferedImage par1buffimg) {
		return jobs.apply(par1buffimg);	
	}
	
	public static void clearJobs() {
		jobs = null;
	}
	
	public static boolean isJobClear() {
		return jobs == null;
	}
}
