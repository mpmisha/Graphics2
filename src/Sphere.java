
public class Sphere extends Surface {
	private double radius;

	
	public Sphere(Material material, Point centerPoint, int mat_idx,
			double radius) {
		super(material, centerPoint, mat_idx);
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public String toString() {
		return "Sphere [radius=" + radius + "]";
	}

	@Override
	public Intersection nearestIntersection(Ray ray) {
		double b,c,squred_d,squred_r;
		
		double disc, solution;
		double t1, t2,t_ca,t_hc, t, t_min;
		
		Vector distance = new Vector(ray.getOrigin(), this.getCenterPoint());
		t_ca = distance.dotProdcut(ray.getDiraction());
		
		if(t_ca<0) return null;
		
		squred_d = distance.dotProdcut(distance)-t_ca*t_ca;
		squred_r= this.getRadius()*this.getRadius();
		if(squred_d>squred_r) return null;
		
		t_hc= Math.sqrt(squred_r-squred_d);	
		t1 = t_ca - t_hc;
		t2 = t_ca + t_hc;
		
		t_min = Math.min(t1, t2);
		
		Point intersection = new Point(ray.getOrigin());
		intersection.mac(t_min, ray.getDiraction());
		return new Intersection(t_min, intersection, this);
		
		
		
	}
	
	
}
