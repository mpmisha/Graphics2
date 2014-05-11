package RayTracing;
import java.util.ArrayList;

import javax.print.attribute.standard.Finishings;



public class Color {
	private final static float EPSILON = 0.0001f;
	private float R;
	private float G;
	private float B;
	
	public Color(float r, float g, float b) {
		R = r;
		G = g;
		B = b;
	}
	public Color(){
		this(0,0,0);
	}
	public Color(Vector v) {
		this.R = v.getX();
		this.G = v.getY();
		this.B = v.getZ();
	}
	public float getR() {
		return R;
	}
	public void setR(float r) {
		R = r;
	}
	public float getG() {
		return G;
	}
	public void setG(float g) {
		G = g;
	}
	public float getB() {
		return B;
	}
	public void setB(float b) {
		B = b;
	}
	@Override
	public String toString() {
		return "Color [" + R + "," + G + "," + B + "]";
	}
	
	public Color multiplyByScalar(float scalar){
		return (new Color(this.getR()*scalar,this.getG()*scalar,this.getB()*scalar));
	}	
	
	public Color ReturnColorBytes() {
		if(this.R > 1.0f) this.R = 1.0f;
		if(this.G > 1.0f) this.G = 1.0f;
		if(this.B > 1.0f) this.B = 1.0f;
		return new Color((float)Math.floor(this.R * 255), (float)Math.floor(this.G * 255), (float)Math.floor(this.B * 255));
	}
	
	public static float saturate(float n){
		if(n > 255.0f)
			n = 255.0f;
		else if (n < 0.0f)
			n = 0.0f;
		return n;
	}
	
	public static Color getColor(Intersection hit, ArrayList<Light> lightList,Ray ray,Camera camera,int iter){
		if(iter==0){
			return new Color();
		}
		Vector outputColor= new Vector();
		float transparency = hit.getSurface().getMaterial().getTransparency();
		for (Light l:lightList)
		{
			
			//get diffuse value		
			Color K_d = hit.surface.getMaterial().getDiffuseColor();
			Vector surf_Normal = hit.surface.getNormal(hit.pointOfIntersection);
			Vector light_Direction= new Vector(l.getPosition().Subsract(hit.getPointOfIntersection()));
			light_Direction = light_Direction.Normalize();
			Color i_p = l.getColor(); 
			float NL = surf_Normal.dotProdcut(light_Direction);
			if (NL<0) continue;
			Vector diffuse = new Vector(K_d.getR()*i_p.getR()*NL,K_d.getG()*i_p.getG()*NL,K_d.getB()*i_p.getB()*NL);
			
			//get specular value
			Vector v = ray.getDiraction().multiplyByScalar(-1.0f);
			v=v.Normalize();
			Vector r=light_Direction.getReflection(hit);
			float rv = r.dotProdcut(v);
			
			Vector specular;
			if (!(rv<0)){
				float rv_n =(float) Math.pow(rv, hit.getSurface().getMaterial().getPhongCoeffticient());
				Color k_s = hit.getSurface().getMaterial().getSpecularColor();
				specular = new Vector(k_s.getR()*i_p.getR()*l.getSpecularIntensity()*rv_n,k_s.getG()*l.getSpecularIntensity()*i_p.getG()*rv_n,k_s.getB()*l.getSpecularIntensity()*i_p.getB()*rv_n);
			}else{
				specular = new Vector();	
			}
			
			Vector diffuseAndSpecular = new Vector(specular.vectorAdd(diffuse));
			
			//sum color value according to formula
			outputColor= outputColor.vectorAdd(diffuseAndSpecular.multiplyByScalar(1-transparency));
		}
		
		
		/*-----transparency-----*/
		/*TODO:extract method*/
		
		if (transparency>0){
			//find intersection with further surfaces
			Intersection seconderyHit = RayTracer.findNearestIntersection(ray,hit);
			
			Color bgColor;
			if (seconderyHit!=null){
				//get Background objects color recursively
				bgColor= getColor(seconderyHit, lightList, ray, camera,iter-1);				
			}else{
				//if no intersection , that means we see the background 
				bgColor=camera.getBackgroundColor();
			}	
			outputColor= outputColor.vectorAdd((new Vector(bgColor)).multiplyByScalar(transparency));	
		}
		
		/*-----reflection-----*/
		/*TODO:extract method*/
		
		
		//construct reflection ray 
		Ray reflectionRay = new Ray(hit.pointOfIntersection,(ray.getDiraction().multiplyByScalar(-1.0f)).getReflection(hit));
		
		//find intersection with object
		Intersection reflectionHit = RayTracer.findNearestIntersection(reflectionRay);
		
		Color reflection;		
		
		if (reflectionHit!=null){
			//get reflection color recursively		
			reflection = getColor(reflectionHit, lightList, reflectionRay, camera,iter-1);
		}else{
			//if no intersection , that means we see a reflection of the background
			reflection = camera.getBackgroundColor();
		}
		
		//scale the reflection colors
		reflection = hit.getSurface().getMaterial().scaleReflection(reflection);
		
		outputColor= outputColor.vectorAdd((new Vector(reflection)));
	
		return new Color(outputColor);
	}
	
	
	
	
	
	
	
}
