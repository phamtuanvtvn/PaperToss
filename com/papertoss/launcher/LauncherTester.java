package com.papertoss.launcher;
import com.papertoss.display.SpherePainter;


public class LauncherTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		float initialRadius = 2f;
		SpherePainter painter = new SpherePainter(initialRadius);
		painter.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println("ERROR: " + e);
			return;
		}
		float finalRadius = 4f;
		int numSteps = 100;
		float currentRadius = initialRadius;
		float step = (finalRadius - initialRadius)/((float)numSteps);
		System.out.println("STEP: " + step);
		for(int i = 0; i < numSteps; i++){
			try {
				currentRadius += step;
				System.out.println("CURRENT_RADIUS: " + currentRadius);
				painter.refresh(currentRadius);
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println("ERROR: " + e);
				return;
			} catch (IllegalStateException e){
				System.out.println("ERROR 2: " + e);
				return;
			}
		}
		
		for(int i = 0; i < numSteps; i++){
			try {
				currentRadius -= step;
				System.out.println("CURRENT_RADIUS: " + currentRadius);
				painter.refresh(currentRadius);
				Thread.sleep(50);
			} catch (InterruptedException e) {
				System.out.println("ERROR: " + e);
				return;
			} catch (IllegalStateException e){
				System.out.println("ERROR 2: " + e);
				return;
			}
		}
	}

}
