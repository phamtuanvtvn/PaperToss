import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.jme3.util.TangentBinormalGenerator;

public class SpherePainter extends SimpleApplication {

	private Sphere sphereMesh;
	private Geometry sphereGeo;
	private Material sphereMat;
	private float radius = 2f;
	private float scale = 1;

	public SpherePainter() {
		this.setShowSettings(false);
	}
	
	public SpherePainter(float initialRadius) {
		this();
		radius = initialRadius;
	}

	@Override
	public void simpleInitApp() {

		/** A bumpy rock with a shiny light effect. */
		sphereMesh = new Sphere(32, 32, radius);
		sphereGeo = new Geometry("Shiny rock", sphereMesh);

		sphereMesh.setTextureMode(Sphere.TextureMode.Projected); // better
																	// quality
																	// on
																	// spheres
		TangentBinormalGenerator.generate(sphereMesh); // for lighting effect
		initSphereMat(); 
		sphereGeo.setMaterial(sphereMat);
		sphereGeo.setLocalTranslation(0, 2, -2); // Move it a bit
		sphereGeo.rotate(1.6f, 0, 0); // Rotate it a bit
		rootNode.attachChild(sphereGeo);

		/** Must add a light to make the lit object visible! */
		DirectionalLight sun = new DirectionalLight();
		sun.setDirection(new Vector3f(1, 0, -2).normalizeLocal());
		sun.setColor(ColorRGBA.White);
		rootNode.addLight(sun);
	}

	public void refresh(float radius) {
		if(radius < 1.0f){
			radius = 1.0f;
		}
		this.scale = radius/this.radius;
		this.radius = radius;
	}

	@Override	
	public void simpleUpdate(float tpf) {
		sphereGeo.scale(this.scale, this.scale, this.scale);
		this.scale = 1;
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

}
