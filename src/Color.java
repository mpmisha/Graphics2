import java.util.ArrayList;


public class Color {
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
	
	public Color ReturnColorBytes() {
		if(this.R > 1.0f) this.R = 1.0f;
		if(this.G > 1.0f) this.G = 1.0f;
		if(this.B > 1.0f) this.B = 1.0f;
		return new Color((float)Math.floor(this.R * 255), (float)Math.floor(this.G * 255), (float)Math.floor(this.B * 255));
	}
	
	public static float saturate(float n){
		if(n > 1.0f)
			n = 1.0f;
		else if (n < 0.0f)
			n = 0.0f;
		return n;
	}
	
	public static Color getColor(Intersection hit, ArrayList<Light> lightList,Ray ray){
		Vector colorIllum= new Vector();
		for (Light l:lightList)
		{
			//get diffuse value		
			Color K_d = hit.surface.getMaterial().getDiffuseColor();
			Vector surf_Normal = hit.surface.getNormal(hit.pointOfIntersection);
			Vector light_Direction= new Vector(l.getPosition().Subsract(hit.getPointOfIntersection()));
			light_Direction.Normalize();
			Color i_p = l.getColor(); 
			
			float NL = surf_Normal.dotProdcut(light_Direction);
			if (NL<0) continue;
			
			colorIllum=colorIllum.vectorAdd(new Vector(K_d.getR()*i_p.getR()*NL,K_d.getG()*i_p.getG()*NL,K_d.getB()*i_p.getB()*NL));
			
			
			//get specular value
			Vector v = ray.getDiraction().multiplyByScalar(-1.0f);
			
			Vector r = surf_Normal.multiplyByScalar(((light_Direction.dotProdcut(surf_Normal))*2.0f)).vectorSubsract(light_Direction);
			
			float rv = r.dotProdcut(v);
			
			if (rv<0) continue;
			
			float rv_n =(float) Math.pow(rv, hit.getSurface().getMaterial().getPhongCoeffticient());
			Color k_s = hit.getSurface().getMaterial().getSpecularColor();
			colorIllum= colorIllum.vectorAdd(new Vector(k_s.getR()*i_p.getR()*l.getSpecularIntensity()*rv_n,k_s.getG()*l.getSpecularIntensity()*i_p.getG()*rv_n,k_s.getB()*l.getSpecularIntensity()*i_p.getB()*rv_n));
		}
		
		return new Color(colorIllum);
	}
	
	
	
	
}
