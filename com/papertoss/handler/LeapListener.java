package com.papertoss.handler;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;
import com.papertoss.display.canvas.Canvas3D;
import com.papertoss.display.shapes.ShinyRockSphere;

public class LeapListener extends Listener {
	ShinyRockSphere sphere = null;
	Canvas3D canvas = null;
	public LeapListener(Canvas3D canvas, ShinyRockSphere sphere) {
		this.canvas = canvas;
		this.sphere = sphere;
	}

	public void onInit(Controller controller) {
        System.out.println("Initialized");
    }

    public void onConnect(Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
    }

    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }

    public void onFrame(Controller controller) {
    	 // Get the most recent frame and report some basic information
        Frame frame = controller.frame();
        if (frame.hands().count() == 1) {
        	if (frame.hands().get(0).fingers().count() == 1) {
        		Finger finger = frame.hand(0).finger(0);
        		Vector vec = finger.tipPosition();
        		sphere.setPosition(vec.getX(), vec.getY(), vec.getZ());
        	}
        }
    }
}
