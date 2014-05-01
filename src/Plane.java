
public class Plane extends Surface {
	private float offset;
	private Vector normal;

	public Plane(Material material, Vector normal, int mat_idx, int offset) {
		super(material, mat_idx);
		this.offset = offset;
		this.normal = normal;
		this.normal.Normalize();
	}

	public float getOffset() {
		return offset;
	}

	public void setOffset(float offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "Plane [offset=" + offset + "]";
	}
	
	
	@Override
	public Intersection findIntersection(Ray ray) { 
		Vector v = ray.getDiraction();
		v.Normalize();
		float f = this.normal.dotProdcut(new Vector(ray.getOrigin()));
		float s = this.normal.dotProdcut(v);
		float t = (this.offset - f) / s;
		if(t<=0)
			return null;
		else
			return new Intersection(t, new Point(v.multiplyByScalar(t)), this);
	}
//	@Override
//	public Intersection findIntersection(Ray ray) {
//		float N_P0,N_v;
//		Vector v;
//		Point P0;
//		
//		P0 = ray.getOrigin();
//		v = ray.getDiraction();
//		v.Normalize();
//		
//		N_P0 = this.normal.dotProdcut(new Vector(P0));
//		N_v  = this.normal.dotProdcut(v);
//		
//		float result =(getOffset() - N_P0)/N_v; 
//		return (result>0)? new Intersection(result, new Point(v.multiplyByScalar(result)), this) : null;
//	}
	@Override
	public Vector getNormal(Point pointOfIntersection) {
		return this.normal;
	}
	
}
