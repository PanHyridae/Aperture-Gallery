package com.marlonjones.projectevo;


public class ApertureUtility {
	public static void runOnUIThread(Runnable runnable) {
		runOnUIThread(runnable, 0);
	}

	public static void runOnUIThread(Runnable runnable, long delay) {
		if (delay == 0) {
			ApertureBasing.applicationHandler.post(runnable);
		} else {
			ApertureBasing.applicationHandler.postDelayed(runnable, delay);
		}
	}
}
