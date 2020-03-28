package jp.risu.CRGK.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import jp.risu.CRGK.GUI.GUIController;
import jp.risu.CRGK.ai.CentralCommander;
import jp.risu.CRGK.ai.ScreenCapture;

/**
 * <p>Date created: 2020/03/20
 * @author Risusan
 */
public class ThreadProxy {
	private static final ExecutorService AI_ThreadPool = Executors.newWorkStealingPool();
	private static final ExecutorService CT_ThreadPool = Executors.newWorkStealingPool();
	public static volatile float FPS = 30.f;
	public static volatile float PPS = 60.f;
	@SuppressWarnings("unused")
	private static volatile boolean exit01, exit02, exit03;
	public static int currentPPS = -1;
	public static int currentFPS = -1;
	private static Thread AI_Loop;
	@SuppressWarnings("unused")
	private static Thread CT_Loop;
	private static Thread CP_Loop;
	
	public static CentralCommander AI;
	public static GUIController GUI;
	public static ScreenCapture CAP;
	
	public static void init() {
		AI = new CentralCommander();
		GUI = new GUIController();
		CAP = new ScreenCapture();
	}
	public static ExecutorService poolAI() {
		return AI_ThreadPool;
	}
	
	public static ExecutorService poolCT() {
		return CT_ThreadPool;
	}
	
	public static float secPerFrame() {
		return 1 / FPS;
	}
	
	public static float secPerProcess() {
		return 1 / PPS;
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
							((long)(ThreadProxy.secPerProcess() * 1000000000l) - 
									(System.nanoTime() - rec));
							currentPPS = (int)((1000000000l) / (System.nanoTime() - rec));
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
		if (AI_Loop != null) {
			exit01 = false;
			AI_Loop = null;
		}
	}
	
	public static void activateCP() {
		if (CP_Loop == null) {
			exit03 = true;
			CP_Loop = new Thread() {
				@Override
				public void run() {
					ThreadProxy.CAP.onThreadStart();
					for (int prog = 0; exit01; prog++) {
						long rec = System.nanoTime();
						ThreadProxy.CAP.onStateUpdate(prog);
						try {
							TimeUnit.NANOSECONDS.sleep
							((long)(ThreadProxy.secPerFrame() * 1000000000l) - 
									(System.nanoTime() - rec));
							currentFPS = (int)((1000000000l) / (System.nanoTime() - rec));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};
			CP_Loop.start();
		}
	}
	
	public static void tarminateCP() {
		if (CP_Loop != null)
			exit03 = false;
	}
}
