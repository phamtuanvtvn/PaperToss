package com.papertoss.display.shapes;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.jme3.util.TangentBinormalGenerator;

public class ShinyRockSphere extends Geometry implements UpdatableShape {
	private AssetManager assetManager;
	private Sphere sphereMesh;
	private Material sphereMat;
	private float radius = 2f;
	private float scale = 1;
	private float posX = 0;
	private float posY = 2;
	private float posZ = -2;
	
	public ShinyRockSphere(AssetManager assetManager){
		this.assetManager = assetManager;
		sphereMesh = new Sphere(32, 32, radius);
		this.setName("Shiny rock");
		this.setMesh(sphereMesh);
		TangentBinormalGenerator.generate(sphereMesh);
		initSphereMat(); 
		this.setMaterial(sphereMat);
		this.setLocalTranslation(posX, posY, posZ); // Move it a bit
		this.rotate(1.6f, 0, 0); // Rotate it a bit
	}
	
	private void initSphereMat() {
		sphereMat = new Material(assetManager,
				"Common/MatDefs/Light/Lighting.j3md");
		sphereMat.setTexture("DiffuseMap",
				assetManager.loadTexture("Textures/Terrain/Pond/Pond.jpg"));
		sphereMat.setTexture("NormalMap", assetManager
				.loadTexture("Textures/Terrain/Pond/Pond_normal.png"));
		sphereMat.setBoolean("UseMaterialColors", true);
		sphereMat.setColor("Diffuse", ColorRGBA.White);
		sphereMat.setColor("Specular", ColorRGBA.White);
		sphereMat.setFloat("Shininess", 64f); // [0,128]
	}

	@Override
	public void update() {
		this.scale(this.scale, this.scale, this.scale);
		this.scale = 1;
		this.setLocalTranslation(this.posX, this.posY, this.posZ);
	}
	
	public void setRadius(float radius){
		if(radius < 1.0f){
			radius = 1.0f;
		}
		this.scale = radius/this.radius;
		this.radius = radius;
	}
	
	public void setPosition(float posX, float posY, float posZ){
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
	}
}
