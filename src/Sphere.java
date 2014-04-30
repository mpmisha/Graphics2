
public class Sphere extends Surface {
	private float radius;

	
	public Sphere(Material material, Point centerPoint, int mat_idx,
			float radius) {
		super(material, centerPoint, mat_idx);
		this.radius = radius;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "Sphere [radius=" + radius + "]";
	}

	@Override
	public Intersection findIntersection(Ray ray) {
		float t_ca,squred_d,squred_r,t_hc,t1,t2;
		Vector v,L,p1,p2;
		Intersection inter;
		Point P0;
		
		P0 = ray.getOrigin();
		v = ray.getDiraction();
		v.Normalize();
		
		L = new Vector(this.getCenterPoint()).vectorSubsract(new Vector(P0));
		
		t_ca = L.dotProdcut(v);
		if(t_ca<0) return null;
		
		squred_d = L.dotProdcut(L) - (t_ca*t_ca);
		squred_r=this.getRadius()*this.getRadius();
		if(squred_d>squred_r) return null;
		
		t_hc=(float)Math.sqrt(squred_r-squred_d);
		t1 = t_ca-t_hc;
		t2 = t_ca+t_hc;
		
		p1=new Vector(P0).vectorAdd(v.multiplyByScalar(t1));
		p2=new Vector(P0).vectorAdd(v.multiplyByScalar(t2));
		
		Vector temp_p1=new Vector(ray.getOrigin()).vectorSubsract(p1);
		Vector temp_p2=new Vector(ray.getOrigin()).vectorSubsract(p2);
		
		if (temp_p1.GetMagnitude()<=temp_p2.GetMagnitude()){ 
			inter = new Intersection(t1, new Point(p1), this);
		}else{
			inter = new Intersection(t2, new Point(p2), this);
		}
		
		return inter;
		
//		float b,c,squred_d,squred_r;
//		
//		float disc, solution;
//		float t1, t2,t_ca,t_hc, t, t_min;
//		
//		Vector distance = new Vector(ray.getOrigin(), this.getCenterPoint());
//		t_ca = distance.dotProdcut(ray.getDiraction());
//		
//		if(t_ca<0) return null;
//		
//		squred_d = distance.dotProdcut(distance)-t_ca*t_ca;
//		squred_r= this.getRadius()*this.getRadius();
//		if(squred_d>squred_r) return null;
//		
//		t_hc= Math.sqrt(squred_r-squred_d);	
//		t1 = t_ca - t_hc;
//		t2 = t_ca + t_hc;
//		
//		t_min = Math.min(t1, t2);
//		
//		Point intersection = new Point(ray.getOrigin());
//		intersection.mac(t_min, ray.getDiraction());
//		return new Intersection(t_min, intersection, this);
		
		
		
	}
	
	
}
