package jp.risu.CRGK.thread;

import java.awt.AWTException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import jp.risu.CRGK.ai.CentralCommander;

/**
 * <p>Date created: 2020/03/20
 * @author Risusan
 */
public class ThreadProxy {
	private static final ExecutorService AI_ThreadPool = Executors.newWorkStealingPool();
	private static final ExecutorService CT_ThreadPool = Executors.newWorkStealingPool();
	public static volatile float REFRATE = 30.f;
	private static volatile boolean exit01, exit02;
	public static float currentPPS = -1;
	private static Thread AI_Loop;
	private static Thread CT_Loop;
	private static final CentralCommander AI = new CentralCommander();
	
	public static ExecutorService poolAI() {
		return AI_ThreadPool;
	}
	
	public static ExecutorService poolCT() {
		return CT_ThreadPool;
	}
	
	public static float secPerFrame() {
		return 1 / REFRATE;
	}
	public static void activateAI() {
		if (AI_Loop == null) {
			exit01 = true;
			AI_Loop = new Thread() {
				@Override
				public void run() {
					ThreadProxy.AI.onThreadStart();
					for (int prog = 0; exit01; prog++) {
						long rec = System.nanoTime();
						ThreadProxy.AI.updateStatus(prog);
						try {
							TimeUnit.NANOSECONDS.sleep
							((long)(ThreadProxy.secPerFrame() * 1000000000l) - 
									(System.nanoTime() - rec));
							currentPPS = (1000000000l) / (System.nanoTime() - rec);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					ThreadProxy.AI.onThreadClose();
				}
			};
			AI_Loop.start();
		}
	}
	
	public static void tarminateAI() {
		if (AI_Loop != null)
			exit01 = false;
	}
}
