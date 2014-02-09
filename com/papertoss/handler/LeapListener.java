package com.papertoss.handler;

import com.jme3.scene.shape.Sphere;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Vector;
import com.papertoss.display.canvas.Canvas3D;
import com.papertoss.display.shapes.ShinyRockSphere;

public class LeapListener extends Listener {
	private static final float FACTOR_SCALE = 10f;
	
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
        		//sphere.setPosition(vec.getX()/FACTOR_SCALE, vec.getY()/FACTOR_SCALE, vec.getZ()/FACTOR_SCALE);
        	}
        }
        
        if (!frame.hands().isEmpty()) {
            // Get the first hand
            Hand hand = frame.hands().get(0);

            // Check if the hand has any fingers
            FingerList fingers = hand.fingers();
            if (!fingers.isEmpty()) {
                // Calculate the hand's average finger tip position
                Vector avgPos = Vector.zero();
                for (Finger finger : fingers) {
                    avgPos = avgPos.plus(finger.tipPosition());
                }
                avgPos = avgPos.divide(fingers.count());
                System.out.println("Hand has " + fingers.count()
                                 + " fingers, average finger tip position: " + avgPos);
            }

            // Get the hand's sphere radius and palm position
            System.out.println("Hand sphere radius: " + hand.sphereRadius()
                             + " mm, palm position: " + hand.palmPosition());
            
            float posX = constraintPosition(hand.palmPosition().getX()/FACTOR_SCALE);
            float posY = constraintPosition(hand.palmPosition().getY()/FACTOR_SCALE, 11);
            float posZ = constraintPosition(hand.palmPosition().getZ()/FACTOR_SCALE);
            sphere.setRadius(hand.sphereRadius());
            sphere.setPosition(posX, posY, posZ);
        }
    }
    
    public float constraintPosition(float pos){
    	if(pos > 10){
    		pos = 10;
    	}
    	if(pos < -10){
    		pos = -10;
    	}
    	return pos;
    }
    
    public float constraintPosition(float pos, float limit){
    	if(pos > limit){
    		pos = limit;
    	}
    	if(pos < -limit){
    		pos = -limit;
    	}
    	return pos;
    }
}
