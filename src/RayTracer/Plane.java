package RayTracer;
public class Plane extends Surface {
	private float offset;
	private Vector normal;

	public Plane(Material material, Vector normal, int mat_idx, int offset) {
		super(material, mat_idx);
		this.offset = offset;
		this.normal = normal;
	}

	public float getOffset() {
		return offset;
	}

	public void setOffset(float offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "Plane[offset=" + offset + "]";
	}
	
	@Override
	public Intersection findIntersection(Ray ray) { 
		Vector v = ray.getDiraction().Normalize();
		float f = this.normal.dotProdcut(new Vector(ray.getOrigin()));
		float s = this.normal.dotProdcut(v);
		float t = (this.offset - f) / s;
		if(t>0)
		{
			Vector originV = new Vector(ray.getOrigin());
			Vector result = v.multiplyByScalar(t);
			result = originV.vectorAdd(result);
			return new Intersection(t, new Point(result), this);	
		}
		else return null;
	}
	
	@Override
	public Vector getNormal(Point pointOfIntersection) {
		return this.normal;
	}	
}
