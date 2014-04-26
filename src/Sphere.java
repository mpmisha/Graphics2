
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
		double b;
		double c;
		double disc, solution;
		double t1, t2;
		
		Vector distance = new Vector(ray.getOrigin(), this.getCenterPoint());
		
		Vector distanceSquared = new Vector(distance);
		distanceSquared.multiplyByScalar(2);
		b = distanceSquared.dotProdcut(ray.getDiraction());
		c = distance.getLength() - Math.pow(radius, 2);

		disc = b * b - 4 * c;
	
		if (disc < 0) {
			return null;
		}
		
		if (b < 0) {
			solution = (-b - Math.sqrt(disc)) / 2;
		} else {
			solution = (-b + Math.sqrt(disc)) / 2;
		}
	
		t1 = solution;
		t2 = c / solution;

		if (t1 < t2) {
			if (t2 < 0) {
				return null;
			}

			if (t1 < 0) {
				Point intersection = new Point(ray.getOrigin());
				intersection.mac(t2, ray.getDiraction());
				return new Intersection(t2, intersection, this);
			}

			Point intersection = new Point(ray.getOrigin());
			intersection.mac(t1, ray.getDiraction());
			return new Intersection(t1, intersection, this);
			
		} else {
			if (t1 < 0) {
				return null;
			}

			if (t2 < 0) {
				Point intersection = new Point(ray.getOrigin());
				intersection.mac(t1, ray.getDiraction());
				return new Intersection(t1, intersection, this);
			}

			Point intersection = new Point(ray.getOrigin());
			intersection.mac(t2, ray.getDiraction());
			return new Intersection(t2, intersection, this);

		}
	}
	
	
}
