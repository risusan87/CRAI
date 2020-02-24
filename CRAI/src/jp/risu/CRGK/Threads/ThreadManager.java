package jp.risu.CRGK.Threads;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {
	private static List<ThreadBase> thread = new ArrayList<ThreadBase>();
	private static List<String> name = new ArrayList<String>();
	
	public static void initThread(ThreadBase th, String thname) {
		thread.add(th);
		name.add(thname);
		
		thread.get(thread.size() - 1).start();
	}
	
	public static void terminateThread(String thname) {
		for (int i = 0; i < name.size(); i++)
			thread.get(i).stopThread();
	}
}
