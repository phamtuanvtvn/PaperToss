package com.papertoss.display.canvas;

import java.util.ArrayList;
import java.util.List;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.papertoss.display.shapes.UpdatableShape;

public class Canvas3D extends SimpleApplication {
	private List<UpdatableShape> shapeList;
	
	public Canvas3D(){
		this.setShowSettings(false);
		shapeList = new ArrayList<UpdatableShape>();
	}
	
	@Override
	public void simpleInitApp() {
		/** Must add a light to make the lit object visible! */
		DirectionalLight sun = new DirectionalLight();
		sun.setDirection(new Vector3f(1, 0, -2).normalizeLocal());
		sun.setColor(ColorRGBA.White);
		rootNode.addLight(sun);
		this.getCamera().setLocation(new Vector3f(0, 5, 20));
	}
	
	@Override	
	public void simpleUpdate(float tpf) {
		for(UpdatableShape shape : shapeList){
			shape.update();
		}
	}
	
	public void addShape(Spatial shape){
		if(shape instanceof UpdatableShape){
			shapeList.add(((UpdatableShape)shape));	
		}
		rootNode.attachChild(shape);
	}
	
	public void removeShape(Spatial shape){
		if(shape instanceof UpdatableShape){
			shapeList.remove(((UpdatableShape)shape));	
		}
		rootNode.detachChild(shape);
	}

}
