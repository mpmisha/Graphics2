
public class Ellipsoid extends Surface{
	private float[][] rotationMatrix = new float[3][3];

	public Ellipsoid(Material material, Point centerPoint,float[][] rotationMatrix, int mat_idx) {
		super(material, centerPoint, mat_idx);
		this.rotationMatrix = rotationMatrix;
	}

	public float[][] getRotationMatrix() {
		return rotationMatrix;
	}

	public void setRotationMatrix(float[][] rotationMatrix) {
		this.rotationMatrix = rotationMatrix;
	}

	@Override
	public Intersection findIntersection(Ray ray) {
		Sphere unitSphere = new Sphere(this.getMaterial(), this.getCenterPoint(), this.getMat_idx(), 1.0f);
		Point originTransformed = ray.getOrigin().multiplyByMatrix(this.rotationMatrix);
		Vector directionTrasnformed = ray.getDiraction().multiplyByMatrix(this.rotationMatrix);
		Ray rayTransformed = new Ray(originTransformed, directionTrasnformed);
		Intersection IntersectionTransformed = unitSphere.findIntersection(rayTransformed);
		if(IntersectionTransformed == null)
			return null;
		Point newPointOfIntersection = IntersectionTransformed.getPointOfIntersection().multiplyByMatrix(inverse(this.rotationMatrix));
		Vector newDistance = newPointOfIntersection.Subsract(ray.getOrigin());
		return new Intersection(newDistance.getLength(), newPointOfIntersection, this);
	}
	
	
	public static float[][] inverse(float[][] rotationMatrix) {
		//need to verify this shit;
		for (int i = 0; i < rotationMatrix.length; i++) {
			for (int j = 0; j < rotationMatrix[0].length; j++) {
				if(rotationMatrix[i][j] != 0)
					rotationMatrix[i][j] = 1 / rotationMatrix[i][j];
			}
		}
		return rotationMatrix;
	}

	@Override
	public Vector getNormal(Point pointOfIntersection)
	{
				Vector n =  pointOfIntersection.Subsract(this.getCenterPoint());
				n.Normalize();
				return n;
	}
	
	
}
