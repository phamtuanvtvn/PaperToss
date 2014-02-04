package com.papertoss.launcher;

import java.io.IOException;

import com.leapmotion.leap.Controller;
import com.papertoss.display.canvas.Canvas3D;
import com.papertoss.display.shapes.ShinyRockSphere;
import com.papertoss.handler.LeapListener;
import com.papertoss.handler.SampleListener;


public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Create a sample listener and controller
		Canvas3D canvas = new Canvas3D();
		canvas.start();
		waitMillis(5000);
		ShinyRockSphere sphere = new ShinyRockSphere(canvas.getAssetManager());
		canvas.addShape(sphere);
		sphere.setRadius(4f);
		
		LeapListener listener = new LeapListener(canvas, sphere);
		Controller controller = new Controller();

		// Have the sample listener receive events from the controller
		controller.addListener(listener);

		// Keep this process running until Enter is pressed
		System.out.println("Press Enter to quit...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Remove the sample listener when done
		controller.removeListener(listener);

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
