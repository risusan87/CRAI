package jp.risu.CRGK.ai;

import java.awt.image.BufferedImage;
import java.util.function.Function;

/**
 * Class {@code ProcessPromiser} is an advanced {@code Future} like implemention forcused on
 * for synchronization of FPS and PPS. Because the application shows the process of image by printing visible
 * recognitions on captured screens, it is nessesally to make sure both FPS and PPS are running in sync to avoid
 * weired looking output. 
 * <p>Once {@code ProcessPromiser} accepts the requests of image procession for the result, 
 * it gurantees that those processes will not be lost until next requests come in. 
 * <p>Date created: 2020/03/25
 * @author Risusan
 */
public final class ProcessPromiser {
	private static Function<BufferedImage, BufferedImage> jobs;
	
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
