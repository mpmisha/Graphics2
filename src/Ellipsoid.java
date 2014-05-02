
public class Ellipsoid extends Surface{
	private Matrix rotationMatrix = new Matrix(3,3);

	public Ellipsoid(Material material, Point centerPoint,Matrix rotationMatrix, int mat_idx) {
		super(material, centerPoint, mat_idx);
		this.rotationMatrix = rotationMatrix;
	}

	public Matrix getRotationMatrix() {
		return rotationMatrix;
	}

	public void setRotationMatrix(Matrix rotationMatrix) {
		this.rotationMatrix = rotationMatrix;
	}

	//translate the whole world to a 3D world where the ellipsoid is a unit sphere
	//find an Intersection point, and translate the world back to the original coordinates
	public Intersection findIntersection(Ray ray) {
		//create transformed unit sphere
		Point centerPointTransformed = this.getCenterPoint().multiplyByMatrix(this.rotationMatrix);
		Sphere unitSphere = new Sphere(this.getMaterial(), centerPointTransformed, this.getMat_idx(), 1.0f);
		
		//create transformed ray
		Point originTransformed = ray.getOrigin().multiplyByMatrix(this.rotationMatrix);
		Vector directionTrasnformed = ray.getDiraction().multiplyByMatrix(this.rotationMatrix);
		//directionTrasnformed.Normalize(); 
		Ray rayTransformed = new Ray(originTransformed, directionTrasnformed);
		//find transformed intersection  point
		Intersection IntersectionTransformed = unitSphere.findIntersection(rayTransformed);
		
		if(IntersectionTransformed == null)
			return null;
		
		//transform the point of intersection back to normal world
		Point newPointOfIntersection = IntersectionTransformed.getPointOfIntersection().multiplyByMatrix(Matrix.Inverse(this.rotationMatrix));
		Vector newDistance = newPointOfIntersection.Subsract(ray.getOrigin());
		//float dist = newDistance.GetMagnitude() / ((ray.getDiraction().multiplyByMatrix(rotationMatrix)).GetMagnitude());
		return new Intersection(newDistance.GetMagnitude(), newPointOfIntersection, this);
	}

	@Override
	public Vector getNormal(Point pointOfIntersection)
	{
		//create transformed unit sphere
		Point centerPointTransformed = this.getCenterPoint().multiplyByMatrix(this.rotationMatrix);
		Sphere unitSphere = new Sphere(this.getMaterial(), centerPointTransformed, this.getMat_idx(), 1.0f);
		
		//get normal of unit sphere
		Point pointOfIntersectionTransformed = pointOfIntersection.multiplyByMatrix(rotationMatrix);
		Vector normTransformed = unitSphere.getNormal(pointOfIntersectionTransformed);
		//transform back to ellipsoid world
		
		Vector norm = normTransformed.multiplyByMatrix(Matrix.transpose(rotationMatrix));
		norm.Normalize();
		return norm;		
	}
	
	
}
