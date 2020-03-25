package jp.risu.CRGK.util;

/**
 * 
 * <p>Date created: 2020/03/22
 * @author Risusan
 */
public class SystemUtils {
	private long record = -1;
	private static final long pr_record = System.nanoTime();
	
	public void recordTime() {
		record = System.nanoTime();
	}
	
	public long getRlTime() {
		if (record != -1)
			return System.nanoTime() - record;
		return -1;
	}
	
	public static long getActiveTime() {
		return System.nanoTime() - pr_record;
	}
}
