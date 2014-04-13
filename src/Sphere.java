
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
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
