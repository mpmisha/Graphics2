
public class Plane extends Surface {
	private float offset;

	public Plane(Material material, Point centerPoint, int mat_idx, int offset) {
		super(material, centerPoint, mat_idx);
		this.offset = offset;
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
		float N_P0,N_v;
		Vector v;
		Point P0;
		
		P0 = ray.getOrigin();
		v = ray.getDiraction();
		v.Normalize();
		
		N_P0 = new Vector(getCenterPoint()).dotProdcut(new Vector(P0));
		N_v  = new Vector(getCenterPoint()).dotProdcut(v);
		
		float result =(getOffset() - N_P0)/N_v; 
		return (result>0)? new Intersection(result, new Point(v.multiplyByScalar(result)), this) : null;
	}
	
	
}
