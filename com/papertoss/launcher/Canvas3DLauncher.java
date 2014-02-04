package com.papertoss.launcher;

import com.papertoss.display.canvas.Canvas3D;
import com.papertoss.display.shapes.ShinyRockSphere;

public class Canvas3DLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Canvas3D canvas = new Canvas3D();
		canvas.start();
		waitMillis(5000);
		
		ShinyRockSphere sphere = new ShinyRockSphere(canvas.getAssetManager());
		canvas.addShape(sphere);
		
		waitMillis(3000);
		
		sphere.setRadius(4f);
		
		waitMillis(3000);
		
		sphere.setPosition(0, 3, -2);
	}
	
	public static void waitMillis(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
