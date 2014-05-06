package RayTracing;

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
		return "Sphere[radius=" + radius + "]";
	}

	@Override
	public Intersection findIntersection(Ray ray) {
		
		Point P0 = ray.getOrigin();
		Vector v = ray.getDiraction().Normalize();
		Vector L = new Vector(this.getCenterPoint()).vectorSubsract(new Vector(P0));
		
		float t_ca = L.dotProdcut(v);
		if(t_ca<0) return null;
		
		float squred_d = L.dotProdcut(L) - (t_ca*t_ca);
		float squred_r=this.getRadius()*this.getRadius();
		if(squred_d>squred_r) return null;
		
		float t_hc=(float)Math.sqrt(squred_r-squred_d);
		float t1 = t_ca-t_hc;
		float t2 = t_ca+t_hc;
		
		Vector p1=new Vector(P0).vectorAdd(v.multiplyByScalar(t1));
		Vector p2=new Vector(P0).vectorAdd(v.multiplyByScalar(t2));
		
		Vector temp_p1=new Vector(ray.getOrigin()).vectorSubsract(p1);
		Vector temp_p2=new Vector(ray.getOrigin()).vectorSubsract(p2);
		
		Intersection inter;
		
		if (temp_p1.GetMagnitude()<=temp_p2.GetMagnitude()){ 
			inter = new Intersection(t1, new Point(p1), this);
		}else{
			inter = new Intersection(t2, new Point(p2), this);
		}
		
		return inter;
		
	}
	@Override
	public Vector getNormal(Point pointOfIntersection)
	{
				return pointOfIntersection.Subsract(this.getCenterPoint()).Normalize();
	}
	
}
